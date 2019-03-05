package by.epam.dmitriysedin.finaltask.dao;

import java.util.List;

import by.epam.dmitriysedin.finaltask.entity.RateInfo;

public interface RateDAO {

	boolean addRate(RateInfo movieInfo) throws DAOException;
	
	RateInfo selectConcreteRate(int id) throws DAOException;

	List<RateInfo> selectConcretMovieRates(int id) throws DAOException;
}
