package edu.umw.cpsc.twitterAlt.controller;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;

import edu.umw.cpsc.twitterAlt.model.Message;

public class MessageDao {
	private final DBCollection messagesCollection;

	public MessageDao(final DB db) {
		messagesCollection = db.getCollection("users");
	}

	public boolean postMessage(Message msg) {
		BasicDBObject userDoc = MongoUtil.convertToDBObject(msg);
		messagesCollection.insert(userDoc);
		return true;
	}
}
