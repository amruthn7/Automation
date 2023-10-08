package numbers;

import java.util.Scanner;

public class DiffBtwBigSmall {
	private static int getdiff(int n) {
		int big,small;
		big=small=n%10;
		System.out.println(big);
		do {
			int d=n%10;
			if(d>big)
				big=d;
			if(d<small)
				small=d;
				n=n/10;
		}while(n!=0);
		return big-small;
	}

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter the number");//
		int n = sc.nextInt();
		int rs=getdiff(n);
		System.out.println("Difference btw biggest and smallest number is "+rs);
	}
}
