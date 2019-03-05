package by.epam.dmitriysedin.finaltask.controller.command.impl;

import java.io.IOException;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.dmitriysedin.finaltask.controller.command.Command;
import by.epam.dmitriysedin.finaltask.controller.command.util.CreatorFullURL;
import by.epam.dmitriysedin.finaltask.entity.MovieInfo;
import by.epam.dmitriysedin.finaltask.entity.RateInfo;
import by.epam.dmitriysedin.finaltask.entity.User;
import by.epam.dmitriysedin.finaltask.entity.UserInfo;
import by.epam.dmitriysedin.finaltask.service.MovieService;
import by.epam.dmitriysedin.finaltask.service.RateService;
import by.epam.dmitriysedin.finaltask.service.ServiceException;
import by.epam.dmitriysedin.finaltask.service.ServiceProvider;

public class AddNewRateCommand implements Command {

	private static final String RATE_VALUE = "rate_value";
	private static final String RATE_COMMENT = "rate_comment";
	private static final String USER_INFO = "user";
	private static final String MOVIE_INFO = "movieInfo";
	
	private static final String REDIRECT_PAGE_URL = "http://localhost:8080/jwd23_final_task/Servlet?command=goToShowConcreteMovieInfoPageCommand";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Timestamp rateDate = new Timestamp(System.currentTimeMillis());
		int rateValue;
		String rateComment;
		int userID;
		int movieID;
		
		User user = null;
		MovieInfo movieInfo = null;

		HttpSession session;
		
		session = request.getSession(true);
		
		
		rateValue = Integer.parseInt(request.getParameter(RATE_VALUE));
		rateComment = request.getParameter(RATE_COMMENT);
		user = (User)session.getAttribute(USER_INFO);
		movieInfo = (MovieInfo) session.getAttribute(MOVIE_INFO);
		
		movieID = movieInfo.getMovieID();
		userID = user.getUserID();
		
		RateService rateService = ServiceProvider.getInstance().getRateService();
		
		try {
			rateService.addRate(createRateInfo(movieID, userID, rateValue, rateComment, rateDate));
		} catch (ServiceException e) {
			// log
		}
		
		
		String url = CreatorFullURL.create(request);
		
		session.setAttribute("prev_request", url);
		
		response.sendRedirect(REDIRECT_PAGE_URL);
		
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
