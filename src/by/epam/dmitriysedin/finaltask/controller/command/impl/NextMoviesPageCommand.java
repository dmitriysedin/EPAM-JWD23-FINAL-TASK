package by.epam.dmitriysedin.finaltask.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.dmitriysedin.finaltask.controller.command.Command;
import by.epam.dmitriysedin.finaltask.controller.command.util.CreatorFullURL;


public class NextMoviesPageCommand implements Command{

	private static final String PARAMETER_FIRST_ROW = "firstrow";
	private static final String PARAMETER_LAST_ROW = "lastrow";
	
	private static final String REDIRECT_PAGE_URL = "http://localhost:8080/jwd23_final_task/Servlet?command=goToShowAllMoviesPageCommand";
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();

		int firstIndex = Integer.parseInt(session.getAttribute(PARAMETER_LAST_ROW).toString());
		int lastIndex = firstIndex + SelectionAllMoviesCommand.rowsByPage;

		session.setAttribute(PARAMETER_FIRST_ROW, firstIndex);
		session.setAttribute(PARAMETER_LAST_ROW, lastIndex);

		String url = CreatorFullURL.create(request);
		
		session.setAttribute("prev_request", url);
		
		response.sendRedirect(REDIRECT_PAGE_URL);
	}

	
}
