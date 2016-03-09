//aditya bhatkar 800887086
package myProject;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//calculates LCS by storing complete table
public class TableLCSCalculator {

	static String inputStr1=null; //stores left string
	static String inputStr2=null; //stores top string
	static int [][] table=null; //table to store values
	static List<String> stack; //stack to store LCS
	static int numberOfDeletions=0;
	static int numberOfRows=0;
	static int numberOfColumns=0;
	//static float normalizedEditDistance=0;

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

		

		//initialize table and stack
		init(inputSequences);
		
		//calculate table
		buildTable();
		
		//to print table, commented for now
		//printTable();
		
		//to Calculate NED, commented for now
		//calNED();
		
		
		//build the LCS stack 
		buildStack();
		
		//pop the stack, print the LCS
		getOutput();

	}
	
	//print output
	private static void getOutput() {
		
		//input are added in reverse order of the actual LCS hence reverse the ARRAYLIST
		Collections.reverse(stack);
		for(String symbol : stack){
			System.out.print(symbol+" ");
		}	
		
	}
	
	//build the LCS stack from the stored table
	private static void buildStack() {
		
		int rowIndex=numberOfRows-1;		
		int columnIndex=numberOfColumns-1;
		

		//calculate till left or top boundary is not reached 
		while(rowIndex!=0 && columnIndex!=0){
			
				//match found
				if(inputStr1.charAt(rowIndex-1)==inputStr2.charAt(columnIndex-1)){
					
					//push symbol to stack					
					String symbol=String.valueOf(inputStr1.charAt(rowIndex-1));
					stack.add(symbol);
					
					// move above diagonally
					rowIndex=rowIndex-1;
					columnIndex=columnIndex-1;
					
				}
				else{
					//left is smaller or equal to top
					if(table[rowIndex][columnIndex-1]<=table[rowIndex-1][columnIndex]){
						//move left
						columnIndex=columnIndex-1;
					}
					else{
						//top is smaller
						//move above
						rowIndex=rowIndex-1;
					}
				}
		}
	}
	
	//commented methods to calculate NED and print the actual table
	//can be used if required

	/*private static void calNED() {

		numberOfDeletions=table[numberOfRows-1][numberOfColumns-1];		
		System.out.println("Number of deletions required= "+numberOfDeletions);

		float len1=inputStr1.length();
		float len2=inputStr2.length();				
		normalizedEditDistance=(((len1+len2)-numberOfDeletions)/(len1+len2));
		System.out.println("Normalized Edit Distance for given Strings is "+normalizedEditDistance);


	}*/

	/*private static void printTable() {

		for(int i=0; i<=inputStr1.length(); i++){
			for(int j=0; j<=inputStr2.length(); j++){
				System.out.print(" "+table[i][j]);
			}
			System.out.println();
		}

	}*/

	//input: String obtained from given files
	//function: initialize table and stack
	private static void init(String [] args) {
		inputStr1=args[0]; //left string
		inputStr2=args[1]; //top string

		//one more row and column are required to store initial values in the table 
		numberOfRows=inputStr1.length()+1;
		numberOfColumns=inputStr2.length()+1;

		table=new int[numberOfRows][numberOfColumns]; //create table

		//initialize table
		for(int i=0; i<numberOfRows; i++){			
			table[i][0]=i;		
		}
		for(int j=0; j<numberOfColumns; j++){			
			table[0][j]=j;		
		}		
		
		//create stack
		stack=new ArrayList<String>();		

	}

	//function: build the table to find LCS
	private static void buildTable() {

		int rowCounter=1;
		int rowIndex=0;	
		int str1Index=0;
		int str2Index=0;

		//left string index
		while(rowCounter<numberOfRows){

			str2Index=0;
			//top string index
			for(rowIndex=1; rowIndex<numberOfColumns; rowIndex++){

				//match found
				if(inputStr1.charAt(str1Index)==inputStr2.charAt(str2Index)){

					//get diagonal left symbol
					table[rowCounter][rowIndex]=table[rowCounter-1][rowIndex-1];
				}
				else{
					//left smaller than or equal to top, copy left+1
					if(table[rowCounter][rowIndex-1]<=table[rowCounter-1][rowIndex]){


						table[rowCounter][rowIndex]=table[rowCounter][rowIndex-1]+1;
					}
					else{						
						//top smaller than left, copy top+1
						table[rowCounter][rowIndex]=table[rowCounter-1][rowIndex]+1;
					}
				}
				//increment top string index
				str2Index++;

			}				
			//increment left string index
			str1Index++;
			rowCounter++;			

		}
	}

}

