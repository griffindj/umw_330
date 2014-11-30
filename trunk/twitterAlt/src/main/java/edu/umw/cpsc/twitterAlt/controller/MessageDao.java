package edu.umw.cpsc.twitterAlt.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

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
		Message msg = new Message(username, text, isPublic);
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
	 * This method takes a username and postedDate strings. THe username allows
	 * us to find the user's mongodb document. The postedDate allows us to
	 * remove all messages that were posted on that specific datetime(down to
	 * the second)
	 * 
	 * @param msg
	 * @return
	 */
	public boolean deleteMessage(String username, String postedDate) {
		// query to fine the document that matches the username
		BasicDBObject query = new BasicDBObject("username", username);
		// update instructions telling the db to pull/remove the message from
		// the messages array that was posted on the "postedDate"
		BasicDBObject updateInstructions = new BasicDBObject("$pull",
				new BasicDBObject("messages", new BasicDBObject("date",
						postedDate)));

		System.out.println(updateInstructions);

		if (usersCollection.update(query, updateInstructions).getN() > 0) {
			return true;
		} else {
			return false;
		}
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

		DBCursor subscriptions = usersCollection.find(query);

		while (subscriptions.hasNext()) {
			User followedUser = (User) MongoUtil.fromDBObject(
					subscriptions.next(), new User());
			for (Message message : followedUser.getMessages()) {
				messages.add(message);
			}
		}
		// sort the messages by date
		Collections.sort(messages);
		// and return them
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
	public List<Message> getMessages() {
		// create an empty list of messages that we will pass back
		List<Message> messages = new ArrayList<Message>();

		// query the database for all users (so we can process their messages)
		DBCursor allUsers = usersCollection.find();

		// loop through all users
		while (allUsers.hasNext()) {
			User user = (User) MongoUtil.fromDBObject(allUsers.next(),
					new User());
			// for each message, if it's public, add it to the list
			for (Message message : user.getMessages()) {
				if (message.isPublic()) {
					messages.add(message);
				}
			}
		}
		Collections.sort(messages);
		// return our list of all Public Messages in the database
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
		Pattern pattern = Pattern.compile(".*" + textQuery + ".*");
		DBObject query = new BasicDBObject("messages.text",
				Pattern.compile(".*" + textQuery + ".*"));
		DBCursor matchingMessages = usersCollection.find(query);
		System.out.println(query);
		System.out.println(matchingMessages.count());
		while (matchingMessages.hasNext()) {
			User user = (User) MongoUtil.fromDBObject(matchingMessages.next(),
					new User());
			for (Message message : user.getMessages()) {
				if (message.getText().contains(textQuery) && message.isPublic()) {
					messages.add(message);
				}
			}
		}
		System.out.println(messages.size());
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
