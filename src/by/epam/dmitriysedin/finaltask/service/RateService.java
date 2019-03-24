package by.epam.dmitriysedin.finaltask.service;

import java.util.List;

import by.epam.dmitriysedin.finaltask.entity.RateInfo;

public interface RateService {

	List<RateInfo> selectConcretMovieRates(int id) throws ServiceException; 
	
	void addRate(RateInfo rateInfo) throws ServiceException;
	
}
