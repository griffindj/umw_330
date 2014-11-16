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

public class MessageDao {
	private final DB db = MongoUtil.getInstance().getDb();
	private final DBCollection usersCollection = db.getCollection("users");

	public boolean postMessage(String username, String text, boolean isPublic) {
		// create a new message based on our text
		Message msg = new Message(text, isPublic);
		// using username, create a query to find user we're updating
		DBObject query = new BasicDBObject("username", username);
		// construct the new message to be pushed onto the Messages array
		DBObject newMsg = new BasicDBObject("$push", new BasicDBObject(
				"messages", MongoUtil.toDBObject(msg)));

		System.out.println(newMsg);

		// finally we actually try to perform the update
		if (usersCollection.update(query, newMsg).getN() > 0) {
			// at least one record was updated, so return true
			return true;
		} else {
			return false;
		}
	}

	public boolean deleteMessage(Message msg) {
		return true;
	}

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

	public List<Message> getAllMessages() {
		return null;
	}

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

	public Set<String> getTrendingHashtags() {
		return null;
	}
}
