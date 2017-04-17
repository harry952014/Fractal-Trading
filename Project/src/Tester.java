import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Tester {
	private TradeArray mTrades;
	private String fileName = "";
	//constructors
	//create an array of trades to fill and return
	
	public Tester(){
		mTrades = new TradeArray();	
	}	
	//file where all the stocks to be test are there
	public Tester(String f){
		mTrades = new TradeArray();	
		fileName = f;

	}
	//a method testAll that would test the stocks and return TradeArray
	
	public TradeArray tesAll() {
		//open the file of the symbols 
		String  Symbol="";
		
		try{
			 
			BufferedReader buff = new BufferedReader(new FileReader(fileName));
			
		//go through the file and test one stock at a time 
			
			while((Symbol = buff.readLine()) != null){
				
				SymbolTester st = new SymbolTester(Symbol);
				
				System.out.println("--------------------------- " + Symbol + "--------------------");
				st.loadBars();
				Trade temp = st.test();
				//Add temp to mTrades
				mTrades.insertTail(temp);
				
			}
			
			buff.close();
		}catch(IOException e){
			System.out.println(e.getMessage());
			return null;
		}
		
		
		return mTrades;
	}
}