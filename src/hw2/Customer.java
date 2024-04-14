/**
 * @author Christoforos Kontzias 1134670
 * @date   15/03/2024
 */
package hw2;
/**
 * Represents a customer in a reservation system. A customer has a name, a balance in cents,
 * and a basket of reservations.
 */
public class Customer {
	private String name;
	private int balance; // in cents
	private Basket basket;
	  /**
     * Constructs a Customer instance with a given name and balance.
     *
     * @param name The name of the customer.
     * @param balance The initial balance of the customer, in cents.
     */
	public Customer(String name, int balance) {
		this.name = name;
		this.balance = balance;
		this.basket = new Basket(); // Initialize with an empty Basket
	}

	  /**
     * Returns the name of the customer.
     *
     * @return The customer's name.
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the current balance of the customer, in cents.
     *
     * @return The customer's balance.
     */
    public int getBalance() {
        return balance;
    }

    /**
     * Returns the customer's basket of reservations.
     *
     * @return The customer's basket.
     */
    public Basket getBasket() {
        return basket;
    }
    /**
     * Adds funds to the customer's balance.
     *
     * @param amount The amount to add to the balance, in cents.
     * @return The new balance after adding funds.
     * @throws IllegalArgumentException if the amount is negative.
     */
	public int addFunds(int amount) {
		if (amount < 0) {
			throw new IllegalArgumentException("Amount cannot be negative.");
		}
		balance += amount;
		return balance;
	
	}
	  /**
     * Adds a reservation to the customer's basket if the reservation name matches the customer's name.
     *
     * @param reservation The reservation to add.
     * @return The total number of reservations in the basket after adding.
     * @throws IllegalArgumentException if the reservation name does not match the customer's name.
     */
	public int addToBasket(Reservation reservation) {
		if (!reservation.reservationName().equals(this.name)) {
			throw new IllegalArgumentException("Reservation name does not match customer name.");
		}
		basket.add(reservation);
		
		return basket.getNumOfReservations();
	}
	  /**
     * Adds a hotel reservation to the customer's basket, with or without breakfast, based on parameters.
     *
     * @param hotel The hotel for the reservation.
     * @param roomType The type of room to reserve.
     * @param numOfNights The number of nights for the reservation.
     * @param breakfast Whether breakfast is included.
     * @return The total number of reservations in the basket after adding.
     */
	public int addToBasket(Hotel hotel, String roomType, int numOfNights, boolean breakfast) {
		Reservation reservation;
		if (breakfast)
		{
			reservation = new BnBReservation(name, hotel, roomType, numOfNights);
		} else {
			
			reservation = new HotelReservation(name, hotel, roomType, numOfNights);
		 
		}
		basket.add(reservation);
		return basket.getNumOfReservations();
	  }
	   /**
     * Adds a flight reservation to the customer's basket.
     *
     * @param departure The departure airport.
     * @param arrival The arrival airport.
     * @return The total number of reservations in the basket after adding.
     */
	public int addToBasket(Airport departure, Airport arrival) {
		FlightReservation flightReservation = new FlightReservation(name, departure, arrival);
		basket.add(flightReservation);
		
		return basket.getNumOfReservations();
	}
	 /**
     * Removes a reservation from the customer's basket.
     *
     * @param reservation The reservation to remove.
     * @return true if the reservation was successfully removed; false otherwise.
     */
	public boolean removeFromBasket(Reservation reservation) {
		return basket.remove(reservation);
	}
    /**
     * Checks out the customer's basket. Deducts the total cost of reservations from the customer's balance
     * and clears the basket.
     *
     * @return The remaining balance after checkout.
     * @throws IllegalStateException if the customer's balance is insufficient to cover the total cost.
     */
	public int checkOut() {
		if (balance < basket.getTotalCost()) {
			throw new IllegalStateException("Insufficient funds to complete the purchase.");
		}
		balance -= basket.getTotalCost();
		int remainingBalance = balance;
		basket.clear(); // empty the basket after checkout
		
		return remainingBalance;
	}

}
