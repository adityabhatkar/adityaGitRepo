package qualifierJam16;
import java.util.*;
import java.io.*;
import java.math.BigInteger;

public class CoinJam {


	public static int[] getJamCoinDivisors(String binNumber){


		int [] nonTrivialDivisors=new int[9];
		int divisorIndex=0;
		boolean isJamCoin=true;
		for(int radix=2; radix<=10; radix++){

			//int number=Integer.parseInt(binNumber, radix);
			//long number=Long.parseLong(binNumber, radix);
			BigInteger number=new BigInteger(binNumber, radix);
			
			System.out.println("number "+number);
			int divisor=getNonTrivialDivisor(number);
			if(divisor==0){
				isJamCoin=false;
			}

			nonTrivialDivisors[divisorIndex]=divisor;
			divisorIndex++;

		}
		if(isJamCoin){
			return nonTrivialDivisors;
		}
		else{
			return null;
		}


	}

	public static int getNonTrivialDivisor(BigInteger b) {

		//int n=b.intValue();
		long n=b.longValue();
		if (n <= 1) {
			return 0;
		}
		for (int i = 2; i < Math.sqrt(n); i++) {
			if (n % i == 0) {
				return i;
			}
		}
		return 0;
	}

	public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {

		Scanner scanner = new Scanner(new File("CoinJam\\C-large.in"));
		PrintWriter writer = new PrintWriter("CoinJam\\B-largeOut2.txt", "UTF-8");

		int numberOfTestCases=scanner.nextInt();
		scanner.nextLine();

		for(int testCaseCounter=1; testCaseCounter<=numberOfTestCases; testCaseCounter++){
			writer.println("Case #"+testCaseCounter+":");

			String[] input=scanner.nextLine().split(" ");
			int n=Integer.parseInt(input[0]);
			int j=Integer.parseInt(input[1]);		

			int numberOfJamCoins=0;
			int placesToFill=n-2;
			for(int i=0; i<(int)Math.pow(2, placesToFill) && (numberOfJamCoins<j);i++){

				String binNumberAsString=Integer.toString(i,2);
				//System.out.println("number before formatting "+binNumberAsString);
				int numberOfZeros=placesToFill-binNumberAsString.length();
				if(binNumberAsString.length()<placesToFill){
					for(int placeIndex=0; placeIndex<numberOfZeros; placeIndex++){
						binNumberAsString="0"+binNumberAsString;
					}

				}
				binNumberAsString="1"+binNumberAsString+"1";
				//System.out.println("binNumberAsString "+ binNumberAsString);
				int[] divisors=getJamCoinDivisors(binNumberAsString);
				//System.out.println(Arrays.toString(divisors));


				if(divisors!=null){

					System.out.print(binNumberAsString);
					writer.print(binNumberAsString);
					for(int divisorIndex=0; divisorIndex<divisors.length; divisorIndex++){
						System.out.print(" "+divisors[divisorIndex]);
						writer.print(" "+divisors[divisorIndex]);
					}

					System.out.println();
					writer.println();
					numberOfJamCoins++;
				}


			}
		}
		//close resources
		scanner.close();
		writer.close();
	}

}
