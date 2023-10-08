package stringsExamples;

import java.util.Scanner;

public class CountVowels {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the string");
		String st = sc.nextLine();
		int rs=countVowels(st);
		System.out.println("No of vowels in the given string is "+rs);
	}

	private static int countVowels(String st) {
		String v="AEIOUaeiou";
		int count=0;
		for (int i=0; i<st.length(); i++) {
			char ch= st.charAt(i);
			if(v.indexOf(ch)!=-1)
				count++;
		}
		return count;
	}
	

}
