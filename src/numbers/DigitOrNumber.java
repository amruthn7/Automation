package numbers;

import java.util.Scanner;

public class DigitOrNumber {
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter the no");
		int n = sc.nextInt();
		if(n>9||n<-9) {
			System.out.println(n+"is a number");
		}else {
			System.out.println(n+"is a digit");
		}
	}
}
