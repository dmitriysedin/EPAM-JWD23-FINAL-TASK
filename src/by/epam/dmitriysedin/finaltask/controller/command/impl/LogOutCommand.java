package by.epam.dmitriysedin.finaltask.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.dmitriysedin.finaltask.controller.command.Command;
import by.epam.dmitriysedin.finaltask.controller.command.util.CreatorFullURL;

public class LogOutCommand implements Command{

	private static final String PARAMETER_USER = "user";
	private static final String TARGET_PAGE = "Servlet?command=goToShowAllMoviesPageCommand";
	private static final String PARAMETER_PREVIOUS_REQUEST = "prev_request";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		
		session.removeAttribute(PARAMETER_USER);
		
		String url = CreatorFullURL.create(request);
		
		session.setAttribute(PARAMETER_PREVIOUS_REQUEST, url);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(TARGET_PAGE);
		dispatcher.forward(request, response);
		
	}

}
