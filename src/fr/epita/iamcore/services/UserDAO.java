package fr.epita.iamcore.services;


import java.util.List;

import fr.epita.iamcore.datamodel.User;
import fr.epita.iamcore.exceptions.DAOUserException;


/**
 * Interface for UserDAO containing the Login and Signup methods
 * 
 * @author Thejus and Bhanuja
 *
 */

public interface UserDAO {

	/** Create User Account */
	public void signUp(User user)throws DAOUserException;

	/** Search User Account to login the User */
	public List<User> search(User criteria)throws DAOUserException;

}
