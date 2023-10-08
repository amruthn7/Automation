package arrays;

import java.util.Scanner;

public class SumOfArray {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the size of array");
		int n=sc.nextInt();
		int ar[]= new int[n];
		System.out.println("Enter the " +n+ " values");
		for(int i=0; i<n; i++) {
			ar[i]=sc.nextInt();
		}
		int sum=sumOfArray(ar);
		System.out.println("Sum of the given array elements is "+sum);
		System.out.println("Sum of the given array elements is "+sum/n);
	}

	private static int sumOfArray(int[] ar) {
		int total = 0;
		for(int i=0; i<ar.length; i++) {
			total=total+ar[i];
		}
		return total;
	}

}
