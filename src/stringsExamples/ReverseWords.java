package stringsExamples;

import java.util.Scanner;

public class ReverseWords {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter the String");
		String st = sc.nextLine();//welcome to java
		String rs = getReverse(st);
		System.out.println(rs);
		
}

	private static String getReverse(String st) {
		String []words=st.split(" ");
		String reverseString="";
		for (String w : words) {
			String reverseword="";
			
			for (int i = w.length()-1; i>=0; i--) {
				reverseword=reverseword + w.charAt(i);
			}
			reverseString=reverseString+reverseword+" ";
		}
		return reverseString;
	}
}