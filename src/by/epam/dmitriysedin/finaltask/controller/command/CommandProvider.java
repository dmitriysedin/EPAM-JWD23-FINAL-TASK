package by.epam.dmitriysedin.finaltask.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.epam.dmitriysedin.finaltask.controller.command.impl.AddNewMovieCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.AuthorizationCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.ChangeLocaleCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.GoToAddNewMoviePageCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.GoToDefaultPageCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.GoToHomePageCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.GoToRegistrationPageCommand;

import by.epam.dmitriysedin.finaltask.controller.command.impl.RegistrationCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.ShowAllMoviesCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.ShowNextMoviesPage;
import by.epam.dmitriysedin.finaltask.controller.command.impl.ShowPreviousMoviesPage;

public class CommandProvider {
	
	private static final CommandProvider instance = new CommandProvider();

	private Map<String, Command> commands = new HashMap<>();

	private CommandProvider() {
		commands.put("authorization", new AuthorizationCommand());
		commands.put("goToDefaultPage", new GoToDefaultPageCommand());
		commands.put("changeLocale", new ChangeLocaleCommand());
		commands.put("goToRegistrationPage", new GoToRegistrationPageCommand());
		commands.put("registration", new RegistrationCommand());
		commands.put("addNewMovie", new AddNewMovieCommand());
		commands.put("goToAddNewMovie", new GoToAddNewMoviePageCommand());
		commands.put("goToHomePage", new GoToHomePageCommand());
		commands.put("showAllMovies", new ShowAllMoviesCommand());
		commands.put("showNextPage", new ShowNextMoviesPage());
		commands.put("showPreviousPage", new ShowPreviousMoviesPage());
		
	}
	
	public static CommandProvider getInstance() {
		
		return instance;
	}
	
	public Command getCommand(String commandName) {
		
		return commands.get(commandName);
	}
}
