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
	public List<MovieInfo> selectAllMovies() throws ServiceException {
		
		MovieDAO movieDAO = DAOProvider.getInstance().getMovieDAO();
		
		List<MovieInfo> moviesInfo = new ArrayList<MovieInfo>();
		
		try {
			moviesInfo = movieDAO.selectAll();
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return moviesInfo;
	}
	
	@Override
	public MovieInfo selectConcreteMovie(int id) throws ServiceException {

		MovieDAO movieDAO = DAOProvider.getInstance().getMovieDAO();
		
		MovieInfo movieInfo = null;
		
		try {
			movieInfo = movieDAO.selectConcreteMovie(id);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return movieInfo;
	}

	@Override
	public boolean addNewMovie(MovieInfo movieInfo) throws ServiceException {
		
		MovieDAO movieDAO = DAOProvider.getInstance().getMovieDAO();
		
		boolean isAddedNewMovieResult;
		try {
			isAddedNewMovieResult = movieDAO.addMovie(movieInfo);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return isAddedNewMovieResult;
	}

	
}
