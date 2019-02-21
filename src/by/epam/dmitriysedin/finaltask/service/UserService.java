package by.epam.dmitriysedin.finaltask.service;

import by.epam.dmitriysedin.finaltask.entity.User;
import by.epam.dmitriysedin.finaltask.entity.UserInfo;

public interface UserService {

	User authentification(String login, String password) throws ServiceException;
	
	boolean registration(UserInfo userInfo)  throws ServiceException;
}
