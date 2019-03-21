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
	private static final String PARAMETER_FIRST_ROW = "concreteMovieFirstRow";
	private static final String PARAMETER_LAST_ROW = "concreteMovieLastRow";
	private static final String PARAMETER_RATE_LIST = "ratesList";
	private static final String PARAMETER_MOVIE_INFO = "movieInfo";
	private static final String PARAMETER_NUMBER_OF_PAGES = "concreteMovieNumberOfPages";
	private static final String PARAMETER_NUMBER_OF_CURRENT_PAGE = "concreteMovieNumberOfCurrentPage";
	private static final String PARAMETER_PREVIOUS_REQUEST = "prev_request";
	private static final Integer ERROR_NUMBER_500 = 500;
	
	private static final String REDIRECT_PAGE_URL = "http://localhost:8080/jwd23_final_task/Servlet?command=goToShowConcreteMovieInfoPageCommand";
	
	public static final int rowsByPage = 5;
	
	public static final int pageNumber = 1;
	
	public static final int defaultNumberOfPages = 1;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		MovieInfo movieInfo = null;
		List<RateInfo> rateList = null;
		
		String url = CreatorFullURL.create(request);

		int firstIndex = 0;
		int lastIndex = firstIndex + rowsByPage;

		MovieService movieService = ServiceProvider.getInstance().getMovieService();
		RateService rateService = ServiceProvider.getInstance().getRateService();
		
		try {
			movieInfo = movieService.selectConcreteMovie(Integer.parseInt(request.getParameter(MOVIE_ID)));
		
			rateList = rateService.selectConcretMovieRates(Integer.parseInt(request.getParameter(MOVIE_ID)));
			
		} catch (ServiceException e) {
			response.sendError(ERROR_NUMBER_500);
			return;
		}
		
		session.setAttribute(PARAMETER_FIRST_ROW, firstIndex);
		session.setAttribute(PARAMETER_LAST_ROW, lastIndex);
		session.setAttribute(PARAMETER_RATE_LIST, rateList);
		session.setAttribute(PARAMETER_MOVIE_INFO, movieInfo);
		
		int numbOfPages = (int) Math.ceil(rateList.size() / (double)rowsByPage);
		if(numbOfPages == 0) {
			numbOfPages = defaultNumberOfPages;
		}
		
		session.setAttribute(PARAMETER_NUMBER_OF_PAGES, numbOfPages);
		session.setAttribute(PARAMETER_NUMBER_OF_CURRENT_PAGE, pageNumber);
		session.setAttribute(PARAMETER_PREVIOUS_REQUEST, url);
		
		response.sendRedirect(REDIRECT_PAGE_URL);
		
	}

}
