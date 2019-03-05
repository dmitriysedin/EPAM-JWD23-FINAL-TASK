package by.epam.dmitriysedin.finaltask.controller.command.impl.gotopage;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.dmitriysedin.finaltask.controller.command.Command;
import by.epam.dmitriysedin.finaltask.controller.command.util.CreatorFullURL;


public class GoToRegistrationPageCommand implements Command{
	
	private static final String TARGET_PAGE = "/WEB-INF/jsp/registration.jsp";

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session;
		
		String url = CreatorFullURL.create(request);
		
		session = request.getSession(true);
		
		session.setAttribute("prev_request", url);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(TARGET_PAGE);
		dispatcher.forward(request, response);
		
	}

	
}
