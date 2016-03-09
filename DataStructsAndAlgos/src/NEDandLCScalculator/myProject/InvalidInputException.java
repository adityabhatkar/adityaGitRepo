//aditya bhatkar 800887086
package myProject;

public class InvalidInputException extends Exception{
	//Exception thrown when input is invalid
	public InvalidInputException(String s){
		super(s);		//set the error message
	}

}
