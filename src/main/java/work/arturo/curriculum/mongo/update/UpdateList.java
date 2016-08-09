package work.arturo.curriculum.mongo.update;

import java.util.ArrayList;
import java.util.List;

import org.bson.Document;

/**
 * 
 * This class store the update list with changes to do in MongoDB. It generate a
 * Document with all updates in the same Document
 * 
 * @author Arturo
 * 
 */
public class UpdateList {

	/**
	 * List with individual updates
	 */
	private List<UpdateElement> updates;

	public UpdateList() {
		updates = new ArrayList<UpdateElement>();
	}

	/**
	 * Add an update to update list
	 * 
	 * @param field
	 *            Field witch will be updated
	 * @param newValue
	 *            New field value
	 */
	public void addUpdate(String field, Object newValue) {
		updates.add(new UpdateElement(field, newValue));
	}

	/**
	 * 
	 * Generate a document with all updates stored
	 * 
	 * @return Document with update structure and all update list
	 */
	public Document generateUpdates() {
		Document documentUpdates = new Document();
		for (UpdateElement update : updates) {
			documentUpdates.append(update.getField(), update.getNewValue());
		}

		return new Document("$set", documentUpdates);
	}

}
