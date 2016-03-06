package search;

import java.util.Random;
import sorting.Sorter;
import myAVLTree.Node;

public class Searcher {

	public static int binarySearch(Comparable [] arr, Comparable item, int startIndex, int endIndex){

		if(startIndex>=endIndex){
			return -1;
		}

		int midIndex=(startIndex+endIndex)/2;

		if(item.equals(arr[midIndex])){
			return midIndex;
		}

		if(item.compareTo(arr[midIndex])<=0){
			endIndex=midIndex;
			return binarySearch(arr, item, startIndex, endIndex);
		}
		else{
			startIndex=midIndex+1;
			return binarySearch(arr, item, startIndex, endIndex);
		}
		
	}
	
	public static Node bstSearcher(Node treeNode, Node keyNode){
		
		if(treeNode==null || treeNode.equals(keyNode)){
			return treeNode;
		}
		if(keyNode.compareTo(treeNode)<=0){
			return bstSearcher(treeNode.getLeftChild(), keyNode);
		}
		else{
			return bstSearcher(treeNode.getRightChild(), keyNode);
		}
	}

	public static void main(String[] args){

		Element[] arr=new Element[10];
		for(int i=0; i<10; i++){
			arr[i]=new Element(getRandomInteger(1,10));
		}

		int item=getRandomInteger(1,10);
		System.out.println("searching "+ item);

		arr=(Element [])Sorter.insertionSort(arr);
		System.out.println("sorted array");
		for(int i=0; i<10; i++){
			System.out.println(" i: "+i+" key: "+arr[i].getKey());
		}
		System.out.println();
		int itemLocation=binarySearch(arr, new Element(item), 0, arr.length-1);
		System.out.println("itemLocation "+itemLocation);
	}

	public static int getRandomInteger(int min, int max){
		Random rand=new Random();
		return rand.nextInt((max-min)+1)+min;
	}

}


