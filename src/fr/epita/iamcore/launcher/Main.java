package fr.epita.iamcore.launcher;

import java.util.List;
import java.util.Scanner;

import fr.epita.iamcore.datamodel.Identity;
import fr.epita.iamcore.datamodel.User;
import fr.epita.iamcore.exceptions.DAOIdentityExceptions;
import fr.epita.iamcore.exceptions.DAOUserException;
import fr.epita.iamcore.services.IdentityDB_JDBC_DAO;
import fr.epita.iamcore.services.UserDB_JDBC_DAO;
import fr.epita.logger.Logger;

/**
 * Enterprise Main class containing the Main methods.
 * Contains an authentication method to create User , and login the User after that.
 * Contains Main class with a selection menu, select the number corresponding to the Selection list.
 * 
 * @author Thejus and Bhanuja
 *
 */

public class Main {
	/** The Logger */
	private static final Logger logger = new Logger(Main.class);
	
	/** The IdentityDAO Object */
	private static final IdentityDB_JDBC_DAO dao = new IdentityDB_JDBC_DAO();
	
	/** The UserDAO Object */
	private static final UserDB_JDBC_DAO daoUser = new UserDB_JDBC_DAO();

	/** The Scanner for user Inputs */
	private static Scanner sc= new Scanner(System.in);

	/** The Main Method */
	public static void main(String[] args) {

		menuLogin();
	}
	
	/**
	 * Displays a selection menu for the CRUD Operations.
	 * Input Integers from 1-5 , 5 for exit , anything else is not recognized.
	 * 1.Create 2.Search 3.Update 4.Delete 5.Exit.
	 * Method is called after successful user authentication.
	 * 
	 */
	private static void menuOptions() {
		int val=0; 
		do {

			System.out.println("------------------------------------");
			System.out.println("------------------------------------");
			System.out.println("    ");
			System.out.println("SELECT YOUR OPERATION");
			System.out.println("    ");
			System.out.println("1.Create");
			System.out.println("2.Search");
			System.out.println("3.Update");
			System.out.println("4.Delete");
			System.out.println("5.Exit");
			System.out.println("    ");
			System.out.println("------------------------------------");
			System.out.println("------------------------------------");

			val = sc.nextInt();

			switch(val) {
			case 1 :

				createData();

				break; 

			case 2 :

				searchData();

				break; 
			case 3 :

				updateData();

				break;

			case 4 :
				
				deleteData();
				
				break;

			case 5 :
				System.out.println("Selection Menu Exited");
				logger.info("Selection Menu Exited");
				System.exit(0);
				break;

			default : 
				System.out.println("No Valid option selected");
				logger.error("No Valid option selected");
				break;
			}
		} while(val!=5);	
	}

	/**
	 * Main method for creating the Identity..
	 * Asks for Display name , Email and Uid.
	 * Passes all values to Identity to create a new Identity in the DB.
	 * Returns the result of creating the new Identity.
	 */
	private static void createData() {

		final Identity identity = new Identity();


		System.out.println("Enter Display Name");
		identity.setDisplayName(sc.next());

		System.out.println("Enter The Email Address");
		identity.setEmail(sc.next());

		System.out.println("Enter UID");
		identity.setUid(sc.next());
		

		try {
			dao.create(identity);

		} catch (final DAOIdentityExceptions e) {
			System.out.println(e.getMessage());
		}

	}

	/**
	 * Main method for Searching the Identity.
	 * Offers a selection menu to search by name,email or uid.
	 * Asks for Display name or Email or Uid to search by.
	 * Passes all values to IdentityDAO to perform the search operation.
	 * Returns the result of search.
	 * 
	 */
	private static void searchData() {

		final Identity criteria = new Identity();

		int val=0;
		do {

			System.out.println("Search by Options");
			System.out.println("1.Name");
			System.out.println("2.Email");
			System.out.println("3.U_ID");
			System.out.println("4.Exit");
			val = sc.nextInt();

			switch(val) {
			case 1 :

				System.out.println("Enter Display Name");
				criteria.setDisplayName(sc.next());

				val=4;

				break; 

			case 2 :

				System.out.println("Enter Email");
				criteria.setEmail(sc.next());

				val=4;

				break; 
			case 3 :

				System.out.println("Enter Display Name");
				criteria.setUid(sc.next());

				val=4;

				break;

			case 4 :
				logger.info("Search Selection Menu Exited");
				System.exit(0);

				val=4;

				break;

			default : 
				System.out.println("No Valid option selected");
				logger.error("No Valid option selected");
				break;
			}
		} while(val!=4);




		final List<Identity> identities = dao.search(criteria);

		if (identities == null || identities.isEmpty()) {
			System.out.println("Data not found in the Data Base");
		} else {
			System.out.println("Data found in the Data Base");
		}
	}

