/**
 * @author Christoforos Kontzias 1134670
 * @date   15/03/2024
 */
package hw2;

/**
 * This class serves as a foundation for various types of reservations
 * by providing a common structure and functionality.
 */
public abstract class Reservation {
	private String reservationName;
	  /**
     * Constructs a Reservation instance with the specified name.
     *
     * @param name The name associated with the reservation.
     */
	public Reservation(String name) {
		reservationName = name;
	}
	  /**
     * Returns the name associated with the reservation.
     *
     * @return The reservation name.
     */
	public final String reservationName() {
		return this.reservationName;
	}
	  /**
     * Calculates and returns the cost of the reservation
     * The implementation of this method is different to each subclass
     *
     * @return The cost of the reservation in cents
     */
	public abstract int  getCost();

	 /**
     * Compares this reservation with the specified Object for equality.
     * The implementation of this method is deferred to subclasses to define
     * equality based on the subclass-specific fields
     *
     * @param obj The object to compare with.
     * @return true if the specified object is equal to this reservation.
     */
	public abstract boolean equals(Object obj); //it overrides the object class

}