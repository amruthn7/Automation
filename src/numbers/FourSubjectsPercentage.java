package numbers;

import java.util.Scanner;

public class FourSubjectsPercentage {
	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter the subject marks");
		int p = sc.nextInt();
		int c = sc.nextInt();
		int m = sc.nextInt();
		int b = sc.nextInt();
		String rs = getResult(p,c,m,b);
		System.out.println(rs);
	}

	private static String getResult(int p, int c, int m, int b) {
		if(p>100||c>100||m>100||b>100) 
			return "Invalid Input";
		else if(p<35||c<35||m<35||b<35) 
			return "Fail";
		else {
			double per=((p+c+m+b)/4.0);
			if(per>=85) {
				return "Distinction";}
			else if(per>=60) {
				return "First class";}
			else if(per>=50) {
				return "Second class";}
			else {
				return "Pass";
			}
		}
		
	}
}
