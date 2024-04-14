/**
 * @author Christoforos Kontzias 1134670
 * @date   15/03/2024
 */
package hw2;
/**
 * Represents a bed and breakfast reservation within the hotel reservation system.
 * This class extends the {@link HotelReservation} class, adding the cost of breakfast
 * to the total reservation cost.
 */
public class BnBReservation extends HotelReservation {
	  /**
     * Constructs a BnBReservation with the specified details.
     * Delegates the construction to the {@link HotelReservation} superclass.
     *
     * @param name The name on the reservation.
     * @param hotel The hotel associated with the reservation.
     * @param roomType The type of room reserved (e.g., "double", "queen", "king").
     * @param numOfNights The number of nights for the reservation.
     */
	public BnBReservation(String name, Hotel hotel, String roomType, int numOfNights) {
		super(name, hotel, roomType, numOfNights);
	}

	  /**
     * Calculates the total cost of the bed and breakfast reservation.
     * Includes the cost of the hotel stay from {@link HotelReservation#getCost()}
     * and adds the cost of breakfast for each night stayed.
     *
     * @return The total cost of the reservation, including breakfast, in cents.
     */
	public int getCost() {
		int breakfastCostPerNight = 1000;
		int totalBreakfastCost = breakfastCostPerNight * getNumOfNights();
		return super.getCost() + totalBreakfastCost;
	}
}
