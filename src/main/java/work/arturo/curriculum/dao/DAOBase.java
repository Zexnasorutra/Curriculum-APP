package work.arturo.curriculum.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import work.arturo.curriculum.config.Properties;
import work.arturo.curriculum.exception.ApplicationException;
import work.arturo.curriculum.mongo.MongoManager;
import work.arturo.curriculum.util.Constants;

/**
 * Base for DAO classes. Storage the parameters for establish MongoDB
 * connections
 * 
 * @author Arturo
 * 
 */
public class DAOBase {

	protected Logger logger;

	// MongoDB parameters
	private String mongoHost;
	private int mongoPort;
	private String mongoDatabase;

	// Manager witch communicate with MongoDB
	protected MongoManager mongoManager;

	/**
	 * Obtains a MongoDB manager using application parameters
	 * 
	 * @throws ApplicationException
	 *             Error searching parameters
	 */
	public DAOBase() throws ApplicationException {
		logger = LogManager.getLogger();

		// Search parameters
		mongoHost = Properties.getProperty(Constants.MONGO_HOST);
		mongoPort = Integer.parseInt(Properties
				.getProperty(Constants.MONGO_PORT));
		mongoDatabase = Properties.getProperty(Constants.MONGO_DATABASE);

		// Initialize the MongoDB manager
		mongoManager = new MongoManager(mongoHost, mongoPort, mongoDatabase);

	}
}
