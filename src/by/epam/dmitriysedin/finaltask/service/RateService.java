package by.epam.dmitriysedin.finaltask.service;

import java.util.List;

import by.epam.dmitriysedin.finaltask.entity.RateInfo;

public interface RateService {

	List<RateInfo> selectConcretMovieRates(int id) throws ServiceException; 
	
	boolean addRate(RateInfo rateInfo) throws ServiceException;
	
	RateInfo selectConcreteRate(int id) throws ServiceException;
}
