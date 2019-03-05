package by.epam.dmitriysedin.finaltask.dao;

import by.epam.dmitriysedin.finaltask.dao.impl.SQLMovieDAO;
import by.epam.dmitriysedin.finaltask.dao.impl.SQLRateDAO;
import by.epam.dmitriysedin.finaltask.dao.impl.SQLUserDAO;

public class DAOProvider {

	private static final DAOProvider instance = new DAOProvider();
	
	private final UserDAO userDAO = new SQLUserDAO();
	private final MovieDAO movieDAO = new SQLMovieDAO();
	private final RateDAO rateDAO = new SQLRateDAO();
	
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

	public RateDAO getRateDAO() {
		return rateDAO;
	}
	
	
}
