package work.arturo.curriculum.dao;

import work.arturo.curriculum.config.Properties;
import work.arturo.curriculum.exception.ApplicationException;
import work.arturo.curriculum.util.Constants;



public class PhotoDAO extends DAOBase {

	/**
	 * Store the name of Person collection in MongoDB
	 */
	private String photoBucket;

	public PhotoDAO() throws ApplicationException {
		super();
		photoBucket = Properties.getProperty(Constants.PHOTO_BUCKET);
	}

	public byte[] findPhoto(String name) throws Exception {
		byte[] photoReturn;
		try {
			mongoManager.open();

			photoReturn = mongoManager.findFile(name, photoBucket);
		} catch (Exception e) {
			throw e;
		} finally {
			if (mongoManager != null) {
				mongoManager.close();
			}
		}

		return photoReturn;

	}

}
