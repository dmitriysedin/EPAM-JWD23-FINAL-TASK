package by.epam.dmitriysedin.finaltask.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import by.epam.dmitriysedin.finaltask.controller.command.Command;
import by.epam.dmitriysedin.finaltask.controller.command.CommandProvider;


@WebServlet("/Servlet")
public class Controller extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private static final String PARAMETER_COMMAND = "command";

	public Controller() {
		super();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		String commandName = request.getParameter(PARAMETER_COMMAND);

		Command command = CommandProvider.getInstance().getCommand(commandName);

		command.execute(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
		
	}
}