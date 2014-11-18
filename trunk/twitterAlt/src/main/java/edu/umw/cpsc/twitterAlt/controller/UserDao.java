package edu.umw.cpsc.twitterAlt.controller;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

import edu.umw.cpsc.twitterAlt.model.User;

/**
 * This class performs operations and logic that has to deal with users. It
 * connects the View (routes) to the Model (database)
 * 
 * @author davidgriffin
 *
 */
public class UserDao {
	private final DB db = MongoUtil.getInstance().getDb();
	private final DBCollection usersCollection = db.getCollection("users");

	/**
	 * Registers a user by creating a new MongoDB user document in the database.
	 * Handles other logic like checking to see if there's an existing user, and
	 * checking the password complexity
	 * 
	 * @param user
	 * @return
	 */
	public boolean registerUser(User user) {
		// convert User object to a DBObject to query/insert into Mongo
		DBObject userDoc = MongoUtil.toDBObject(user);
		// we query to ensure the user doesn't already exist
		// TODO we should also check to ensure the password is 20 chars long and
		// has 1 special char, 1 number and 1 captial
		if (usersCollection.count(userDoc) > 0) {
			System.out.print("User already exists");
			return false;
		} else {
			usersCollection.insert(userDoc);
			System.out.println("User " + user.getUsername()
					+ " saved to MongoDB");
			return true;
		}
	}

	/**
	 * Checks the database for a user that exists with the matching username and
	 * password
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public boolean login(String username, String password) {
		BasicDBObject loginQuery = new BasicDBObject("username", username)
				.append("password", password);
		if (usersCollection.count(loginQuery) > 0) {
			return true;
		} else {
			return false;

		}
	}

	/**
	 * Searches the database for a user with the given username and returns it
	 * (or null if not found)
	 * 
	 * @param username
	 * @return
	 */
	public User getUser(String username) {
		// create a mongo query based on username
		BasicDBObject getUserQuery = new BasicDBObject("username", username);
		// populate a new User object from that query
		return (User) MongoUtil.fromDBObject(
				usersCollection.findOne(getUserQuery), new User());
	}

	/**
	 * Updates the database with a new password for the user that's passed in
	 * 
	 * @param user
	 *            the user we are updating (could possibly just be the username)
	 * @param newPassword
	 *            (the new password)
	 * @return true if the password was updated, false if not updated for
	 *         reasons like the user doesn't exist
	 */
	public boolean resetPassword(User user, String newPassword) {
		return true;
	}

	/**
	 * Updates the database and adds a subscription to the subscribee's account
	 * 
	 * @param subscriber
	 * @param subscribee
	 * @return
	 */
	public boolean subscribeToUser(User subscriber, User subscribee) {
		return true;
	}

	/**
	 * Deletes a user from the database
	 * 
	 * @param user
	 *            the user to be deleted
	 * @return true if the user was deleted, false if for some reason their were
	 *         not (like they didn't exist in the first place)
	 */
	public boolean deleteUser(User user) {
		return true;
	}

}
