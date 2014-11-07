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

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

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

	public static BasicDBObject convertToDBObject(Object o) {
		Map<String, Object> objectAsMap = new HashMap<String, Object>();
		try {
			BeanInfo info = Introspector.getBeanInfo(o.getClass());
			for (PropertyDescriptor pd : info.getPropertyDescriptors()) {
				Method reader = pd.getReadMethod();
				if (reader != null)
					objectAsMap.put(pd.getName(), reader.invoke(o));
			}
		} catch (Exception e) {
			System.out
					.println("Something went wrong during converting Java Object to Mongo DB Object");
			e.printStackTrace();
		}
		return new BasicDBObject(objectAsMap);
	}

	public static Object convertFromDBObject(DBObject dbo) {
		return null;
	}
}
