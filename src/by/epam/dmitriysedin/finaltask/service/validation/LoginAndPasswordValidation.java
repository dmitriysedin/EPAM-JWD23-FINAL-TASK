package by.epam.dmitriysedin.finaltask.service.validation;

import by.epam.dmitriysedin.finaltask.dao.DAOException;
import by.epam.dmitriysedin.finaltask.dao.DAOProvider;
import by.epam.dmitriysedin.finaltask.dao.UserDAO;
import by.epam.dmitriysedin.finaltask.entity.User;
import by.epam.dmitriysedin.finaltask.service.ServiceException;
import by.epam.dmitriysedin.finaltask.util.PasswordHash;

public final class LoginAndPasswordValidation{
	
	private LoginAndPasswordValidation() {

	}
	
	public static boolean isLoginContains(String login) throws ServiceException{
		
		User user = null;
		UserDAO userDAO = DAOProvider.getInstance().getUserDAO();
		
		try {
			user = userDAO.findUserByLogin(login);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		return user != null;
	}
	
	public static boolean isPasswordCorrect(User user, String password) throws ServiceException{
		return PasswordHash.checkPassword(password, user.getUserPassword());
	}
	
}
