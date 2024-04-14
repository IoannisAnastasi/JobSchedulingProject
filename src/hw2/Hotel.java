/**
 * @author Christoforos Kontzias 1134670
 * @date   15/03/2024
 */
package hw2;
/**
 * Represents a hotel with a name and a collection of rooms
 * This class allows for reserving and canceling rooms based on room type
 */
public class Hotel {
	private String hotelName;
	private Room[] rooms;

	  /**
     * Constructs a Hotel instance with a specified name and an array of rooms.
     * Performs a deep copy of the rooms array to ensure that the Hotel instance
     * has its own separate copies of the Room objects.
     *
     * @param name The name of the hotel.
     * @param rooms An array of Room objects representing the rooms available in the hotel.
     */
	public Hotel(String name, Room[] rooms) {
		this.hotelName = name;
		this.rooms = new Room[rooms.length];
		for (int i = 0; i < rooms.length; i++) {
			this.rooms[i] = new Room(rooms[i]);   // deep copy
		}
	}

	/**
     * Attempts to reserve a room of a specified type within the hotel.
     * If an available room of the specified type is found, it is reserved (marked as unavailable),
     * and the price of the room is returned.
     *
     * @param type The type of room to reserve.
     * @return The price of the reserved room.
     * @throws IllegalArgumentException If no available room of the specified type is found.
     */
	public int reserveRoom(String type)  {
		Room currentRoom = Room.findAvailableRoom(rooms ,type);
		if(currentRoom == null)
			throw new IllegalArgumentException("An available room of the determined type was not found");
		else {
			currentRoom.changeAvailability();
			return (currentRoom.getPrice());
		} 
	}
	 /**
     * Attempts to make a room of a specified type available again (cancels the reservation)
     * This method searches for the first room of the specified type that is currently reserved
     * and makes it available again
     *
     * @param type The type of room to cancel the reservation for
     * @return true if a room was successfully made available amd false if not
     */
	public boolean cancelRoom(String type) {

		return Room.makeRoomAvailable(rooms, type);

	}
}