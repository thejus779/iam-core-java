package fr.epita.iamcore.services.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;


import fr.epita.iamcore.datamodel.Identity;
import fr.epita.iamcore.exceptions.DAOIdentityExceptions;
import fr.epita.iamcore.services.DB_Connection;
import fr.epita.iamcore.services.IdentityDB_JDBC_DAO;


/**
 * Enterprise Test class containing the Test methods.
 * Operations are performed in the order Create Search Update Delete.
 * 
 * @author Thejus and Bhanuja
 *
 */
public class TestDBOperations {

	public static void main(String[] args) throws SQLException, ClassNotFoundException {
		createTest();
		testCreateSearchFromDAO();
		testConnectAndSelect();
		testUpdate();
		testDeleteFromDAO();

	}

	/**
	 * Test method for creating the Identity..
	 * Uses a dummy Display name , Email and Uid.
	 * Passes all values to Identity to create a new Identity in the DB.
	 */
	private static void createTest() throws ClassNotFoundException, SQLException {
		final Connection connection = DB_Connection.getConnection();

		final PreparedStatement preparedStatement = connection
				.prepareStatement("INSERT INTO IDENTITIES (DISPLAY_NAME, EMAIL, UID) VALUES (?, ?, ?)");

		preparedStatement.setString(1, "Thejus");
		preparedStatement.setString(2, "you@u.com");
		preparedStatement.setString(3, "123");
		preparedStatement.execute();

		connection.close();
	}


	/**
	 * Test method for Displaying the list of Identity..
	 * 
	 */
	private static void testConnectAndSelect() throws ClassNotFoundException, SQLException {
		final Connection connection = DB_Connection.getConnection();

		final String sqlQuery = "select * from IDENTITIES";

		final PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
		final ResultSet resultSet = preparedStatement.executeQuery();
		while (resultSet.next()) {
			System.out.println(resultSet.getString("IDENTITY_ID"));
			System.out.println(resultSet.getString("DISPLAY_NAME"));
			System.out.println(resultSet.getString("EMAIL"));
			System.out.println(resultSet.getString("UID"));
		}
		connection.close();
	}

	/**
	 * Test method for Searching the Identity.
	 * Creates a dummy value, then searches for the same value.
	 * Uses a dummy Display name , Email and Uid.
	 * Passes all values to Identity to create a new Identity in the DB.
	 * Search in db my name or email or uid.
	 */
	private static void testCreateSearchFromDAO() {
		// given
		final IdentityDB_JDBC_DAO dao = new IdentityDB_JDBC_DAO();
		final Identity identity = new Identity();
		identity.setDisplayName("Thomas Broussard");
		identity.setUid("1234");
		identity.setEmail("tbr@tbr.com");

		try {
			dao.create(identity);
		} catch (final DAOIdentityExceptions e) {
			System.out.println(e.getMessage());
		}

		// when
		final Identity criteria = new Identity();
		criteria.setDisplayName("Thejus");
		final List<Identity> identities = dao.search(criteria);


		// then
		if (identities == null || identities.isEmpty()) {
			System.out.println("failure");
		} else {
			System.out.println("success");
		}

	}

	/**
	 * Test method for Deleting the Identity..
	 * delete by name or uid or email.
	 */
	private static void testDeleteFromDAO() {

		final IdentityDB_JDBC_DAO dao = new IdentityDB_JDBC_DAO();
		final Identity criteria = new Identity();
		criteria.setUid("123");

		try {
			dao.delete(criteria);
		} catch (final UnknownError e) {
			System.out.println(e.getMessage());
		}


	}

	/**
	 * Test method for Updating the Identity..
	 * You can only update name and email by using uid.
	 * Passes all values to Identity to update the Identity in the DB.
	 */
	private static void testUpdate() {
		// help me make the update, simple update all data 

		final IdentityDB_JDBC_DAO dao = new IdentityDB_JDBC_DAO();
		final Identity criteria = new Identity();
		criteria.setUid("1234");
		criteria.setDisplayName("new  name");
		criteria.setEmail("new  name");
		//new email ok

		try {
			dao.update(criteria);
		} catch (final UnknownError | DAOIdentityExceptions e) {
			System.out.println(e.getMessage());
		}


	}

	private static void differenceBetweenPreparedStatementAndStatement() throws ClassNotFoundException, SQLException {
		final Connection connection = DB_Connection.getConnection();
		final PreparedStatement prepareStatement = connection.prepareStatement("select * from IDENTITIES where DISPLAY_NAME = ?");
		// Don't do what follows, this will not be protected against SQL injections.
		// Statement statement = connection.createStatement("select * from IDENTITIES where DISPLAY_NAME = ?");
		final String parameter = " 'toto' and 1 = 1; DROP TABLE IDENTIES";
		prepareStatement.setString(1, parameter);
	}
}
