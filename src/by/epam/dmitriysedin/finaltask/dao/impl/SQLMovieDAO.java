package by.epam.dmitriysedin.finaltask.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.dmitriysedin.finaltask.dao.DAOException;
import by.epam.dmitriysedin.finaltask.dao.MovieDAO;
import by.epam.dmitriysedin.finaltask.dao.connectionpool.myconnectionpool.ConnectionPoolException;
import by.epam.dmitriysedin.finaltask.dao.connectionpool.myconnectionpool.MyConnectionPool;
import by.epam.dmitriysedin.finaltask.entity.MovieInfo;

public class SQLMovieDAO implements MovieDAO{
	
	private static final Logger logger = LogManager.getLogger(SQLMovieDAO.class);

	private static final String SELECT_ALL_MOVIES = "SELECT * FROM all_movies_rate";
	private static final String SELECT_CONCRETE_MOVIE = "SELECT * FROM all_movies_rate WHERE movie_id = ?";
	private static final String INSERT_INTO_MOVIES = "INSERT INTO movies(movie_title, movie_director, movie_released_year)"
			+ "VALUES(?, ?, ?)";
	private static final int DEFAULT_AVG_RATE = 0;
	
	@Override
	public List<MovieInfo> selectAll() throws DAOException {
		
		Connection con = null;
		PreparedStatement st= null;
		ResultSet rs = null;
		
		MyConnectionPool myConnectionPool = MyConnectionPool.getInstance();
	
		List<MovieInfo> movies = new ArrayList<MovieInfo>();
		
		try {
			con = myConnectionPool.takeConnection();
				
			st = con.prepareStatement(SELECT_ALL_MOVIES);

			rs = st.executeQuery();
			
			while(rs.next()) {
				movies.add(createMovieInfo(rs));
			}

		} catch (ConnectionPoolException | SQLException e) {
			logger.error("SQLException in SQLMovieDAO/selectAll()", e);
			throw new DAOException(e);
		} finally {
			myConnectionPool.closeConnection(con, st);
		}

		return movies;
	}
	
	@Override
	public MovieInfo selectConcreteMovie(int id) throws DAOException {

		Connection con = null;
		PreparedStatement st= null;
		ResultSet rs = null;
		MovieInfo movieInfo = null;
		
		MyConnectionPool myConnectionPool = MyConnectionPool.getInstance();
		
		try {
			con = myConnectionPool.takeConnection();
			
			st = con.prepareStatement(SELECT_CONCRETE_MOVIE);
			
			st.setInt(1, id);
			
			rs = st.executeQuery();
			
			while(rs.next()) {
				movieInfo = createMovieInfo(rs);
			}
			
		} catch (ConnectionPoolException | SQLException e) {
			logger.error("SQLException in SQLMovieDAO/selectConcreteMovie()", e);
			throw new DAOException(e);
		} finally {
			myConnectionPool.closeConnection(con, st);
		}

		return movieInfo;
	}

	@Override
	public boolean addMovie(MovieInfo movieInfo)  throws DAOException{
		
		Connection con = null;
		PreparedStatement st= null;
		
		MyConnectionPool myConnectionPool = MyConnectionPool.getInstance();

		try {
			con = myConnectionPool.takeConnection();
			con.setAutoCommit(false);
			st = con.prepareStatement(INSERT_INTO_MOVIES);
			

			st.setString(1, movieInfo.getMovieTitle());
			st.setString(2, movieInfo.getMovieDirector());
			st.setString(3, movieInfo.getMovieReleasedYear());
			
			if(st.executeUpdate() > 0) {
			con.commit();
			return true;
			}
			
		} catch (ConnectionPoolException | SQLException e) {
			logger.error("SQLException in SQLMovieDAO/addMovie()", e);
			try {
				con.rollback();
			} catch (SQLException e1) {
				logger.error("SQLException in catch-block in SQLMovieDAO/addMovie()", e1);
				throw new DAOException(e1);
			}
			throw new DAOException(e);
		} finally {
			myConnectionPool.closeConnection(con, st);
		}
		return false;
	}
	
	private MovieInfo createMovieInfo(ResultSet rs) throws SQLException {
		MovieInfo movieInfo = new MovieInfo();
		
		movieInfo.setMovieID(Integer.parseInt(rs.getString("movie_id")));
		movieInfo.setMovieTitle(rs.getString("movie_title"));
		movieInfo.setMovieDirector(rs.getString("movie_director"));
		movieInfo.setMovieReleasedYear(rs.getString("movie_released_year"));
		
		String avgRateString = rs.getString("avg_rate_value");
		double avg_rate = DEFAULT_AVG_RATE;
		
		if(rs.getString("avg_rate_value") != null) {
			avg_rate = Double.parseDouble(avgRateString);
		}
		
		movieInfo.setMovieAVGRate(avg_rate);
		
		return movieInfo;
		
	}
	
}
