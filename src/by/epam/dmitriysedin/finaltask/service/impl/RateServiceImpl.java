package by.epam.dmitriysedin.finaltask.service.impl;

import java.util.ArrayList;
import java.util.List;

import by.epam.dmitriysedin.finaltask.dao.DAOException;
import by.epam.dmitriysedin.finaltask.dao.DAOProvider;
import by.epam.dmitriysedin.finaltask.dao.MovieDAO;
import by.epam.dmitriysedin.finaltask.dao.RateDAO;
import by.epam.dmitriysedin.finaltask.entity.RateInfo;
import by.epam.dmitriysedin.finaltask.service.RateService;
import by.epam.dmitriysedin.finaltask.service.ServiceException;

public class RateServiceImpl implements RateService{

	@Override
	public boolean addRate(RateInfo rateInfo) throws ServiceException {

		RateDAO rateDAO = DAOProvider.getInstance().getRateDAO();
		
		boolean isAddedNewRateResult;
		try {
			isAddedNewRateResult = rateDAO.addRate(rateInfo);
		} catch (DAOException e) {
			throw new ServiceException(e);
		}
		
		return isAddedNewRateResult;
	}

	@Override
	public RateInfo selectConcreteRate(int id) throws ServiceException {
		// TODO Auto-generated method stub
		return null;
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
