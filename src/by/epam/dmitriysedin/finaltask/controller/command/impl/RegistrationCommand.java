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
	
	private static final String REDIRECT_PAGE_URL = "http://localhost:8080/jwd23_final_task/Servlet?command=goToShowAllMoviesPageCommand";
	
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
		UserInfo userInfo = null;
		User user = null;
		
		try {
			userInfo = createUserInfo(firstName, lastName, email, login, password);
			userService.register(userInfo);
			user = userService.authentification(login, password);
			
		} catch (ServiceException e) {
			// log
		} 
		
		HttpSession session;
		
		session = request.getSession(true);
		
		String url = CreatorFullURL.create(request);
		
		session.setAttribute("prev_request", url);
		session.setAttribute("user", user);
		
		response.sendRedirect(REDIRECT_PAGE_URL);
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
