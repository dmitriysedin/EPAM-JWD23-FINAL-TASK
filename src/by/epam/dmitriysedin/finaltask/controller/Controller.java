package by.epam.dmitriysedin.finaltask.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.dmitriysedin.finaltask.controller.command.Command;
import by.epam.dmitriysedin.finaltask.controller.command.CommandProvider;

@WebServlet("/Servlet")
public class Controller extends HttpServlet {
	
	private static final Logger logger = LogManager.getLogger(Controller.class);
	
	private static final Integer ERROR_NUMBER_500 = 500;

	private static final long serialVersionUID = 1L;
	
	private static final String PARAMETER_COMMAND = "command";

	public Controller() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		process(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		process(request, response);
		
	}
	
	private void process (HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String commandName = request.getParameter(PARAMETER_COMMAND);

		Command command = CommandProvider.getInstance().getCommand(commandName);

		try {
			command.execute(request, response);
		} catch (Exception e) {
			logger.error("Exception in Controller");
			response.sendError(ERROR_NUMBER_500);
		}

	}
	
}