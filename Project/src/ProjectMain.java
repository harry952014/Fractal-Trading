
public class ProjectMain {
	public static void main(String[] args){
		//download the data 
		//create a downloader and download the stocks, do it only once
		
		//Downloader data = new Downloader("Stocks.txt","","01/01/2006","12/31/2016");
		//data.loadAll();
		
		//data.setmFileName("Indices.txt");
		//data.loadAll();
	
		//Create a test that accepts a file of Stocks to be tested
		Tester testA = new Tester("Stocks.txt");
		
		//Test should return a Vector f trades TradeArray
		
		
		//You TradeArray should have a method called statistics
		testA.tesAll().stats("Stats.csv");
	}
}