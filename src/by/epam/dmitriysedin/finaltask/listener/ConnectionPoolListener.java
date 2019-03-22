package by.epam.dmitriysedin.finaltask.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import by.epam.dmitriysedin.finaltask.dao.connectionpool.myconnectionpool.ConnectionPoolException;
import by.epam.dmitriysedin.finaltask.dao.connectionpool.myconnectionpool.MyConnectionPool;

public class ConnectionPoolListener implements ServletContextListener{
	
	private static final Logger logger = LogManager.getLogger(ConnectionPoolListener.class);

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		
		MyConnectionPool.getInstance().dispose();
		
		logger.info("Connection pool destroyed successfully");
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {

		try {
			MyConnectionPool.getInstance().initPoolData();
			logger.info("Connection pool initialized successfully");
		} catch (ConnectionPoolException e) {
			logger.error("ConnectionPoolException during initializing", e);
			throw new ExceptionInInitializerError("Could not initialize pool!");
		}
	}

	
}
