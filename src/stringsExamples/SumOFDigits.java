package stringsExamples;

import java.util.Scanner;

public class SumOFDigits {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the string");
		String st = sc.nextLine();
		int rs=sumOfDigits(st);
		System.out.println("Sum OF Digits :"+rs);
	}

	private static int sumOfDigits(String st) {
		int sum=0;
		for(int i=0; i<st.length(); i++) {
			char ch=st.charAt(i);
			if(ch>='0'&& ch<='9') {
				sum=sum+ch-48;
			}
		}
		return sum;
		
	}

}
