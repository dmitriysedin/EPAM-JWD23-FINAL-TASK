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


public class AddNewMovieCommand implements Command{
	
	private static final String PARAMETER_MOVIES_LIST = "moviesList";
	private static final String PARAMETER_MOVIE_TITLE = "movie_title";
	private static final String MOVIE_DIRECTOR = "movie_director";
	private static final String MOVIE_RELEASED_YEAR = "movie_released_year";
	private static final String PARAMETER_NUMBER_OF_PAGES = "allMoviesNumberOfPages";
	private static final String PARAMETER_PREVIOUS_REQUEST = "prev_request";
	private static final Integer ERROR_NUMBER_500 = 500;
	
	private static final String SUCCESSFUL_REDIRECT_PAGE_URL = "http://localhost:8080/jwd23_final_task/Servlet?command=goToAddNewMovie&success_param=true";
	
	public static final int rowsByPage = 5;
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String movieTitle = request.getParameter(PARAMETER_MOVIE_TITLE);
		String movieDirector = request.getParameter(MOVIE_DIRECTOR);
		String movieReleasedYear = request.getParameter(MOVIE_RELEASED_YEAR);
		HttpSession session = request.getSession(true);
		String url = CreatorFullURL.create(request);
		List<MovieInfo> moviesList = null;
		
		MovieService movieService = ServiceProvider.getInstance().getMovieService();
		
		try {
			movieService.addNewMovie(createMovieInfo(movieTitle, movieDirector, movieReleasedYear));

			moviesList = movieService.selectAllMovies();
		} catch (ServiceException e) {
			response.sendError(ERROR_NUMBER_500);
			return;
		}
		
		session.setAttribute(PARAMETER_MOVIES_LIST, moviesList);
		
		int numbOfPages = (int) Math.ceil(moviesList.size() / (double)rowsByPage);
		
		session.setAttribute(PARAMETER_NUMBER_OF_PAGES, numbOfPages);
		
		session.setAttribute(PARAMETER_PREVIOUS_REQUEST, url);
		
		response.sendRedirect(SUCCESSFUL_REDIRECT_PAGE_URL);
	}

	private MovieInfo createMovieInfo(String movieTitle, String movieDirector, String movieReleasedYear) {
		
		MovieInfo movieInfo = new MovieInfo();
		
		movieInfo.setMovieTitle(movieTitle);
		movieInfo.setMovieDirector(movieDirector);
		movieInfo.setMovieReleasedYear(movieReleasedYear);
		
		return movieInfo;
	}

	
}
