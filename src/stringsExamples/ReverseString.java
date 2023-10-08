package stringsExamples;

import java.io.PrintStream;
import java.util.Scanner;

public class ReverseString {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the string");
		String st = sc.nextLine();
		String lc = st.toLowerCase();
		System.out.println(lc);
		String uc=st.toUpperCase();
		System.out.println(uc);
		//String rs=getReverse(st);
		//String rev = getReverse2(st);
		getReverse3(st);
		//System.out.println("Reverse of the string is "+rs);
		//System.out.println("Reverse of the string is "+rev);
	}

	private static String getReverse2(String st) {
		char ch[]=st.toCharArray();
		int i=0, j=st.length()-1;
		while(i<j) {
			char temp=ch[i];
			ch[i]=ch[j];
			ch[j]=temp;
			i++;
			j--;
		}
		String str=new String(ch);
		return str;
	}

	public static String getReverse(String st) {
		String rev=" ";
		for(int i=0; i<st.length(); i++) {
			rev=st.charAt(i) + rev;
		}
		return rev;
	}
	
	public static void  getReverse3 (String st) {
		
		for (int i=st.length()-1; i>=0; i--) {
			char rev = st.charAt(i);
			System.out.print(rev);
		}
		
		
	}
}
