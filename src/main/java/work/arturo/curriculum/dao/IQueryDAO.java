package work.arturo.curriculum.dao;

import java.util.List;

import work.arturo.curriculum.exception.ApplicationException;

public interface IQueryDAO {
	/**
	 * Find all stored objects of the implemented type 
	 * @return List of found objects
	 * @throws ApplicationException Error finding objects
	 */
	public List<?> findAll() throws ApplicationException;
	
	/**
	 * Find a stored object of the implemented type by id 
	 * @param id Object Id
	 * @return Found object
	 * @throws ApplicationException Error finding object
	 */
	public <T> T findByID(String id) throws ApplicationException;

}
