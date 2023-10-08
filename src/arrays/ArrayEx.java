package arrays;

import java.util.LinkedHashSet;

public class ArrayEx {

	
	public static void main(String [] args) {
		      String s= "My name name is is Amruht";
		      String word[]=s.split(" ");
		      LinkedHashSet <String> set=new LinkedHashSet<String>();
		      for(String w: word) {
		    	 set.add(w);
		      } 
		      System.out.println(set+" "); 		 
		      
		    
		        
		
}
}