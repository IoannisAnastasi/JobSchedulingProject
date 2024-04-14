/**
 * @author Christoforos Kontzias 1134670
 * @date   15/03/2024
 */
package hw2;
/**
 * Manages a collection of reservations, allowing operations to add, remove,
 * and retrieve reservations.
 */
public class Basket {
    private Reservation[] reservations;
    /**
     * Constructs an empty Basket.
     */
    public Basket() {
        this.reservations = new Reservation[0]; // Initialize with an empty array
    }
    /**
     * Provides a copy of the current reservations in the basket.
     * This method ensures that the returned array is a copy, preventing
     * external modifications to the original reservations array.
     * 
     * @return A copy of the reservations array.
     */
    public Reservation[] getProducts() {
        Reservation[] copy = new Reservation[reservations.length];//SHALLOW COPY
        for (int i = 0; i < reservations.length; i++) {
            copy[i] = reservations[i];
        }
        return copy;
    }
    /**
     * Adds a reservation to the basket.
     * 
     * @param reservation The reservation to add to the basket.
     * @return The new total number of reservations in the basket.
     */
    public int add(Reservation reservation) {
        Reservation[] newReservations = new Reservation[reservations.length + 1];
        for (int i = 0; i < reservations.length; i++) {
            newReservations[i] = reservations[i];
        }
        newReservations[reservations.length] = reservation; //add the new reservation at the end
        reservations = newReservations;
        return reservations.length;
    }
    /**
     * Attempts to remove the first occurrence of a specified reservation from the basket.
     * 
     * @param reservation The reservation to remove.
     * @return true if the reservation was successfully removed, false if it was not found.
     */
    public boolean remove(Reservation reservation) {
        for (int i = 0; i < reservations.length; i++) {
            if (reservations[i].equals(reservation)) {
                for (int j = i; j < reservations.length - 1; j++) {//remove the reservation by shifting subsequent elements left
                    reservations[j] = reservations[j + 1];
                }
                Reservation[] newReservations = new Reservation[reservations.length - 1];//resize the reservations array
                for (int k = 0; k < newReservations.length; k++) {
                    newReservations[k] = reservations[k];
                }
                reservations = newReservations;
                return true; // reservation was found and removed
            }
        }
        return false; // reservation not found
    }
    /**
     * Clears all reservations from the basket.
     */
    public void clear() {
        reservations = new Reservation[0]; // Reset the array to empty
    }
    /**
     * Returns the number of reservations in the basket.
     * 
     * @return The total number of reservations.
     */
    public int getNumOfReservations() {
        return reservations.length;
    }
    /**
     * Calculates and returns the total cost of all reservations in the basket.
     * 
     * @return The total cost of the reservations in cents.
     */
    public int getTotalCost() {
        int totalCost = 0;
        for (Reservation reservation : reservations) {
            totalCost += reservation.getCost();
        }
        return totalCost;
    }
}
