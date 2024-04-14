/**
 * @author Christoforos Kontzias 1134670
 * @date   15/03/2024
 */
package hw2;
/**
 * Represents a flight reservation in the reservation system. This class extends the
 * abstract Reservation class and includes specific attributes for flight reservations,
 * such as departure and arrival airports.
 */
public class FlightReservation extends Reservation{
	private Airport departure;
	private Airport arrival;
	   /**
     * Constructs a FlightReservation with the specified name, departure airport, and arrival airport.
     * Validates that the departure and arrival airports are not the same.
     *
     * @param name The name associated with the flight reservation.
     * @param departure The departure airport.
     * @param arrival The arrival airport.
     * @throws IllegalArgumentException if the departure and arrival airports are the same.
     */
	public FlightReservation(String name, Airport departure, Airport arrival) {
		super(name); 
		if (departure.equals(arrival)) {
			throw new IllegalArgumentException("Departure and arrival airports cannot be the same.");
		}
		this.departure = departure;
		this.arrival = arrival;
	}
	 /**
     * Calculates the cost of the flight based on distance, fuel costs, and fees from both departure and
     * arrival airports, along with any additional fixed costs.
     *
     * @return The total cost of the flight in cents.
     */
	public int getCost() {
	 
		final double fuelCostPerGallon = 124;
		final double milesPerGallon = 167.52;
		final int additionalCosts = 5375; 
                     
		
		int distance = Airport.getDistance(departure, arrival);

		
		double gallonsNeeded = distance / milesPerGallon;
		double fuelCost = gallonsNeeded * fuelCostPerGallon;

		
		int totalCost = (int)Math.ceil(fuelCost * 100) + departure.getFees() + arrival.getFees() + additionalCosts;

		return totalCost;
	}
	  /**
     * Compares this FlightReservation with another object for equality. Flight reservations are considered
     * equal if they have the same reservation name, departure airport, and arrival airport.
     *
     * @param obj The object to compare with.
     * @return true if the given object represents a FlightReservation equivalent to this flight reservation,
     *         false otherwise.
     */
	public boolean equals(Object obj) {  //overrides the object class
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;

		FlightReservation other = (FlightReservation) obj;
		return this.reservationName().equals(other.reservationName()) &&
				this.departure.equals(other.departure) &&
				this.arrival.equals(other.arrival);
	}
}


