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
	
	public boolean login(String username, String password){
		return true;
	}
	
	public boolean resetPassword(User user, String newPassword){
		return true;
	}
	
	public boolean subscribeToUser(User subscriber, User subscribee){
		return true;
	}
	
	public boolean deleteUser(User user){
		return true;
	}

}
