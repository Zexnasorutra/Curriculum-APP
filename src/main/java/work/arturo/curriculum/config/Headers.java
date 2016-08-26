package work.arturo.curriculum.config;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import work.arturo.curriculum.dao.HeaderDAO;
import work.arturo.curriculum.dao.IQueryDAO;
import work.arturo.curriculum.entity.header.Header;
import work.arturo.curriculum.exception.ApplicationException;

/**
 * 
 * Class that load and store the application headers
 * 
 * @author Arturo
 * 
 */
public class Headers {

	/**
	 * Static logger for Headers class
	 */
	private static Logger logger = LogManager.getLogger(Properties.class);

	/**
	 * Static List with application Headers
	 */
	private static List<Header> headers;

	// Launch when call this class first time
	static {
		try {
			// Try to load the List of headers
			loadHeaders();
		} catch (Exception e) {
			logger.error("Error loading the List of Headers", e);
		}
	}

	/**
	 * (Re)load the application headers from storage system
	 * 
	 * @throws ApplicationException
	 *             Error while search the headers
	 */
	@SuppressWarnings("unchecked")
	public static void loadHeaders() throws ApplicationException {
		logger.debug("Loading headers");
		IQueryDAO headerDAO = new HeaderDAO();
		headers = (List<Header>) headerDAO.findAll();
		
		// Control exceptions
		if (headers == null) {
			throw new ApplicationException("Static list of headers is null");
		}
		
		logger.debug("Headers loaded: " + headers.size());
	}

	/**
	 * Obtains all static headers
	 * 
	 * @return List of static headers
	 */
	public static List<Header> getAll() {
		return headers;
	}

}
