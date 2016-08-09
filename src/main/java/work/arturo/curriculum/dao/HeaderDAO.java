package work.arturo.curriculum.dao;

import java.util.List;

import work.arturo.curriculum.config.Properties;
import work.arturo.curriculum.entity.header.Header;
import work.arturo.curriculum.exception.ApplicationException;
import work.arturo.curriculum.util.Constants;

/**
 * Class that manage the Header operations in MongoDB
 * 
 * @author Arturo
 *
 */
public class HeaderDAO extends DAOBase implements IQueryDAO {

	/**
	 * Store the name of Header collection in MongoDB
	 */
	private String headerCollection;

	/**
	 * Constructor that search needed parameters
	 * 
	 * @throws ApplicationException
	 *             Error searching parameters
	 */

	public HeaderDAO() throws ApplicationException {
		super();
		headerCollection = Properties.getProperty(Constants.HEADER_COLLECTION);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IQueryDAO#findAll()
	 */
	public List<Header> findAll() throws ApplicationException {
		List<Header> headersReturn;
		try {
			mongoManager.open();

			// Search the headers objects in MongoDB
			headersReturn = mongoManager
					.findAll(headerCollection, Header.class);

		} catch (ApplicationException e) {
			throw e;
		} finally {
			if (mongoManager != null) {
				mongoManager.close();
			}
		}
		return headersReturn;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IQueryDAO#findByID(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Header findByID(String id) throws ApplicationException {

		Header headerReturn = null;

		try {
			mongoManager.open();

			// Search the header object in MongoDB
			headerReturn = mongoManager.findById(headerCollection, id,
					Header.class);

		} catch (ApplicationException e) {
			throw e;
		} finally {
			if (mongoManager != null) {
				mongoManager.close();
			}
		}

		return headerReturn;

	}

}
