package hw4;

import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class OrderGenerator {

	public static void main(String[] args) {
		
		if(args.length!=1)
		{
			System.err.println("Usage: java OrderGenerator source-file");
			System.exit(0);
		}
		List<Order> orders = new ArrayList<>();
		try { 
			  
            // attach a file to FileWriter 
            FileWriter fw  = new FileWriter("Orders.txt");
            fw.write(args[0]+"\n");
            for(int i=0; i<Integer.parseInt(args[0]); i++)
            {
        		Generator order=new Generator();
        		order.generateEachPortion();
        		order.generateFries();
        		order.generateTimeOfOrder();
        		order.generateRequestedTime();
        		orders.add(i, order);
                fw.write(orders.get(i)+"\n");


            }
            // close the file 
            fw.close(); 
        } 
        catch (Exception e) { 
            System.err.println("Error. "+e.getMessage()); 
        } 
     
	}

}
