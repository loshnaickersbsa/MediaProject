package vzap.losh.user;
import  vzap.losh.utils.StringChecks;

import java.io.Serializable;

import  vzap.losh.exceptions.*;

public class User implements Serializable{
	
	private String userId;
	private String password;
	private String firstName;
	private String surName;
	private boolean isAdmin;

	
	
	public User(String userId, String password, String firstName, String surname, boolean isAdmin) throws UserClassException{
		super();
		
		
		if (firstName == null || (firstName.isEmpty()) || StringChecks.isNonAlpha(firstName) )
		{
			throw new UserClassException("Name must not be empty and must be alphanumeric");
		}
		
		if (surname == null || (surname.isEmpty()) || StringChecks.isNonAlpha(surname))
		{
			throw new UserClassException("Name must not be empty and must be alphanumeric");
		}
		
		this.userId = userId;
		this.password = password;
		this.firstName = firstName;
		this.surName = surname;
		this.isAdmin = isAdmin;
		/**
		 * User exception class occurs when a proper name and surname is not entered. 
		 * Numbers or underscores etc. 1 exception class for it. 
		 */

	}

	/*public User(String userId, String password, String firstName, String surName, boolean isAdmin) {
		this();
		this.userId = userId;
		this.password = password;
		this.firstName = firstName;
		this.surName = surName;
		this.isAdmin = isAdmin;
	}
	*/
	
	public static void main(String[] args) {

		// TODO Auto-generated method stub

	}
	public String getFirstName() {
		return firstName;
	}
	
	public String getUserID() {
		return this.userId;
	}

	public String getPassword() {
		
		return this.password;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", password=" + password + ", firstName=" + firstName + ", surName=" + surName
				+ ", isAdmin=" + isAdmin + "]";
	}

}
