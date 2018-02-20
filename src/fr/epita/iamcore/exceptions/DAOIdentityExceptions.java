package fr.epita.iamcore.exceptions;

import fr.epita.iamcore.datamodel.Identity;

/**
 * Enterprise data object for Handling Business Exceptions
 * 
 * @author Thejus and Bhanuja
 *
 */

public class DAOIdentityExceptions extends Exception{

		 Identity faultyIdentity;


		public void DAOIdentityExceptions(Identity identity, Exception originalCause) {
			faultyIdentity = identity;
			initCause(originalCause);

		}
		
		 /**
			 * Overrides parent method and shows local message
			 * 
			 * @return FaultyIdentity The faulty Identity and a business message as String.
			 */
		@Override
		public String getMessage() {
			return "Problem occured while creating that identity in the system " + faultyIdentity.toString();
		}

	
}
