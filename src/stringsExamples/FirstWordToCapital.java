package stringsExamples;

import java.util.Scanner;

public class FirstWordToCapital {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter the String");
		String st = sc.nextLine();
		String rs=convertCap(st);
		System.out.println(rs);
	}

	private static String convertCap(String st) {
		char ch[]=st.toCharArray();
		for(int i=0; i<ch.length; i++) {
			if(i==0 && ch[i]!=' ' || ch[i]!=' ' && ch[i-1]==' ')
			{
				if(ch[i]>='a'&& ch[i]<'z')
					ch[i]=(char) (ch[i]-32);
			}
			else if(ch[i]>='A'&& ch[i]<'Z')
					ch[i]=(char) (ch[i]+32);
		}
		String cap=new String(ch);
		return cap;
	}

}
