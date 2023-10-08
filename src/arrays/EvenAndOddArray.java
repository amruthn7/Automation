package arrays;

public class EvenAndOddArray {

	public static void main(String[] args) {
		int ar[]= {45,67,88,43,56,23};
		int ct[]=countEO(ar);
		System.out.println("Even numbers in array elements is "+ct[0]);
		System.out.println("Odd numbers in array elements is "+ct[1]);
	}

	private static int[] countEO(int[] ar) {
		int ec=0, oc=0;
		for(int i=0; i<ar.length; i++) {
			if(ar[i]%2==0) 
				ec++;
			else 
				oc++;
		}
		int count[]= {ec,oc};
		return count;
	}

}
