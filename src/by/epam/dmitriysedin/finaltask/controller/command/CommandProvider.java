package by.epam.dmitriysedin.finaltask.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.epam.dmitriysedin.finaltask.controller.command.impl.AddNewMovieCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.AddNewRateCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.AuthorizationCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.ChangeLocaleCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.ConcreteMovieInfoCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.RegistrationCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.SelectionAllMoviesCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.NextMoviesPageCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.PreviousMoviesPageCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.gotopage.GoToAddNewMoviePageCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.gotopage.GoToAddNewRatePageCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.gotopage.GoToHomePageCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.gotopage.GoToLoginPageCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.gotopage.GoToRegistrationPageCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.gotopage.GoToShowAllMoviesPageCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.gotopage.GoToShowConcreteMovieInfoPageCommand;

public class CommandProvider {
	
	private static final CommandProvider instance = new CommandProvider();

	private Map<String, Command> commands = new HashMap<>();

	private CommandProvider() {
		commands.put("authorization", new AuthorizationCommand());
		commands.put("goToLoginPage", new GoToLoginPageCommand());
		commands.put("changeLocale", new ChangeLocaleCommand());
		commands.put("goToRegistrationPage", new GoToRegistrationPageCommand());
		commands.put("registration", new RegistrationCommand());
		commands.put("addNewMovie", new AddNewMovieCommand());
		commands.put("goToAddNewMovie", new GoToAddNewMoviePageCommand());
		commands.put("goToHomePage", new GoToHomePageCommand());
		commands.put("showAllMovies", new SelectionAllMoviesCommand());
		commands.put("showNextPage", new NextMoviesPageCommand());
		commands.put("showPreviousPage", new PreviousMoviesPageCommand());
		commands.put("goToShowAllMoviesPageCommand", new GoToShowAllMoviesPageCommand());
		commands.put("concreteMovieInfo", new ConcreteMovieInfoCommand());
		commands.put("goToAddNewRate", new GoToAddNewRatePageCommand());
		commands.put("addNewRate", new AddNewRateCommand());
		commands.put("goToShowConcreteMovieInfoPageCommand", new GoToShowConcreteMovieInfoPageCommand());
		
	}
	
	public static CommandProvider getInstance() {
		
		return instance;
	}
	
	public Command getCommand(String commandName) {
		
		return commands.get(commandName);
	}
}
