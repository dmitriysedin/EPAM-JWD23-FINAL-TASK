package by.epam.dmitriysedin.finaltask.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.dmitriysedin.finaltask.dao.DAOException;
import by.epam.dmitriysedin.finaltask.dao.DAOProvider;
import by.epam.dmitriysedin.finaltask.dao.RateDAO;
import by.epam.dmitriysedin.finaltask.entity.RateInfo;
import by.epam.dmitriysedin.finaltask.service.RateService;
import by.epam.dmitriysedin.finaltask.service.ServiceException;

public class RateServiceImpl implements RateService{
	
	private static final Logger logger = LogManager.getLogger(RateServiceImpl.class);

	@Override
	public void addRate(RateInfo rateInfo) throws ServiceException {

		RateDAO rateDAO = DAOProvider.getInstance().getRateDAO();

		try {
			if(!rateDAO.addRate(rateInfo)){
				logger.error("Method addRate() in RateServiceImpl returned false");
				throw new ServiceException("Method addRate() returned false");
			}
		} catch (DAOException e) {
			throw new ServiceException(e);
		}

	}

	@Override
	public List<RateInfo> selectConcretMovieRates(int id) throws ServiceException{
		
		RateDAO rateDAO = DAOProvider.getInstance().getRateDAO();
		
		List<RateInfo> ratesList = new ArrayList<>();
		
		try {
			ratesList = rateDAO.selectConcretMovieRates(id);
		} catch (DAOException e) {
			
			throw new ServiceException(e);
		}
		return ratesList;
	}

}
