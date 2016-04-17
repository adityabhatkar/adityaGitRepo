package Round1A16;

import java.util.*;
import java.io.*;

public class ProblemB {

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
		
		//Scanner scanner = new Scanner(System.in);
		Scanner scanner = new Scanner(new File("B-large.in"));
		PrintWriter writer = new PrintWriter("B-large.out", "UTF-8");
		
		int numberOfTestCases=scanner.nextInt();
		
		scanner.nextLine();
		
		for(int testCaseCounter=1; testCaseCounter<=numberOfTestCases; testCaseCounter++){
			
			int n=Integer.parseInt(scanner.nextLine());
			
			Map<Integer, Integer> heightMap=new HashMap<Integer, Integer>();
			
			for(int i=0; i<(2*n-1); i++){
				
				String[] line=scanner.nextLine().split(" ");
				
				for(int j=0; j<line.length; j++){
					
					
					Integer count=heightMap.get(Integer.parseInt(line[j]));
					if(count==null){
						count=1;
					}
					else{
						count++;
					}
					heightMap.put(Integer.parseInt(line[j]), count);
				}
			}
			
			int[] output=new int[n];
			int i=0;
			for(Integer height:heightMap.keySet()){
				
				Integer count=heightMap.get(height);
				if((count%2)!=0){	
					output[i]=height;
					i++;
				}
			}
			Arrays.sort(output);
			
			StringBuilder outputBuilder=new StringBuilder();
			for(int j=0; j<output.length; j++){
				outputBuilder.append(output[j]+" ");
			}
			String res=outputBuilder.toString().trim();
			
			
			writer.println("Case #"+testCaseCounter+": "+res);
			//System.out.println("Case #"+testCaseCounter+": "+res);
		}
		
		//close resources
		scanner.close();
		writer.close();
	}

}