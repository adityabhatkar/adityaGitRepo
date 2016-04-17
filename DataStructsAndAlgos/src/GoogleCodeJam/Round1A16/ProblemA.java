package Round1A16;

import java.util.*;
import java.io.*;

public class ProblemA {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		
		//Scanner scanner = new Scanner(System.in);
		Scanner scanner = new Scanner(new File("A-large.in"));
		PrintWriter writer = new PrintWriter("A-large.out", "UTF-8");		
		
		int numberOfTestCases=scanner.nextInt();		
		scanner.nextLine();
		
		for(int testCaseCounter=1; testCaseCounter<=numberOfTestCases; testCaseCounter++){
			
			char [] word=scanner.nextLine().toCharArray();
			char letter=word[0];
			StringBuilder lastWord=new StringBuilder().append(word[0]);
			
			for(int letterIndex=1; letterIndex<word.length; letterIndex++){
				if(word[letterIndex]>=letter){
					letter=word[letterIndex];					
					lastWord.insert(0, word[letterIndex]);					
				}
				else{
					lastWord.append(word[letterIndex]);					
				}
			}	
			
			
			//System.out.println("Case #"+testCaseCounter+": "+lastWord);
			writer.println("Case #"+testCaseCounter+": "+lastWord);
		}
		
		//close resources
		scanner.close();
		writer.close();
	}

}
