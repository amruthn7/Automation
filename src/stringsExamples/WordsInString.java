package stringsExamples;

import java.util.Scanner;

public class WordsInString {

	public static void main(String[] args) {
		Scanner sc= new Scanner(System.in);
		System.out.println("Enter the String");
		String st = sc.nextLine();
		//int rs=getWords(st);
		//System.out.println(rs);
		getWords1(st);
	}

	public static int getWords(String st) {
		char ch[]= st.toCharArray();
		int count=0;
		for(int i=0; i<ch.length; i++) {
			if(i==0 && ch[i]!=' ' || ch[i]!=' ' && ch[i-1]==' ') {
				count++;
			}
		}
		return count;
	}
	
	public static void getWords1(String st) {
		
		 String[] s1=st.split(" ");
		 int i=s1.length;
		System.out.println(i);
		 
	}

}
