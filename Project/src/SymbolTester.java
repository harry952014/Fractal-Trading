import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class SymbolTester {
	TradeArray mTrades;
	String mSymbol;
	BarArray mBars;
	int win, loss;
	
	//constructors
	
	public SymbolTester (String s){
		
		mSymbol = s;
		mBars = new BarArray(1000);
		mTrades = new TradeArray();
		win = loss = 0;
	
	}
	
	///Accessor and mutators
	public TradeArray getmTrades() {
		return mTrades;
	}
	public void setmTrades(TradeArray mTrades) {
		this.mTrades = mTrades;
	}
	public String getmSymbol() {
		return mSymbol;
	}
	public void setmSymbol(String mSymbol) {
		this.mSymbol = mSymbol;
	}
	public BarArray getmBars() {
		return mBars;
	}
	public void setmBars(BarArray mBars) {
		this.mBars = mBars;
	}
	
	
	//method to load the bars into mBars
		void loadBars() {
			
			String line;
			String fileName =  mSymbol + "_Daily.csv";
			
			try{
				//open the file of data 
				BufferedReader buff = new BufferedReader(new FileReader(fileName));
				buff.readLine();
				
				//go through it line by line 
				//make a Bar from a line
				while((line = buff.readLine()) != null){
	
					//insert the bar into mBars
					mBars.insertHead(new Bar(line));
				   
				}
				 
				buff.close();
				
			}
			catch(IOException e){
				
				System.out.println(e.getMessage());
			}
		}
	
	public TradeArray test() {
		//go through mBars
		//if pattern found 
		//create a trade: entry date, entryprice, target, stoploss, direction
		
		
		for (int i=2; i<mBars.size()-10; i++){
			
			int trigInx;
			
			//look for a fractal pattern of 5 Bars for long trade (Bullish fractal)
			if(isBullish(i)){
				
				//look for the trigger bar(after the 5-bar fractal,
				//the bar that closes above the previous bar's high).
				//If found, enter the "long" trade.
				trigInx = bullishTrigger(i);
				if(trigInx != -1){
					
					Trade trade = new Trade();		
					Date entDate = new Date(mBars.At(trigInx).getDate());  //trigger bar's date
					float entPrice = mBars.At(trigInx).getHigh();         // trigger bar's high
					
					//stop loss should be below the low of fractal pattern
					float SL = minLow(mBars.At(i-2).getLow(),mBars.At(i-1).getLow(),
							   mBars.At(i).getLow(),mBars.At(i+1).getLow(),
							   mBars.At(i+2).getLow()) - 1;
					
					float target = targetLong(i-4, entPrice, SL);      //sets the target
					
				
					trade.open(entDate,entPrice,SL,target,"long");    //place the order
				
					//checkLongTrade will look for the target after the trigger bar
					//and update exitDate,and exitPrice
					//insert trade into mTrades
					
					checkLongTrade(trade, trigInx);
						System.out.println(trade);
					
					
				
				}
				//look for a fractal pattern of 5 Bars for short trade (Bearish fractal)
			}else if(isBearish(i)){
				
				//look for the trigger bar(after the 5-bar fractal,
				//the bar that closes below the previous bar's low).
				//If found, enter the "short" trade.
				
				trigInx = bearishTrigger(i);
				
				if(trigInx != -1){
					Trade trade = new Trade();		
					Date entDate = new Date(mBars.At(trigInx).getDate());           //trigger bar's date
					float entPrice = mBars.At(trigInx).getLow();       	  			//trigger bar's low
					float SL = mBars.At(trigInx).getHigh() + 1;           			//set SL 1 tick above the trigger bar.
					float target = targetShort(i+3,entPrice, SL);
					
					trade.open(entDate,entPrice,SL,target,"short");
					
					//checkShortTrade will look for the target after the trigger bar
					//and update exitDate,and exitPrice
					//insert trade into mTrades
					 
					checkShortTrade(trade, trigInx);
							
					
				}	
				
			}
	
		}
			
		return mTrades;
	}
	
	
	public void checkLongTrade(Trade t, int k){
		
		
		
		//from the point of entry, loop ahead to look for the target or stop-loss
		for(int i=k; i<mBars.size(); i++){
			
			
			//if the bar with a high above or equal to target is found, its a win. Exit the trade.
			if(mBars.At(i).getHigh() >= t.getTarget()){
				
				System.out.println("Target Reached");
				t.close(mBars.At(i).getDate(), t.getTarget());
				
				mTrades.insertTail(t);
				//System.out.println(t.toString());
				
				return;
				
				//else if the bar with a low below or equal to SL, its a loss. Exit the trade.
			} else if(mBars.At(i).getLow() <= t.getStopLoss()){
				
				System.out.println("Stop-Loss Reached");
				t.close(mBars.At(i).getDate(), t.getStopLoss());
				
			    mTrades.insertTail(t);
				//System.out.println(t.toString());
				return;
			}
		}
		
		
	}
	
	public void checkShortTrade(Trade t, int k){
		
		for(int i=k; i<mBars.size(); i++){
			
			if(mBars.At(i).getLow() <= t.getTarget()){
				
				System.out.println("Target Reached");
				t.close(mBars.At(i).getDate(), t.getTarget());
				mTrades.insertTail(t);
				//System.out.println(t.toString());
				return;
				
			} else if(mBars.At(i).getHigh() >= t.getStopLoss()){
				
				System.out.println("Stop-Loss Reached");
				t.close(mBars.At(i).getDate(), t.getStopLoss());
				mTrades.insertTail(t);
				//System.out.println(t.toString());
				return;
			}
		}
	
	}
	
	//this method finds the lowest low in 5-bar fractal
	final static float minLow(float a, float b, float c, float d, float e){
		
		float min = Math.min(a,b);
		min = Math.min(min, c);
		min = Math.min(min, d);
		min = Math.min(min, e);
		
		return min;
	}
	
	
	//method to check the bullish fractals (for long entry)
	final boolean isBullish(int i){
		
		if(i<mBars.size()-3)
			return mBars.At(i).getHigh() > mBars.At(i-1).getHigh()   &&
				mBars.At(i-1).getHigh() > mBars.At(i-2).getHigh() &&
				mBars.At(i).getLow() > mBars.At(i+1).getLow()    &&
				mBars.At(i+1).getLow() > mBars.At(i+2).getLow();
				
			//return mBars.At(i).getLow() < mBars.At(i-1).getLow()   &&
					//mBars.At(i-1).getLow() < mBars.At(i-2).getLow() &&
					//mBars.At(i-2).getHigh() > mBars.At(i-3).getHigh()    &&
					//mBars.At(i-3).getHigh() > mBars.At(i-4).getHigh();
				
		return false;
	}
	
	//method to check the bearish fractals (for short entry)
	final boolean isBearish(int i){
		
		if(i<mBars.size()-3)
			return mBars.At(i-1).getLow() < mBars.At(i-2).getLow()   &&
				 mBars.At(i).getHigh() < mBars.At(i+1).getHigh() &&
				 mBars.At(i+1).getHigh() < mBars.At(i+2).getHigh();
		
		//return mBars.At(i).getHigh() > mBars.At(i-1).getHigh()   &&
				// mBars.At(i-1).getHigh() > mBars.At(i-2).getHigh() &&
				// mBars.At(i-3).getLow() < mBars.At(i-4).getLow();
		
		return false;
	}
	
	public float targetShort(int k, float entP, float SL){
		
		for (int i = k+2; !isBullish(i)&&i<mBars.size()-3; i++){
			
			
			// look for a swing low before the next fractal formation (bullish in this case)
			if(mBars.At(i).getLow() < mBars.At(i-1).getLow() &&
					mBars.At(i).getLow() < mBars.At(i+1).getLow()){
				
				//if that low swing point is less than the entry price
				if(mBars.At(i).getLow() < entP)   
					return mBars.At(i).getLow();  //returns the low swing point as target
			}
		}
		
		//if no swing low found, set the target below the entry price 
		//the difference between entry price and stop loss.
		return entP - (SL - entP);
	}
	
	public float targetLong(int k, float entP, float SL){
		
		
		//look for the previous swing high (before the fractal)
		for (int i=k; i>0; i--){
			
			if(mBars.At(i).getHigh() > mBars.At(i+1).getHigh() &&
					mBars.At(i).getHigh() > mBars.At(i-1).getHigh()){
				
				
				//if that high swing point is greater than the entry price
				if(mBars.At(i).getHigh() > entP)
					return mBars.At(i).getHigh();    //return the swing high point as target
			}
		}
		
		//if no swing high found, set the target above the entry price 
		//twice the difference between entry price and stop loss.
		return entP + (2*(entP - SL));
	}
	
	//Wait for the trigger bar to close above the high of the fractal
	
	public int bullishTrigger(int i){
		
		for(int j= i+3; j<2000; j++){
			
			if(mBars.At(j).getClose() > mBars.At(i).getHigh())
				return j;
		}
		
		return -1;
		
	}
	
	//Wait for the trigger bar to close below the low of the fractal
	
	public int bearishTrigger(int i){
		
		for(int j= i+3; j<20; j++){
			
			if(mBars.At(j).getClose() < mBars.At(i).getLow())
				return j;
		}
		
		return -1;
		
	}
	
}