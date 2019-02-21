package by.epam.dmitriysedin.finaltask.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

public class Rate implements Serializable{

	private static final long serialVersionUID = 1L;

	private int rateID;
	private Movie movieID;
	private User userID;
	private String rateValueString;
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

	public Movie getMovieID() {
		return movieID;
	}

	public void setMovieID(Movie movieID) {
		this.movieID = movieID;
	}

	public User getUserID() {
		return userID;
	}

	public void setUserID(User userID) {
		this.userID = userID;
	}

	public String getRateValueString() {
		return rateValueString;
	}

	public void setRateValueString(String rateValueString) {
		this.rateValueString = rateValueString;
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
		return Objects.hash(movieID, rateComment, rateDate, rateID, rateValueString, userID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Rate)) {
			return false;
		}
		Rate other = (Rate) obj;
		return Objects.equals(movieID, other.movieID) && Objects.equals(rateComment, other.rateComment)
				&& Objects.equals(rateDate, other.rateDate) && rateID == other.rateID
				&& Objects.equals(rateValueString, other.rateValueString) && Objects.equals(userID, other.userID);
	}

	@Override
	public String toString() {
		return "Rate [rateID=" + rateID + ", movieID=" + movieID + ", userID=" + userID + ", rateValueString="
				+ rateValueString + ", rateDate=" + rateDate + ", rateComment=" + rateComment + "]";
	}
	
	
}
