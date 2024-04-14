/**
 * @author Christoforos Kontzias 1134670
 * @date   15/03/2024
 */


package hw2;
/**
	 *represents a room with a specific type price , and availability status
	 */
public class Room {
	private String roomType;
	private int roomPrice;
	private boolean roomAvailability;
	
	 /**
     * Constructs a Room instance with specified type
     * Sets the room price and availability based on he room type
     * 
     * @param type The type of the room
     * @throws IllegalArgumentException If the specified room type is not valid
     */
	public Room(String type) {
		if(type.equals("double"))
		{
			this.roomPrice = 9000; // in cents
			this.roomAvailability = true;
		}
		else if(type.equals( "queen")) 
		{
			this.roomPrice = 11000; // in cents
			this.roomAvailability = true;

		}
		else if(type.equals("king"))
		{
			this.roomPrice = 15000; // in cents
			this.roomAvailability = true;
		}
		else throw new IllegalArgumentException("Invalid room type: " + type + ". Valid types are: double, queen, king.");

	}
	 /**
     * copy constructor
     * 
     * @param otherRoom The Room instance to copy from.
     */
	public Room(Room otherRoom) {  //the copy constructor
		this.roomType = otherRoom.roomType;
		this.roomPrice = otherRoom.roomPrice;
		this.roomAvailability = otherRoom.roomAvailability;
	}
	 /**
     * Gets the type of the room.
     * 
     * @return The room type.
     */
    public String getType() {
        return this.roomType;
    }

    /**
     * Gets the price of the room in cents.
     * 
     * @return The room price in cents.
     */
    public int getPrice() {
        return this.roomPrice;
    }

    /**
     * Changes the availability status of the room.
     * If the room was available, it becomes unavailable, and vice versa.
     */
    public void changeAvailability() {
        this.roomAvailability = !this.roomAvailability;
    }

    /**
     * Finds the first available room of a specified type within an array of rooms.
     * 
     * @param rooms An array of Room objects to search through.
     * @param type The type of room to find.
     * @return The first available room of the specified type, or null if none found.
     */

	public static Room findAvailableRoom(Room[] rooms, String type) {
		for(int i=0; i<rooms.length;i++) {
			if(rooms[i].getType() == type && rooms[i].roomAvailability == true)
				return rooms[i];
		}
		return null;
	}
	 /**
     * Makes the first unavailable room of a specified type available again
     * 
     * @param rooms An array of Room objects to search through
     * @param type The type of room to make available
     * @return true if a room was made available and false if no matching unavailable room was found
     */
	public static boolean makeRoomAvailable(Room[] rooms, String type) {
		for(int i=0; i<rooms.length;i++) {
			if(rooms[i].roomAvailability == false && rooms[i].getType() == type) {
				rooms[i].changeAvailability();
				return true;
			} 
		}
		return false;
	}

}
