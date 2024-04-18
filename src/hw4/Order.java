package hw4;
/**
 * This class represents an order in a restaurant.
 * An order includes details such as the time it was received, the time at which the customer wishes to receive it,
 * and the quantities of various food items in the order.
 * 
 * @author Ioannis Anastasi ID: 1131712
 * @since 14/04/2024
 */
public class Order {
	private static int count=1;
	private int num;  		//serial number of the order
	private int tOrder; 	//time of receiving the order
	private int tDelReq; 	//time at which the customer wishes to receive the order
	private int npp;   		//number of pork skewers pittas of the order.
	private int npc;		//number of pitta chicken skewers in the order
	private int nps;		//number of sheftalia pittas in the order
	private int npm;		//number of mixed pitta in the order 
	private int fries;		//number of portions of fries in the order
	
	/**
     * Default constructor for Order class.
     * Initializes the order with a serial number and increments the count.
     */
	public Order()
	{
		num=count;
		count++;
	}
	
	/**
     * Parameterized constructor for Order class.
     * Initializes the order with the given parameters.
     * 
     * @param num     Serial number of the order
     * @param tOrder  Time of receiving the order
     * @param tDelReq Time at which the customer wishes to receive the order
     * @param npp     Number of pork skewers pittas in the order
     * @param npc     Number of pitta chicken skewers in the order
     * @param nps     Number of sheftalia pittas in the order
     * @param npm     Number of mixed pitta in the order
     * @param fries   Number of portions of fries in the order
     */
	public Order(int num, int tOrder, int tDelReq, int npp, int npc, int nps, int npm, int fries)
	{
		this.num=num;
		this.tOrder=tOrder;
		this.tDelReq=tDelReq;
		this.npp=npp;
		this.npc=npc;
		this.nps=nps;
		this.npm=npm;
		this.fries=fries;
	}
	
	
	 /**
     * Sets the time of receiving the order.
     * 
     * @param tOrder Time of receiving the order
     */
    public void setTorder(int tOrder) {
        this.tOrder = tOrder;
    }

    /**
     * Sets the time at which the customer wishes to receive the order.
     * 
     * @param tDelReq Time at which the customer wishes to receive the order
     */
    public void setTdelReq(int tDelReq) {
        this.tDelReq = tDelReq;
    }

    /**
     * Sets the number of pork skewers pittas in the order.
     * 
     * @param npp Number of pork skewers pittas in the order
     */
    public void setNpp(int npp) {
        this.npp = npp;
    }

    /**
     * Sets the number of pitta chicken skewers in the order.
     * 
     * @param npc Number of pitta chicken skewers in the order
     */
    public void setNpc(int npc) {
        this.npc = npc;
    }

    /**
     * Sets the number of sheftalia pittas in the order.
     * 
     * @param nps Number of sheftalia pittas in the order
     */
    public void setNps(int nps) {
        this.nps = nps;
    }

    /**
     * Sets the number of mixed pitta in the order.
     * 
     * @param npm Number of mixed pitta in the order
     */
    public void setNpm(int npm) {
        this.npm = npm;
    }

    /**
     * Sets the number of portions of fries in the order.
     * 
     * @param fries Number of portions of fries in the order
     */
    public void setFries(int fries) {
        this.fries = fries;
    }

    /**
     * Returns the serial number of the order.
     * 
     * @return The serial number of the order
     */
    public int getNum() {
        return num;
    }

    /**
     * Returns the time of receiving the order.
     * 
     * @return The time of receiving the order
     */
    public int getTorder() {
        return tOrder;
    }

    /**
     * Returns the time at which the customer wishes to receive the order.
     * 
     * @return The time at which the customer wishes to receive the order
     */
    public int getTdelReq() {
        return tDelReq;
    }

    /**
     * Returns the number of pork skewers pittas in the order.
     * 
     * @return The number of pork skewers pittas in the order
     */
    public int getNpp() {
        return npp;
    }

    /**
     * Returns the number of pitta chicken skewers in the order.
     * 
     * @return The number of pitta chicken skewers in the order
     */
    public int getNpc() {
        return npc;
    }

    /**
     * Returns the number of sheftalia pittas in the order.
     * 
     * @return The number of sheftalia pittas in the order
     */
    public int getNps() {
        return nps;
    }

    /**
     * Returns the number of mixed pitta in the order.
     * 
     * @return The number of mixed pitta in the order
     */
    public int getNpm() {
        return npm;
    }

    /**
     * Returns the number of portions of fries in the order.
     * 
     * @return The number of portions of fries in the order
     */
	public int getFries()
	{
		return fries;
	}
    
	/**
     * Returns a string representation of the Order object.
     * 
     * @return A string representation of the Order object.
     */
    public String toString()
    {
    	return (num+" "+tOrder+" "+tDelReq+" "+npp+" "+npc+" "+nps+" "+npm+" "+fries);
    }
	

}
