package by.epam.dmitriysedin.finaltask.dao;

import java.util.List;

import by.epam.dmitriysedin.finaltask.entity.Movie;
import by.epam.dmitriysedin.finaltask.entity.MovieInfo;

public interface MovieDAO {

	List<Movie> selectAll() throws DAOException;

	boolean isAddedNewMovie(MovieInfo movieInfo) throws DAOException;
}
