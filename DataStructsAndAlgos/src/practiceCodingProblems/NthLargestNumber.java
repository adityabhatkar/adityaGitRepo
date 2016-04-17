package practiceCodingProblems;

import java.util.Arrays;

public class NthLargestNumber {


	public static void main(String[] args) {
		int [] arr={1,5,6,6,6,7,7,10};
		System.out.println(getNthLargest(arr, 7));
	}
	
	//can be done more efficiently with quick select/heap
	public static int getNthLargest(int [] arr, int n){
		
		Arrays.sort(arr);
		int count=1;
		int largest=arr[arr.length-1];
		for(int i=arr.length-2; count<n && i>=0 ; i--){
			if(arr[i]!=arr[i+1]){
				largest=arr[i];
				count++;
			}
			
		}
		return largest;
		
	}
}
