package numbers;

import java.util.Scanner;
//print all the prime no within the user given no
public class AllPrimeNumber {
	private static boolean getAllPrime(int n) {
		if(n==0||n==1)
			return false;
		for(int i=2; i<n; i++) {
			if(n%i==0)
				return false;
		}
		return true;
	}
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter the number");
		int n = sc.nextInt();
		//int n=100;
		for(int i=1; i<=n; i++) {
			if(getAllPrime(i)) {
				System.out.println(i+"");
			}
		}
	}
}
