package by.epam.dmitriysedin.finaltask.controller.command.impl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import by.epam.dmitriysedin.finaltask.controller.command.Command;
import by.epam.dmitriysedin.finaltask.controller.command.util.CreatorFullURL;


public class NextPageCommand implements Command{

	private static final String PARAMETER_FIRST_ROW = "first_row_parameter_name";
	private static final String PARAMETER_LAST_ROW = "last_row_parameter_name";
	private static final String PARAMETER_CURRENT_PAGE = "current_page_URL";
	private static final String PARAMETER_CURRENT_PAGE_NUMBER = "current_page_number_parameter_name";
	private static final String PARAMETER_NUMBER_OF_PAGES = "number_of_pages_parameter_name";
	
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		HttpSession session = request.getSession();
		
		String parameterFirstRowName = request.getParameter(PARAMETER_FIRST_ROW);
		String parameterLastRowName = request.getParameter(PARAMETER_LAST_ROW);
		String currentPageNumberParameterName = request.getParameter(PARAMETER_CURRENT_PAGE_NUMBER);
		String numberOfPagesParameterName = request.getParameter(PARAMETER_NUMBER_OF_PAGES);

		int firstIndex = Integer.parseInt(session.getAttribute(parameterLastRowName).toString());
		int lastIndex = firstIndex + SelectionAllMoviesCommand.rowsByPage;
		int numberOfPages = Integer.parseInt(session.getAttribute(numberOfPagesParameterName).toString());
		int currentPageNumber = Integer.parseInt(session.getAttribute(currentPageNumberParameterName).toString());

		
		
		if(numberOfPages > currentPageNumber) {
		session.setAttribute(currentPageNumberParameterName, (++currentPageNumber));
		session.setAttribute(parameterFirstRowName, firstIndex);
		session.setAttribute(parameterLastRowName, lastIndex);
		}
		
		String url = CreatorFullURL.create(request);
		
		session.setAttribute("prev_request", url);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(request.getParameter(PARAMETER_CURRENT_PAGE));
		dispatcher.forward(request, response);
		
	}

	
}
