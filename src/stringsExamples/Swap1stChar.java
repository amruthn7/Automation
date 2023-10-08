package stringsExamples;

import java.util.Scanner;

public class Swap1stChar {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter the String");
		String st = sc.nextLine();
		String rs=swapFirstChar(st);
		System.out.println(rs);
	}

	private static String swapFirstChar(String st) {
		char ch[]=st.toCharArray();
		int f=0;
		for(int i=0; i<ch.length; i++) {
			
			if(i==0 && ch[i]!=' ' || ch[i]!=' ' && ch[i-1]==' ' ) {
				f=i;
			}
			else if(i==ch.length-1 && ch[i]!=' ' ||  ch[i]!=' ' && ch[i+1]==' ') {
				char temp=ch[i];
				ch[i]= ch[f];
				ch[f]=temp;
			}
		}
		String str= new String(ch);
		return str;
	}

}
