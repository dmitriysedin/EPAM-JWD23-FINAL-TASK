package by.epam.dmitriysedin.finaltask.service;

import java.util.List;

import by.epam.dmitriysedin.finaltask.entity.Movie;
import by.epam.dmitriysedin.finaltask.entity.MovieInfo;

public interface MovieService {
	
	List<Movie> selectAllMovies() throws ServiceException;
	
	boolean isAddedNewMovie(MovieInfo movieInfo) throws ServiceException;
}
