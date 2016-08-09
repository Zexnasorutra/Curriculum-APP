package work.arturo.curriculum.mongo.update;

/**
 * Class that store a changed field in MongoDB.
 * 
 * @author Arturo
 * 
 */
public class UpdateElement {
	/**
	 * Field name that will be update
	 */
	private String field;

	/**
	 * New field value
	 */
	private Object newValue;

	public UpdateElement(String field, Object newValue) {
		this.field = field;
		this.newValue = newValue;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public Object getNewValue() {
		return newValue;
	}

	public void setNewValue(Object newValue) {
		this.newValue = newValue;
	}

}
