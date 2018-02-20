package fr.epita.iamcore.services;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.sun.org.apache.xerces.internal.util.SynchronizedSymbolTable;

import fr.epita.iamcore.constants.sqlCons;
import fr.epita.iamcore.datamodel.Identity;
import fr.epita.iamcore.exceptions.DAOIdentityExceptions;
import fr.epita.iamcore.services.IdentityDAO;
import fr.epita.logger.Logger;;

/**
 * Enterprise data object for handling CRUD Operations using DB derby and sql commands
 * 
 * @author Thejus and Bhanuja
 *
 */
public class IdentityDB_JDBC_DAO implements IdentityDAO  {

	/** The Logger */
	private static final Logger logger = new Logger(IdentityDB_JDBC_DAO.class);


	/**
	 * Creates new Identity with display Name email and uid.
	 * Returns a success or failure in creating the new Identity message
	 * 
	 * @param identity, The Identity object.
	 */
	@Override	
	public void create(Identity identity) throws DAOIdentityExceptions {
		final Connection connection = DB_Connection.getConnection();
		try {
			final PreparedStatement preparedStatement = connection.prepareStatement(sqlCons.CREATE);
			preparedStatement.setString(1, identity.getDisplayName());
			preparedStatement.setString(2, identity.getEmail());
			preparedStatement.setString(3, identity.getUid());
			//preparedStatement.execute();
			int pef=preparedStatement.executeUpdate();

			if(pef>0) {
				logger.info("SQL Query Executed");
				System.out.println("Data Created Successfully");
			}
			else {
				logger.error("SQL Query execution error");
				System.out.println("Something went wrong, try again!");
			}

		} catch ( SQLException e) {

			logger.error("Exception in Create method :" + e.getMessage());

		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (final SQLException e) {
				logger.error("Something went wrong :"+ e.getMessage());
			}
		}
	}

	/**
	 * Searches for an Identity with display Name or email or uid.
	 * 
	 * Returns a success or failure message in finding the Identity searched for.
	 * 
	 * @param identity, The Identity object.
	 * @return identities , The searched Identity
	 */
	@Override
	public List<Identity> search(Identity criteria) {
		final List<Identity> identities = new ArrayList<>();

		final Connection connection = DB_Connection.getConnection();
		try {

			final PreparedStatement preparedStatement = connection.prepareStatement(sqlCons.SEARCH);
			preparedStatement.setString(3, criteria.getUid());
			preparedStatement.setString(1, criteria.getDisplayName());
			preparedStatement.setString(2, criteria.getEmail());

			final ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				final Identity identity = new Identity();
				identity.setDisplayName(resultSet.getString(2));
				identity.setEmail(resultSet.getString(3));
				identity.setUid(resultSet.getString(1));
				identities.add(identity);
			}
		} catch (SQLException e) {
			logger.error("Exception in Search method :" + e.getMessage());
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (final SQLException e) {
				logger.error("Something went wrong :"+ e.getMessage());
			}
		}

		return identities;
	}


	/**
	 * Updates existing Identity with display Name and email.
	 * Restricted to updating only name and email.
	 * Returns a success or failure message in updating the  Identity.
	 * 
	 * @param identity, The Identity object.
	 */
	@Override
	public void update(Identity identity) throws DAOIdentityExceptions{

		final Connection connection = DB_Connection.getConnection();
		try {

			final PreparedStatement preparedStatement = connection.prepareStatement(sqlCons.UPDATE);
			preparedStatement.setString(1, identity.getDisplayName());
			preparedStatement.setString(2, identity.getEmail());
			preparedStatement.setString(3, identity.getUid());
			//preparedStatement.execute();
			int pef=preparedStatement.executeUpdate();


			if(pef>0) {
				logger.info("SQL Query Executed");
			}
			else {
				logger.error("SQL Query execution error");
			}

		} catch (SQLException e) {
			logger.error("Exception in Update method :" + e.getMessage());
			//		final IdentityCreationException businessException = new IdentityCreationException(identity, e);

			//throw businessException;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (final SQLException e) {
				logger.error("Something went wrong :"+ e.getMessage());
			}
		}

	}

	/**
	 * Deletes existing Identity.
	 * Delete can be performed by name , email or uid.
	 * Returns a success or failure message in deleting the  Identity.
	 * 
	 * @param identity, The Identity object.
	 */
	@Override
	public void delete(Identity identity) {
		final Connection connection = DB_Connection.getConnection();
		try {
			final PreparedStatement preparedStatement = connection.prepareStatement(sqlCons.DELETE);
			preparedStatement.setString(1, identity.getDisplayName());
			preparedStatement.setString(2, identity.getEmail());
			preparedStatement.setString(3, identity.getUid());
			//preparedStatement.execute();
			int pef=preparedStatement.executeUpdate();

			if(pef>0) {
				System.out.println("Delete successfull");
				logger.info("SQL Query Executed");
			}
			else {
				System.out.println("Data not found to delete");
				logger.error("SQL Query execution error");
			}

		} catch (SQLException e) {
			logger.error("Exception in delete method :" + e.getMessage());
			//	final IdentityCreationException businessException = new IdentityCreationException(identity, e);

			//	throw businessException;
		} finally {
			try {
				if (connection != null) {
					connection.close();
				}
			} catch (final SQLException e) {
				logger.error("Something went wrong :"+ e.getMessage());
			}
		}
	}


}
