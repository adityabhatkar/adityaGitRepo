package practiceCodingProblems;

public class OneInBinary {

	public static void main(String[] args) {
		 System.out.println(getOnesInBinary(10));
		 
		 int [] arr={1,5,6,6,6,7,7,10};
		 arr=mergeSort(arr, 0, arr.length-1);
		 for(int i=0; i<arr.length; i++){
			 System.out.print(arr[i]+" ");
		 }
	}
	
	public static int getOnesInBinary(int number){
		
		String binaryRep=Integer.toBinaryString(number);
		int sum=0;
		int length=binaryRep.length();
		for(int i=0; i<length; i++){
			if(binaryRep.charAt(i)=='1'){
				sum++;
			}
		}
		
		return sum;
		
		
	}
	
	public static int[] mergeSort(int[] arr, int first, int last){
		
		if(first<last){
			int split=(first+last)/2;
			mergeSort(arr, first, split);
			mergeSort(arr, split+1, last);
			merge(arr, first, split, last);
		}
		return arr;
	}
	
	public static int[] merge(int [] arr, int first, int split, int last){
		
		int n1=split-first+1;
		int n2=last-split;
		
		int [] left=new int[n1+1];
		int [] right=new int[n2+1];
		
		left[n1]=Integer.MAX_VALUE;
		right[n2]=Integer.MAX_VALUE;
		
		for(int i=0; i<n1; i++){
			left[i]=arr[first+i];
		}
		
		for(int j=0; j<n2; j++){
			right[j]=arr[split+j+1];
		}
		
		int i=0,j=0;
		for(int k=first; k<=last; k++){
			if(left[i]<=right[j]){
				arr[k]=left[i];
				i++;
			}
			else{
				arr[k]=right[j];
				j++;
			}
		}
		
		return arr;
	}

}
