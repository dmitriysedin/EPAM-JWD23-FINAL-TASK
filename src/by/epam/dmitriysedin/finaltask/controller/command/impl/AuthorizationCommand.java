package by.epam.dmitriysedin.finaltask.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.dmitriysedin.finaltask.controller.command.Command;
import by.epam.dmitriysedin.finaltask.controller.command.util.CreatorFullURL;
import by.epam.dmitriysedin.finaltask.entity.User;
import by.epam.dmitriysedin.finaltask.service.ServiceException;
import by.epam.dmitriysedin.finaltask.service.ServiceProvider;
import by.epam.dmitriysedin.finaltask.service.UserService;


public class AuthorizationCommand implements Command{
	
	private static final String PARAMETER_LOGIN = "login";
	private static final String PARAMETER_USER = "user";
	private static final String PARAMETER_PASSWORD = "password";
	private static final String PARAMETER_WRONG_PARAMS = "wrong_params";
	private static final String PARAMETER_PREVIOUS_REQUEST = "prev_request";
	private static final Integer ERROR_NUMBER_500 = 500;

	private static final String HOME_PAGE = "/WEB-INF/jsp/showAllMovies.jsp";
	private static final String LOGIN_PAGE = "/WEB-INF/jsp/login.jsp";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession(true);
		String url = CreatorFullURL.create(request);

		String login = request.getParameter(PARAMETER_LOGIN);
		String password = request.getParameter(PARAMETER_PASSWORD);
		
		UserService userService = ServiceProvider.getInstance().getUserService();
		
		User user = null;
		String page = null;
		
		try {
			user = userService.authentification(login, password);
			
			if (user == null) {
				request.setAttribute(PARAMETER_WRONG_PARAMS, "true");
				page = LOGIN_PAGE;
			} else {
				session.setAttribute(PARAMETER_USER, user);
				page = HOME_PAGE;
			}
		} catch (ServiceException e) {
			// log
			response.sendError(ERROR_NUMBER_500);
			return;
		}
		
		session.setAttribute(PARAMETER_PREVIOUS_REQUEST, url);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);

	} 
}

