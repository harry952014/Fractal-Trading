import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class TradeArray {
	
	private Vector<Trade> vec;
	private int wins,losses;
	
	//constructor
	
	public int getWins() {
		return wins;
	}


	public void setWins(int wins) {
		this.wins = wins;
	}


	public int getLosses() {
		return losses;
	}


	public void setLosses(int losses) {
		this.losses = losses;
	}


	public TradeArray(){
		
		vec = new Vector<Trade> (1000);
		wins = losses = 0;
	}
	
	
	//The most important method is method that computes the statistics and 
		//records them in a file (passed a parmater)
		public void stats(String fileName) {
			//go through all trades and record stats
			
			String lineStr="";
			float PL;
			int wins=0,losses=0;
			float totalProfit=0;
			float totalLoss = 0;
			float APPT=0;
			float percentWin = 0;
			float avgWin = 0;
			float avgLoss = 0;
			
			
	
	
	APPT = (totalProfit)/(vec.size());      	//Average profit per trade
	percentWin = 100 * (wins)/(vec.size());		// % winners
	avgWin = (totalProfit)/(wins);				//Average win
	avgLoss = (totalLoss)/(losses);				//Average loss
	
	lineStr += "\n\n\nAPPT, " + APPT + "\n% Winners, " + percentWin + "%" 
			+ "\navgWin, " + avgWin + "\navgLoss, " + avgLoss;
			
			
			
			
			try{
			FileWriter outFile = new FileWriter(fileName);
			BufferedWriter bOut = new BufferedWriter(outFile);
			

			for(int i=0; i<vec.size(); i++){
				
				lineStr = vec.elementAt(i).toString();
				PL = vec.elementAt(i).PL();
				
				if(PL>=0){
					
					lineStr += "\nProfit, " + PL; 
					totalProfit += PL;
					wins++;
				}else{
					lineStr += "\nLoss, " + PL;
					totalLoss += PL; 
					losses++;
				}
			
				
				bOut.write(lineStr + "\n");	
			}
			
		APPT = (totalProfit)/(vec.size());      	//Average profit per trade
		percentWin = 100 * (wins)/(vec.size());		// % winners
		avgWin = (totalProfit)/(wins);				//Average win
		avgLoss = (totalLoss)/(losses);				//Average loss

		String statStr = "\n\n\nAPPT, " + APPT + "\n% Winners, " + percentWin + "%" 
		+ "\nAverage Win, " + avgWin + "\nAverage Loss, " + avgLoss;
			
		bOut.write(statStr + "\n");	

			bOut.close();
			outFile.close();
			}catch(IOException e){
				System.out.println(e.getMessage());
			}
		}
	
	//methods insert
	
	public void insertHead(Trade t){
		
		vec.insertElementAt(t, 0);
	}
	
	public void insertTail(Trade t){
		
		vec.add(t);
	}
	
	public void insert(Trade t, int i){
		if(i > vec.size()){
			System.out.println("Out of bounds");
		}
		vec.insertElementAt(t, i);
	}
	
	//method remove
	public void remove(int i){
		
		vec.remove(i);
	}
	
	public void removeHead(){
		
		vec.remove(0);
	}
	public void removeTail(){
		
		vec.remove(vec.size());
	}
	
	public Trade At(int i){
		return vec.elementAt(i);
	}
	//display
	public void display(){
		
		for(int i=0; i<vec.size(); i++){
			
			System.out.println(At(i));
		}
	}
	
	//accessors and mutators
	public Vector<Trade> getVec() {
		return vec;
	}

	public void setVec(Vector<Trade> vec) {
		this.vec = vec;
	}

	

}