package by.epam.dmitriysedin.finaltask.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import by.epam.dmitriysedin.finaltask.dao.ConnectionPool;
import by.epam.dmitriysedin.finaltask.dao.DAOException;
import by.epam.dmitriysedin.finaltask.dao.MovieDAO;
import by.epam.dmitriysedin.finaltask.entity.Movie;
import by.epam.dmitriysedin.finaltask.entity.MovieInfo;

public class SQLMovieDAO implements MovieDAO{

	private static final String SELECT_ALL_MOVIES = "SELECT * FROM movies";
	private static final String INSERT_INTO_MOVIES = "INSERT INTO movies(movie_title, movie_director, movie_released_year)"
			+ "VALUES(?, ?, ?)";
	
	@Override
	public List<Movie> selectAll() throws DAOException {
		
		Connection con = null;
		PreparedStatement st= null;
		ResultSet rs = null;
	
		List<Movie> movies = new ArrayList<Movie>();
		
		try {
			con = ConnectionPool.getInstance().getConnection();
			st = con.prepareStatement(SELECT_ALL_MOVIES);

			rs = st.executeQuery();
	
			while(rs.next()) {
				movies.add(createMovie(rs));
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			close(rs, st, con);
		}

		return movies;
	}
	
	@Override
	public boolean isAddedNewMovie(MovieInfo movieInfo)  throws DAOException{
		
		Connection con = null;
		PreparedStatement st= null;
		
		con = ConnectionPool.getInstance().getConnection();
		try {
			st = con.prepareStatement(INSERT_INTO_MOVIES);
			
			st.setString(1, movieInfo.getMovieTitle());
			st.setString(2, movieInfo.getMovieDirector());
			st.setString(3, movieInfo.getMovieReleasedYear());
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			close(null, st, con);
		}

		return true;
	}
	
	private Movie createMovie(ResultSet rs) throws SQLException {
		Movie movie = new Movie();
		
		movie.setMovieID(Integer.parseInt(rs.getString("movie_id")));
		movie.setMovieTitle(rs.getString("movie_title"));
		movie.setMovieDirector(rs.getString("movie_director"));
		movie.setMovieReleasedYear(rs.getString("movie_released_year"));
		
		return movie;
		
	}
	
	private void close(ResultSet rs, PreparedStatement st, Connection con) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			// log
		}
		try {
			if (st != null) {
				st.close();
			}
		} catch (SQLException e) {
			// log
		}
		try {
			if (con != null) {
				con.close();
			}
		} catch (SQLException e) {
			// log
		}
	}	
}
