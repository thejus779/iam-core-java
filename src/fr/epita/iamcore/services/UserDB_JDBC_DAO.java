package fr.epita.iamcore.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import fr.epita.iamcore.constants.sqlCons;
import fr.epita.iamcore.datamodel.User;
import fr.epita.iamcore.exceptions.DAOUserException;
import fr.epita.logger.Logger;

/**
 * Enterprise data object for handling User Operations using DB derby and sql commands
 * 
 * @author Thejus and Bhanuja
 *
 */
public class UserDB_JDBC_DAO implements UserDAO{

	/** The Logger */
	private static final Logger logger = new Logger(IdentityDB_JDBC_DAO.class);
	
	/**
	 * Creates new User with UID Email and Password.
	 * Returns a success or failure in creating the new User Account
	 * 
	 * @param user, The User object.
	 */
	@Override
	public void signUp(User user) throws DAOUserException{
		final Connection connection = DB_Connection.getConnection();
		try {
			final PreparedStatement preparedStatement = connection.prepareStatement(sqlCons.CREATEUSER);
			preparedStatement.setString(1, user.getUid());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getEmail());
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
	 * Searches for an User with display UID and matching Password.
	 * 
	 * Returns a success or failure message in finding the User searched for.
	 * 
	 * @param user, The User object.
	 * @return Users , The searched User
	 */
	@Override
	public List<User> search(User criteria) throws DAOUserException{
		final List<User> users = new ArrayList<>();

		final Connection connection = DB_Connection.getConnection();
		try {

			final PreparedStatement preparedStatement = connection.prepareStatement(sqlCons.SEARCHUSER);
			preparedStatement.setString(1, criteria.getUid());
			preparedStatement.setString(2, criteria.getPassword());

			final ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				final User user = new User();
				user.setPassword(resultSet.getString(2));
				user.setEmail(resultSet.getString(3));
				user.setUid(resultSet.getString(1));
				users.add(user);
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

		return users;
	}

	
}
