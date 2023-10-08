package numbers;

import java.util.Scanner;

public class HappyNumber {
	private static boolean getHappyNumber(int n) {
		while(n>9) {
			int sum=0;
			do {
				int d=n%10;
				sum=sum+d*d;
				n=n/10;
			}while(n!=0);
			n=sum;
		}
		return n==1||n==7;
	}
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter the number");//7,13,19
		int n = sc.nextInt();
		boolean rs=getHappyNumber(n);
		if(rs==true) {
			System.out.println(n+" is a Happy number");
		}
		else {
			System.out.println(n+" is not a Happy number");
		}
	}
}
