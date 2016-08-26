package work.arturo.curriculum.action;

import java.util.List;

import work.arturo.curriculum.config.Headers;
import work.arturo.curriculum.config.Properties;
import work.arturo.curriculum.dao.IQueryDAO;
import work.arturo.curriculum.dao.PersonDAO;
import work.arturo.curriculum.entity.header.Header;
import work.arturo.curriculum.entity.person.Person;
import work.arturo.curriculum.exception.ApplicationException;
import work.arturo.curriculum.util.Constants;

import com.opensymphony.xwork2.Action;

public class PersonAction extends ActionBase {

	private static final long serialVersionUID = 1L;

	private String personID;
	private Person person;
	private List<Header> headers;

	private String defaultLanguage;

	public String find() throws ApplicationException {
		logger.info("Finding person. ID: " + personID);

		// If the person ID is empty, set the default person ID
		if (personID == null || personID.equals("")) {
			try {
				personID = Properties.getProperty(Constants.DEFAULT_PERSON);
			} catch (ApplicationException e) {
				logger.error("There aren't default person ID", e);
				throw e;
			}
		}

		// Search the person by ID
		try {
			IQueryDAO personDAO = new PersonDAO();
			this.person = personDAO.findByID(personID);
		} catch (ApplicationException e) {
			logger.error("Error searching user", e);
			throw e;
		}

		// Obtains the static headers
		this.headers = Headers.getAll();

		// Obtains default language
		if (this.person.getAvailableLanguages() != null) {
			this.defaultLanguage = this.person.getAvailableLanguages().get(0);
		}

		return Action.SUCCESS;
	}

	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String getPersonID() {
		return personID;
	}

	public void setPersonID(String personID) {
		this.personID = personID;
	}

	public List<Header> getHeaders() {
		return headers;
	}

	public void setHeaders(List<Header> headers) {
		this.headers = headers;
	}

	public String getDefaultLanguage() {
		return defaultLanguage;
	}

	public void setDefaultLanguage(String defaultLanguage) {
		this.defaultLanguage = defaultLanguage;
	}

}
