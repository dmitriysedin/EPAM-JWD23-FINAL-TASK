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
import by.epam.dmitriysedin.finaltask.service.MovieService;
import by.epam.dmitriysedin.finaltask.service.ServiceException;
import by.epam.dmitriysedin.finaltask.service.ServiceProvider;

public class SelectionAllMoviesCommand implements Command{
	
	private static final String PARAMETER_MOVIES_LIST = "moviesList";
	private static final String PARAMETER_FIRST_ROW = "allMoviesFirstRow";
	private static final String PARAMETER_LAST_ROW = "allMoviesLastRow";
	private static final String PARAMETER_NUMBER_OF_PAGES = "allMoviesNumberOfPages";
	private static final String PARAMETER_CURRENT_PAGE_NUMBER = "allMoviesCurrentPageNumber";
	
	private static final String REDIRECT_PAGE_URL = "http://localhost:8080/jwd23_final_task/Servlet?command=goToShowAllMoviesPageCommand";
	
	public static final int rowsByPage = 5;
	
	
	public static final int firstPageNumber = 1;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);

		int firstIndex = 0;
		int lastIndex = firstIndex + rowsByPage;

		
		List<MovieInfo> moviesList = null;
		MovieService movieService = ServiceProvider.getInstance().getMovieService();
		
		try {
			moviesList = movieService.selectAllMovies();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		session.setAttribute(PARAMETER_FIRST_ROW, firstIndex);
		session.setAttribute(PARAMETER_LAST_ROW, lastIndex);
		
		int numbOfPages = (int) Math.ceil(moviesList.size() / (double)rowsByPage);
		
		session.setAttribute(PARAMETER_MOVIES_LIST, moviesList);
		session.setAttribute(PARAMETER_NUMBER_OF_PAGES, numbOfPages);
		session.setAttribute(PARAMETER_CURRENT_PAGE_NUMBER, firstPageNumber);
		
		String url = CreatorFullURL.create(request);
		
		session.setAttribute("prev_request", url);
		
		response.sendRedirect(REDIRECT_PAGE_URL);
	}
	
}
