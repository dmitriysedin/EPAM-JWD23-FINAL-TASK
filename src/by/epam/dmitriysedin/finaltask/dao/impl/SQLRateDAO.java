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
import by.epam.dmitriysedin.finaltask.dao.RateDAO;
import by.epam.dmitriysedin.finaltask.dao.connectionpool.myconnectionpool.ConnectionPoolException;
import by.epam.dmitriysedin.finaltask.dao.connectionpool.myconnectionpool.MyConnectionPool;
import by.epam.dmitriysedin.finaltask.entity.RateInfo;

public class SQLRateDAO implements RateDAO{

	private static final Logger logger = LogManager.getLogger(SQLRateDAO.class);
	
	//private static final String SELECT_CONCRETE_USER_RATES = "SELECT * FROM rates_with_user_name_and_user_status WHERE users_user_id = ?";
	private static final String SELECT_CONCRETE_MOVIE_RATES = "SELECT * FROM rates_with_user_name_and_user_status WHERE movies_movie_id = ?";
	private static final String INSERT_INTO_RATES = "INSERT INTO rates(movies_movie_id, users_user_id, rate_value, rate_date, rate_comment)"
			+ "VALUES(?, ?, ?, ?, ?)";
	
	@Override
	public List<RateInfo> selectConcretMovieRates(int id) throws DAOException {
		
		Connection con = null;
		PreparedStatement st= null;
		ResultSet rs = null;
		
		MyConnectionPool myConnectionPool = MyConnectionPool.getInstance();
	
		List<RateInfo> rates = new ArrayList<RateInfo>();
		
		try {
			con = myConnectionPool.takeConnection();
			
			st = con.prepareStatement(SELECT_CONCRETE_MOVIE_RATES);
			
			st.setInt(1, id);

			rs = st.executeQuery();
	
			while(rs.next()) {
				rates.add(createRateInfo(rs));
			}

		} catch (ConnectionPoolException | SQLException e) {
			logger.error("SQLException in SQLRateDAO/selectConcretMovieRates()", e);
			throw new DAOException(e);
		} finally {
			myConnectionPool.closeConnection(con, st);
		}

		return rates;

	}

	@Override
	public boolean addRate(RateInfo rateInfo) throws DAOException {
		
		Connection con = null;
		PreparedStatement st= null;
		
		MyConnectionPool myConnectionPool = MyConnectionPool.getInstance();

		try {
			con = myConnectionPool.takeConnection();
			con.setAutoCommit(false);
			st = con.prepareStatement(INSERT_INTO_RATES);
			
			st.setInt(1, rateInfo.getMovieID());
			st.setInt(2, rateInfo.getUserID());
			st.setInt(3, rateInfo.getRateValue());
			st.setTimestamp(4, rateInfo.getRateDate());
			st.setString(5, rateInfo.getRateComment());

			if(st.executeUpdate() > 0) {
			con.commit();
			return true;
			}
			
		} catch (ConnectionPoolException | SQLException e) {
			logger.error("SQLException in SQLRateDAO/addRate()", e);
			try {
				con.rollback();
			} catch (SQLException e1) {
				logger.error("SQLException in catch-block in SQLRateDAO/addRate()", e);
				throw new DAOException(e1);
			}
			throw new DAOException(e);
		} finally {
			myConnectionPool.closeConnection(con, st);
		}
		return false;
	}

	@Override
	public RateInfo selectConcreteRate(int id) throws DAOException {
		// TODO Auto-generated method stub
		return null;
	}

	private RateInfo createRateInfo(ResultSet rs) throws SQLException {
		RateInfo rateInfo = new RateInfo();
		
		rateInfo.setMovieID(Integer.parseInt(rs.getString("movies_movie_id")));
		rateInfo.setUserID(Integer.parseInt(rs.getString("users_user_id")));
		rateInfo.setRateValue(Integer.parseInt(rs.getString("rate_value")));
		rateInfo.setRateDate(rs.getTimestamp("rate_date"));
		rateInfo.setUserFirstName(rs.getString("user_first_name"));
		rateInfo.setUserLastName(rs.getString("user_last_name"));
		rateInfo.setRateComment(rs.getString("rate_comment"));
		
		return rateInfo;
		
	}
}
