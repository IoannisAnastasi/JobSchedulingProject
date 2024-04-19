package hw4;
/**
 * @author Christoforos Kontzias 1134670
 */
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Handles the choice of algorithm and file handling
 */
public class OrderDelivery {
    public static void main(String[] args) {
        if (args.length != 8) {
            System.err.println("Usage: java OrderDelivery <grill length> <charcoal time> <number of fryers> <capacity per fryer> <skewer x space> <skewer y space> <pita z space> <algorithm number>");
            System.exit(1);
        }

        int grillLength = Integer.parseInt(args[0]);
        int charcoalTime = Integer.parseInt(args[1]);
        int numberOfFryers = Integer.parseInt(args[2]);
        int capacityPerFryer = Integer.parseInt(args[3]);
        int skewerXSpace = Integer.parseInt(args[4]);
        int skewerYSpace = Integer.parseInt(args[5]);
        int pitaZSpace = Integer.parseInt(args[6]);
        int algorithmNumber = Integer.parseInt(args[7]);

        Grill grill = new Grill(grillLength, skewerXSpace, skewerYSpace, pitaZSpace,charcoalTime);
        Fryer fryer = new Fryer(numberOfFryers * capacityPerFryer);
        Scheduler scheduler;

        switch (algorithmNumber) {
            case 1:
                scheduler = new FCFSScheduler();
                break;
            case 2:
                scheduler = new SJFScheduler();
                break;
            case 3:
                scheduler = new WeightedScheduler();
                break;
            default:
                System.err.println("Invalid scheduling algorithm number");
                return;
        }

        List<Order> orders = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File("Orders.txt"))) {
            scanner.nextInt();//skip the number of orders at the top
            while (scanner.hasNextInt()) {
                int num = scanner.nextInt();
                int tOrder = scanner.nextInt();
                int tDelReq = scanner.nextInt();
                int npp = scanner.nextInt();
                int npc = scanner.nextInt();
                int nps = scanner.nextInt();
                int npm = scanner.nextInt();
                int fries = scanner.nextInt();
                orders.add(new Order(num, tOrder, tDelReq, npp, npc, nps, npm, fries));
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + e.getMessage());
        }

        scheduler.schedule(orders, grill, fryer);
    }



    public static void writeResults(List<Order> orders, int[] endTimes, String filename) {
        try (FileWriter writer = new FileWriter(filename)) {
            int totalDeviation = 0;
            int satisfiedCount = 0;

          
            for (int i = 0; i < orders.size(); i++) {
                Order order = orders.get(i);
                int endTime = endTimes[i];
                int deviation = endTime - order.getTdelReq();
                totalDeviation += Math.abs(deviation);
                if (endTime <= order.getTdelReq()) {
                    satisfiedCount++; 
                }
            }

           
            double averageDeviation = orders.size() > 0 ? (double) totalDeviation / orders.size() : 0;

           
            writer.write(String.format("%d %.2f %d\n", orders.size(), averageDeviation, satisfiedCount));

  
            for (int i = 0; i < orders.size(); i++) {
                Order order = orders.get(i);
                int endTime = endTimes[i];
                int deviation = endTime - order.getTdelReq();
                int totalPitas = order.getNpp() + order.getNpc() + order.getNps() + order.getNpm();
                writer.write(String.format("%d %d %d %d %d %d\n",
                              order.getNum(), order.getTorder(), endTime,
                              deviation, totalPitas, order.getFries()));
            }
        } catch (IOException e) {
            System.err.println("Unable to write to file: " + e.getMessage());
        }
    }


}
