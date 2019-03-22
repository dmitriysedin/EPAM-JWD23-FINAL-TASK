package by.epam.dmitriysedin.finaltask.controller.command.impl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.dmitriysedin.finaltask.controller.command.Command;

public class NoSuchCommand implements Command{
	
	private static final Integer ERROR_NUMBER_500 = 500;

	private static final Logger logger = LogManager.getLogger(NoSuchCommand.class);
	
	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		logger.error("No such command error");
		response.sendError(ERROR_NUMBER_500);
	}

}
