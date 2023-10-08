package numbers;

import java.util.Scanner;
//print all the Palindrome no within the user given no
public class AllPalindromeNo {
	private static int getAllPalindrome(int n) {
		int reverse=0;
		while(n>0) {
			reverse=reverse*10+n%10;
			n=n/10;
		}
		return reverse;
	}
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter the number");
		int n = sc.nextInt();
		for(int i=1; i<=n; i++) {
			if(i==getAllPalindrome(i)) {
				System.out.println(i+"");
			}
		}
	}

	

}
