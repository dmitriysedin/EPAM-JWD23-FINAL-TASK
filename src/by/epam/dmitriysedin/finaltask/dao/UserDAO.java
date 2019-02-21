package by.epam.dmitriysedin.finaltask.dao;

import by.epam.dmitriysedin.finaltask.entity.User;
import by.epam.dmitriysedin.finaltask.entity.UserInfo;

public interface UserDAO {
	
	User authentification(String login, String password) throws DAOException;
	
	boolean isLoginContains(String login) throws DAOException;
	
	boolean registration(UserInfo userInfo)  throws DAOException;
}
