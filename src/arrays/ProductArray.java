package arrays;

import java.util.Scanner;

public class ProductArray {
	
	public static void main(String[] args) {
	Scanner sc=new Scanner(System.in);
	System.out.println("Enter the size of array");
	int n=sc.nextInt();
	int ar[]= new int[n];
	System.out.println("Enter the " +n+ " values");
	for(int i=0; i<n; i++) {
		ar[i]=sc.nextInt();
		}
	int p=getProduct(ar);
	System.out.println("Product of Array elements is "+p);
	}

	private static int getProduct(int[] ar) {
		int product=1;
		for(int i=0; i<ar.length; i++) {
			product=product*ar[i];
		}
		return product;
	}
}
