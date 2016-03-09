package QualifierJam15;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Scanner;

public class StandingOvation {

	public StandingOvation() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

		//read input file
		Scanner scanner = new Scanner(new File("A-large-practice.in"));
		PrintWriter writer = new PrintWriter("Out-A-large-practice.txt", "UTF-8");

		int numberOfTestCases=scanner.nextInt();
		//get to the start of next line
		//nextInt() only gives int till delimiter it does not reposition scanner to new line		
		scanner.nextLine();

		String testCase=null;
		String [] testCaseArr=null;
		int Smax=0;
		//loop through all test cases
		for(int testCaseCounter=1; testCaseCounter<=numberOfTestCases; testCaseCounter++){

			testCase=scanner.nextLine();
			testCaseArr=testCase.split(" ");

			//first part is Smax second is the audience 
			Smax=Integer.parseInt(testCaseArr[0]);
			char[] audiance=testCaseArr[1].toCharArray();

			//init sum and count
			long totalPeople=(Character.getNumericValue(audiance[0]));
			long count=0;
			long peopleShort=0;
			//loop though audience
			for(int shyIndex=1; shyIndex<=Smax; shyIndex++){
				//if for some shy index not enough people then add the difference
				if (totalPeople<shyIndex){
					peopleShort=shyIndex-totalPeople;
					count=count+peopleShort;
					totalPeople=totalPeople+Character.getNumericValue(audiance[shyIndex])+peopleShort;
				}
				else{
					totalPeople=totalPeople+Character.getNumericValue(audiance[shyIndex]);
				}
			}

			//debug on console
			System.out.println("Case #"+testCaseCounter+": "+count);
			//writing in the file
			writer.println("Case #"+testCaseCounter+": "+count);
		}

		//close resources
		scanner.close();
		writer.close();

	}
}
