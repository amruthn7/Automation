package stringsExamples;

import java.util.Scanner;

public class StrToLowerCase {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the string");
		String st = sc.nextLine();
		String rs=getLowerCase(st);
		System.out.println(rs);
	}

	private static String getLowerCase(String st) {
		//String rs = st.toLowerCase();
		char ch[]=st.toCharArray();
		for(int i=0; i<ch.length; i++) {
			if(ch[i]>='A'&&ch[i]<='Z')
				ch[i]=(char) (ch[i]+32);
		}
		String rs =new String (ch); 
		return rs;
	}

}
