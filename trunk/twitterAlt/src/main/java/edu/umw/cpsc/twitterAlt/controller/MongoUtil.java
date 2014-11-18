package edu.umw.cpsc.twitterAlt.controller;

import java.net.UnknownHostException;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.util.JSON;

/**
 * This class is a utility class that translates objects to MongoDB and vice
 * versa
 * 
 * @author davidgriffin
 *
 */
public class MongoUtil {
	private static MongoUtil instance;
	private MongoClient mongoClient;
	private DB db;
	public static final String MONGO_SERVER_ADDRESS = "mongodb://localhost";

	/**
	 * private constructor that limits instantiation to one instance
	 */
	private MongoUtil() {
		// private constructor that instantiates our Database and establishes a
		// single connection for our application
		try {
			this.mongoClient = new MongoClient(new MongoClientURI(
					MONGO_SERVER_ADDRESS));
		} catch (UnknownHostException e) {
			System.out
					.println("could not find local Mongo Host.  Ensure mongod is running");
			e.printStackTrace();
		}
		this.db = mongoClient.getDB("twitterAlt");
	}

	// this allows external classes access to our single MongoUtil instance
	public static MongoUtil getInstance() {
		return instance == null ? new MongoUtil() : instance;
	}

	public DB getDb() {
		return this.db;
	}

	/**
	 * Converts an object to a Mongo specific DBObject (which is essentially a
	 * json representation of the object)
	 * 
	 * @param o
	 *            the object to be converted
	 * @return the converted object o, as represented as a DBObject
	 */
	public static DBObject toDBObject(Object o) {
		DBObject dbObject = null;
		try {
			// first convert object to JSON (which is how Mongo will store it
			Gson gson = new Gson();
			String oAsJson = gson.toJson(o);
			// convert JSON formatted string to a Mongo DBObject
			dbObject = (BasicDBObject) JSON.parse(oAsJson);
		} catch (Exception e) {
			System.out
					.println("error trying to convert object to Json then to Mongo DB Object");
			e.printStackTrace();
		}
		return dbObject;
	}

	/**
	 * This method populates an object from a DBObject. So if you have a user
	 * object in MongoDB's DBObject format, then you can populate a new/or
	 * existing User object from that.
	 * 
	 * @param dbo
	 *            the MongoDB DBObject that contains the data from the database
	 * @param o
	 *            the Plain Old Java Object that you want to be populated
	 * @return returns o after it has been populated with the values in dbo
	 */
	public static Object fromDBObject(DBObject dbo, Object o) {
		try {
			// first convert mongodb object to JSON string
			Gson gson = new Gson();
			String dbObjectAsJson = JSON.serialize(dbo);
			// then we populate our object from JSON string based on class type
			o = gson.fromJson(dbObjectAsJson, o.getClass());
		} catch (Exception e) {
			System.out.println("error converting mongo DB Object to " + o);
			e.printStackTrace();
		}
		return o;
	}
}
