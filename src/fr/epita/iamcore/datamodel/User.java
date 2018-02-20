package fr.epita.iamcore.datamodel;

import java.util.Map;

/**
 * Enterprise data object for containing the Identity i.e it contains Identity DataModel.
 * 
 * @author Thejus and Bhanuja
 *
 */
public class User {

	/** The Password */
	private String password;
	
	/** The display UID */
	private String uid;
	
	/** The Email Address */
	private String email;
	
	Map<String,String>attributes;
	
	/**
	 * Gets the Password
	 * 
	 * @return password The Password
	 */

	public String getPassword() {
		return password;
	}

	/**
	 * Sets the Password
	 * 
	 * @param password The Password
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	
	
	/**
	 * Gets the UID
	 * 
	 * @return String The UID
	 */
	public String getUid() {
		return uid;
	}
	
	/**
	 * Sets the UID
	 * 
	 * @param uid The UID
	 */
	public void setUid(String uid) {
		this.uid = uid;
	}
	
	/**
	 * Gets the Email address
	 * 
	 * @return String The Email address
	 */
	public String getEmail() {
		return email;
	}
	
	/**
	 * Sets the Email address
	 * 
	 * @param email The Email address
	 */
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	/**
	 * Overrides superclass method and displays name uid and email
	 * 
	 * @return Identity the Identity details like name uid and email.
	 */
	@Override
	public String toString() {
		return "Identity [ uid=" + uid + ", email=" + email + "]";
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((uid == null) ? 0 : uid.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (uid == null) {
			if (other.uid != null)
				return false;
		} else if (!uid.equals(other.uid))
			return false;
		return true;
	}


	
}
