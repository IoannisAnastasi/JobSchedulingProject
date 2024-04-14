package hw3.squarelotron;

import java.util.Scanner;
/**
 * the main class for running the game. This class provides a user interface
 * for interacting with Squarelotron objects allowing users to create Squarelotrons of
 * specified sizes and perform various flips on them and also view the results.
 * 
 * @author Christoforos Kontzias
 * @date 02/04/2024
 */
public class SquarelotronTest {
	
    private static final Scanner scanner = new Scanner(System.in);
    /**
     *Entry point for the Squarelotron game. This method handles user input to
     * create Squarelotrons, perform rotations and repeat the process or exit based
     * on user decisions.
     *
     * @param args
     */
    public static void main(String[] args) {
        boolean continuePlaying = true;

        while (continuePlaying) {
            System.out.println("Choose the size of the Squarelotron (4 for 4x4, 5 for 5x5): ");
            int size = scanner.nextInt();
            while(size!=4 && size!=5) {
            	System.out.println("Choose a valid value: ");
            	size = scanner.nextInt();
            }
            int[] configuration = generateConfiguration(size);

            Squarelotron squarelotron = SquarelotronFactoryUtil.makeSquarelotron(configuration);// giafto ylopiithike me factories
            System.out.println("Initial Squarelotron:\n" + squarelotron);

            performRotation(squarelotron);

            continuePlaying = getUserDecision();
            	       
        }

        scanner.close();
    }
    /**
     * generates a sequential configuration array for initializing a Squarelotron.
     * the numbers start from 1 and increase sequentially filling the Squarelotron
     * row by row.
     *
     * @param size The dimension of the Squarelotron
     * @return An array representing the initial configuration of the Squarelotron.
     */
    private static int[] generateConfiguration(int size) {
        int[] configuration = new int[size * size];
        for (int i = 0; i < configuration.length; i++) {
            configuration[i] = i + 1;
        }
        return configuration;
    }
    /**
     * prompts the user to decide whether to start over with a new Squarelotron or exit the game.
     * repeats the prompt until a valid response ('yes' or 'no') is received.
     *
     * @return true if the user decides to start over and false to exit the game.
     */
    private static boolean getUserDecision() {
        String input;
        while (true) {
            System.out.println("Do you want to start over with a new Squarelotron? (yes/no): ");
            input = scanner.next().trim().toLowerCase();
            if (input.equals("yes")) {
                return true;
            } else if (input.equals("no")) {
            	System.out.println("Goodbye!");
                return false;
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }
    /**
     * performs a rotation on the given Squarelotron based on user input. The user
     * specifies the type of rotation and the ring or side to be affected.
     * 
     *
     * @param squarelotron The Squarelotron object to perform the rotation on.
     */
    private static void performRotation(Squarelotron squarelotron) {
    	boolean validInput = false;
        String rotationType = "";
        scanner.nextLine();
        while (!validInput) {
            System.out.println("Enter the type of rotation (upsideDownFlip, leftRightFlip, inverseDiagonalFlip, mainDiagonalFlip, sideFlip): ");
            rotationType = scanner.nextLine().trim();
            
            if (rotationType.equals("upsideDownFlip") || rotationType.equals("leftRightFlip") ||
                rotationType.equals("inverseDiagonalFlip") || rotationType.equals("mainDiagonalFlip") ||
                rotationType.equals("sideFlip")) {
                validInput = true; 
            } else {
                System.out.println("Invalid rotation type. Please enter a valid name.");
            }
        }
       
        System.out.println("Specify the ring (outer/inner) or side (left/right/top/bottom) for sideFlip: ");
        String ringOrSide = "";
        ringOrSide = scanner.next();
          while(!ringOrSide.equals("inner") && !ringOrSide.equals("outer")) {
        	  System.out.println("Wrong ring type! try again: ");
              ringOrSide = scanner.next();

          }
   
        Squarelotron rotatedSquarelotron;
        switch (rotationType) {
            case "upsideDownFlip":
                rotatedSquarelotron = squarelotron.upsideDownFlip(ringOrSide);
                break;
            case "leftRightFlip":
                rotatedSquarelotron = squarelotron.leftRightFlip(ringOrSide);
                break;
            case "inverseDiagonalFlip":
                rotatedSquarelotron = squarelotron.inverseDiagonalFlip(ringOrSide);
                break;
            case "mainDiagonalFlip":
                rotatedSquarelotron = squarelotron.mainDiagonalFlip(ringOrSide);
                break;
            case "sideFlip":
                rotatedSquarelotron = squarelotron.sideFlip(ringOrSide);
                break;
            default:
                System.out.println("");
                return;
        }

        System.out.println("Squarelotron after " + rotationType + ":\n" + rotatedSquarelotron);
    }
}