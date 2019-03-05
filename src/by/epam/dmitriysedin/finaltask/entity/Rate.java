package by.epam.dmitriysedin.finaltask.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

public class Rate implements Serializable{

	private static final long serialVersionUID = 1L;

	private int rateID;
	private int movieID;
	private int userID;
	private int rateValue;
	private Calendar rateDate;
	private String rateComment;
	
	public Rate() {
		
	}

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

	public void setRateValue(int rateValueString) {
		this.rateValue = rateValueString;
	}

	public Calendar getRateDate() {
		return rateDate;
	}

	public void setRateDate(Calendar rateDate) {
		this.rateDate = rateDate;
	}

	public String getRateComment() {
		return rateComment;
	}

	public void setRateComment(String rateComment) {
		this.rateComment = rateComment;
	}

	@Override
	public int hashCode() {
		return Objects.hash(movieID, rateComment, rateDate, rateID, rateValue, userID);
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
		Rate other = (Rate) obj;
		return movieID == other.movieID && Objects.equals(rateComment, other.rateComment)
				&& Objects.equals(rateDate, other.rateDate) && rateID == other.rateID && rateValue == other.rateValue
				&& userID == other.userID;
	}

	@Override
	public String toString() {
		return "Rate [rateID=" + rateID + ", movieID=" + movieID + ", userID=" + userID + ", rateValue=" + rateValue
				+ ", rateDate=" + rateDate + ", rateComment=" + rateComment + "]";
	}
	

}
