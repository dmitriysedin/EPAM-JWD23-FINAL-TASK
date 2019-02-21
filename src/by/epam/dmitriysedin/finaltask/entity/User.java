package by.epam.dmitriysedin.finaltask.entity;

import java.io.Serializable;
import java.util.Objects;

public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	private int userID;
	private String userLogin;
	private String userPassword;
	private String userFirstName;
	private String userLastName;
	private String userEmail;
	private String userRole;
	
	public User() {

	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public String getUserLogin() {
		return userLogin;
	}
	public void setUserLogin(String userLogin) {
		this.userLogin = userLogin;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	public String getUserEmail() {
		return userEmail;
	}
	public void setUserEmail(String userEmail) {
		this.userEmail = userEmail;
	}
	
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	@Override
	public int hashCode() {
		return Objects.hash(userEmail, userFirstName, userID, userLastName, userLogin, userPassword,
				userRole);
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof User)) {
			return false;
		}
		User other = (User) obj;
		return Objects.equals(userEmail, other.userEmail)
				&& Objects.equals(userFirstName, other.userFirstName) && userID == other.userID
				&& Objects.equals(userLastName, other.userLastName) && Objects.equals(userLogin, other.userLogin)
				&& Objects.equals(userPassword, other.userPassword) && Objects.equals(userRole, other.userRole);
	}
	
	@Override
	public String toString() {
		return "User [userID=" + userID + ", userLogin=" + userLogin + ", userPassword=" + userPassword
				+ ", userFirstName=" + userFirstName + ", userLastName=" + userLastName + ", userEmail=" + userEmail
				+ ", countryID=" + ", cityID=" + ", userRole=" + userRole + "]";
	}
	
	
}
