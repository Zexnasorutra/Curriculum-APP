package work.arturo.curriculum.action;

import work.arturo.curriculum.dao.PhotoDAO;


public class PhotoAction extends ActionBase implements IImageAction {

	private static final long serialVersionUID = 1L;

	// Parameters
	private String photoId;

	// Internal variables
	private String contentType;
	private byte[] imageBytes;

	public String execute() throws Exception {
		logger.debug("Loading photo: " + photoId);
		try {
			PhotoDAO photoDAO = new PhotoDAO();

			this.imageBytes = photoDAO.findPhoto(photoId);
			this.contentType = "image/jpeg";
		} catch (Exception e) {
			logger.error("Error searching photo. Photho ID: " + photoId, e);
			throw e;
		}

		return SUCCESS;
	}

	public String getPhotoId() {
		return photoId;
	}

	public void setPhotoId(String photoId) {
		this.photoId = photoId;
	}

	public String getContentType() {
		return this.contentType;
	}

	public byte[] getImageBytes() {
		return this.imageBytes;
	}

}
