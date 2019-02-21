package by.epam.dmitriysedin.finaltask.service;

import by.epam.dmitriysedin.finaltask.service.impl.MovieServiceImpl;
import by.epam.dmitriysedin.finaltask.service.impl.UserServiceImpl;

public class ServiceProvider {
	
	private static final ServiceProvider instance = new ServiceProvider();
	
	private ServiceProvider() {
	}

	private UserService userService = new UserServiceImpl();
	private MovieService movieService = new MovieServiceImpl();

	public static ServiceProvider getInstance() {
		return instance;
	}
	public UserService getUserService() {
		return userService;
	}
	public MovieService getMovieService() {
		return movieService;
	}
	
	
}
