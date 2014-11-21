package edu.umw.cpsc.twitterAlt.controller;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;
import com.mongodb.WriteConcern;
import edu.umw.cpsc.twitterAlt.model.Message;

import edu.umw.cpsc.twitterAlt.model.User;
import java.util.ArrayList;
import java.util.List;

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

		DBObject query = new BasicDBObject("username", user.getUsername());
		DBObject newPass = new BasicDBObject("$set", new BasicDBObject(
				"password", newPassword));

		if (usersCollection.update(query, newPass).getN() > 0) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Updates the database and adds a subscription to the subscribee's account
	 * 
	 * @param subscriber
	 * @param subscribee
	 * @return
	 */
	public boolean subscribeToUser(String subscriber, String subscribee) {
            DBObject query = new BasicDBObject("username", subscriber);
            
            
            DBObject newSub = new BasicDBObject("$push", new BasicDBObject(
                    "subscriptions", subscribee));
            
            return (usersCollection.update(query, newSub).getN() > 0);
	}

	/**
	 * Deletes a user from the database
	 * 
         * @param username
         *         username of the user to be deleted
	 * @return true if the user was deleted, false if for some reason their were
	 *         not (like they didn't exist in the first place)
	 */
	public boolean deleteUser(String username) {
                DBObject query = new BasicDBObject("username", username);
                
                if (usersCollection.remove(query).getN() > 0)
                    return true;
                else 
                    return false;
	}
        
        /**
         * 
         * @return 
         */
        public List<String> getAllUsernames()
        {
            List<String> usernames = new ArrayList<String>();
            DBCursor users = usersCollection.find();
		while (users.hasNext()) {
			User user = (User) MongoUtil.fromDBObject(
					users.next(), new User());
                        usernames.add(user.getUsername());
		}
            return usernames;
        }
        
        /**
         * 
         * @param username
         * @return 
         */
        public List<String> getPossibleSubscriptions(String username) {
            List<String> availableUsers = getAllUsernames();
            
            User currentUser = getUser(username);
            
            availableUsers.removeAll(currentUser.getSubscriptions());
            availableUsers.remove(username);
            
            return availableUsers;
        }

}
