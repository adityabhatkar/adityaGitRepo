//aditya bhatkar 800887086
package myProject;


import java.io.FileNotFoundException;

//calculates NED without string complete table
public class NEDCalculator {

	static String inputStr1=null; //stores left string
	static String inputStr2=null; //stores top string
	static int [] upperRow=null; //these two rows are used instead of complete table 
	static int [] currentRow=null; 


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


		//initialize two table rows, left and top string 		
		init(inputSequences);

		//calculate table rows
		calRows();	


		int numberOfDeletions=currentRow[currentRow.length-1]; //last element of last row
		//System.out.println("Number of deletions required= "+numberOfDeletions);

		float len1=inputStr1.length();
		float len2=inputStr2.length();				
		//Formula for NED
		float normalizedEditDistance=(((len1+len2)-numberOfDeletions)/(len1+len2)); 
		System.out.println(normalizedEditDistance);

	}

	// input: two given strings from input files 
	//function: initialize two table rows, left and top string
	private static void init(String [] args){

		//initialize left and top strings
		inputStr1=args[0];
		inputStr2=args[1];

		//initialize upper and current rows
		upperRow=new int[inputStr2.length()+1];
		currentRow=new int[inputStr2.length()+1];

		for(int i=0; i<upperRow.length; i++){
			upperRow[i]=i;
		}

	}

	//function: determines the two required rows of table
	//uses only two rows named as current and upper, instead of complete table 
	private static void calRows(){

		int rowCounter=1;
		int rowIndex=0;	
		int str1Index=0;
		int str2Index=0;

		//left string index
		while(rowCounter<inputStr1.length()+1){

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

			//swap upper row with current till end of left string is reached
			if(str1Index<inputStr1.length()){
				int [] temp=upperRow;
				upperRow=currentRow;
				currentRow=temp;
			}

		}
	}

}

