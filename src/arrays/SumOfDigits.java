package arrays;

public class SumOfDigits {

	public static void main(String[] args) {
		int ar[]= {45,67,88,43,56,23};
		System.out.println("Before update:");
		for(int i=0; i<=ar.length-1; i++) {
			System.out.print(ar[i]+" ");
		}
		getUpdate(ar);
		System.out.println("\nAfter update:");
		for(int i=0; i<=ar.length-1; i++) {
			System.out.print(ar[i]+" ");
		}
		getUpdate(ar);
	}

	private static void getUpdate(int[] x) {
		for(int i=0; i<x.length; i++) {
			int sum=0;
			while(x[i]!=0) {
				sum=sum+x[i]%10;
				x[i]=x[i]/10;
			}
			x[i]=sum;
		}
	}

}
