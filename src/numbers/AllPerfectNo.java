package numbers;

import java.util.Scanner;

public class AllPerfectNo {
	
	private static int getAllPerfect(int n) {
		int sum=0;
		for(int i=1; i<=n/2; i++) {
			if(n%i==0)
				sum=sum+i;
		}
		return sum;
	}
	
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter the number");
		int n = sc.nextInt();
		for(int i=0; i<=n; i++) {
			if(i==getAllPerfect(i)) {
				System.out.println(i+"");
			}
		}
	}
}

