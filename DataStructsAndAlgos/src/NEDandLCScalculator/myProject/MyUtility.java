//aditya bhatkar 800887086
package myProject;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

//Stores utility methods, they are required in all three programs
public class MyUtility {
	
	//gets required strings from the files mentioned in arguments
	//validates the arguments
	public static String[] getInput(String [] args) throws InvalidInputException,  FileNotFoundException{
		
		if(args==null || args.length<2 || args[0]==null || args[1]==null){			
			
			throw new InvalidInputException("Invalid Arguments"); //custom exception
		}
		
		//get left string from first file and top string from second file
		File inputFile1=new File(args[0]); 
		File inputFile2=new File(args[1]);
		
		Scanner scanner1=null;
		Scanner scanner2=null;
		
		try {
			scanner1=new Scanner(inputFile1);			
			scanner2=new Scanner(inputFile2);
			
		} catch (FileNotFoundException e) {		
			
			throw e;
		}
		
		String input1=null;
		String input2=null;
		
		//extract left string
		while(scanner1.hasNextLine()){			
			input1=scanner1.nextLine();
		}
		//extract top string		
		while(scanner2.hasNextLine()){			
			input2=scanner2.nextLine();
		}		
		
		scanner1.close();
		scanner2.close();		
		
		return new String[] {input1, input2};
	}
	
	//validates strings in the files
	//throws custom exception if they are empty
	public static void validateInput(String[] inputSequences) throws InvalidInputException {
		
		if(inputSequences==null || inputSequences.length<2 || inputSequences[0]==null || inputSequences[1]==null){			
			throw new InvalidInputException("Two Strings required"); //custom exception
		}
		if(inputSequences[0].length()==0 || inputSequences[1].length()==0){			
			throw new InvalidInputException("Input Strings are empty"); //custom exception
		}
	}



}
