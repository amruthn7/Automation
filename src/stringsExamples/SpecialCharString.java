package stringsExamples;

import java.util.Scanner;

public class SpecialCharString {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the string");
		String st = sc.nextLine();
		int rs=countSpChar(st);
		System.out.println("No of special characters in the given string is "+rs);
	}

	private static int countSpChar(String st) {
		int count=0;
		for(int i=0; i<st.length(); i++) {
			char ch=st.charAt(i);
			if(((ch>='0'&& ch<='9')||(ch>='A'&& ch<='Z')||(ch>='a'&& ch<='z')) ==false) {
				count++;
			}
		}
		return count;
	}
}
