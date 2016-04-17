package qualifierJam16;

import java.util.*;
import java.io.*;

public class CountingSheep {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		
		Scanner scanner = new Scanner(new File("CountSheep\\A-large.in"));
		PrintWriter writer = new PrintWriter("CountSheep\\A-largeOut.txt", "UTF-8");
		
		int numberOfTestCases=scanner.nextInt();		
		scanner.nextLine();
		//System.out.println(numberOfTestCases);
		for(int testCaseCounter=1; testCaseCounter<=numberOfTestCases; testCaseCounter++ ){
			
			long number=scanner.nextLong();
			//System.out.println(number);
			//scanner.nextLine();
			
			long lastNumber=number;
			
			String output="";
			
			Set<Character> visitedNumbers=new HashSet<Character>();
			
			long numberIndex=1;
			while(visitedNumbers.size()<10){
				
				lastNumber=number*numberIndex;
				
				String numberAsAtring=""+lastNumber;
				for(int stringIndex=0; stringIndex<numberAsAtring.length(); stringIndex++){
					visitedNumbers.add(numberAsAtring.charAt(stringIndex));
				}
				
				numberIndex++;
				if(numberIndex>10 && visitedNumbers.size()<2){
					output="INSOMNIA";
					break;
				}
				
				output=lastNumber+"";
				//System.out.println(visitedNumbers);
			}
			
			
			//System.out.println("Case #"+testCaseCounter+": "+output+" "+number);
			writer.println("Case #"+testCaseCounter+": "+output);
			
		}
		
		//close resources
		scanner.close();
		writer.close();
	}
}
