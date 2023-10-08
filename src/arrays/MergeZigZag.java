package arrays;

public class MergeZigZag {

	public static void main(String[] args) {
		int a[]= {1,2,3,4,5};
		int b[]= {6,7,8};
		int[] m=zigZag(a,b);
		System.out.println("ZigZag order :");
		for(int i=0; i<m.length; i++) {
		System.out.print(m[i]+" ");
	}
	}

	private static int[] zigZag(int[] a, int[] b) {
		int c[]= new int[a.length+b.length];
		int k=0, i=0;
		while (i<a.length && i<b.length) {
			c[k]=a[i];
			k++;
			c[k]=b[i];
			k++;
			i++;
		}
		while(i<a.length) {
			c[k]=a[i];
			k++;
			i++;
		}
		while(i<b.length) {
			c[k]=b[i];
			k++;
			i++;
		}
		return c;
	}

}
