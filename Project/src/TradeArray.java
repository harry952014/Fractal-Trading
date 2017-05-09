import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Vector;

public class TradeArray {
	
	private Vector<Trade> vec;
	
	//constructor
	
	public TradeArray(){
		
		vec = new Vector<Trade> (10000,1000);

	}
	
	//The most important method is method that computes the statistics and 
		//records them in a file (passed a parameter)
	
		public void stats(String fileName) {
			//go through all trades and record stats
			
	
		File outFile;
			try{
				//*****Prints all the trades****** ///////
				
				FileWriter ft = new FileWriter(fileName + "_trades.csv");
				BufferedWriter bf = new BufferedWriter(ft);
				for (int i =0; i < this.size(); i++){
					bf.write(this.At(i).toString()+"\n");
				}
				bf.close();
				ft.close();
				
				////// ************* End printing trades  **********  ////////
				
				///******* Open a new file to store statistics   ****////////
				
				outFile = new File(fileName);
				int counter = 1;
				while (outFile.exists()) {
					outFile = new File(fileName + "("+counter+").csv" );
					counter++;
				}
				
			FileWriter fw = new FileWriter(outFile);
			BufferedWriter bOut = new BufferedWriter(fw);
			
            // All the variables to store stats.
			
			int numberWin= 0, numberLoss = 0, numberLongWin = 0, numberShortWin = 0;
			int numberLongLoss = 0, numberShortLoss = 0;
			double percentWin = 0.0, percentLoss = 0.0, percentLongWin = 0, percentLongLoss = 0;
			double percentShortWin = 0.0, percentShortLoss = 0;
			double maxWin = 0.0, maxLoss = 0.0;
			
			
			for(int i=0; i<this.size(); i++){
				
				if(this.At(i).getDirection().equalsIgnoreCase("long")){
					
					if(this.At(i).percentPL() > 0){
						
						numberWin++;
						numberLongWin++;
						percentWin += this.At(i).percentPL();
						percentLongWin += this.At(i).percentPL();
						
						if(this.At(i).percentPL() > maxWin){
							maxWin = this.At(i).percentPL();
						}
					}else {//it is a loss	
						numberLoss++;
						numberLongLoss++;
						//loss is negative
						percentLoss += this.At(i).percentPL();
						percentShortLoss += this.At(i).percentPL();
						if (this.At(i).percentPL()< maxLoss) {
							maxLoss = this.At(i).percentPL();
						}
					
				}
				
				
				}else{ // it is short
				
				if (this.At(i).percentPL() >= 0){//it a win
					numberWin++;
					numberShortWin++;
					percentWin += this.At(i).percentPL();
					percentShortWin += this.At(i).percentPL();
					if (this.At(i).percentPL() > maxWin) {
						maxWin = this.At(i).percentPL();
					}
				}else {//it is a loss	
					numberLoss++;
					numberShortLoss++;
					//loss is negative
					percentLoss += this.At(i).percentPL();
					percentShortLoss += this.At(i).percentPL();
					if (this.At(i).percentPL()< maxLoss) {
						maxLoss = this.At(i).percentPL();
					}
			}
		}
				
				
				
	}
			
			//write the stats to the file and we are done!!
			bOut.write("Number Trades = " + this.size()+ "\n" + " Number Wins = " + numberWin + "\n" +
			"number Losses = " + numberLoss + "\n" + "Total Wining = " + percentWin + " % \n" +
					"Total Loss = " + percentLoss + " %\n" + "Average PL = " + (percentWin+percentLoss)/this.size() + "\n" +
			"Average Win for wins = " + percentWin/numberWin + "\n" + "AverageLoss for Losers = " + percentLoss/numberLoss + "\n" +
					"numberLongWin = " + numberLongWin + "   numberLongLoss = " + numberLongLoss + "\n" + 
					"PL for Longs = " + (percentLongWin + percentLongLoss)/(numberLongWin + numberLongLoss) + "\n" + 
					"numberShortWin = " + numberShortWin + "   numberShortLoss = " + numberShortLoss + "\n" + 
					"PL for Shorts = " + (percentShortWin + percentShortLoss)/(numberShortWin + numberShortLoss) + "\n" +
					"maxWin = " + maxWin + ",   maxLoss = " + maxLoss);
			bOut.close();
			fw.close();

			
			///////////////**********************Statistics***************//////////////
			
			/*
		APPT = (totalProfit)/(vec.size());      	//Average profit per trade
		percentWin = 100 * (wins)/(vec.size());		// % winners
		avgWin = (totalProfit)/(wins);				//Average win
		avgLoss = (totalLoss)/(losses);				//Average loss

		String statStr = "\n\n\nAPPT, " + APPT + "\nTotal PL %, " + totalPLPercent + "\n% Winners, " + percentWin + "%" 
		+ "\nAverage Win, " + avgWin + "\nAverage Loss, " + avgLoss;
			
		bOut.write(statStr + "\n");	
*/
			
			
			
			}catch(IOException e){
				System.out.println(e.getMessage());
			}
		}
	
	//methods insert
	
	public void insertHead(Trade t){
		
		vec.add(0, t);
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
	public Trade remove(int i){
		
		return vec.remove(i);
	}
	
	public Trade removeHead(){
		
		return vec.remove(0);
	}
	public Trade removeTail(){
		
		return vec.remove(vec.size());
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
	
	public int size(){
		
		return vec.size();
	}

	public int capacity() {
		return vec.capacity();
	}
	
	public void resize(int newCapacity){
		vec.setSize(newCapacity);
	}

}