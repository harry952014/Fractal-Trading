import java.util.*;
import java.util.regex.Pattern;

public class Date {

	  private int day = 1, month = 1, year = 2000;
	  private int[] Days = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	  
	  //Default Constructors
	  public Date() {
		  
		  day = 1;
		  month = 1;
		  year = 2000;
		  
	  }
	  
	  public Date(Date d){
		  
		  day = d.day ;
		  month = d.month ;
		  year = d.year;
		  
	  }
	  
	  //A Constructor that accepts three integer for m,d and year
	  
	  public Date(int m , int d, int y){
		  
		  setDay(d);
		  setMonth(m);
		  setYear(y);
		  
	  }
	  
	//A Constructor that accepts a string date of form "mm/dd/yyyy".
	  
	  public Date(String st){
		  
		  setDate(st);
		  
	  }
	  
	  public Date Assign(Date d){
		  
		 setYear(d.year);
		 setDay(d.day);
		 setMonth(d.month);
		 
		 return this;
		  
	  }
	  
	  public boolean isLeapYear(int y){
		
	                if ((y%4 == 0 && y%100 !=0) || y%400 == 0) {
	                        return true;
	                    }
	                return false;
	                    		  
	  }
	  
	  public boolean isGreater(Date d){
			if (this.year > d.year)
				return true;
			else if (this.year == d.year) {
				if (this.month > d.month){
					return true;
				} else if (this.month == d.month){
					if (this.day>=d.day)
						return true;
					return false;
				}else 
					return false;	
			}else 
				return false;
		}
		//computes how many days elapsed since the beginning of the year
		//example daysSinceYear("02/12/2001") = 31 for January + 12 for February
		private int daysSinceYear(Date d) {
			int sumDays = 0;
			for (int i =1; i<d.month; i++){
				sumDays += Days[i];
			}
			sumDays += d.day;
			if (d.month > 2 && isLeapYear(d.year))
				sumDays++;
			return sumDays;
		}
		public int diff(Date right) {
			Date small = this, large = right;
			if (this.isGreater(right)){
				small = right;
				large = this;
			}
				
				int numDays = 0;
				for (int i = small.year; i < large.year; i++){
					if (isLeapYear(i))
						numDays += 366;
					else 
						numDays += 365;
				}
				int DaysInFirstDate = daysSinceYear(small);
				int DaysInSecondYear = daysSinceYear(large);
				//-1 to exclude the last day
				return (numDays+DaysInSecondYear-DaysInFirstDate+1);	
		}

	  
	  /*
	  public int diff(Date startDate,Date endDate){
		  
		  if(startDate.year > endDate.year){
			  Date d = new Date();
			  d.Assign(startDate);
			  startDate.Assign(endDate);
			  endDate.Assign(d);
		  }
		  
		  int yDiff=0, mDiff=0, dDiff=0;
		  int totalDays=0;
		  
		  yDiff = endDate.year - startDate.year;
		  totalDays = yDiff * 365;
		  totalDays += yDiff / 4;
		  
		  if(endDate.month > startDate.month){
			

				for(int i = endDate.month; i>startDate.month; i--){
					if(i == 2 && (isLeapYear(startDate.year) || isLeapYear(endDate.year))){
						totalDays +=  29; 
					}else 
						totalDays +=  Days[i]; 
					
				}
		  }else if(startDate.month > endDate.month){
			  
			  for(int i = startDate.month; i>endDate.month; i--){
				  if(i == 2 && (isLeapYear(startDate.year) || isLeapYear(endDate.year))){
						totalDays -=  29; 
					}else 
						totalDays -=  Days[i]; 
				}
		  }
		  
		  
			  dDiff = endDate.day - startDate.day;
			  totalDays += dDiff;
		  
		 
			  return totalDays;
		  
	  }
	   */ 
	    //Accessors
	    public int getDay() {
	        return day;
	    }
	    public int getMonth() {
	        return month;
	    }
	    public int getYear() {
	        return year;
	    }
	    
	   
	    //mutators 
	    public void setDay(int d) {
	        
	        //you can add some constraints <0 < 30
	        //Complete this
	        if (d < 1 || d > 31) {
	            System.out.println("Invalid day\n");
	            d = Days[month];
	            return;
	        }
	        if (d > Days[month] && month != 2) {
	            System.out.println("Invalid day");
	            d = Days[month];
	            return;
	        } else if (month == 2) {
	            if (d > 29) {
	                System.out.println("Invalid day");
	                d = Days[month];
	                return;
	            } else {
	                //if it is not a leap year and d >28 return
	                if (!isLeapYear(this.year)) {
	                    if (d > 28) {
	                        System.out.println("Invalid day");
	                        d = 28;
	                        return;
	                    }
	                }
	            }        
	        }
	        //valid day set day to d
	        day = d;
	    }
	    public void setMonth(int m) {
	        if (m < 1 || m >12 ) {
	        	System.out.println(m);
	            System.out.println("Invalid month\n");
	            month = 12;
	            return;
	        }
	        //valid month set month to m
	        //current date 01/31/2006
	        //set month to 02 --> 02/31/2006 not good reset day to 31
	        //which will reset day to 28
	        month = m;
	        setDay(day);
	    }
	    public void setYear(int y) {
	        if (y < 0) {
	        	System.out.println(y);
	            System.out.println("\nInvalid year");
	            year = 0;
	            return;
	        }
	        year = y;
	        setDay(day);
	    }
	    
	    public void setDate(int d, int m) {
	        setMonth(m);
	        setDay(d);
	    }
	    public void setDate(int y, int m, int d) {
	        setYear(y);
	        setMonth(m);
	        setDay(d);
	    }
	    
	    //set the date from a string
	    public void setDate(String s) {
	    	
	    	Pattern p = Pattern.compile("(/)|(-)");
	    	
	    	//s.replaceAll("\\s", "");
	    	String[] splitDate; 
	    	
	    	 splitDate = p.split(s);
	    	 
	    	 if (splitDate.length != 3) {
		            System.out.println("Invalid date!");
		            return;
		        }
	    	 
	    	 if(s.contains("-")){
	    		 	setYear(Integer.valueOf(splitDate[0]));
	    		 	setMonth(Integer.valueOf(splitDate[1]));
			        setDay(Integer.valueOf(splitDate[2]));
	
	    	 }else {
	    		 setMonth(Integer.valueOf(splitDate[0]));
			     setDay(Integer.valueOf(splitDate[1]));
			     setYear(Integer.valueOf(splitDate[2]));
	    	 }
	    	}
	    
	    
	    
	    //read the string date from the user
	    public void readDate(Scanner keyb) {
	       // Scanner keyb = new Scanner(System.in);
	        System.out.println("Enter a date in the form mm/dd/yyyy");
	        String st = keyb.nextLine();
	        setDate(st);
	       // keyb.close();
	    }
	     
	    public void print() {
	        System.out.println(month + "/" + day + "/" + year);
	    }
	   
	    public String toString(){
	    	
	    	String st = month + "/" + day + "/" + year;
	    	
	    	return st;
	    	
	    }
		
}
