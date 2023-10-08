package stringsExamples;

import java.util.LinkedHashSet;

public class PrintDuplicatesInGivenSentence {

	public static void main(String[] args) {
		String str="ms is my fav dhoni dhoni dhoni dhoni dhoni";
	      String[] s1=str.split(" ");
	      LinkedHashSet<String> hs=new LinkedHashSet<String>();
	      for(String word:s1){
	          if(hs.add(word)==false)
	          System.out.println(word);
	      }
	}

}
