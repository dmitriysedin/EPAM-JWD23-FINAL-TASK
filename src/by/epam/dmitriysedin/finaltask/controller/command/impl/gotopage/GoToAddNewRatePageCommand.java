package by.epam.dmitriysedin.finaltask.controller.command.impl.gotopage;

import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.dmitriysedin.finaltask.controller.command.Command;
import by.epam.dmitriysedin.finaltask.controller.command.util.CreatorFullURL;
import by.epam.dmitriysedin.finaltask.entity.MovieInfo;
import by.epam.dmitriysedin.finaltask.entity.RateInfo;
import by.epam.dmitriysedin.finaltask.entity.User;
import by.epam.dmitriysedin.finaltask.service.RateService;
import by.epam.dmitriysedin.finaltask.service.ServiceException;
import by.epam.dmitriysedin.finaltask.service.ServiceProvider;

public class GoToAddNewRatePageCommand implements Command{
	
	private static final String USER_INFO = "user";
	private static final String MOVIE_INFO = "movieInfo";
	private static final Integer ERROR_NUMBER_500 = 500;
	private static final String PARAMETER_PREVIOUS_REQUEST = "prev_request";
	private static final String DOUBLE_ADD_PARAM = "double_add_param";

	private static final String SUCCESS_TARGET_PAGE = "/WEB-INF/jsp/addNewRate.jsp";
	private static final String UNSUCCESS_TARGET_PAGE = "/WEB-INF/jsp/showConcreteMovieInfo.jsp";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		HttpSession session = request.getSession(true);
		
		User user = (User)session.getAttribute(USER_INFO);
		MovieInfo movieInfo = (MovieInfo) session.getAttribute(MOVIE_INFO);
		
		int movieID = movieInfo.getMovieID();
		int userID = user.getUserID();
		
		List<RateInfo> rateList = null;
		String targetPage = SUCCESS_TARGET_PAGE;

		RateService rateService = ServiceProvider.getInstance().getRateService();
		
		try {
			rateList = rateService.selectConcretMovieRates(movieID);
		} catch (ServiceException e) {
			response.sendError(ERROR_NUMBER_500);
			return;
		}
		
		if(isUserContainsInRateList(rateList, userID)){
			request.setAttribute(DOUBLE_ADD_PARAM, "true");
			targetPage = UNSUCCESS_TARGET_PAGE;
		}
		
		String url = CreatorFullURL.create(request);
		
		session = request.getSession(true);
		
		session.setAttribute(PARAMETER_PREVIOUS_REQUEST, url);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(targetPage);
		dispatcher.forward(request, response);
		
	}
	
	private boolean isUserContainsInRateList(List<RateInfo> rateList, int userID) {
		
		for (RateInfo rateInfo : rateList) {
			if(rateInfo.getUserID() == userID) {
				return true;
			}
		}
		
		return false;
	}

}
