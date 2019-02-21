package by.epam.dmitriysedin.finaltask.entity;

import java.io.Serializable;
import java.util.Objects;

public class Movie implements Serializable{

	private static final long serialVersionUID = 1L;

	private int movieID;
	private String movieTitle;
	private String movieDirector;
	private String movieReleasedYear;
	
	public Movie() {
		
	}

	public int getMovieID() {
		return movieID;
	}

	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		this.movieTitle = movieTitle;
	}

	public String getMovieDirector() {
		return movieDirector;
	}

	public void setMovieDirector(String movieDirector) {
		this.movieDirector = movieDirector;
	}

	public String getMovieReleasedYear() {
		return movieReleasedYear;
	}

	public void setMovieReleasedYear(String movieReleasedYear) {
		this.movieReleasedYear = movieReleasedYear;
	}

	@Override
	public int hashCode() {
		return Objects.hash(movieDirector, movieID, movieReleasedYear, movieTitle);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Movie)) {
			return false;
		}
		Movie other = (Movie) obj;
		return  Objects.equals(movieDirector, other.movieDirector)
				&& movieID == other.movieID && Objects.equals(movieReleasedYear, other.movieReleasedYear)
				&& Objects.equals(movieTitle, other.movieTitle);
	}

	@Override
	public String toString() {
		return "Movie [movieID=" + movieID + ", movieTitle=" + movieTitle + ", movieDirector=" + movieDirector
				+ ", movieReleasedYear=" + movieReleasedYear + ", categotiesID=" + "]";
	}
	
	
}
