package edu.umw.cpsc.twitterAlt.controller;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import edu.umw.cpsc.twitterAlt.model.User;

public class UserDao {
	private final DB db = MongoUtil.getInstance().getDb();
	private final DBCollection usersCollection = db.getCollection("users");;

	public boolean registerUser(User user) {
		// convert User object to a DBObject to query/insert into Mongo
		DBObject userDoc = MongoUtil.toDBObject(user);
		// we query to ensure the user doesn't already exist
		if (usersCollection.count(userDoc) > 0) {
			System.out.print("User already exists");
			return false;
			//TODO add more checks for password validation and null checks
		}else {
			usersCollection.insert(userDoc);
			System.out.println("User " + user.getUsername()
					+ " saved to MongoDB");
			return true;
		}
	}

	public boolean login(String username, String password) {
		BasicDBObject loginQuery = new BasicDBObject("username", username).append("password", password);
		if (usersCollection.count(loginQuery) > 0){
			return true;
		}else{
			return false;
			
		}
	}

	public User getUser(String username) {
		return null;
	}

	public boolean resetPassword(User user, String newPassword) {
		return true;
	}

	public boolean subscribeToUser(User subscriber, User subscribee) {
		return true;
	}

	public boolean deleteUser(User user) {
		return true;
	}

}
