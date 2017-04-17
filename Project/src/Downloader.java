import java.io.*;
public class Downloader extends YahooData {
	private String mFileName;
	private BufferedReader buf;
	private FileReader fr;
	
	//constructor 
	public Downloader(String f, String D, String start, String last){
		super("unknown", D, start, last);
		mFileName = f;
	}
	//accessors
	public String getmFileName() {
		return mFileName;
	}
	//mutators
	public void setmFileName(String mFileName) {
		this.mFileName = mFileName;
	}
	//method Download all
	public boolean loadAll() {
		//open the symbol file
		try {
			fr = new FileReader(mFileName);
			buf = new BufferedReader(fr);
			//read one symbol at a time and download it 
			String symbol;
			while((symbol = buf.readLine()) != null) {
				//change the symbol to current symbol and the output file as well
				//just call setSymbol on yahooData
				this.setSymbol(symbol);
				if (!this.load()) {
					System.out.println("Unable to load " + symbol);
					return false;
				}
			}
			buf.close();
			fr.close();
		}catch(IOException e) {
			System.out.println("Message : " + e.getMessage());
		}
		return true;
	}
}