package edu.umw.cpsc.twitterAlt.controller;

import java.util.List;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
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
		return null;
	}

	public List<Message> getAllMessages() {
		return null;
	}

	public List<Message> searchMessages(String textQuery, boolean isHashtag) {
		return null;
	}

	public Set<String> getTrendingHashtags() {
		return null;
	}
}
