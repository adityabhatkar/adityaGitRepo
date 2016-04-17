package Round1A16;

import java.util.*;
import java.io.*;

public class ProblemC {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		Scanner scanner = new Scanner(System.in);
		//Scanner scanner = new Scanner(new File("A-large-practice.in"));
		//PrintWriter writer = new PrintWriter("Out-A-large-practice.txt", "UTF-8");
		
		int numberOfTestCases=scanner.nextInt();
		
		scanner.nextLine();
		
		for(int testCaseCounter=1; testCaseCounter<=numberOfTestCases; testCaseCounter++){
			long count=0;
			
			
			
			//writer.println("Case #"+testCaseCounter+": "+count);
			System.out.println("Case #"+testCaseCounter+": "+count);
		}
		
		//close resources
		scanner.close();
		//writer.close();
	}

}

class Kid{
	
	int id;
	Kid bffTo;
	List<Kid> bffsFrom;
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Kid other = (Kid) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
	
}
