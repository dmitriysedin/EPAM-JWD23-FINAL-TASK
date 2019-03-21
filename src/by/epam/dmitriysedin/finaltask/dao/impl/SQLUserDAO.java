package by.epam.dmitriysedin.finaltask.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.dmitriysedin.finaltask.dao.DAOException;
import by.epam.dmitriysedin.finaltask.dao.UserDAO;
import by.epam.dmitriysedin.finaltask.dao.connectionpool.myconnectionpool.ConnectionPoolException;
import by.epam.dmitriysedin.finaltask.dao.connectionpool.myconnectionpool.MyConnectionPool;
import by.epam.dmitriysedin.finaltask.entity.User;
import by.epam.dmitriysedin.finaltask.entity.UserInfo;


public class SQLUserDAO implements UserDAO {

	private static final Logger logger = LogManager.getLogger(SQLUserDAO.class);

	private static final String QUERY_CHECK_LOGIN = "SELECT * FROM users WHERE user_login=?";
	private static final String INSERT_INTO_USERS = "INSERT INTO users(user_login, user_password, user_first_name, "
			+ "user_last_name, user_email, user_role) VALUES(?, ?, ?, ?, ?, ?)";
	private static final String USER_ROLE = "USER";

	public User findUserByLogin(String login) throws DAOException{
		 Connection con = null;
		 ResultSet rs = null;
		 PreparedStatement st = null;
		 User user = null;
		 
		 MyConnectionPool myConnectionPool = MyConnectionPool.getInstance();

		 try {
			 con = myConnectionPool.takeConnection();
			 
			 st = con.prepareStatement(QUERY_CHECK_LOGIN);
			 
			 st.setString(1, login);
			 
			 rs = st.executeQuery();
			 
			 if(rs.next()) {
				 user = createUser(rs);
			 }
			 
		 } catch (ConnectionPoolException | SQLException e) {
			 	logger.error("SQLException in SQLUserDAO/findUserByLogin()", e);
				throw new DAOException(e);
			} finally {
				myConnectionPool.closeConnection(con, st);
			}
		
		return user;
	}

	@Override
	public boolean addUser(UserInfo userInfo) throws DAOException {

		Connection con = null;
		PreparedStatement st = null;
		MyConnectionPool myConnectionPool = MyConnectionPool.getInstance();

		try {
			con = myConnectionPool.takeConnection();
			con.setAutoCommit(false);
			
			st = con.prepareStatement(INSERT_INTO_USERS);

			st.setString(1, userInfo.getUserLogin());
			st.setString(2, userInfo.getUserPassword());
			st.setString(3, userInfo.getUserFirstName());
			st.setString(4, userInfo.getUserLastName());
			st.setString(5, userInfo.getUserEmail());
			st.setString(6, USER_ROLE);
		
			if(st.executeUpdate() > 0) {
			con.commit();
			return true;
			} 

		} catch (ConnectionPoolException | SQLException e) {
			logger.error("SQLException in SQLUserDAO/addUser()", e);
			try {
				con.rollback();
			} catch (SQLException e1) {
				logger.error("SQLException in catch-block SQLUserDAO/addUser()", e1);
				throw new DAOException(e1);
			}
			throw new DAOException(e);
		} finally {
			myConnectionPool.closeConnection(con, st);
		}

		return false;
	}

	private User createUser(ResultSet rs) throws SQLException {
		User user = new User();

		user.setUserLogin(rs.getString("user_login"));
		user.setUserPassword(rs.getString("user_password"));
		user.setUserID(Integer.parseInt(rs.getString("user_id")));
		user.setUserFirstName(rs.getString("user_first_name"));
		user.setUserLastName(rs.getString("user_last_name"));
		user.setUserEmail(rs.getString("user_email"));
		user.setUserRole(rs.getString("user_role"));

		return user;

	}

}
