package arrays;

public class AddAnotherArraySpecifiedIndex {

	public static void main(String[] args) {
		int ar[]= {1,2,3,4,5,6};
		int br[]= {10, 20, 30};
		int cr[]=insertArray(ar, br, 3);
		for(int i=0; i<cr.length; i++) {
			System.out.print(cr[i]+" ");
		}
	}

	private static int[] insertArray(int[] ar, int[] br, int in) {
		if(in<0||in>ar.length) {
			System.out.println("Index not in range");
			return ar;
		}
		else { 
			int cr[]=new int [ar.length+br.length];
		for(int i=0; i<ar.length; i++) {
			if(i<in)
				cr[i]=ar[i];
			else
				cr[i+br.length]=ar[i];
				}
		for(int i=0; i<br.length; i++) {
			cr[in+i]=br[i];
			}
		return cr;
		}
	}
}
