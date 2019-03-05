package by.epam.dmitriysedin.finaltask.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import by.epam.dmitriysedin.finaltask.dao.connectionpool.myconnectionpool.ConnectionPoolException;
import by.epam.dmitriysedin.finaltask.dao.connectionpool.myconnectionpool.MyConnectionPool;

public class ConnectionPoolListener implements ServletContextListener{

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
		MyConnectionPool.getInstance().dispose();
		
		//logger.info("Connection pool destroyed successfully");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		try {
			MyConnectionPool.getInstance().initPoolData();
			//logger.info("Connection pool initialized successfully");
		} catch (ConnectionPoolException e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError("Could not initialize pool!");
		}
	}

	
}
