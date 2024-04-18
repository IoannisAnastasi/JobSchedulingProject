package hw4;

public class Order {
	private static int count=1;
	private int num;
	private int tOrder;
	private int tDelReq;
	private int npp;
	private int npc;
	private int nps;
	private int npm;
	private int fries;
	
	public Order()
	{
		num=count;
		count++;
	}
	
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
	
	public void setTorder(int tOrder)
	{
		this.tOrder=tOrder;
	}
	public void setTdelReq(int tDelReq)
	{
		this.tDelReq=tDelReq;
	}
	public void setNpp(int npp)
	{
		this.npp=npp;
	}
	public void setNpc(int npc)
	{
		this.npc=npc;
	}
	public void setNps(int nps)
	{
		this.nps=nps;
	}
	public void setNpm(int npm)
	{
		this.npm=npm;
	}
	public void setFries(int fries)
	{
		this.fries=fries;
	}
	public int getNum()
	{
		return num;
	}
	public int getTorder()
	{
		return tOrder;
	}
	public int getTdelReq()
	{
		return tDelReq;
	}
	public int getNpp()
	{
		return npp;
	}
	public int getNpc()
	{
		return npc;
	}
	public int getNps()
	{
		return nps;
	}
	public int getNpm()
	{
		return npm;
	}
	public int getFries()
	{
		return fries;
	}
    
    public String toString()
    {
    	return (num+" "+tOrder+" "+tDelReq+" "+npp+" "+npc+" "+nps+" "+npm+" "+fries);
    }
	

}
