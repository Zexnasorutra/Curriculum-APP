package work.arturo.curriculum.mongo.filter.components;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bson.Document;

import work.arturo.curriculum.exception.ApplicationException;

/**
 * 
 * Class that store a condition for filtering in MongoDB. There are two
 * condition types: </br> <b>- Simple condition(AND): </b>Compare a field with a
 * value using a type of condition </br> <b>- Multiple condition(OR): </b> There
 * will be several conditions and at least one should be true </p> The
 * constructors will generate the conditions.
 * 
 * @author Arturo
 * 
 */
public class MongoCondition {

	// Fields for define condition type
	private final int AND_CONDITION = 0;
	private final int OR_CONDITION = 1;
	private int condition;

	/**
	 * Field <b>=</b> value
	 */
	public static final int EQUALS = 0;

	/**
	 * Field <b>></b> value
	 */
	public static final int GREATER_THAN = 1;

	/**
	 * Field <b>>=</b> value
	 */
	public static final int GREATER_EQUAL_THAN = 2;

	/**
	 * Field <b><</b> value
	 */
	public static final int LESS_THAN = 3;

	/**
	 * Field <b><=</b> value
	 */
	public static final int LESS_EQUAL_THAN = 4;

	/**
	 * Field <b>!=</b> value
	 */
	public static final int NOT_EQUALS = 5;

	/**
	 * <b>NOTE: </b>The value must be an instance of ArrayList</br> Field
	 * <b>in</b> (value[0], value[1]...)
	 */
	public static final int IN = 6;

	/**
	 * <b>NOTE: </b>The value must be an instance of ArrayList</br> Field <b>not
	 * in</b> (value[0], value[1]...)
	 */
	public static final int NOT_IN = 7;

	/**
	 * Check if the field exist. The value could be null
	 */
	public static final int EXISTS = 8;

	/**
	 * Check if the field doesn't exist. The value could be null
	 */
	public static final int NOT_EXISTS = 9;

	/**
	 * Check a REGEX format on the field value. </br> <b>NOTE: </b>The value
	 * must be an ArrayList of String</br> String[0]: REGEX format </br>
	 * String[1]: REGEX operations </br> <a
	 * href="https://docs.mongodb.org/manual/reference/operator/query/regex/"
	 * >How to build REGEX formats in MongoDB</a>
	 */
	public static final int REGEX = 10;

	// Fields for 'AND' condition
	private String field;
	private int operation;
	private Object value;

	// Fields for 'OR' condition(List of conditions)
	private List<MongoCondition> orConditions;

	/**
	 * Generate a simple condition(AND condition)
	 * 
	 * @param field
	 *            Where is the field in the JSON
	 * @param operation
	 *            Operation Type (MongoCondition.EQUALS,
	 *            MongoCondition.GREATER_THAN,
	 *            MongoCondition.GREATER_EQUAL_THAN...)
	 * @param value
	 *            Value for compare field
	 */
	public MongoCondition(String field, int operation, Object value) {
		this.condition = AND_CONDITION;
		this.field = field;
		this.operation = operation;
		this.value = value;
	}

	/**
	 * Multiple condition(OR condition). At least one must be true for filtering
	 * 
	 * @param conditions
	 *            List of conditions
	 */
	public MongoCondition(List<MongoCondition> conditions) {
		condition = OR_CONDITION;
		orConditions = conditions;
	}

	/**
	 * Generate a Map with all rules that contains the condition
	 * 
	 * @return Map with JSON structure for filtering
	 * @throws ApplicationException
	 *             Error generating condition
	 */
	public Map<String, Object> generateCondition() throws ApplicationException {
		Map<String, Object> returnCondition = new HashMap<String, Object>();
		try {
			if (condition == AND_CONDITION) {
				switch (operation) {
				case EQUALS:
					returnCondition.put(field, value);
					break;

				case GREATER_THAN:
					returnCondition.put(field, new Document("$gt", value));
					break;

				case GREATER_EQUAL_THAN:
					returnCondition.put(field, new Document("$gte", value));
					break;

				case LESS_THAN:
					returnCondition.put(field, new Document("$lt", value));
					break;

				case LESS_EQUAL_THAN:
					returnCondition.put(field, new Document("$lte", value));
					break;

				case NOT_EQUALS:
					returnCondition.put(field, new Document("$not",
							new Document("$eq", value)));
					break;

				case IN:
					// The value must be an ArrayList
					if (value instanceof ArrayList<?>) {
						returnCondition.put(field, new Document("$in", value));
					} else {
						String msgError = "Error in 'IN' condition for field: "
								+ field + " The fielt isn't a list";
						throw new ApplicationException(msgError);
					}
					break;

				case NOT_IN:
					// The value must be an ArrayList
					if (value instanceof ArrayList<?>) {
						returnCondition.put(field, new Document("$nin", value));
					} else {
						String msgError = "Error in 'NOT_IN' condition for field: "
								+ field + " The fielt isn't a list";
						throw new ApplicationException(msgError);
					}
					break;

				case EXISTS:
					returnCondition.put(field, new Document("$exists", true));
					break;

				case NOT_EXISTS:
					returnCondition.put(field, new Document("$exists", false));
					break;

				case REGEX:
					// The value must be an ArrayList
					if (value instanceof ArrayList<?>) {
						@SuppressWarnings("rawtypes")
						List valores = (ArrayList) value;
						returnCondition.put(field, new Document("$regex",
								valores.get(0)).append("$options",
								valores.get(1)));
					} else {
						String msgError = "Error in 'REGEX' condition for field: "
								+ field + " The fielt isn't a ArrayList<?>";
						throw new ApplicationException(msgError);
					}
					break;

				default:
					break;
				}

			} else if (condition == OR_CONDITION) {
				List<Document> orListDocs = new ArrayList<Document>();
				// For each OR condition obtains the map of all sub-conditions
				for (MongoCondition orCondition : orConditions) {
					Document orDoc = new Document();
					Map<String, Object> mapCondition = orCondition
							.generateCondition();
					for (String key : mapCondition.keySet()) {
						orDoc.append(key, mapCondition.get(key));
					}
					orListDocs.add(orDoc);
				}

				returnCondition.put("$or", orListDocs);
			}

		} catch (Exception e) {
			String msgError = "There are an error generate condition in field: "
					+ field;
			throw new ApplicationException(msgError, e);
		}
		return returnCondition;
	}

}
