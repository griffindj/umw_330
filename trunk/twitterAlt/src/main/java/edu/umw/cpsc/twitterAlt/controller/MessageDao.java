package edu.umw.cpsc.twitterAlt.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBCursor;
import com.mongodb.DBObject;

import edu.umw.cpsc.twitterAlt.model.Message;
import edu.umw.cpsc.twitterAlt.model.User;

/**
 * This class handles interactions between the View and the Model that have to
 * deal with Messages
 * 
 * @author davidgriffin
 * @author zachpayne
 * @author evenmay
 */
public class MessageDao {
	private final DB db = MongoUtil.getInstance().getDb();
	private final DBCollection usersCollection = db.getCollection("users");

	/**
	 * Updates the database by adding a new message to the user's list of
	 * messages
	 * 
	 * @param username
	 * @param text
	 * @param isPublic
	 * @return
	 */
	public boolean postMessage(String username, String text, boolean isPublic) {
		// create a new message based on our text
		Message msg = new Message(text, isPublic);
		// using username, create a query to find user we're updating
		DBObject query = new BasicDBObject("username", username);
		// construct the new message to be pushed onto the Messages array
		DBObject newMsg = new BasicDBObject("$push", new BasicDBObject(
				"messages", MongoUtil.toDBObject(msg)));
		// finally we actually try to perform the update
		if (usersCollection.update(query, newMsg).getN() > 0) {
			// at least one record was updated, so return true
			return true;
		} else {
			return false;
		}
	}

	/**
	 * This method will remove a message from a user's document in the database.
	 * It should also take a user or username object so it knows which user to
	 * update (although I suppose it could search the database based on the
	 * timestamp to find the message since odds are low that any two messages
	 * will have same millisecond timestamp)
	 * 
	 * @param msg
	 * @return
	 */
	public boolean deleteMessage(Message msg,User user) {
		return true;
	}

	/**
	 * Get a list of messages that a particular user can see. This includes
	 * their own written messages, the public messages of users they are
	 * subscribed to, and any messages that contain a mention to them
	 * 
	 * @param user
	 * @return
	 */
	public List<Message> getMessages(User user) {
		List<Message> messages = new ArrayList<Message>();
		// build query that searchs for users who this user is subscribed
		user.getSubscriptions().add(user.getUsername());
		BasicDBObject query = new BasicDBObject("username", new BasicDBObject(
				"$in", user.getSubscriptions()));
		// append("$or", new BasicDBObject("username", user.getUsername()));
		System.out.println(query);
		DBCursor subscriptions = usersCollection.find(query);
		while (subscriptions.hasNext()) {
			User followedUser = (User) MongoUtil.fromDBObject(
					subscriptions.next(), new User());
			System.out.println(followedUser.getUsername());
			for (Message message : followedUser.getMessages()) {
				System.out.println(followedUser.getUsername());
				if (user.equals(followedUser) || message.isPublic()) {
					messages.add(message);
				}
			}
		}
		return messages;
	}

	/**
	 * Gets all public messages for all user's in the database. This is very
	 * similar to getMessages(user) and searchMessages(String) except that it
	 * only cares if a message is public or private. If it's public then add it
	 * to the list of messages to return
	 * 
	 * @return messages
	 */
	public List<Message> getAllMessages() {
		List<Message> messages = new ArrayList<Message>();
			for (Message message) {
				if (message.isPublic()) {
					messages.add(message);
				}else{
					
				}
			}
		return messages;
	}

	/**
	 * Searches all public messages for a given string.
	 * 
	 * @param textQuery
	 * @return
	 */
	public List<Message> searchMessages(String textQuery) {
		List<Message> messages = new ArrayList<Message>();
		DBObject query = new BasicDBObject("messages.text", textQuery);
		DBCursor matchingMessages = usersCollection.find(query);
		while (matchingMessages.hasNext()) {
			User user = (User) MongoUtil.fromDBObject(matchingMessages.next(),
					new User());
			for (Message message : user.getMessages()) {
				if (message.getText().contains(textQuery) && message.isPublic()) {
					messages.add(message);
				}
			}
		}
		return messages;
	}

	/**
	 * This should query the database and return the five or ten most used
	 * hashtags. //TODO implement this method
	 * 
	 * @return
	 */
	public Set<String> getTrendingHashtags() {
		return null;
	}
}
