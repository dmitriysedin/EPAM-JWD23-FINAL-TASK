package by.epam.dmitriysedin.finaltask.controller.command.impl;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.dmitriysedin.finaltask.controller.command.Command;
import by.epam.dmitriysedin.finaltask.controller.command.util.CreatorFullURL;
import by.epam.dmitriysedin.finaltask.entity.MovieInfo;
import by.epam.dmitriysedin.finaltask.entity.RateInfo;
import by.epam.dmitriysedin.finaltask.entity.User;
import by.epam.dmitriysedin.finaltask.service.MovieService;
import by.epam.dmitriysedin.finaltask.service.RateService;
import by.epam.dmitriysedin.finaltask.service.ServiceException;
import by.epam.dmitriysedin.finaltask.service.ServiceProvider;

public class AddNewRateCommand implements Command {

	private static final String PARAMETER_RATE_LIST = "ratesList";
	private static final String PARAMETER_MOVIES_LIST = "moviesList";
	private static final String RATE_VALUE = "rate_value";
	private static final String RATE_COMMENT = "rate_comment";
	private static final String USER_INFO = "user";
	private static final String MOVIE_INFO = "movieInfo";
	private static final String PARAMETER_NUMBER_OF_PAGES = "concreteMovieNumberOfPages";
	private static final Integer ERROR_NUMBER_500 = 500;
	private static final String PARAMETER_PREVIOUS_REQUEST = "prev_request";
	
	private static final String REDIRECT_PAGE_URL = "http://localhost:8080/jwd23_final_task/Servlet?command=goToShowConcreteMovieInfoPageCommand";
	private static final String SUCCESS_URL_PARAM = "&success_param=true";
	
	public static final int rowsByPage = 5;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Timestamp rateDate = new Timestamp(System.currentTimeMillis());
		HttpSession session = request.getSession(true);
		int rateValue = Integer.parseInt(request.getParameter(RATE_VALUE));
		String rateComment = request.getParameter(RATE_COMMENT);
		User user = (User)session.getAttribute(USER_INFO);
		MovieInfo movieInfo = (MovieInfo) session.getAttribute(MOVIE_INFO);
		int movieID = movieInfo.getMovieID();
		int userID = user.getUserID();
		RateService rateService = ServiceProvider.getInstance().getRateService();
		MovieService movieService = ServiceProvider.getInstance().getMovieService();
		String url = CreatorFullURL.create(request);
		
		List<RateInfo> rateList = null;
		
		try {
			rateList = rateService.selectConcretMovieRates(movieID);
		
			rateService.addRate(createRateInfo(movieID, userID, rateValue, rateComment, rateDate));
		
			session.setAttribute(PARAMETER_RATE_LIST, rateList = rateService.selectConcretMovieRates(movieID));
		
			session.setAttribute(PARAMETER_MOVIES_LIST, movieService.selectAllMovies());
		
			session.setAttribute(MOVIE_INFO, movieService.selectConcreteMovie(movieID));
			
		} catch (ServiceException e) {
			response.sendError(ERROR_NUMBER_500);
			return;
		}
		
		int numbOfPages = (int) Math.ceil(rateList.size() / (double)rowsByPage);
		
		session.setAttribute(PARAMETER_NUMBER_OF_PAGES, numbOfPages);
		
		session.setAttribute(PARAMETER_PREVIOUS_REQUEST, url);
		
		response.sendRedirect(REDIRECT_PAGE_URL + SUCCESS_URL_PARAM);
		
	}
	
	private RateInfo createRateInfo(int movieID, int userID, int rateValue, String rateComment, Timestamp rateDate) {
		RateInfo rateInfo = new RateInfo();
		
		rateInfo.setMovieID(movieID);
		rateInfo.setUserID(userID);
		rateInfo.setRateValue(rateValue);
		rateInfo.setRateComment(rateComment);
		rateInfo.setRateDate(rateDate);
		
		return rateInfo;
	}

}
