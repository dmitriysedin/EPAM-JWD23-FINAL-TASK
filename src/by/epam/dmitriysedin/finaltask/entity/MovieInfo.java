package by.epam.dmitriysedin.finaltask.entity;

import java.util.Objects;

public class MovieInfo {
	
	private String MovieTitle;
	private String MovieDirector;
	private String MovieReleasedYear;
	
	public MovieInfo() {
		
	}

	public String getMovieTitle() {
		return MovieTitle;
	}

	public void setMovieTitle(String movieTitle) {
		MovieTitle = movieTitle;
	}

	public String getMovieDirector() {
		return MovieDirector;
	}

	public void setMovieDirector(String movieDirector) {
		MovieDirector = movieDirector;
	}

	public String getMovieReleasedYear() {
		return MovieReleasedYear;
	}

	public void setMovieReleasedYear(String movieReleasedYear) {
		MovieReleasedYear = movieReleasedYear;
	}

	@Override
	public int hashCode() {
		return Objects.hash(MovieDirector, MovieReleasedYear, MovieTitle);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof MovieInfo)) {
			return false;
		}
		MovieInfo other = (MovieInfo) obj;
		return Objects.equals(MovieDirector, other.MovieDirector)
				&& Objects.equals(MovieReleasedYear, other.MovieReleasedYear)
				&& Objects.equals(MovieTitle, other.MovieTitle);
	}

}