	/**
	 * Main method for Deleting the Identity.
	 * Offers a selection menu to delete by name,email or uid
	 * Asks for Display name or Email or Uid to delete by.
	 * Passes all values to IdentityDAO to perform the delete operation.
	 * Returns the result of delete.
	 * 
	 */
	private static void deleteData() {

		
		final Identity criteria = new Identity();


		int val=0; 
		do {

			System.out.println("Delete by Options");
			System.out.println("1.Name");
			System.out.println("2.Email");
			System.out.println("3.U_ID");
			System.out.println("4.Exit");
			val = sc.nextInt();

			switch(val) {
			case 1 :

				System.out.println("Enter Display Name");
				criteria.setDisplayName(sc.next());

				val=4;

				break; 

			case 2 :

				System.out.println("Enter Email");
				criteria.setEmail(sc.next());

				val=4;

				break; 
			case 3 :

				System.out.println("Enter Display Name");
				criteria.setUid(sc.next());

				val=4;

				break;

			case 4 :
				logger.info("Delete Selection Menu Exited");
				System.exit(0);

				val=4;

				break;

			default : 
				System.out.println("No Valid option selected");
				logger.error("No Valid option selected");
				break;
			}
		} while(val!=4);


		try {
			dao.delete(criteria);
		} catch (final UnknownError e ) {
			System.out.println(e.getMessage());
		}


	}
	
	/**
	 * Main method for Updating the Identity.
	 * Asks for Uid to delete by.
	 * Asks for new Display name and Email.
	 * Passes all values to IdentityDAO to perform the update operation.
	 * Returns the result of update.
	 * 
	 */
	private static void updateData() {

		final Identity criteria = new Identity();
		System.out.println("Enter UID of the data to be deleted");
		criteria.setUid(sc.next());
		System.out.println("Enter new Display Name");
		criteria.setDisplayName(sc.next());
		System.out.println("Enter new Email Address");
		criteria.setEmail(sc.next());
		
		
		
		try {
			dao.update(criteria);
		} catch (final UnknownError | DAOIdentityExceptions e) {
			System.out.println(e.getMessage());
		}
	}
	
	
	/**
	 * Displays a selection menu for the Login or Signup Operation.
	 * Input Integers from 1-3 , 3 for exit , anything else is not recognized.
	 * 1.Login 2.SignUp 3.Exit.
	 * 
	 */
	private static void menuLogin(){
		int val=0; 

			System.out.println("------------------------------------");
			System.out.println("    ");
			System.out.println("SELECT YOUR OPERATION");
			System.out.println("    ");
			System.out.println("1.Login");
			System.out.println("2.SignUp");
			System.out.println("3.Exit");
			System.out.println("    ");
			System.out.println("------------------------------------");
			
			val = sc.nextInt();

			switch(val) {
			case 1 :

				login();

				break; 

			case 2 :

				signUp();

				break; 
			case 3 :
				logger.info("Selection Menu Exited");
				System.exit(0);
				break;

			default : 
				System.out.println("No Valid option selected");
				logger.error("No Valid option selected");
				break;
	
			}
	}
	
	/**
	 * Main method for creating the User Account..
	 * Asks for UID , Email and Password.
	 * Passes all values to User to create a new User in the DB.
	 * Returns the result of creating the new User.
	 * calls the method menuOptions() after successful creation.
	 */
	private static void signUp() {

		final User user = new User();

		System.out.println("Enter UID");
		user.setUid(sc.next());
		
		System.out.println("Enter The Email Address");
		user.setEmail(sc.next());
		
		System.out.println("Enter Password");
		user.setPassword(sc.next());
		

		try {
			daoUser.signUp(user);
			menuOptions();
		} catch (final DAOUserException e) {
			System.out.println(e.getMessage());
		}

	}
	
	/**
	 * Main method for login the User Account..
	 * Asks for UID and Password.
	 * Passes all values to User to search for a User in the DB with matching UID and Password.
	 * Returns the result of searching the new User.
	 * calls the method menuOptions() after successful search or menuLogin() after unsuccessful search.
	 */
	private static void login() {
		
		final User criteria = new User();


		String pass,uid;
		
				System.out.println("Enter UID");
				uid=sc.next();
				criteria.setUid(uid);
				
				System.out.println("Enter Password");
				pass=sc.next();
				criteria.setPassword(pass);

				
		List<User> users = null;
		try {
			users= daoUser.search(criteria);

		} catch (final DAOUserException e) {
			System.out.println(e.getMessage());
		}
		

		if (users == null || users.isEmpty()) {
			System.out.println("User Not Found, Login Unsuccessfull");
			menuLogin();
		} else {
			System.out.println("User Found, Login Successfull");
			menuOptions();
		}
	}
}
