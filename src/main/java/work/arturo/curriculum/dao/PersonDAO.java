package work.arturo.curriculum.dao;

import java.util.List;

import work.arturo.curriculum.config.Properties;
import work.arturo.curriculum.entity.person.Person;
import work.arturo.curriculum.exception.ApplicationException;
import work.arturo.curriculum.util.Constants;

/**
 * Class that manage the Person operations in MongoDB
 * 
 * @author Arturo
 * 
 */
public class PersonDAO extends DAOBase implements IQueryDAO {

	/**
	 * Store the name of Person collection in MongoDB
	 */
	private String personCollection;

	/**
	 * Constructor that search needed parameters
	 * 
	 * @throws ApplicationException
	 *             Error searching parameters
	 */
	public PersonDAO() throws ApplicationException {
		super();
		personCollection = Properties.getProperty(Constants.PERSON_COLLECTION);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IQueryDAO#findAll()
	 */
	public List<Person> findAll() throws ApplicationException {
		List<Person> personReturn;
		try {
			mongoManager.open();

			// Search the person objects in MongoDB
			personReturn = mongoManager.findAll(personCollection, Person.class);

		} catch (ApplicationException e) {
			throw e;
		} finally {
			if (mongoManager != null) {
				mongoManager.close();
			}
		}
		return personReturn;

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see IQueryDAO#findByID(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	public Person findByID(String id) throws ApplicationException {
		Person personReturn = null;

		try {
			mongoManager.open();

			// Search the person object in MongoDB
			personReturn = mongoManager.findById(personCollection, id,
					Person.class);

		} catch (ApplicationException e) {
			throw e;
		} finally {
			if (mongoManager != null) {
				mongoManager.close();
			}
		}

		return personReturn;
	}

}
