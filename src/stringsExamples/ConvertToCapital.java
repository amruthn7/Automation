package stringsExamples;

import java.util.Scanner;

public class ConvertToCapital {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter the String");
		String st = sc.nextLine();
		String rs=convertToCap(st);
		System.out.println(rs);
	}

	private static String convertToCap(String st) {
		char ch[] = st.toCharArray();
		for(int i=0; i<ch.length; i++) {
			if(ch[i]>='a' && ch[i]<='z') {
				ch[i]=(char)(ch[i]-32);
			}
		}
		String str=new String(ch);
		return str;
	}

}
