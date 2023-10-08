package arrays;

public class MergeArray {

	public static void main(String[] args) {
		int a[]= {1,2,3,4,5};
		int b[]= {6,7,8};
		int[] m=merge(a,b);
		System.out.println("New array :");
		for(int i=0; i<m.length; i++) {
		System.out.print(m[i]+" ");
	}
}

	private static int[] merge(int[] a, int[] b) {
		int[] c= new int[a.length + b.length];
		for(int i=0; i<a.length; i++) {
			c[i]=a[i];
		}
		for(int i=0; i<b.length; i++) {
			c[a.length+i]=b[i];
		}
		return c;
	}

}
