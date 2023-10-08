package arrays;

import java.util.Scanner;

public class ForwardAndReverse {
	
		public static void main(String[] args) {
			
			Scanner sc= new Scanner(System.in);
			System.out.println("Enter the size of an array");
			int n=sc.nextInt();
			int ar[]= new int[n];
			System.out.println("Enter the " +n+ " values");
			for(int i=0; i<n; i++) {
				ar[i]=sc.nextInt();
			}
			System.out.println("User entered array in forward direction");
			for(int i=0; i<n; i++) {
				System.out.println(ar[i]+"");
			}
			System.out.println("User entered array in reverse direction");
			for(int i=n-1; i>=0; i--) {
				System.out.println(ar[i]+"");
			}
		}
	}
