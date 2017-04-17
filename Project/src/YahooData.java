//This class is downloading historical data for a specific stock starting at a date and finishing at specific date.
//the data is going to  be saved in a specified directory  as csv file. The name of the file will be Symbol_Daily.csv
// Downloader ("MSFT", "C:/myData/", "01/01/2006, "12/31/2016");
//Downloader.load();

import java.util.*;
import java.io.*;
import java.net.*;

public class YahooData extends Exception{

private String symbol;
private String directory;
private Date startDate, endDate;
private String fileName;
//private String url = "http://chart.finance.yahoo.com/table.csv?s=GOOG&a=1&b=14&c=2013&d=3&e=14&f=2017&g=d&ignore=.csv";
	


	public YahooData(){
	symbol = "" ;
	directory = "/";
	startDate = new Date();
	endDate = new Date();
	fileName = "_Daily.csv";
	}

	public YahooData(String s, String d, Date st, Date end){
		symbol = s;
		directory = d;
		startDate = new Date(st);
		endDate = new Date(end);
		fileName = d + symbol + "_Daily.csv";
	}

	public YahooData(String s, String d, String st, String end){
		symbol = s;
		directory = d;
		startDate = new Date(st);
		endDate = new Date(end);
		fileName = d + symbol + "_Daily.csv";
	}
	
	public YahooData( String s, String d, String f, Date sD, Date eD){
		
		setSymbol(s);
		setDirectory(d);
		setStartDate(sD);
		setEndDate(eD);
		fileName = d + f;  
			
	}
	
public YahooData( YahooData d){
		
		setSymbol(d.symbol);
		setDirectory(d.directory);
		setStartDate(d.startDate);
		setEndDate(d.endDate);
		setFileName(d.fileName);  
			
	}


	
	public boolean load() {
		//create URL a string
		
		String url = "http://chart.finance.yahoo.com/table.csv?s=";
		url += symbol + "&a=" + (startDate.getMonth() - 1) + "&b=" + startDate.getDay() + "&c=" + startDate.getYear();
		url += "&d=" + (endDate.getMonth()-1)  + "&e=" + endDate.getDay() + "&f=" + endDate.getYear() ;
		url += "&g=d&ignore=.csv";
		
		try{
			URL urlYahoo = new URL(url);
			
			//open the connection
			URLConnection urlConn = urlYahoo.openConnection();
			
			
			//get the data
			InputStreamReader inStream = new InputStreamReader(urlConn.getInputStream());
			
			//read the data
			BufferedReader buffer = new BufferedReader(inStream);
		
			FileWriter outFile = new FileWriter(this.fileName);
			BufferedWriter bOut = new BufferedWriter(outFile);
			
			String lineStr;
			
			while((lineStr = buffer.readLine())!= null){
				
				bOut.write(lineStr + "\n");	
			}
			
			bOut.close();
			outFile.close();
			
			
			return true;
			
			
		}catch(MalformedURLException e){
			
			System.out.println("Exception: " + e.getMessage());
			return false;
			
		}catch (IOException e){ // for exception from file handling
			System.out.println("Exception: " + e.getMessage());
			return false;
		}
		
	
	}
	
	public String getSymbol(){
		return symbol;
	}
	public String getDirectory(){
		return directory;
	}
	public Date getStartDate(){
		return startDate;
	}
	public Date getEndDate(){
		return endDate;
	}
	
	public String getFileName(){
		return fileName;
	}
	
	
	//
	public void setSymbol(String s){
		symbol = s;
		fileName = directory + symbol + "_Daily.csv";
		
	}
	
	public void setDirectory(String d){
		directory = d;
		
	}
	public void setStartDate(Date sD){
		
		startDate = new Date(sD);
		
	}
	public void setStartDate(String sD){
		
		startDate = new Date(sD);
		
	}
	public void setEndDate(Date eD){
		
		endDate = new Date(eD);
		
	}
	public void setEndDate(String eD){
		
		endDate = new Date(eD);
		
	}
	public void setFileName(String f){
		fileName = directory + f;
		
	}
	
	public void setData(String s, String d, Date st, Date end){
		symbol = s;
		directory = d;
		startDate = new Date(st);
		endDate = new Date(end);
		fileName = d + symbol + "_Daily.csv";
	}
	
	public void setData( String s, String d, String f, Date sD, Date eD){
		
		setSymbol(s);
		setDirectory(d);
		setStartDate(sD);
		setEndDate(eD);
		fileName = d + f;  
			
	}
	

}
