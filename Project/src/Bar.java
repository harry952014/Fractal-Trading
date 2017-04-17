import java.util.*;

/*
  Design and code a class Bar that has the following properties
Date for the day
floats o, h, l, c, adjC
v (int),  
provide accessors for open it would be open(), etc...
provide mutators 
range that returns the difference from high to low
constructor with all arguments 
constructor that accepts a string int form: "mm-dd-yyyy, open, high, low, close, volume, adjClose"
*/

public class Bar {

	
	private Date date;
	private float open,high,low,close, adjustedClose;
	private int volume;
	
	public Bar(){
		date = new Date();
		open = high = low = close = adjustedClose = 0.0f;
		volume = 0;
		
		
	}
	
	public Bar(Bar b){
		date = new Date(b.date);
		open = b.open;
		high = b.high;
		low = b.low;
		close = b.close;
		adjustedClose = b.adjustedClose;
		volume = b.volume;
	}
	
	public Bar(Date d, float o, float h, float l, float c, float adjC, int v) {
		
		 date = new Date(d); 
		 open = o;
		 high = h;
		 low = l;
		 close = c;
		 adjustedClose = adjC;
		 volume = v;
	}
	
	
	public Bar(String d, float o, float h, float l, float c, float adjC, int v) {
		
		 date = new Date(d); 
		 open = o;
		 high = h;
		 low = l;
		 close = c;
		 adjustedClose = adjC;
		 volume = v;
	}
	
	public Bar(String b) {
		
		String[] bar = b.split(",");
		
		if(bar.length != 7){
			System.err.println("Corrupt Data");
			return;
		}
		
		 date = new Date((bar[0])); 
		 open = Float.valueOf(bar[1]);
		 high = Float.valueOf(bar[2]);
		 low = Float.valueOf(bar[3]);
		 close = Float.valueOf(bar[4]);
		 volume = Integer.valueOf(bar[5]);
		 adjustedClose = Float.valueOf(bar[6]);
		 
	}
	
	
	public float range(){
		return this.high - this.low;
	}
	
	//Accessors 
	
	public Date getDate(){
		return this.date;
	}
	
	public float getOpen(){
		return this.open;
	}
	
	public float getHigh(){
		return this.high;
	}
	public float getLow(){
		return this.low;
	}
	public float getClose(){
		return this.close;
	}
	public float getAdjClose(){
		return this.adjustedClose;
	}
	public long getVolume(){
		return this.volume;
	}
	
	
	//Mutators
	public void setDate(String d){
		this.date.setDate(d);
	}
	
	public void setDate(Date d){
		this.date.Assign(d);
	}
	
	public void setOpen(float o){
		 this.open = o;
	}
	
	public void setHigh(float h){
		 this.high = h;
	}
	public void setLow(float l){
		this.low = l;
	}
	public void setClose(float c){
		this.close = c;
	}
	public void setAdjClose(float adjC){
		this.adjustedClose = adjC;
	}
	public void setVolume(int v){
		 this.volume = v;
	}
	
	public String toString(){
		String st = "\n*****************************************\n\nDate: " + date.toString() + "\nOpen: " + open + "\nHigh: " + high 
				+ "\nLow: " + low + "\nClose: "+ close + "\nAdjusted Close: " + adjustedClose + 
				"\nVolume: " + volume;
		return st;
	}
	
	
}
