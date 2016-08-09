package work.arturo.curriculum.mongo.filter.components;

/**
 * 
 * Class for sort structure. There is a field for sort and a type witch define
 * if is ascending or descending
 * 
 * @author Arturo
 * 
 */
public class MongoSort {

	/**
	 * Type ascending(1,2,3,4,...)
	 */
	public static final int TYPE_ASCENDING = 1;

	/**
	 * type descending(...,4,3,2,1)
	 */
	public static final int TYPE_DESCENDING = -1;

	private String field;
	private int type;

	public MongoSort(String field, int type) {
		this.field = field;
		this.type = type;
	}

	public String getField() {
		return field;
	}

	public void setField(String field) {
		this.field = field;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}
