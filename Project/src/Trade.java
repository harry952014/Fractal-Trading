/*
 * design and code a class Trade that has the following properties
 * entryDate: date on which the trade is opened
 * exitDate: Date on which the trade is closed
 * entryrice: the price the trade is initiatied (buyprice for long
 * and sell price for short)
 * stopLoss: the price at which the is forced to exit with loss
 * target: the price at which the trade is closed for a win
 * exitPrice: the price at which the trade is closed
 * Direction: "short", "long", "none"
 * accessors and mutators for all of them 
 * method PL() that would return the profit (positive) or loss (negative)  
 * 
 */
public class Trade {
	private Date entryDate, exitDate;
	private float entryPrice, exitPrice, stopLoss, target;
	private String Direction = "none";
	
	//constructors
	public Trade() {
		entryDate = new Date();
		exitDate = new Date();
		entryPrice = 0.0f;
		exitPrice = 0.0f;
		stopLoss = 0.0f; 
		target  = 0.0f;
		Direction = "none";
		
	}
	public Trade(Date e, float price, float stop, float t, String Dir) {
		entryDate = new Date(e);
		entryPrice = price;
		stopLoss = stop; 
		target  = t;
		Direction = Dir;
		
	}
	public Trade(String e, float price, float stop, float t, String Dir) {
		entryDate = new Date(e);
		entryPrice = price;
		stopLoss = stop; 
		target  = t;
		Direction = Dir;
	
	}

	
	//accessor
	public Date getEntryDate() {return entryDate;}
	public Date getExitDate() {return exitDate;}
	public float getEntryPrice(){return entryPrice;}
	public float getExitPrice(){return exitPrice;}
	public float getStopLoss() {return stopLoss;}
	public float getTarget(){return target;}
	public String getDirection(){return Direction;}
	//mutators
	public void setEntryDate(Date d) {
		entryDate = new Date(d);
	}
	public void setEntryDate(String d) {
		entryDate = new Date(d);
	}
	public void setExitDate(Date d) {
		exitDate = new Date(d);
	}
	public void setexitDate(String d) {
		exitDate = new Date(d);
	}
	public void setEntryPrice(float p){
		entryPrice = p;
	}
	public void setExitPrice(float p){
		exitPrice = p;
	}
	public void setStopLoss(float p){
		stopLoss = p;
	}
	public void setTarget(float p){
		target = p;
	}
	public void setDirection(String d){
		Direction = d;
	}
	public void open(Date e, float price, float stop, float t, String Dir) {
		entryDate = new Date(e);
		entryPrice = price;
		stopLoss = stop; 
		target  = t;
		Direction = Dir;
	}
	public void close(Date c, float price) {
		exitDate = new Date(c);
		exitPrice = price;
	}
	public float PL() {             //Profit or Loss
		if (Direction.equals("long")){
			return (exitPrice - entryPrice);
		}else {
			return (entryPrice - exitPrice);
		}
	}
	
	public double percentPL() {             //Profit or Loss
		if (Direction.equals("long")){
			return ((exitPrice - entryPrice)/entryPrice) * 100;
		}else {
			return ((entryPrice - exitPrice)/entryPrice) * 100;
		}
	}
	
	public String toString(){
		
		String st = "\nEnter Date:," + entryDate + "\nEntry Price:, " + entryPrice + 
				"\nTarget:, " + target + "\nStop Loss:, " + stopLoss + "\nExit Price:, " + exitPrice +
				"\nExit Date:, " + exitDate  +  
				"\nDirection:, " + Direction;
		
		return st;
	}
}