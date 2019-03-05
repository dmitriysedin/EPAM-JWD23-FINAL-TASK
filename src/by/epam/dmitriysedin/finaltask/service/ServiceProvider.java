package by.epam.dmitriysedin.finaltask.service;

import by.epam.dmitriysedin.finaltask.service.impl.MovieServiceImpl;
import by.epam.dmitriysedin.finaltask.service.impl.RateServiceImpl;
import by.epam.dmitriysedin.finaltask.service.impl.UserServiceImpl;

public class ServiceProvider {
	
	private static final ServiceProvider instance = new ServiceProvider();
	
	private ServiceProvider() {
	}

	private UserService userService = new UserServiceImpl();
	private MovieService movieService = new MovieServiceImpl();
	private RateService rateService = new RateServiceImpl();

	public static ServiceProvider getInstance() {
		return instance;
	}
	public UserService getUserService() {
		return userService;
	}
	public MovieService getMovieService() {
		return movieService;
	}
	public RateService getRateService() {
		return rateService;
	}
	
	
}
