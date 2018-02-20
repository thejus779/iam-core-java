package fr.epita.iamcore.services;

import java.util.List;

import fr.epita.iamcore.datamodel.Identity;
import fr.epita.iamcore.exceptions.DAOIdentityExceptions;

/**
 * Interface for IdentityDAO containing the CRUD Operations
 * 
 * @author Thejus and Bhanuja
 *
 */

public interface IdentityDAO {

	/** Create Identity */
	public void create(Identity identity) throws DAOIdentityExceptions;

	/** Update Identity */
	public void update(Identity identity) throws DAOIdentityExceptions;

	/** Delete Identity */
	public void delete(Identity identity);

	/** Search Identity */
	public List<Identity> search(Identity criteria);


}
