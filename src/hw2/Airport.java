/**
 * @author Christoforos Kontzias 1134670
 * @date   15/03/2024
 */


package hw2;
/**
 * represents an airport with specific coordinates and associated fees
 * The airports location is defined by x and y coordinates on a 2D plane
 * and it includes fees associated with using the airport
 */
public class Airport {
	private int x;
	private int y;
	private int fees;
	 /**
     * constructs an Airport instance with specified coordinates and fees
     *
     * @param x The x-coordinate of the airports location
     * @param y The y-coordinate of the airports location
     * @param fees The fees associated with the airport
     */
	public Airport(int x, int y, int fees) {

		this.x = x;
		this.y = y;
		this.fees = fees;

	}
	 /**
     * retrieves the fees associated with the airport
     *
     * @return The fees of the airport.
     */
	public int getFees() {

		return fees;
	}
	 /**
     * calculates the distance between two airports. The distance
     * is determined based on the airports coordinates on a 2D plane
     * and is rounded up to the nearest whole number
     *
     * @param a1 The first airport
     * @param a2 The second airport
     * @return The distance between the two airports and its  rounded up to the nearest integer
     */
	public static int getDistance(Airport a1, Airport a2) {
		double distance = Math.sqrt(Math.pow(a1.x - a2.x, 2) + Math.pow(a1.y - a2.y, 2));
		return (int) Math.ceil(distance);
	}



}

