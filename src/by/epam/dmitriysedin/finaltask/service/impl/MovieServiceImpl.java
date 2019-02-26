package by.epam.dmitriysedin.finaltask.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.dmitriysedin.finaltask.dao.DAOException;
import by.epam.dmitriysedin.finaltask.dao.DAOProvider;
import by.epam.dmitriysedin.finaltask.dao.MovieDAO;

import by.epam.dmitriysedin.finaltask.entity.Movie;
import by.epam.dmitriysedin.finaltask.entity.MovieInfo;
import by.epam.dmitriysedin.finaltask.service.MovieService;
import by.epam.dmitriysedin.finaltask.service.ServiceException;

public class MovieServiceImpl implements MovieService{

	@Override
	public List<Movie> selectAllMovies() throws ServiceException {
		
		MovieDAO movieDAO = DAOProvider.getInstance().getMovieDAO();
		
		List<Movie> movies = new ArrayList<Movie>();
		
		try {
			movies = movieDAO.selectAll();
		} catch (DAOException e) {
			e.printStackTrace();
		}
		
		return movies;
	}

	@Override
	public boolean isAddedNewMovie(MovieInfo movieInfo) throws ServiceException {
		
		MovieDAO movieDAO = DAOProvider.getInstance().getMovieDAO();
		
		boolean isAddedNewMovieResult;
		try {
			isAddedNewMovieResult = movieDAO.isAddedNewMovie(movieInfo);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return isAddedNewMovieResult;
	}

	
}
