package work.arturo.curriculum.mongo.filter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import work.arturo.curriculum.exception.ApplicationException;
import work.arturo.curriculum.mongo.filter.components.MongoCondition;
import work.arturo.curriculum.mongo.filter.components.MongoSort;

/**
 * 
 * Class that store all conditions and sorts for mind a MongoDB document
 * 
 * @author Arturo
 * 
 */
public class MongoFilter {

	/**
	 * Condition list for find documents
	 */
	private List<MongoCondition> conditions;

	/**
	 * List for sort JSON returned
	 */
	private List<MongoSort> sort;

	public MongoFilter() {
		conditions = new ArrayList<MongoCondition>();
		sort = new ArrayList<MongoSort>();
	}

	/**
	 * Add a condition to filter
	 * 
	 * @param condition
	 *            Condition witch add to filter
	 */
	public void addCondition(MongoCondition condition) {
		conditions.add(condition);
	}

	/**
	 * Add a sort to filter. The MongoDB response will be sorter by this
	 * 
	 * @param field
	 *            Field name to sort
	 * @param sortType
	 *            MongoSort.TYPE_ASCENDING or MongoSort.TYPE_DESCENDING
	 */
	public void addSort(String field, int sortType) {
		sort.add(new MongoSort(field, sortType));
	}

	/**
	 * Generate a Document with all stored conditions
	 * 
	 * @return Document with filter structure
	 * @throws ApplicationException
	 *             Error generating conditions
	 */
	public Document generateFilter() throws ApplicationException {
		Document filter = new Document();
		// For each condition get a map with rules to apply
		for (MongoCondition condition : conditions) {
			Map<String, Object> mapCondition = condition.generateCondition();
			for (String key : mapCondition.keySet()) {
				filter.append(key, mapCondition.get(key));
			}
		}

		return filter;
	}

	/**
	 * Generate a Document with all stored sorts
	 * 
	 * @return Document with sort structure
	 */
	public Document generateSort() {
		Document documentSort = new Document();

		for (MongoSort sort : this.sort) {
			documentSort.append(sort.getField(), sort.getType());
		}

		return documentSort;
	}

}
