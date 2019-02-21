package by.epam.dmitriysedin.finaltask.dao;

import by.epam.dmitriysedin.finaltask.dao.impl.SQLMovieDAO;
import by.epam.dmitriysedin.finaltask.dao.impl.SQLUserDAO;

public class DAOProvider {

	private static final DAOProvider instance = new DAOProvider();
	
	private final UserDAO userDAO = new SQLUserDAO();
	private final MovieDAO movieDAO = new SQLMovieDAO();
	
	private DAOProvider() {
		
	}

	public static DAOProvider getInstance() {
		return instance;
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public MovieDAO getMovieDAO() {
		return movieDAO;
	}
	
	
}
