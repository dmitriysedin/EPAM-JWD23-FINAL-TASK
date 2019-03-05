package by.epam.dmitriysedin.finaltask.dao;

import by.epam.dmitriysedin.finaltask.entity.User;
import by.epam.dmitriysedin.finaltask.entity.UserInfo;

public interface UserDAO {
	
	User authenticate(String login, String password) throws DAOException;
	
	boolean isLoginContains(String login) throws DAOException;
	
	boolean addUser(UserInfo userInfo)  throws DAOException;
}
