package numbers;

import java.util.Scanner;

public class BiggestNumber {
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter the first no");
		int x = sc.nextInt();
		System.out.println("Enter the second no");
		int y = sc.nextInt();
		System.out.println("Enter the third no");
		int z = sc.nextInt();
		//System.out.println("Enter the fourth no");
		//int v = sc.nextInt();
		int rs= getBiggest(x, y, z);
		System.out.println("Biggest is "+rs);
		
		/*int big=(x>y && x>z && x>v)? x: ((y>z && y>v)? y: (z>v)?z:v);
		System.out.println("Biggest is " +big);
		/*
		int big=(x>y && x>z)?x:(y>z)?y:z;
		System.out.println("Biggest is " +big);
		/*
		 if(x>y && x>z) {
		 
			System.out.println(x+" is the biggest no");
		}
		else if(y>z){
			System.out.println(y+" is the biggest no");
		}
		else {
			System.out.println(z+" is the biggest no");
		}*/
		
		/*by using simple if
		int big=x;
		if(y>big);
		big=y;
		if(z>big);
		big=z;
		System.out.println("Biggest is " +big);
		*/
		
		/*using condition operator
		int big=(x>y)?x:y;
		big=(z>big)?z:big;
		System.out.println("Biggest is " +big);
		*/
	}

	private static int getBiggest(int x, int y, int z) {
		 if(x>y && x>z) {
			 return x;
			}
			else if(y>z){
				return y;
			}
			else {
				return z;
			}
	}
}
