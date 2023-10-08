package arrays;

public class ReverseArray {

	public static void main(String[] args) {
		int ar[]= {10,20,30,40,50};
		System.out.println("Before reverse:");
		for(int i=0; i<=ar.length-1; i++) {
			System.out.print(ar[i]+" ");
		}
		getReverse(ar);
		System.out.println("\nAfter reverse:");
		for(int i=0; i<=ar.length-1; i++) {
			System.out.print(ar[i]+" ");
		}
		getReverse(ar);
	}

	private static void getReverse(int[] x) {
		int i=0, j=x.length-1;
		while(i<j) {
			int temp=x[i];
			x[i]=x[j];
			x[j]=temp;
			i++;
			j--;
		}
	}

}
