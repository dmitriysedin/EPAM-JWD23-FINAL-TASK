package by.epam.dmitriysedin.finaltask.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.dmitriysedin.finaltask.controller.command.Command;
import by.epam.dmitriysedin.finaltask.controller.command.util.CreatorFullURL;
import by.epam.dmitriysedin.finaltask.entity.UserInfo;
import by.epam.dmitriysedin.finaltask.service.ServiceException;
import by.epam.dmitriysedin.finaltask.service.ServiceProvider;
import by.epam.dmitriysedin.finaltask.service.UserService;

public class RegistrationCommand implements Command{

	private static final String PARAMETER_FIRST_NAME = "first_name";
	private static final String PARAMETER_LAST_NAME = "last_name";
	private static final String PARAMETER_EMAIL = "email";
	private static final String PARAMETER_LOGIN = "login";
	private static final String PARAMETER_PASSWORD = "password";

	private static final String DEFAULT_PAGE = "/WEB-INF/jsp/default.jsp";
	private static final String ERROR_PAGE = "/WEB-INF/jsp/error.jsp";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String firstName;
		String lastName;
		String email;
		String login;
		String password;

		firstName = request.getParameter(PARAMETER_FIRST_NAME);
		lastName = request.getParameter(PARAMETER_LAST_NAME);
		email = request.getParameter(PARAMETER_EMAIL);
		login = request.getParameter(PARAMETER_LOGIN);
		password = request.getParameter(PARAMETER_PASSWORD);
		
		UserService userService = ServiceProvider.getInstance().getUserService();
		String page = "";
		
		try {
			userService.registration(createUserInfo(firstName, lastName, email, login, password));
				request.setAttribute("registration_result", "Вы успешно зарегистрированы. Войдите под своим логином и паролем.");
				page = DEFAULT_PAGE;
			
		} catch (ServiceException e) {
			// log
			page = ERROR_PAGE;
		} 
		
		String url = CreatorFullURL.create(request);
		request.getSession(true).setAttribute("prev_request", url);

		RequestDispatcher dispatcher = request.getRequestDispatcher(page);
		dispatcher.forward(request, response);
	}

	private UserInfo createUserInfo(String firstName, String lastName, String email, String login, String password) {
		
		UserInfo userInfo = new UserInfo();
		
		userInfo.setUserFirstName(firstName);
		userInfo.setUserLastName(lastName);
		userInfo.setUserEmail(email);
		userInfo.setUserLogin(login);
		userInfo.setUserPassword(password);
		
		return userInfo;
	}
}
