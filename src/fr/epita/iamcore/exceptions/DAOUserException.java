package fr.epita.iamcore.exceptions;


import fr.epita.iamcore.datamodel.User;

/**
 * Enterprise data object for Handling Business Exceptions in Login and SignUp
 * 
 * @author Thejus and Bhanuja
 *
 */

public class DAOUserException extends Exception{
	
	User faultyUser;


	public void DAOIdentityExceptions(User user, Exception originalCause) {
		faultyUser = user;
		initCause(originalCause);

	}
	
	 /**
		 * Overrides parent method and shows local message
		 * 
		 * @return FaultyIdentity The faulty Identity and a business message as String.
		 */
	@Override
	public String getMessage() {
		return "Problem occured while creating an Account " + faultyUser.toString();
	}
}
