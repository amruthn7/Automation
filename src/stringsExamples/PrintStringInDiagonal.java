package stringsExamples;


public class PrintStringInDiagonal {

	public static void main(String[] args) {

		String s="public";
		for (int i = 0; i < s.length(); i++) {
			
			for (int j = 0; j < s.length(); j++) {
				if(i==j) {
					System.out.print(s.charAt(i));
				}
				else {
				System.out.print(" ");
			}
		}
			System.out.println();
  }
 }
}
