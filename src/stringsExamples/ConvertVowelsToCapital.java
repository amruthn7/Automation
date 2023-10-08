package stringsExamples;

import java.util.Scanner;

public class ConvertVowelsToCapital {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter the String");
		String st = sc.nextLine();
		String rs=convertVowels(st);
		System.out.println(rs);
	}

	private static String convertVowels(String st) {
		String v="AEIOUaeiou";
		char ch[]=st.toCharArray();
		for(int i=0; i<ch.length; i++) {
			if(ch[i]>='a'&&ch[i]<'z')
				{
				if(v.indexOf(ch[i])!=-1)
					ch[i]=(char) (ch[i]-32);
				}
			else if(ch[i]>='A'&&ch[i]<'Z')
				{
				if(v.indexOf(ch[i])==-1)
					ch[i]=(char)(ch[i]+32);
				}
			}
		String str=new String(ch);
		return str;
		}
	}
