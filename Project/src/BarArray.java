import java.util.*;


/*
 * 
 * 1- a private member Vector of Bar (objects from class Bar already designed)
2- A set of methods that make the use of the class easier (all of them should call the native methods from class Vector; they are merely calls)
- boolean isEmpty()
-boolean isFull()
-int size()
-boolean insertHead(Bar b);
-boolean insertTail(Bar b);
-boolean insert(Bar b, int index); //to insert at specific index
-Bar remove(int index); //removes the Bar at index and returns it 
-Bar removeHead(); //removes the Bar at the head and returns it
-Bar removeTail();//removes the Bar at the tail and returns it.
-Bar At(int ind); //returns the Bar at index ind without removing it from the Vector. 
*/


public class BarArray {

	
	private Vector<Bar> bars ;
	
	public BarArray(){
		
		bars = new Vector<Bar> (1000,100);
	}
	
	public BarArray(int size){
		
		bars = new Vector<Bar> (size,100);
	}
	
	public BarArray(BarArray b){
		
		bars = b.bars;
	}
	public boolean isEmpty(){
		
		return bars.isEmpty();
	}
	
	public boolean isFull(){
		
		if(bars.size() == bars.capacity())
			return true;
		else 
			return false;
	}
	
	public int size(){
		return bars.size();
	}
	
	
	public void insertHead(Bar b){
		
		bars.insertElementAt(b, 0);
	}
	
	public void insertTail(Bar b){
		
		bars.add(b);
	}
	
	public void insert(Bar b, int i){
		if(i > bars.size()){
			System.out.println("Out of bounds");
		}
		bars.insertElementAt(b, i);
	}
	
	public void remove(int i){
		
		bars.remove(i);
	}
	
	public void removeHead(){
		
		bars.remove(0);
	}
	public void removeTail(){
		
		bars.remove(bars.size());
	}
	
	public Bar At(int i){
		return bars.elementAt(i);
	}
	
	public void display(){
		
		for(int i=0; i<bars.size(); i++){
			
			System.out.println( At(i));
		}
	}
	
}
