package arrays;

public class SearchArray {

	public static void main(String[] args) {
		int ar[]= {45,67,58,68,85,89};
		int index=searchArray(ar,58);
		if(index==-1)
		System.out.println("Element is not present");
		else
		System.out.println("Element is present in the index no: " +index);
	}

	public static int searchArray(int[] ar, int ele) {
		for(int i=0; i<ar.length; i++) {
			if(ar[i]==ele)
				return i;
		}
		return -1;
	}


}
