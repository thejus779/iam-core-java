package fr.epita.iamcore.constants;

/**
 * Enterprise data object for maintaining the SQL Query constants.
 * 
 * @author Thejus and Bhanuja
 *
 */

public class sqlCons {

	public static final String CREATE="INSERT INTO IDENTITIES (DISPLAY_NAME, EMAIL, UID) VALUES (?, ?, ?)";
	
	public static final String DELETE=" DELETE FROM IDENTITIES WHERE DISPLAY_NAME = ? OR EMAIL = ? OR UID = ?";
	
	public static final String UPDATE="UPDATE IDENTITIES SET DISPLAY_NAME=?, EMAIL=? WHERE UID = ?";
	
	public static final String SEARCH="select UID, DISPLAY_NAME, EMAIL FROM IDENTITIES WHERE DISPLAY_NAME = ? OR EMAIL = ? OR UID = ? ";
	
	public static final String CREATEUSER="INSERT INTO USERS (UID, PASSWORD, EMAIL) VALUES (?, ?, ?)";
	
	public static final String SEARCHUSER="select UID, PASSWORD, EMAIL FROM USERS WHERE UID = ? AND PASSWORD = ? ";
	
}
