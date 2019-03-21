package by.epam.dmitriysedin.finaltask.service;

import java.util.List;

import by.epam.dmitriysedin.finaltask.entity.MovieInfo;

public interface MovieService {
	
	List<MovieInfo> selectAllMovies() throws ServiceException;
	
	void addNewMovie(MovieInfo movieInfo) throws ServiceException;
	
	MovieInfo selectConcreteMovie(int id) throws ServiceException;
}
