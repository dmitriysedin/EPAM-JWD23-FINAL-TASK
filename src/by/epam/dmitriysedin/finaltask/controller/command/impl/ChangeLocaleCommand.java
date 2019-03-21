package by.epam.dmitriysedin.finaltask.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.dmitriysedin.finaltask.controller.command.Command;


public class ChangeLocaleCommand implements Command{
	
	private static final String PARAMETER_LOCALE = "locale";
	private static final String SESION_ATTRIBUTE_LOCAL = "local";
	private static final String PARAMETER_PREVIOUS_REQUEST = "prev_request";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String newLocale = request.getParameter(PARAMETER_LOCALE);
		HttpSession session = request.getSession(true);
		
		session.setAttribute(SESION_ATTRIBUTE_LOCAL, newLocale);
		
		String url = (String)request.getSession(false).getAttribute(PARAMETER_PREVIOUS_REQUEST);
		
		response.sendRedirect(url);
		
	}

	
}
