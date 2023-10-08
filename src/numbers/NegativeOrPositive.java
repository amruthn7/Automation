package numbers;

import java.util.Scanner;

public class NegativeOrPositive {
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter the no");
		int n = sc.nextInt();
		if(n<0) {
			System.out.println("number is negative");
		}
		else {
			System.out.println("number is positive");
		}
	}
}
