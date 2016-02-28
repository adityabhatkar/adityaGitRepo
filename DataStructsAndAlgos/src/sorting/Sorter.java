package sorting;

public class Sorter {


	private static final int maxValue=Integer.MAX_VALUE;

	public static void main(String[] args) {

		int [] arr={};
		//arrays are called by ref
		//insertionSort(arr);
		//mergeSort(arr, 0, arr.length-1);
		heapSort(arr);
		for(int i=0; i<arr.length; i++){
			System.out.print(arr[i]+" ");
		}

	}

	public static int [] heapSort(int [] arr){

		/*
		 * build max heap
		 * max element is A[0]
		 * swap A[0] with A[n]
		 * Discard last element last element for next round
		 * maxHeapify on new root
		 * 
		 * theta(nlgn)
		 * in place algo
		 * 
		 * */
		
		buildMaxHeap(arr);

		int lastElementIndex=arr.length-1;
		int maxElement=0;

		while(lastElementIndex>=0){
			maxElement=arr[0];
			arr[0]=arr[lastElementIndex];
			arr[lastElementIndex]=maxElement;
			maxHeapify(arr, 0, lastElementIndex);
			lastElementIndex--;
		}
		
		return arr;
	}

	public static void maxHeapify(int [] arr, int i, int lastElementIndex){

		int leftChildIndex=2*i+1;
		int rightChildIndex=2*i+2;

		int temp=0;		
		int largestElementIndex=i;		
		//careful while getting the largest index among left, self and right.
		if(leftChildIndex<lastElementIndex && arr[leftChildIndex]>arr[i]){
			largestElementIndex=leftChildIndex;
		}
		if(rightChildIndex<lastElementIndex && arr[rightChildIndex]>arr[largestElementIndex]){
			largestElementIndex=rightChildIndex;
		}

		if(largestElementIndex!=i){
			temp=arr[i];
			arr[i]=arr[largestElementIndex];
			arr[largestElementIndex]=temp;

			maxHeapify(arr, largestElementIndex, lastElementIndex);
		}

	}

	public static int[] buildMaxHeap(int [] arr){

		for(int i=arr.length/2; i>=0; i--){
			maxHeapify(arr, i, arr.length);
		}		
		return arr;
	}

	public static int[] mergeSort(int [] arr, int first, int last){

		/*
		 *theta(nlgn)
		 *
		 *split input into two parts till only two elements are left
		 *sort the two elements and work upwards
		 *use two finger algo to compare sorted arrays
		 *works for max int value
		 * 
		 * */

		int split=0;
		if(first<last){
			split=(first+last)/2;
			mergeSort(arr, first, split);
			mergeSort(arr, split+1, last);
			merge(arr, first, split, last);
		}

		return arr;
	}

	public static int [] merge(int [] arr, int first, int split, int last){

		int n1=split-first+1;
		int n2=last-split;

		int [] leftArr=new int[n1+1];
		int [] rightArr=new int[n2+1];

		int i=0;
		int j=0;		
		for(; i<n1; i++){
			leftArr[i]=arr[first+i];
		}
		for(; j<n2; j++){
			rightArr[j]=arr[split+j+1];
		}

		leftArr[leftArr.length-1]=maxValue;
		rightArr[rightArr.length-1]=maxValue;

		i=0;
		j=0;
		for(int k=first; k<=last; k++){
			if(leftArr[i]<=rightArr[j]){
				arr[k]=leftArr[i];
				i++;
			}
			else{
				arr[k]=rightArr[j];
				j++;
			}
		}

		return arr;		
	}
	



	public static int[] insertionSort(int [] arr){

		/*
		 * for i in 1 to n-1
		 * 		insert A[i] into sorted Array A[0:i-1]
		 * 		by pairwise swap down to correct position
		 *  
		 *  in place algo
		 *  	does not use any extra space
		 *  theta(n^2)
		 *  
		 * */
		int key=0;
		int j=0;
		int temp=0;
		for(int i=1; i<arr.length; i++){
			key=arr[i];
			j=i-1;
			while (j>=0 && key<arr[j]){

				temp=arr[j];
				arr[j]=key;
				arr[j+1]=temp;

				j--;
			}
		}
		return arr;

	}
	
	public static Comparable[] insertionSort(Comparable [] arr){

		/*
		 * for i in 1 to n-1
		 * 		insert A[i] into sorted Array A[0:i-1]
		 * 		by pairwise swap down to correct position
		 *  
		 *  in place algo
		 *  	does not use any extra space
		 *  theta(n^2)
		 *  
		 * */
		Comparable key=null;
		int j=0;
		Comparable temp=null;
		for(int i=1; i<arr.length; i++){
			key=arr[i];
			j=i-1;
			//while (j>=0 && key<arr[j]){
			while(j>=0 && (key.compareTo(arr[j])<=0)){

				temp=arr[j];
				arr[j]=key;
				arr[j+1]=temp;

				j--;
			}
		}
		return arr;

	}
}
