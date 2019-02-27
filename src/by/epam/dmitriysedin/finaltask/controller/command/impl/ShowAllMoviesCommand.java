package by.epam.dmitriysedin.finaltask.controller.command.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.dmitriysedin.finaltask.controller.command.Command;
import by.epam.dmitriysedin.finaltask.controller.command.util.CreatorFullURL;
import by.epam.dmitriysedin.finaltask.entity.Movie;
import by.epam.dmitriysedin.finaltask.service.MovieService;
import by.epam.dmitriysedin.finaltask.service.ServiceException;
import by.epam.dmitriysedin.finaltask.service.ServiceProvider;

public class ShowAllMoviesCommand implements Command{
	
	private static final String PARAMETER_MOVIES_LIST = "moviesList";
	private static final String PARAMETER_FIRST_ROW = "firstrow";
	private static final String PARAMETER_LAST_ROW = "lastrow";

	private static final String REDIRECT_PAGE_URL = "http://localhost:8080/jwd23_final_task/Servlet?command=goToShowAllMoviesPageCommand";
	
	public static final int rowsByPage = 5;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();

		int firstIndex = 0;
		int lastIndex = firstIndex + rowsByPage;

		
		List<Movie> moviesList = null;
		MovieService movieService = ServiceProvider.getInstance().getMovieService();
		
		try {
			moviesList = movieService.selectAllMovies();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
		
		session.setAttribute(PARAMETER_FIRST_ROW, firstIndex);
		session.setAttribute(PARAMETER_LAST_ROW, lastIndex);
		
		session.setAttribute(PARAMETER_MOVIES_LIST, moviesList);
		
		String url = CreatorFullURL.create(request);
		
		session.setAttribute("prev_request", url);
		
		response.sendRedirect(REDIRECT_PAGE_URL);
	}
	
}
