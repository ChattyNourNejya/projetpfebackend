package iss4u.ehr.clinique_projet.exception;


@SuppressWarnings("serial")
public class UserNotFoundException  extends RuntimeException{
	public UserNotFoundException(String message) {
		super(message);
	}

}