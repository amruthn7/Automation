package arrays;

public class DeleteSpecifiedArray {

	public static void main(String[] args) {
		int ar[]= {1,2,3,4,5,6};
		int br[]= deleteArray(ar, 3);
		for(int i=0; i<br.length; i++) {
			System.out.print(br[i]+" ");
	}
}

	private static int[] deleteArray(int[] ar, int in) {
		if(in<0||in>=ar.length) {
			System.out.println("Index not in the range");
			return ar;
		}
		int br[]=new int[ar.length-1];
		for(int i=0; i<br.length; i++) {
			if(i<in)
				br[i]=ar[i];
			else
				br[i]=ar[i+1];
		}
		return br;
	}

}
