package by.epam.dmitriysedin.finaltask.dao;

import java.util.List;

import by.epam.dmitriysedin.finaltask.entity.Movie;

public interface MovieDAO {

	List<Movie> selectAll() throws DAOException;
}
