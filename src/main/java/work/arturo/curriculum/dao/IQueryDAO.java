package work.arturo.curriculum.dao;

import java.util.List;

import work.arturo.curriculum.exception.ApplicationException;

public interface IQueryDAO {
	/**
	 * Find all stored objects of the implemented type 
	 * @return
	 * @throws ApplicationException
	 */
	public List<?> findAll() throws ApplicationException;
	
	public <T> T findByID(String id) throws ApplicationException;

}
