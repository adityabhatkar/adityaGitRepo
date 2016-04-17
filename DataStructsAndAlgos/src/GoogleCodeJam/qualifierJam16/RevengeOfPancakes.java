package qualifierJam16;
import java.util.*;
import java.io.*;

public class RevengeOfPancakes {


	/*public static String maxFlip(char [] pancakes, int initHappyCount){

		int maxHappyCount=initHappyCount;
		//int flipIndex=-1;
		int flipIndex=0;
		for(int i=0; i<pancakes.length; i++){
			//flipIndex=0;
			//System.out.println("pancakes "+new String(pancakes));
			char [] resultantPancakes=new String(pancakes).toCharArray();

			for(int j=i; j>=0; j--){
				//System.out.println(pancakes[i]);
				if(pancakes[j]=='+'){
					resultantPancakes[i-j]='-';
				}
				else{
					resultantPancakes[i-j]='+';
				}
			}

			String resPancakesString=new String(resultantPancakes);
			int happyCount = resPancakesString.length() - resPancakesString.replace("+", "").length();
			System.out.println("resPancakesString "+resPancakesString+" happyCount "+happyCount);
			if(happyCount>=maxHappyCount){
				maxHappyCount=happyCount;
				flipIndex=i;
			}
		}
		System.out.println("flip index "+flipIndex);

		pancakes=flip(pancakes, flipIndex);
		System.out.println("result of flip "+new String(pancakes));
		return new String(pancakes);
	}*/
	
	public static int getFlipIndex(char [] pancakes){
		
		char first=pancakes[0];
		int flipIndex=0;
		for(int panIndex=0; panIndex<pancakes.length; panIndex++){
			
			if(first=='+'){
				//System.out.println("no");
				
				if(pancakes[panIndex]=='+'){
					flipIndex=panIndex;
				}
				else{
					break;
				}
				
				/*if(pancakes[panIndex]!='+'){
					break;
				}*/
				
			}
			
			else{
				
				if(pancakes[panIndex]!='+'){
					flipIndex=panIndex;
				}
			}
			
			/*if(pancakes[panIndex]==first && pancakes[panIndex]!='+'){
				flipIndex=panIndex;
				
			}*/
		}		
		//System.out.println("flipIndex "+flipIndex);
		return flipIndex;
	}

	public static String flip(char [] pancakes, int flipIndex){
		
			char [] resultantPancakes=new String(pancakes).toCharArray();
			for(int j=flipIndex; j>=0; j--){
				//System.out.println(pancakes[i]);
				if(pancakes[j]=='+'){
					resultantPancakes[flipIndex-j]='-';
				}
				else{
					resultantPancakes[flipIndex-j]='+';
				}
			}
		

		return new String(resultantPancakes);
	}
	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

		//Scanner scanner = new Scanner(new File("Pancakes\\B-small-attempt0.in"));
		Scanner scanner = new Scanner(new File("Pancakes\\B-large.in"));
		PrintWriter writer = new PrintWriter("Pancakes\\sampleOut3.txt", "UTF-8");

		int numberOfTestCases=scanner.nextInt();
		scanner.nextLine();

		for(int testCaseCounter=1; testCaseCounter<=numberOfTestCases; testCaseCounter++){
			
			long flipCount=0;

			String pancakes=scanner.nextLine();
			int happyCount =pancakes.length() - pancakes.replace("+", "").length();
			//System.out.println("happyCount "+happyCount);
			
			while(happyCount!=pancakes.length()){
				
				//pancakes=maxFlip(pancakes.toCharArray(), happyCount);
				int flipIndex=getFlipIndex(pancakes.toCharArray());
				pancakes=flip(pancakes.toCharArray(), flipIndex);
				flipCount++;
				
				happyCount=pancakes.length() - pancakes.replace("+", "").length();			
				
				//System.out.println("pancakes "+pancakes);
				//System.out.println(pancakes);
				//System.out.println("happyCount "+happyCount);
				
				//System.out.println(new String(maxFlip(pancakes.toCharArray(), initHappyCount)));
				
			}
			
			System.out.println("flipCount "+flipCount);
			writer.println("Case #"+testCaseCounter+": "+flipCount);
		}

		//close resources
		scanner.close();
		writer.close();
	}

}
