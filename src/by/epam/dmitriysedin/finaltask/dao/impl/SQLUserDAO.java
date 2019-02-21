package by.epam.dmitriysedin.finaltask.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.epam.dmitriysedin.finaltask.dao.ConnectionPool;
import by.epam.dmitriysedin.finaltask.dao.DAOException;
import by.epam.dmitriysedin.finaltask.dao.UserDAO;
import by.epam.dmitriysedin.finaltask.entity.User;
import by.epam.dmitriysedin.finaltask.entity.UserInfo;

public class SQLUserDAO implements UserDAO{
	
	private static final String QUERY_CHECK_LOGIN = "SELECT * FROM users WHERE user_login=?";
	private static final String QUERY_CHECK_CREDENTIALS = "SELECT * FROM users WHERE user_login=? and user_password=?";
	private static final String INSERT_INTO_USERS = "INSERT INTO users(user_login, user_password, user_first_name, "
			+ "user_last_name, user_email) VALUES(?, ?, ?, ?, ?)";
	
	public boolean isLoginContains(String login) {
		 Connection connection = null;
		 ResultSet rSet = null;
		 PreparedStatement pStatement = null;
		 
		 try {
			 connection = ConnectionPool.getInstance().getConnection();
			 pStatement = connection.prepareStatement(QUERY_CHECK_LOGIN);
			 
			 pStatement.setString(1, login);
			 
			 rSet = pStatement.executeQuery();
			 
			 if(rSet.next()) {
				 return true;
			 }
			 
		 } catch (Exception e) {//SQLException
			// throw DAOException
		} finally {
			close(rSet, pStatement, connection);
		}
		
		return false;
	}

	@Override
	public User authentification(String login, String password) throws DAOException{
	
		Connection con = null;
		PreparedStatement st= null;
		ResultSet rs = null;
		
		User user = null;
		
		try {
			con = ConnectionPool.getInstance().getConnection();
			st = con.prepareStatement(QUERY_CHECK_CREDENTIALS);

			st.setString(1, login);
			st.setString(2, password);
			
			rs = st.executeQuery();

			if(rs.next()) {
				user  = createUser(rs);
			}

		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			close(rs, st, con);
		}

		return user;
}
	
	@Override
	public boolean registration(UserInfo userInfo)  throws DAOException{
		
		Connection con = null;
		PreparedStatement st= null;
		
		con = ConnectionPool.getInstance().getConnection();
		try {
			st = con.prepareStatement(INSERT_INTO_USERS);
			
			st.setString(1, userInfo.getUserLogin());
			st.setString(2, userInfo.getUserPassword());
			st.setString(3, userInfo.getUserFirstName());
			st.setString(4, userInfo.getUserLastName());
			st.setString(5, userInfo.getUserEmail());
			
			st.executeUpdate();
			
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			close(null, st, con);
		}
		
		return true;
	}
	
	private User createUser(ResultSet rs) throws SQLException {
		User user = new User();
		
		user.setUserID(Integer.parseInt(rs.getString("user_id")));
		user.setUserFirstName(rs.getString("user_first_name"));
		user.setUserLastName(rs.getString("user_last_name"));
		user.setUserEmail(rs.getString("user_email"));
		user.setUserRole(rs.getString("user_role"));
		
		return user;
		
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
