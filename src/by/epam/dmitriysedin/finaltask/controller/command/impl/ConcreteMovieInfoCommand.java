package by.epam.dmitriysedin.finaltask.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.dmitriysedin.finaltask.controller.command.Command;
import by.epam.dmitriysedin.finaltask.controller.command.util.CreatorFullURL;
import by.epam.dmitriysedin.finaltask.entity.MovieInfo;
import by.epam.dmitriysedin.finaltask.entity.RateInfo;
import by.epam.dmitriysedin.finaltask.service.MovieService;
import by.epam.dmitriysedin.finaltask.service.RateService;
import by.epam.dmitriysedin.finaltask.service.ServiceException;
import by.epam.dmitriysedin.finaltask.service.ServiceProvider;

public class ConcreteMovieInfoCommand implements Command{
	
	private static final String MOVIE_ID = "movie_id";
	private static final String PARAMETER_FIRST_ROW = "firstrow";
	private static final String PARAMETER_LAST_ROW = "lastrow";
	private static final String PARAMETER_RATE_LIST = "ratesList";
	private static final String PARAMETER_MOVIE_INFO = "movieInfo";
	
	private static final String REDIRECT_PAGE_URL = "http://localhost:8080/jwd23_final_task/Servlet?command=goToShowConcreteMovieInfoPageCommand";
	
	public static final int rowsByPage = 5;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MovieInfo movieInfo = null;

		int firstIndex = 0;
		int lastIndex = firstIndex + rowsByPage;

		MovieService movieService = ServiceProvider.getInstance().getMovieService();
		
		try {
			movieInfo = movieService.selectConcreteMovie(Integer.parseInt(request.getParameter(MOVIE_ID)));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		List<RateInfo> rateList = null;
		RateService rateService = ServiceProvider.getInstance().getRateService();
		
		try {
			rateList = rateService.selectConcretMovieRates(Integer.parseInt(request.getParameter(MOVIE_ID)));
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		session.setAttribute(PARAMETER_FIRST_ROW, firstIndex);
		session.setAttribute(PARAMETER_LAST_ROW, lastIndex);
		
		session.setAttribute(PARAMETER_RATE_LIST, rateList);
		session.setAttribute(PARAMETER_MOVIE_INFO, movieInfo);
		
		String url = CreatorFullURL.create(request);
		
		session.setAttribute("prev_request", url);
		
		response.sendRedirect(REDIRECT_PAGE_URL);
		
	}

}
