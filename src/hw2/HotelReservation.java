package hw2;

/**
 * Represents a hotel reservation within the reservation system.
 * This class stores details about a hotel stay, including the hotel, room type,
 * number of nights, and the price per night.
 */
public  class HotelReservation extends Reservation {
	private Hotel hotel;
	private String roomType;
	private int numOfNights;
	private int pricePerNight; //price for one night in cents
	 /**
     * Constructs a HotelReservation with the specified details. Automatically reserves
     * a room of the specified type in the hotel, initializing the price per night based on the reservation.
     *
     * @param name The name on the reservation.
     * @param hotel The hotel where the reservation is made.
     * @param roomType The type of room reserved.
     * @param numOfNights The number of nights for the stay.
     */
	public HotelReservation(String name, Hotel hotel, String roomType, int numOfNights) {
		super(name);
		this.hotel = hotel;
		this.roomType = roomType;
		this.numOfNights = numOfNights;

	
		this.pricePerNight = hotel.reserveRoom(roomType);
	}
	/**
     * Gets the number of nights for the reservation.
     *
     * @return The number of nights reserved.
     */
	public int getNumOfNights() {
		return this.numOfNights;
	} 

	 /**
     * Calculates the total cost of the hotel stay by multiplying the price per night
     * by the number of nights reserved.
     *
     * @return The total cost of the stay, in cents.
     */
	public int getCost() {
		return this.pricePerNight * this.numOfNights;
	}

	  /**
     * Compares this HotelReservation with another object for equality. Hotel reservations are
     * considered equal if they have the same reservation name, hotel, room type, number of nights,
     * and total cost.
     *
     * @param obj The object to compare with.
     * @return true if the given object represents a HotelReservation equivalent to this one, false otherwise.
     */        
	public boolean equals(Object obj) {
		if (this == obj) return true;
		if (obj == null || getClass() != obj.getClass()) return false;

		HotelReservation other = (HotelReservation) obj;
		return this.reservationName().equals(other.reservationName()) &&
				this.hotel.equals(other.hotel) &&
				this.roomType.equals(other.roomType) &&
				this.numOfNights == other.numOfNights &&
				this.getCost() == other.getCost();
     	}
     }