package by.epam.dmitriysedin.finaltask.service.impl;

import by.epam.dmitriysedin.finaltask.dao.DAOException;
import by.epam.dmitriysedin.finaltask.dao.DAOProvider;
import by.epam.dmitriysedin.finaltask.dao.UserDAO;
import by.epam.dmitriysedin.finaltask.entity.User;
import by.epam.dmitriysedin.finaltask.entity.UserInfo;
import by.epam.dmitriysedin.finaltask.service.ServiceException;
import by.epam.dmitriysedin.finaltask.service.UserService;
import by.epam.dmitriysedin.finaltask.service.validation.LoginValidation;

public class UserServiceImpl implements UserService {
	
	private static final int wrongUserID = -1;

	@Override
	public User authentification(String login, String password) throws ServiceException {
		
		User user = null;
		
		if(!LoginValidation.isLoginContains(login)) {
			
			user = new User();
			user.setUserID(wrongUserID);
			
			return user;
		}
		
		UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
		
		try {
			user = userDAO.authentification(login, password);
		} catch (DAOException e) {
			throw new ServiceException();
		}
		
		return user;
	}

	@Override
	public boolean registration(UserInfo userInfo) throws ServiceException {
		
		UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
		
		boolean registrationResult;
		try {
			registrationResult = userDAO.registration(userInfo);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return registrationResult;
	}
	
}
