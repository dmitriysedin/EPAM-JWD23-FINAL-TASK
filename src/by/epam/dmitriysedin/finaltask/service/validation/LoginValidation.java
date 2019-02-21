package by.epam.dmitriysedin.finaltask.service.validation;

import by.epam.dmitriysedin.finaltask.dao.DAOException;
import by.epam.dmitriysedin.finaltask.dao.DAOProvider;
import by.epam.dmitriysedin.finaltask.dao.UserDAO;
import by.epam.dmitriysedin.finaltask.service.ServiceException;

public class LoginValidation{
	
	public static boolean isLoginContains(String login) throws ServiceException{
		UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
		
		try {
			return userDAO.isLoginContains(login);
		} catch (DAOException e) {
			throw new ServiceException();
		}
	}
	
}
