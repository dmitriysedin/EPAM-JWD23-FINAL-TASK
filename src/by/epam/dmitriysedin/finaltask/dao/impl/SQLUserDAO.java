package by.epam.dmitriysedin.finaltask.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import by.epam.dmitriysedin.finaltask.dao.DAOException;
import by.epam.dmitriysedin.finaltask.dao.UserDAO;
import by.epam.dmitriysedin.finaltask.dao.connectionpool.myconnectionpool.ConnectionPoolException;
import by.epam.dmitriysedin.finaltask.dao.connectionpool.myconnectionpool.MyConnectionPool;
import by.epam.dmitriysedin.finaltask.entity.User;
import by.epam.dmitriysedin.finaltask.entity.UserInfo;
import by.epam.dmitriysedin.finaltask.util.PasswordHash;

public class SQLUserDAO implements UserDAO {

	private static final String QUERY_CHECK_LOGIN = "SELECT * FROM users WHERE user_login=?";
	private static final String INSERT_INTO_USERS = "INSERT INTO users(user_login, user_password, user_first_name, "
			+ "user_last_name, user_email, user_role) VALUES(?, ?, ?, ?, ?, ?)";

	public boolean isLoginContains(String login) {
		 Connection con = null;
		 ResultSet rs = null;
		 PreparedStatement st = null;
		 
		 MyConnectionPool myConnectionPool = MyConnectionPool.getInstance();

		 try {
			 con = myConnectionPool.takeConnection();
			 
			 st = con.prepareStatement(QUERY_CHECK_LOGIN);
			 
			 st.setString(1, login);
			 
			 rs = st.executeQuery();
			 
			 if(rs.next()) {
				 return true;
			 }
			 
		} catch (ConnectionPoolException e1) {
				// log + throw exception
		} catch (Exception e) {//SQLException
			// throw DAOException
		} finally {
			myConnectionPool.closeConnection(con, st, rs);
		}
		
		return false;
	}

	@Override
	public User authenticate(String login, String password) throws DAOException {

		Connection con = null;
		PreparedStatement st = null;
		ResultSet rs = null;

		User user = null;

		MyConnectionPool myConnectionPool = MyConnectionPool.getInstance();

		try {
			con = myConnectionPool.takeConnection();
			
			st = con.prepareStatement(QUERY_CHECK_LOGIN);

			st.setString(1, login);

			rs = st.executeQuery();

			if (rs.next() && PasswordHash.checkPassword(password, rs.getString("user_password"))) {
				user = createUser(rs);
			}

		} catch (ConnectionPoolException e1) {
			// log + throw exception
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			myConnectionPool.closeConnection(con, st, rs);
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
			
			st = con.prepareStatement(INSERT_INTO_USERS);

			st.setString(1, userInfo.getUserLogin());

			String hashedPass = PasswordHash.hashPassword(userInfo.getUserPassword());

			st.setString(2, hashedPass);
			st.setString(3, userInfo.getUserFirstName());
			st.setString(4, userInfo.getUserLastName());
			st.setString(5, userInfo.getUserEmail());
			st.setString(6, "ADMIN");

			st.executeUpdate();

		} catch (ConnectionPoolException e1) {
			// log + throw exception
		} catch (SQLException e) {
			throw new DAOException(e);
		} finally {
			myConnectionPool.closeConnection(con, st);
		}

		return true;
	}

	private User createUser(ResultSet rs) throws SQLException {
		User user = new User();

		user.setUserLogin(rs.getString("user_login"));
		user.setUserID(Integer.parseInt(rs.getString("user_id")));
		user.setUserFirstName(rs.getString("user_first_name"));
		user.setUserLastName(rs.getString("user_last_name"));
		user.setUserEmail(rs.getString("user_email"));
		user.setUserRole(rs.getString("user_role"));

		return user;

	}

}
