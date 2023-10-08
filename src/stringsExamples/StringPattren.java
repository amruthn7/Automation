package stringsExamples;

public class StringPattren {

	public static void main(String[] args) {

		
		String st = "a2b2c2a1b1c1"; // output==> aabbccabc
        String letters[] = st.split("[0-9]+");
        String numbers[] = st.split("[a-z]+");
        int num[] = new int[numbers.length];
        int count = 0;
        for(int i = 0 ; i < numbers.length ; i++) {
            if(!numbers[i].equals("")) {
                num[count] = Integer.parseInt(numbers[i]);
                count++;
            }
        }
        for (int i = 0; i < letters.length; i++) {
			for (int j = 0; j < num[i]; j++) {
				System.out.print(letters[i]);
			}
		}
	}

}
