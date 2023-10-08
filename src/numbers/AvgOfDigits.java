package numbers;

import java.util.Scanner;

public class AvgOfDigits {
	private static double getAvg(int n) {
		int count=0;
		double sum = 0.0;
		do {
			count++;
			sum=sum+n%10;
			n=n/10;
		}while(n!=0);
		return sum/count;
	}
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter the number");//
		int n = sc.nextInt();
		double rs=getAvg(n);
		System.out.println("Average of the given numner "+n+" is "+rs);
	}
}
