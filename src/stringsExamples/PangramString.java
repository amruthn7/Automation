package stringsExamples;

import java.util.Scanner;

public class PangramString {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter the String");
		String st = sc.nextLine();
		boolean rs = getPangram(st);
		if(rs==true)
		System.out.println("Entered string is pangram");
		else
			System.out.println("Entered string is not pangram");
	}

	private static boolean getPangram(String st) {
		if(st.length()<26)
			return false;
		/*
		int[] count = new int[26];
		for(int i=0; i<st.length(); i++) {
			char ch=st.charAt(i);
			if(ch>='A'&&ch<='Z')
				count[ch-65]++;
			else if(ch>='a'&&ch<='z') {
				count[ch-97]++;
			}
		}
		
		for(int i=0; i<26; i++) {
			if(count[i]==0)
				return false;
		}
		return true;
	}*/
		String str = st.toLowerCase();
		for(char ch='a'; ch<='z'; ch++) {
			if(str.indexOf(ch)==-1) 
				return false;
			}
		return true;
		}
}
