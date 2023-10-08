package stringsExamples;


public class PrintInPattren {

	public static void main(String[] args) {  //A=65 a=97 0=48

		//Aa Bb Cc Dd.....
		for (char ch = 'A'; ch <='Z'; ch++) {
			System.out.print(ch +""+(char)(ch+32)+" ");
		}

		System.out.println();
		//A1 B2 C3 D4....
		int i=1;
		for (char alpha = 'A'; alpha <='Z'; alpha++) {
			System.out.print(alpha +""+i+" ");
			i++;
		}
		System.out.println();

		//input aabbbcccc  o/p a2b3c4

		String str="aabbbcccc";
		int count=0;
		for (int j = 0; j < str.length() ; j=j+count) {
			count=1;
			for (int k = j+1 ; k < str.length(); k++) {
				if(str.charAt(j) == str.charAt(k)) {
					count=count+1;
				}else {
					break;
				}
			}
			System.out.print(str.charAt(j)+ "" +count);
		}
	}
}


