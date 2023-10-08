package stringsExamples;

import java.util.Scanner;
//count vowels, consonates, Capital, Small, digits, sp digits 
public class CountString {

	public static void main(String[] args) {
		Scanner sc=new Scanner(System.in);
		System.out.println("Enter the string");
		String st = sc.nextLine();
		int vc=0, cc=0 , uc=0, lc=0, dc=0, sp=0;
		for(int i=0; i<st.length(); i++) {
			char ch = st.charAt(i);
			if(ch>='A'&& ch<='Z') {
				uc++;
				if(ch=='A'||ch=='I'||ch=='E'||ch=='O'||ch=='U')
					vc++;
				else
					cc++;
			}
			else if(ch>='a'&& ch<='z') {
				lc++;
				if(ch=='a'||ch=='e'||ch=='i'||ch=='o'||ch=='u')
					vc++;
				else
					cc++;
			}
			else if(ch>='0'&&ch<='9'){
				dc++;
			}
			else
				sp++;
		}
		System.out.println("No of vowels letters: "+vc);
		System.out.println("No of consonates letters: "+cc);
		System.out.println("No of capital letters: "+uc);
		System.out.println("No of Small letters: "+lc);
		System.out.println("No of digits letters: "+dc);
		System.out.println("No of special digits  letters: "+sp);
		
		 
	}

}
