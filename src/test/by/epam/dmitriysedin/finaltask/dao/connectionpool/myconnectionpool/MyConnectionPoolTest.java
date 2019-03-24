package by.epam.dmitriysedin.finaltask.dao.connectionpool.myconnectionpool;

import static org.junit.Assert.*;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

public class MyConnectionPoolTest {
	
	private static final Logger logger = LogManager.getLogger(MyConnectionPoolTest.class);
	private static final MyConnectionPool INSTANCE_CONNECTION_POOL = MyConnectionPool.getInstance();
	private static final String CONNECTION_CLASS_NAME = "PooledConnection";
	
	 @BeforeClass
	    public static void setUpBeforeClass() throws Exception {
	        try {
	        	INSTANCE_CONNECTION_POOL.initPoolData();
	        } catch (ConnectionPoolException e) {
	        	logger.error("ConnectionPoolException in MyConnectionPoolTest/setUp()");
	        }
	    }
	
	@Test
	public void testTakeConnection() {
		
		try {
			assertEquals(CONNECTION_CLASS_NAME, INSTANCE_CONNECTION_POOL.takeConnection().getClass().getSimpleName());
		} catch (ConnectionPoolException e) {
			logger.error("ConnectionPoolException in MyConnectionPoolTest/testTakeConnection()");
		}
	}
	
	@AfterClass
    public static void tearDownAfterClass() throws Exception {

		INSTANCE_CONNECTION_POOL.dispose();
    }

}
