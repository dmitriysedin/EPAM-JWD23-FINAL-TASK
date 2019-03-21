package by.epam.dmitriysedin.finaltask.service.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.dmitriysedin.finaltask.dao.DAOException;
import by.epam.dmitriysedin.finaltask.dao.DAOProvider;
import by.epam.dmitriysedin.finaltask.dao.UserDAO;
import by.epam.dmitriysedin.finaltask.entity.User;
import by.epam.dmitriysedin.finaltask.entity.UserInfo;
import by.epam.dmitriysedin.finaltask.service.ServiceException;
import by.epam.dmitriysedin.finaltask.service.UserService;
import by.epam.dmitriysedin.finaltask.service.validation.LoginAndPasswordValidation;
import by.epam.dmitriysedin.finaltask.util.PasswordHash;

public class UserServiceImpl implements UserService {
	
	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);
	
	@Override
	public User authentification(String login, String password) throws ServiceException {
		
		if(!LoginAndPasswordValidation.isLoginContains(login)) {
			return null;
		}
		
		UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
		User user = null;
		
		try {
			user = userDAO.findUserByLogin(login);
		} catch (DAOException e) {
			throw new ServiceException();
		}
		
		if(!LoginAndPasswordValidation.isPasswordCorrect(user, password)) {	
			return null;
		}
		
		return user;
	}

	@Override
	public boolean register(UserInfo userInfo) throws ServiceException {
		
		if(LoginAndPasswordValidation.isLoginContains(userInfo.getUserLogin())) {
			return false;
		}
		
		UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
		
		String hashedPass = PasswordHash.hashPassword(userInfo.getUserPassword());
		
		userInfo.setUserPassword(hashedPass);

		try {
			if(!userDAO.addUser(userInfo)){
				logger.error("Method addRate() in UserServiceImpl returned false");
				throw new ServiceException("Method addUser() returned false");
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
	
		return true;
	}
	
}
