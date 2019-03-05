package by.epam.dmitriysedin.finaltask.controller.command.impl;

import java.io.IOException;

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
	
	private static final String PARAMETER_MOVIE_TITLE = "movie_title";
	private static final String MOVIE_DIRECTOR = "movie_director";
	private static final String MOVIE_RELEASED_YEAR = "movie_released_year";
	
	private static final String REDIRECT_PAGE_URL = "http://localhost:8080/jwd23_final_task/Servlet?command=goToAddNewMovie";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String movieTitle;
		String movieDirector;
		String movieReleasedYear;

		movieTitle = request.getParameter(PARAMETER_MOVIE_TITLE);
		movieDirector = request.getParameter(MOVIE_DIRECTOR);
		movieReleasedYear = request.getParameter(MOVIE_RELEASED_YEAR);
		
		MovieService movieService = ServiceProvider.getInstance().getMovieService();
		
		try {
			movieService.addNewMovie(createMovieInfo(movieTitle, movieDirector, movieReleasedYear));
			
		} catch (ServiceException e) {
			// log
		} 
		
		HttpSession session;
		
		session = request.getSession(true);
		
		String url = CreatorFullURL.create(request);
		
		session.setAttribute("prev_request", url);
		
		response.sendRedirect(REDIRECT_PAGE_URL);
	}

	private MovieInfo createMovieInfo(String movieTitle, String movieDirector, String movieReleasedYear) {
		
		MovieInfo movieInfo = new MovieInfo();
		
		movieInfo.setMovieTitle(movieTitle);
		movieInfo.setMovieDirector(movieDirector);
		movieInfo.setMovieReleasedYear(movieReleasedYear);
		
		return movieInfo;
	}

	
}
