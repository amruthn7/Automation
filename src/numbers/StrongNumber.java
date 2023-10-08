package numbers;

import java.util.Scanner;

public class StrongNumber {

	public static void main(String[] args) {
			Scanner sc= new Scanner(System.in);
			System.out.println("Enter the number");//7,13,19
			int n = sc.nextInt();
			boolean rs=getStrongNumber(n);
			if(rs==true) {
				System.out.println(n+" is a Strong number");
			}
			else {
				System.out.println(n+" is not a Strong number");
			}
		}

	private static boolean getStrongNumber(int n) {
		int sum =0, temp=n;
	do{
		int d=n%10;
		sum=sum+getFact(d);
		n=n/10;
			}
	while(n!=0);
			 return sum==temp;
	}

	private static int getFact(int n) {
		int fact=1;
		for(int i=n; i>=1; i--) {
			fact=fact*i;
		}
		return fact;
	}
	}

