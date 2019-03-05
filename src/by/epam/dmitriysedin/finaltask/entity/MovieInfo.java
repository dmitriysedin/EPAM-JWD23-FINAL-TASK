package by.epam.dmitriysedin.finaltask.entity;

import java.io.Serializable;
import java.util.Objects;

public class MovieInfo implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private int movieID;
	private String movieTitle;
	private String movieDirector;
	private String movieReleasedYear;
	private double movieAVGRate;
	
	public int getMovieID() {
		return movieID;
	}

	public void setMovieID(int movieID) {
		this.movieID = movieID;
	}

	public double getMovieAVGRate() {
		return movieAVGRate;
	}

	public void setMovieAVGRate(double movieAVGRate) {
		this.movieAVGRate = movieAVGRate;
	}

	public MovieInfo() {
		
	}

	public String getMovieTitle() {
		return movieTitle;
	}

	public void setMovieTitle(String title) {
		movieTitle = title;
	}

	public String getMovieDirector() {
		return movieDirector;
	}

	public void setMovieDirector(String director) {
		movieDirector = director;
	}

	public String getMovieReleasedYear() {
		return movieReleasedYear;
	}

	public void setMovieReleasedYear(String releasedYear) {
		movieReleasedYear = releasedYear;
	}

	@Override
	public int hashCode() {
		return Objects.hash(movieDirector, movieID, movieAVGRate, movieReleasedYear, movieTitle);
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
		MovieInfo other = (MovieInfo) obj;
		return Objects.equals(movieDirector, other.movieDirector) && movieID == other.movieID
				&& Double.doubleToLongBits(movieAVGRate) == Double.doubleToLongBits(other.movieAVGRate)
				&& Objects.equals(movieReleasedYear, other.movieReleasedYear)
				&& Objects.equals(movieTitle, other.movieTitle);
	}

	@Override
	public String toString() {
		return "MovieInfo [movieID=" + movieID + ", movieTitle=" + movieTitle + ", movieDirector=" + movieDirector
				+ ", movieReleasedYear=" + movieReleasedYear + ", movieRate=" + movieAVGRate + "]";
	}

	

}
