package work.arturo.curriculum.mongo;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;

import work.arturo.curriculum.exception.ApplicationException;
import work.arturo.curriculum.mongo.filter.MongoFilter;
import work.arturo.curriculum.mongo.filter.components.MongoCondition;
import work.arturo.curriculum.mongo.update.UpdateList;
import work.arturo.curriculum.util.JsonUtils;

import com.mongodb.MongoClient;
import com.mongodb.MongoWriteException;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.gridfs.GridFSBucket;
import com.mongodb.client.gridfs.GridFSBuckets;

/**
 * Class for encapsulate MongoDB actions and manage the connection
 * 
 * @author Arturo
 * 
 */
public class MongoManager {

	private String host;
	private int port;
	private String dataBase;

	private MongoClient mongoClient;
	private MongoDatabase mongoDatabase;

	/**
	 * Load the parameters for establish a MongoDB connection
	 * 
	 * @param host
	 *            MongoDB Host to connect
	 * @param port
	 *            MongoDB Port to connect
	 * @param database
	 *            MongoDB Database to connect
	 */
	public MongoManager(String host, int port, String database) {
		this.host = host;
		this.port = port;
		this.dataBase = database;
	}

	/**
	 * Open a MongoDB connection
	 */
	public void open() {
		mongoClient = new MongoClient(host, port);
		mongoDatabase = mongoClient.getDatabase(dataBase);
	}

	/**
	 * Close a MongoDB connection
	 */
	public void close() {
		mongoClient.close();

	}

	/**
	 * Find all documents stored in a MongoDB collection
	 * 
	 * @param collection
	 *            MongoDB collection name
	 * @return List with all JSON inside Document Object
	 * @throws ApplicationException
	 *             Error in search
	 */
	public List<Document> findAll(String collection)
			throws ApplicationException {
		return find(collection, new MongoFilter());
	}

	/**
	 * Find all Objects stored in a MongoDB collection
	 * 
	 * @param collection
	 *            MongoDB collection name
	 * @param type
	 * @return List with all JSON inside Document Object
	 * @throws ApplicationException
	 *             Error in search
	 */
	public <T> List<T> findAll(String collection, Class<T> type)
			throws ApplicationException {
		List<T> listReturn = new ArrayList<T>();
		for (Document json : findAll(collection)) {
			listReturn.add(JsonUtils.fromJson(json, type));
		}

		return listReturn;

	}

	/**
	 * Find a list of documents that match with indicated filters
	 * 
	 * @param collection
	 *            Collection name to search
	 * @param filter
	 *            Filter for find documents
	 * @return Document List with data find
	 * @throws ApplicationException
	 *             Error generating filter
	 */
	public List<Document> find(String collection, MongoFilter filter)
			throws ApplicationException {
		// Launch query with filters
		FindIterable<Document> iterable = mongoDatabase.getCollection(
				collection).find(filter.generateFilter());

		// Sort MongoDB response
		iterable.sort(filter.generateSort());

		return FindIterableToList(iterable);
	}

	/**
	 * Find a list of objects that match with indicated filters
	 * 
	 * @param collection
	 *            Collection name to search
	 * @param filter
	 *            Filter for find objects
	 * @param type
	 *            Class for result List
	 * @return Object List of indicated type with data find
	 * @throws ApplicationException
	 *             Error generating filter
	 */
	public <T> List<T> find(String collection, MongoFilter filter, Class<T> type)
			throws ApplicationException {
		List<T> listReturn = new ArrayList<T>();
		for (Document json : find(collection, filter)) {
			listReturn.add(JsonUtils.fromJson(json, type));
		}

		return null;

	}

	/**
	 * Find a Document with indicated _id
	 * 
	 * @param collection
	 *            Name of collection to search
	 * @param id
	 *            Id that match with document _id
	 * @return Document found
	 * @throws ApplicationException
	 *             Error generating filter, Error in result number
	 */
	public Document findById(String collection, String id)
			throws ApplicationException {

		// Create a filter and find the JSON documents
		MongoFilter filter = new MongoFilter();
		filter.addCondition(new MongoCondition("_id", MongoCondition.EQUALS,
				new ObjectId(id)));

		FindIterable<Document> iterable = mongoDatabase.getCollection(
				collection).find(filter.generateFilter());

		List<Document> documents = FindIterableToList(iterable);

		// Verify the number of results
		if (documents == null || documents.size() != 1) {
			String number;
			if (documents == null) {
				number = "null";
			} else {
				number = String.valueOf(documents.size());
			}
			throw new ApplicationException("Error in results number: " + number);
		}

		return FindIterableToList(iterable).get(0);
	}

