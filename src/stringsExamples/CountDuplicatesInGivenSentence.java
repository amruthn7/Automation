package stringsExamples;

import java.util.LinkedHashSet;

public class CountDuplicatesInGivenSentence {
	public static void main(String[] args) {
	      String str="ms is my fav dhoni dhoni dhoni";
	      String[] s1=str.split(" ");
	      LinkedHashSet<String> set=new LinkedHashSet<String>();
	      for(String word1:s1){
	          set.add(word1);
	          //System.out.println(set);
	      }
	      for(String word2:set){
	          int count=0;
	          for(String word3:s1){
	          if(word3.equals(word2)){
	              count++;
	            }
	        }
	      System.out.println(word2+" "+count);
	      }
	    }
}
