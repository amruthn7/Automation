package stringsExamples;

import java.util.Scanner;

public class PalindromeString {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the string");
		String st = sc.nextLine();
		boolean rs=isPalindrome(st);
		if(rs==true) 
			System.out.println("Given string "+st+" is a palindrome");
		else
			System.out.println("Given string "+st+" is not a palindrome");
	}

	private static boolean isPalindrome(String st) {
		//int i=0, 
				int	j=st.length()-1;
		//while(i<j) {
		for(int i=0; i<j; i++) {
			if(st.charAt(i)!=st.charAt(j))
				return false;
			//i++;
			j--;
		}
		return true;
		
	}

}
