package edu.umw.cpsc.twitterAlt.controller;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

import edu.umw.cpsc.twitterAlt.model.User;

public class UserDao {
	private final DBCollection usersCollection;

	public UserDao(final DB db) {
		usersCollection = db.getCollection("users");
	}

	public boolean registerUser(User user) {
		BasicDBObject userDoc = MongoUtil.convertToDBObject(user);
		usersCollection.insert(userDoc);
		return true;
	}

}
