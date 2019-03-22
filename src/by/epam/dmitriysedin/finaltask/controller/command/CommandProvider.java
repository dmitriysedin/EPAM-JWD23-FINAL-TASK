package by.epam.dmitriysedin.finaltask.controller.command;

import java.util.HashMap;
import java.util.Map;

import by.epam.dmitriysedin.finaltask.controller.command.impl.AddNewMovieCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.AddNewRateCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.AuthorizationCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.ChangeLocaleCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.ConcreteMovieInfoCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.LogOutCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.RegistrationCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.SelectionAllMoviesCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.NextPageCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.NoSuchCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.PreviousPageCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.gotopage.GoToAddNewMoviePageCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.gotopage.GoToAddNewRatePageCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.gotopage.GoToLoginPageCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.gotopage.GoToRegistrationPageCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.gotopage.GoToShowAllMoviesPageCommand;
import by.epam.dmitriysedin.finaltask.controller.command.impl.gotopage.GoToShowConcreteMovieInfoPageCommand;


public class CommandProvider {
	
	private static final CommandProvider instance = new CommandProvider();

	private Map<CommandName, Command> commands = new HashMap<>();

	private CommandProvider() {
		commands.put(CommandName.AUTHORIZATION, new AuthorizationCommand());
		commands.put(CommandName.GOTOLOGINPAGE, new GoToLoginPageCommand());
		commands.put(CommandName.CHANGELOCALE, new ChangeLocaleCommand());
		commands.put(CommandName.GOTOREGISTRATIONPAGE, new GoToRegistrationPageCommand());
		commands.put(CommandName.REGISTRATION, new RegistrationCommand());
		commands.put(CommandName.ADDNEWMOVIE, new AddNewMovieCommand());
		commands.put(CommandName.GOTOADDNEWMOVIE, new GoToAddNewMoviePageCommand());
		commands.put(CommandName.SHOWALLMOVIES, new SelectionAllMoviesCommand());
		commands.put(CommandName.SHOWNEXTPAGE, new NextPageCommand());
		commands.put(CommandName.SHOWPREVIOUSPAGE, new PreviousPageCommand());
		commands.put(CommandName.GOTOSHOWALLMOVIESPAGECOMMAND, new GoToShowAllMoviesPageCommand());
		commands.put(CommandName.CONCRETEMOVIEINFO, new ConcreteMovieInfoCommand());
		commands.put(CommandName.GOTOADDNEWRATE, new GoToAddNewRatePageCommand());
		commands.put(CommandName.ADDNEWRATE, new AddNewRateCommand());
		commands.put(CommandName.GOTOSHOWCONCRETEMOVIEINFOPAGECOMMAND, new GoToShowConcreteMovieInfoPageCommand());
		commands.put(CommandName.LOGOUTCOMMAND, new LogOutCommand());
		commands.put(CommandName.NOSUCHCOMMAND, new NoSuchCommand());
	}
	
	public static CommandProvider getInstance() {
		
		return instance;
	}
	
	public Command getCommand(String commandName) {
		
		CommandName name;
		Command command;
		
		try {
			name = CommandName.valueOf(commandName.toUpperCase());
			command = commands.get(name);
		} catch (Exception e) {
			command = commands.get(CommandName.NOSUCHCOMMAND);
		}
		
		return command;
	}
}
