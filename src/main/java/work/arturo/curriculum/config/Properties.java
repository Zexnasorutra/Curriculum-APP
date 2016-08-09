package work.arturo.curriculum.config;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.MissingResourceException;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import work.arturo.curriculum.exception.ApplicationException;

/**
 * 
 * Class that load and store the application properties
 * 
 * @author Arturo
 *
 */
public class Properties {

	/**
	 * Static logger for Properties class
	 */
	private static Logger logger = LogManager.getLogger(Properties.class);

	/**
	 * The default file name of properties will be application.properties
	 */
	private static final String DEFAULT_FILE_NAME = "application";

	/**
	 * Static map with the application properties
	 */
	private static Map<String, String> properties = new HashMap<String, String>();

	// Launch when call this class first time
	static {
		try {
			// Try to load the map of properties
			loadProperties(DEFAULT_FILE_NAME);
		} catch (Exception e) {
			logger.error("Error loading the map of properties", e);
		}
	}

	/**
	 * Load the map of properties from file
	 * 
	 * @param fileName
	 *            Name of the file of properties
	 * @throws ApplicationException
	 *             When the propertiesFile doesn't exist
	 */
	public static void loadProperties(String fileName)
			throws ApplicationException {
		logger.debug("Loading properties");

		ResourceBundle resourceBundle = null;
		try {
			resourceBundle = (PropertyResourceBundle) PropertyResourceBundle
					.getBundle(fileName);
		} catch (MissingResourceException e) {
			throw new ApplicationException("The properties file: " + fileName
					+ " doesn't exist", e);
		}

		// Get the keys of properties
		Enumeration<String> propertiesKeys = resourceBundle.getKeys();

		// Insert inside the map all properties
		while (propertiesKeys.hasMoreElements()) {
			String propertyKey = propertiesKeys.nextElement();
			logger.debug("Adding the property: " + propertyKey + " - "
					+ resourceBundle.getString(propertyKey));
			properties.put(propertyKey, resourceBundle.getString(propertyKey));
		}

	}

	/**
	 * Get a property value of the application
	 * 
	 * @param propertyKey
	 *            ID of the property
	 * @return Property value
	 * @throws ApplicationException
	 *             When the map of properties is null or the property doesn't
	 *             exist
	 */
	public static String getProperty(String propertyKey)
			throws ApplicationException {
		// Control if the map is null
		if (properties == null) {
			throw new ApplicationException("Map of properties is null");
		}

		// Control if the property doesn't exist
		if (!properties.containsKey(propertyKey)) {
			throw new ApplicationException("The property " + propertyKey
					+ " doesn't exist");
		}

		return properties.get(propertyKey);
	}

}
