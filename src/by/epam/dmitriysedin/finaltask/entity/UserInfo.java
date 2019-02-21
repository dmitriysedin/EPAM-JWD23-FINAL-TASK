package by.epam.dmitriysedin.finaltask.entity;

import java.util.Objects;

public class UserInfo {
	
	private String userLogin;
	private String userPassword;
	private String userFirstName;
	private String userLastName;
	private String userEmail;
	
	public UserInfo() {
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

	@Override
	public int hashCode() {
		return Objects.hash(userEmail, userFirstName, userLastName, userLogin, userPassword);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof UserInfo)) {
			return false;
		}
		UserInfo other = (UserInfo) obj;
		return Objects.equals(userEmail, other.userEmail) && Objects.equals(userFirstName, other.userFirstName)
				&& Objects.equals(userLastName, other.userLastName) && Objects.equals(userLogin, other.userLogin)
				&& Objects.equals(userPassword, other.userPassword);
	}

	@Override
	public String toString() {
		return "UserInfo [userLogin=" + userLogin + ", userPassword=" + userPassword + ", userFirstName="
				+ userFirstName + ", userLastName=" + userLastName + ", userEmail=" + userEmail + "]";
	}
	
	
}
