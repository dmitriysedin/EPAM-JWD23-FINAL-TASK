package by.epam.dmitriysedin.finaltask.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.dmitriysedin.finaltask.controller.command.Command;

public class ChangeLocaleCommand implements Command{

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String newLocale;
		HttpSession session;
		
		newLocale = request.getParameter("locale");
		
		session = request.getSession(true);
		session.setAttribute("local", newLocale);
		
		
		String url = (String)request.getSession(false).getAttribute("prev_request");
		response.sendRedirect(url);
		
	}

	
}