	/**
	 * 
	 * Find a Object with indicated _id
	 * 
	 * @param collection
	 *            Name of collection to search
	 * @param id
	 *            Id that match with document _id
	 * @param type
	 *            Class for returned object
	 * @return Object found
	 * @throws ApplicationException
	 *             Error generating filter, Error in result number
	 */
	public <T> T findById(String collection, String id, Class<T> type)
			throws ApplicationException {
		T objReturn = null;

		// Find the JSON document by id and parse it to indicated object
		Document jsonObj = findById(collection, id);
		objReturn = JsonUtils.fromJson(jsonObj, type);

		return objReturn;
	}
	
	/**
	 * Find a file in a bucket
	 * 
	 * @param fileName
	 *            Name of file
	 * @param bucket
	 *            Name of bucket
	 */
	public byte[] findFile(String filename, String bucket) {
		ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
		GridFSBucket gridFSBucket = GridFSBuckets.create(mongoDatabase, bucket);
		gridFSBucket.downloadToStream(filename, outputStream);

		return outputStream.toByteArray();
	}


	/**
	 * Create a JSON Document in a collection
	 * 
	 * @param collection
	 *            Name of collection where create the Documents
	 * @param document
	 *            Document to create
	 */
	public void create(String collection, Document document) {
		mongoDatabase.getCollection(collection).insertOne(document);

	}

	/**
	 * Create several JSON Documents in a collection
	 * 
	 * @param collection
	 *            Name of collection where create the Documents
	 * @param documents
	 *            List of Documents to create
	 */
	public void create(String collection, List<Document> documents) {
		mongoDatabase.getCollection(collection).insertMany(documents);

	}

	/**
	 * Update documents when match with a filter
	 * 
	 * @param collection
	 *            Collection where the update will be
	 * @param filter
	 *            Filter for update Documents
	 * @param updates
	 *            Update list to do
	 * @throws ApplicationException
	 *             Error generating filter
	 */
	public void update(String collection, MongoFilter filter, UpdateList updates)
			throws ApplicationException {
		mongoDatabase.getCollection(collection).updateMany(
				filter.generateFilter(), updates.generateUpdates());
	}

	/**
	 * Replace a document for another with the same _id
	 * 
	 * @param collection
	 *            Collection name where the Document be
	 * @param oldDocument
	 *            Old Document version for search
	 * @param newDocument
	 *            New Document version for replace
	 * @throws MongoWriteException
	 *             The Documents _id doesn't match
	 */
	public void replace(String collection, Document oldDocument,
			Document newDocument) throws MongoWriteException {
		mongoDatabase.getCollection(collection).replaceOne(oldDocument,
				newDocument);
	}

	/**
	 * Delete only one Document
	 * 
	 * @param collection
	 *            Collection name where the document be
	 * @param document
	 *            Exactly document to delete
	 */
	public void delete(String collection, Document document) {
		mongoDatabase.getCollection(collection).deleteOne(document);

	}

	/**
	 * Delete all Documents on collection that match a filter
	 * 
	 * @param collection
	 *            Collection name
	 * @param filter
	 *            Conditions to delete documents
	 * @throws ApplicationException
	 *             Error generating filter
	 */
	public void delete(String collection, MongoFilter filter)
			throws ApplicationException {
		mongoDatabase.getCollection(collection).deleteMany(
				filter.generateFilter());

	}

	/**
	 * Parse an FindIterable of Documents to List of Documents
	 * 
	 * @param iterable
	 *            FindIterable object to parse
	 * @return List of Documents returned
	 */
	private List<Document> FindIterableToList(FindIterable<Document> iterable) {
		List<Document> documents = new ArrayList<Document>();
		for (Document document : iterable) {
			documents.add(document);
		}
		return documents;
	}

}
