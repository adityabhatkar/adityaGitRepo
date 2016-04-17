package practiceCodingProblems;
import java.util.*;
import java.io.*;

/*
 * this problem finds sum of lengths of paths to the image files in a
 * given directory structure
 * 
 * */ 
public class PathLengthImageFiles {

	public static void main(String[] args) throws FileNotFoundException {

		//Scanner scanner=new Scanner(System.in);
		Scanner scanner=new Scanner(new File("ImageFilePaths.txt"));

		Set<String> imageFileExtensions=new HashSet<String>();
		imageFileExtensions.add("jpeg");
		imageFileExtensions.add("png");
		imageFileExtensions.add("gif");

		Stack<String> directoryStack=new Stack<String>();

		int previousCharIndex=-1;
		long pathLength=1;
		String parentDir="/";		
		
		while(scanner.hasNext()){

			String line=scanner.nextLine();
			int charIndex=-1;
			do{
				charIndex++;
			}
			while(line.charAt(charIndex)==' ');

			String fileName=line.substring(charIndex);
			String extension=null;
			String[] fileInfo=fileName.split("\\.");

			boolean isDir=true;
			if(fileInfo.length==2){
				isDir=false;
				extension=fileInfo[1];
			}		



			if(charIndex>previousCharIndex){
				
				if(isDir){
					previousCharIndex=charIndex;
					//directoryStack.push(fileName);
					directoryStack.push(parentDir);
					parentDir=fileName;
				}
				else if(imageFileExtensions.contains(extension)){
					pathLength+=directoryStack.size();
				}
			}else if (charIndex<previousCharIndex){

				while(charIndex==previousCharIndex){
					//previousCharIndex=charIndex;
					System.out.println(directoryStack.pop());
					previousCharIndex--;
				}
				if(isDir){
					directoryStack.push(fileName);
				}
				else if(imageFileExtensions.contains(extension)){
					pathLength+=directoryStack.size();
				}
			}

			/*if(isDir){
				directoryStack.push(fileName);
			}
			else if(imageFileExtensions.contains(extension)){
				pathLength+=directoryStack.size();
			}*/
		}
		scanner.close();
		System.out.println("Path Length "+(pathLength));

	}

}
