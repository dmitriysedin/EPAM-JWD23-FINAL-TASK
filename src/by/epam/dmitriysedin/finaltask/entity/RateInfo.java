package by.epam.dmitriysedin.finaltask.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Objects;

public class RateInfo implements Serializable{

	private static final long serialVersionUID = 1L;

	private int rateID;
	private int movieID;
	private int userID;
	private int rateValue;
	private Timestamp rateDate;
	private String rateComment;
	private String userFirstName;
	private String userLastName;
	private double userStatus;
	
	public int getRateID() {
		return rateID;
	}
	public void setRateID(int rateID) {
		this.rateID = rateID;
	}
	public int getMovieID() {
		return movieID;
	}
	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
	public int getRateValue() {
		return rateValue;
	}
	public void setRateValue(int rateValue) {
		this.rateValue = rateValue;
	}
	public Timestamp getRateDate() {
		return rateDate;
	}
	public void setRateDate(Timestamp rateDate) {
		this.rateDate = rateDate;
	}
	public String getRateComment() {
		return rateComment;
	}
	public void setRateComment(String rateComment) {
		this.rateComment = rateComment;
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
	public double getUserStatus() {
		return userStatus;
	}
	public void setUserStatus(double userStatus) {
		this.userStatus = userStatus;
	}
	@Override
	public int hashCode() {
		return Objects.hash(movieID, rateComment, rateDate, rateID, rateValue, userFirstName, userID, userLastName,
				userStatus);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		RateInfo other = (RateInfo) obj;
		return movieID == other.movieID && Objects.equals(rateComment, other.rateComment)
				&& Objects.equals(rateDate, other.rateDate) && rateID == other.rateID && rateValue == other.rateValue
				&& Objects.equals(userFirstName, other.userFirstName) && userID == other.userID
				&& Objects.equals(userLastName, other.userLastName)
				&& Double.doubleToLongBits(userStatus) == Double.doubleToLongBits(other.userStatus);
	}
	@Override
	public String toString() {
		return "RateInfo [rateID=" + rateID + ", movieID=" + movieID + ", userID=" + userID + ", rateValue=" + rateValue
				+ ", rateDate=" + rateDate + ", rateComment=" + rateComment + ", userFirstName=" + userFirstName
				+ ", userLastName=" + userLastName + ", userStatus=" + userStatus + "]";
	}
	
	
}
