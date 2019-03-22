package by.epam.dmitriysedin.finaltask.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.dmitriysedin.finaltask.controller.command.Command;
import by.epam.dmitriysedin.finaltask.controller.command.util.CreatorFullURL;
import by.epam.dmitriysedin.finaltask.entity.User;
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
	private static final String PARAMETER_PREVIOUS_REQUEST = "prev_request";
	private static final String PARAMETER_USER = "user";
	private static final Integer ERROR_NUMBER_500 = 500;
	
	private static final String SUCCESSFUL_REDIRECT_PAGE_URL = "http://localhost:8080/jwd23_final_task/Servlet?command=goToShowAllMoviesPageCommand";
	private static final String UNSUCCESSFUL_REDIRECT_PAGE_URL = "http://localhost:8080/jwd23_final_task/Servlet?command=goToRegistrationPage&wrong_params=true";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String redirectPage = UNSUCCESSFUL_REDIRECT_PAGE_URL;
		String firstName = request.getParameter(PARAMETER_FIRST_NAME);
		String lastName = request.getParameter(PARAMETER_LAST_NAME);
		String email = request.getParameter(PARAMETER_EMAIL);
		String login = request.getParameter(PARAMETER_LOGIN);
		String password = request.getParameter(PARAMETER_PASSWORD);
		String url = CreatorFullURL.create(request);
		
		HttpSession session = request.getSession(true);
		
		UserService userService = ServiceProvider.getInstance().getUserService();
		UserInfo userInfo = null;
		User user = null;
		
		try {
			userInfo = createUserInfo(firstName, lastName, email, login, password);
			if(userService.register(userInfo)){
			user = userService.authentification(login, password);
			redirectPage = SUCCESSFUL_REDIRECT_PAGE_URL;
			}
		} catch (ServiceException e) {
			response.sendError(ERROR_NUMBER_500);
			return;
		} 
			
		session.setAttribute(PARAMETER_PREVIOUS_REQUEST, url);
		session.setAttribute(PARAMETER_USER, user);
		
		response.sendRedirect(redirectPage);
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
