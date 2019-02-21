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
	private static final String PARAMETER_PASSWORD = "password";

	private static final String WELCOME_PAGE = "/WEB-INF/jsp/welcome.jsp";
	private static final String DEFAULT_PAGE = "/WEB-INF/jsp/default.jsp";
	
	private static final int wrongUserID = -1;

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login;
		String password;

		login = request.getParameter(PARAMETER_LOGIN);
		password = request.getParameter(PARAMETER_PASSWORD);
		
		UserService userService = ServiceProvider.getInstance().getUserService();
		
		User user = null;
		String page = null;
		
		try {
			user = userService.authentification(login, password);
			
			
			if (user == null) {
				request.setAttribute("error", "password error");
				page = DEFAULT_PAGE;
			} else {
				if (user.getUserID() == wrongUserID) {
			
				request.setAttribute("error", "login error");
				page = DEFAULT_PAGE;
				} else {
				request.setAttribute("user", user);
				
				String role = user.getUserRole();
				HttpSession session = request.getSession(true);
				session.setAttribute("role", role);
				page = WELCOME_PAGE;
				}
			}
		} catch (ServiceException e) {
			// log
			//page = ERROR_PAGE;
		}
		
		String url = CreatorFullURL.create(request);
		request.getSession(true).setAttribute("prev_request", url);

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);

	} 
}

