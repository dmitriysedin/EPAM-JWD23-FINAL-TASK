package by.epam.dmitriysedin.finaltask.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

public class Ban implements Serializable{

	private static final long serialVersionUID = 1L;

	private int banID;
	private int userID;
	private Calendar banStart;
	private Calendar banEnd;
	private String banComment;
	
	public Ban() {
		
	}
	
	public int getBanID() {
		return banID;
	}
	
	public void setBanID(int banID) {
		this.banID = banID;
	}
	
	public int getUserID() {
		return userID;
	}
	
	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	public Calendar getBanStart() {
		return banStart;
	}
	
	public void setBanStart(Calendar banStart) {
		this.banStart = banStart;
	}
	
	public Calendar getBanEnd() {
		return banEnd;
	}
	
	public void setBanEnd(Calendar banEnd) {
		this.banEnd = banEnd;
	}
	
	public String getBanComment() {
		return banComment;
	}
	
	public void setBanComment(String banComment) {
		this.banComment = banComment;
	}

	@Override
	public int hashCode() {
		return Objects.hash(banComment, banEnd, banID, banStart, userID);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Ban)) {
			return false;
		}
		Ban other = (Ban) obj;
		return Objects.equals(banComment, other.banComment) && Objects.equals(banEnd, other.banEnd)
				&& banID == other.banID && Objects.equals(banStart, other.banStart) && userID == other.userID;
	}

	@Override
	public String toString() {
		return "Ban [banID=" + banID + ", userID=" + userID + ", banStart=" + banStart + ", banEnd=" + banEnd
				+ ", banComment=" + banComment + "]";
	}
	
	
}
