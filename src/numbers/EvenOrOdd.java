package numbers;

import java.util.Scanner;

public class EvenOrOdd {
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter the no");
		int n = sc.nextInt();
		
		//using if :
		  if(n%2==0) {
			System.out.println("Entered no is even");
		}else {
			System.out.println("Entered no is odd");
		}
		
		/*without using % operator:
		if(n/2*2==n) {
			System.out.println(n+" is even");
		}else {
			System.out.println(n+" is odd");
		}*/
		
		/* using condition:
		String st = (n%2==0)?"Even":"Odd";
		 System.out.println(n+" is "+st);
		 */
		/* using switch:
		switch(n%2) {
		case 0: System.out.println("Even");
		break;
		case 1: System.out.println("Odd");
		}
		*/
		
		/* using method:
		  boolean eo = normalEO(n);
		if(eo==true) {
			System.out.println("Entered no is even");
		}else {
			System.out.println("Entered no is odd");
		}
	}

	private static boolean normalEO(int n) {
		if(n%2==0) {
			return true;
		}else {
		return false;
		}
	}*/
	}
}
