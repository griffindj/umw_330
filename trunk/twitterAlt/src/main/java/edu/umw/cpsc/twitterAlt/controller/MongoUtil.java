package edu.umw.cpsc.twitterAlt.controller;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

import com.google.gson.Gson;
import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.util.JSON;

public class MongoUtil {
	private static MongoUtil instance;
	private MongoClient mongoClient;
	private DB db;
	public static final String MONGO_SERVER_ADDRESS = "mongodb://localhost";

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

	public static DBObject toDBObject(Object o) {
		DBObject dbObject = null;
		try {
			// first convert object to JSON (which is how Mongo will store it
			Gson gson = new Gson();
			String oAsJson = gson.toJson(o);
			// convert JSON formatted string to a Mongo DBObject
			dbObject = (DBObject) JSON.parse(oAsJson);
		} catch (Exception e) {
			System.out
					.println("error trying to convert object to Json then to Mongo DB Object");
			e.printStackTrace();
		}
		return dbObject;
	}

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
