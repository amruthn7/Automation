package stringsExamples;

public class MainStr {

	public static void main(String[] args) {
		String s1="JAVA";
		String s2=new String("JAVA");
		System.out.println("s1:"+s1);
		System.out.println("s1:"+s2);
		System.out.println(s1.length());
		System.out.println(s2.length());
		System.out.println(s1.charAt(0));
		System.out.println(s2.charAt(3));
		System.out.println(s1.indexOf('V'));
		System.out.println(s2.indexOf('A'));
		System.out.println(s1.indexOf('a'));// if it is not equals it will return -1
		
		//string to char array
		char ch[] = s1.toCharArray();
		for(char i:ch) {
			System.out.print(i+" ");
		}
		System.out.println(" ");
		//char array to string
		char ar[]= {'A','m','r','u','t','h'};
		String str=new String(ar);
		System.out.println(str);
	}
}
