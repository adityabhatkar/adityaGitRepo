//aditya bhatkar 800887086
package myProject;



import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class LinearLCS {

	public static void main(String[] args) {

		String[] inputSequences=null;
		
		try {
			//get required input from files which are given as arguments 
			inputSequences = MyUtility.getInput(args);
		} catch (FileNotFoundException e) {
			System.out.println("Unable to read/find files");			
			return;
			
		}catch (InvalidInputException e) {
			System.out.println(e.getMessage());			
			return;		
		}	
		try {
			//validate input
			MyUtility.validateInput(inputSequences);
		} catch (InvalidInputException e) {			
			System.out.println(e.getMessage());
			return;
		
		}
		
		//init stack
		List<String> stack=new ArrayList<String>();
		
		//calculate LCS
		getLCS(inputSequences[0], inputSequences[1], stack);
		
		//print output
		for(String symbol : stack){
			System.out.print(symbol+" ");
		}
		
	}

	//input: left and top strings, stack
	//function: recursive method to calculate the LCS 
	public static void getLCS(String string1, String string2, List<String> stack){
		
		//base case
		if(string1.length()==1){			
			String symbol=String.valueOf(string1.charAt(0));
			for(int i=0; i<string2.length(); i++){				
				if(string1.charAt(0)==string2.charAt(i)){
					//push symbol to stack
					stack.add(symbol);				
				}				

			}

		}
		//base case
		else if(string2.length()==1){

			String symbol=String.valueOf(string2.charAt(0));
			for(int i=0; i<string1.length(); i++){				
				if(string2.charAt(0)==string1.charAt(i)){				
					//push the symbol to stack
					stack.add(symbol);				
				}
			}
		}		
		else{
			//calculate horizontal split
			int horizontalSplit=(string1.length()/2)-1;
			//calculate vertical split
			int verticalSplit=calVerticalSplit(string1, string2, horizontalSplit);
			
			//break left string 
			String string1Front=string1.substring(0, horizontalSplit+1);
			//break top string
			String string2Front=string2.substring(0, verticalSplit);			

			//break left string
			String string1Back=string1.substring(horizontalSplit+1, string1.length());
			//break top string 
			String string2Back=string2.substring(verticalSplit, string2.length());		

			//recrsive calls
			getLCS(string1Front, string2Front, stack);
			getLCS(string1Back, string2Back, stack);
		}

	}
	//input:left and top string, horizontal split
	//returns the vertical split index
	private static int calVerticalSplit(String inputStr1, String inputStr2, int horizontalSplit) {

		//get the two arrays to calculate vertical split
		int [] topArray=getTopArray(inputStr1,inputStr2, horizontalSplit); 
		int [] bottomArray=getBottomArray(inputStr1,inputStr2, horizontalSplit);

		//find minimum sum 
		int minSum=topArray[0]+bottomArray[0];
		int minIndex=0;
		for(int i=0; i<topArray.length; i++){
			if((topArray[i]+bottomArray[i])<=minSum){			
				minSum=topArray[i]+bottomArray[i];
				minIndex=i;
			}
		}		
		//return vertical split index
		return minIndex;

	}
	
	//input:left and top string, horizontal split
	//returns one of the arrays after series of calculations on bottom half of the table 
	private static int[] getBottomArray(String inputStr1, String inputStr2, int horizontalSplit) {

		int rowCounter=1;
		int rowIndex=inputStr2.length()-1;	
		int str1Index=inputStr1.length()-1;
		int str2Index=inputStr2.length()-1;
		int [] lowerRow=new int[inputStr2.length()+1];
		int [] currentRow=new int[inputStr2.length()+1];
		//init row
		for(int i=0; i<lowerRow.length; i++){
			lowerRow[i]=lowerRow.length-1-i;
		}	

		//left string index, continue from last symbol till the horizontal split+1 index of the left string 
		while(rowCounter<=(inputStr1.length()-(horizontalSplit+1))){

			str2Index=inputStr2.length()-1;
			currentRow[inputStr2.length()]=rowCounter;

			//top string index
			for(rowIndex=inputStr2.length()-1; rowIndex>=0; rowIndex--){

				//match found
				if(inputStr1.charAt(str1Index)==inputStr2.charAt(str2Index)){
					//get diagonally right symbol from lower row
					currentRow[rowIndex]=lowerRow[rowIndex+1];
				}
				else{
					//right is smaller or equal to lower
					if(currentRow[rowIndex+1]<=lowerRow[rowIndex]){
						//get right symbol+1
						currentRow[rowIndex]=currentRow[rowIndex+1]+1;
					}
					else{			
						//lower is smaller
						//get lower symbol +1 
						currentRow[rowIndex]=lowerRow[rowIndex]+1;
					}
				}
				//decrement top string index
				str2Index--;

			}
			//decrement left string index
			str1Index--;
			rowCounter++;

			//swap lower row with current till half of left string is reached
			if(str1Index>=inputStr1.length()/2){
				int [] temp=lowerRow;
				lowerRow=currentRow;
				currentRow=temp;
			}


		}

		return currentRow;
	}


	//input:left and top string, horizontal split
	//returns one of the arrays after series of calculations on top half of the table
	private static int[] getTopArray(String inputStr1, String inputStr2, int horizontalSplit) {

		int rowCounter=1;
		int rowIndex=0;	
		int str1Index=0;
		int str2Index=0;
		int [] upperRow=new int[inputStr2.length()+1];
		int [] currentRow=new int[inputStr2.length()+1];
		//init row 
		for(int i=0; i<upperRow.length; i++){
			upperRow[i]=i;
		}	

		//left string index continue from first symbol till horizontal split index of the left string
		while(rowCounter<=horizontalSplit+1){
			
			str2Index=0;
			currentRow[0]=rowCounter;
			//top string index
			for(rowIndex=1; rowIndex<currentRow.length; rowIndex++){

				//match found
				if(inputStr1.charAt(str1Index)==inputStr2.charAt(str2Index)){
					//get diagonal left symbol 
					currentRow[rowIndex]=upperRow[rowIndex-1];
				}
				else{
					//left is smaller or equal to top, copy left symbol+1
					if(currentRow[rowIndex-1]<=upperRow[rowIndex]){
						
						currentRow[rowIndex]=currentRow[rowIndex-1]+1;
					}
					else{
						//top is smaller, copy top symbol+1
						currentRow[rowIndex]=upperRow[rowIndex]+1;
					}
				}
				//increment top string index
				str2Index++;

			}
			//increment left string index
			str1Index++;
			rowCounter++;

			//swap upper row with current till half of left string is reached
			if(str1Index<inputStr1.length()/2){
				int [] temp=upperRow;
				upperRow=currentRow;
				currentRow=temp;
			}
		}

		return currentRow;
	}


}


