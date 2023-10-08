package arrays;

import java.util.Scanner;

public class PrimeNumArray {
	
	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the size of array");
		int n=sc.nextInt();
		int ar[]= new int[n];
		System.out.println("Enter the " +n+ " values");
		for(int i=0; i<n; i++) {
			ar[i]=sc.nextInt();
			}
		int count= countPrimeNum(ar);
		System.out.println("Prime numbers present in user entered array is "+count);
	}

	private static int countPrimeNum(int[] n) {
		int count=0;
		for(int i=0; i<n.length; i++) {
			boolean rs=isPrime(n[i]);
			 if(rs==true)
			 count++;
		}
		return count;
	}

	private static boolean isPrime(int x) {
	for(int i=2; i<x/2; i++) {
			if(x%i==0)
			return false;
		}
		return true;
		}
	}
