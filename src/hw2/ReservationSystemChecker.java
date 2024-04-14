package hw2;

public class ReservationSystemChecker {

    public static void main(String[] args) {
        testHotelAndRoomReservations();
        testFlightReservations();
        testCustomerAndBasket();
        System.out.println("All tests passed successfully.");
    }

    private static void testHotelAndRoomReservations() {
        System.out.println("Testing Hotel and Room Reservations...");
        Hotel hotel = new Hotel("Grand Hotel", new Room[]{new Room("double"), new Room("king")});
        Customer customer = new Customer("Alice", 20000); 
        
        int nights = 2;
        boolean breakfast = true;
        customer.addToBasket(hotel, "double", nights, breakfast);
        assert customer.getBasket().getNumOfReservations() == 1 : "Failed to add hotel reservation to basket.";


        int expectedCost = (hotel.reserveRoom("double") * nights) + (1000 * nights);
        assert customer.getBasket().getTotalCost() == expectedCost : "BnBReservation cost calculation is incorrect.";

        customer.getBasket().clear();
        assert customer.getBasket().getNumOfReservations() == 0 : "Basket clear method failed.";
        
        System.out.println("Hotel and Room Reservations test passed.");
    }

    private static void testFlightReservations() {
        System.out.println("Testing Flight Reservations...");
        Airport departure = new Airport(100, 100, 1000); 
        Airport arrival = new Airport(200, 200, 1500);
        Customer customer = new Customer("Bob", 30000); 
        
        customer.addToBasket(departure, arrival);
        assert customer.getBasket().getNumOfReservations() == 1 : "Failed to add flight reservation to basket.";

        customer.getBasket().clear();
        assert customer.getBasket().getNumOfReservations() == 0 : "Basket clear method failed after flight reservation.";
        
        System.out.println("Flight Reservations test passed.");
    }

    private static void testCustomerAndBasket() {
        System.out.println("Testing Customer and Basket functionalities...");
        Customer customer = new Customer("Charlie", 50000);

      
        customer.addFunds(10000); 
        assert customer.getBalance() == 60000 : "Add funds to customer failed.";
        Hotel hotel = new Hotel("Luxury Stay", new Room[]{new Room("penthouse")});
        customer.addToBasket(hotel, "penthouse", 1, false);
        int balanceBeforeCheckout = customer.getBalance();
        int costOfStay = customer.getBasket().getTotalCost();
        
        customer.checkOut();
        assert customer.getBalance() == balanceBeforeCheckout - costOfStay : "Checkout did not correctly deduct cost from balance.";
        assert customer.getBasket().getNumOfReservations() == 0 : "Basket was not cleared after checkout.";

        System.out.println("Customer and Basket functionalities test passed.");
    }
}
