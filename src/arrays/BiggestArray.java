package arrays;

import java.util.Scanner;

public class BiggestArray {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the size of array");
		int n=sc.nextInt();
		int ar[]= new int[n];
		System.out.println("Enter the " +n+ " values");
		for(int i=0; i<n; i++) {
			ar[i]=sc.nextInt();
	}
		int big = getBigArray(ar);
		System.out.println("Biggest element in array is "+big);
		int small= getSmallArray(ar);
		System.out.println("Smallest element in array is "+small);

}

	private static int getSmallArray(int[] ar) {
		int small=ar[0];
		for(int i=0; i<ar.length; i++ ) {
			if(ar[i]<small)
				small=ar[i];
		}
		return small;
	}

	private static int getBigArray(int[] x) {
		int big=x[0];
		for(int i=0; i<x.length; i++) {
			if(x[i]>big)
			big=x[i];
		}
		return big;
	}
}
