package SocialNetwork;

/**
* A class that represents a link between two users
* David Aghassi (dsa28@case.edu)
* All methods throw a null point exception if any argument is null.
**/

public class UninitializedObjectException extends NullPointerException{
	public UninitializedObjectException(){

	}

	public UninitializedObjectException(String message){
        System.out.print(message);
	}

	public UninitializedObjectException(String message, Throwable cause){

	}

	public UninitializedObjectException(Throwable cause){
		
	}
}