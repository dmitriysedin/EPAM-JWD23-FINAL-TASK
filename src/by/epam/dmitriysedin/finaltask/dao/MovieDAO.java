package by.epam.dmitriysedin.finaltask.dao;

import java.util.List;

import by.epam.dmitriysedin.finaltask.entity.MovieInfo;

public interface MovieDAO {

	List<MovieInfo> selectAll() throws DAOException;

	boolean addMovie(MovieInfo movieInfo) throws DAOException;
	
	MovieInfo selectConcreteMovie(int id) throws DAOException;
}
