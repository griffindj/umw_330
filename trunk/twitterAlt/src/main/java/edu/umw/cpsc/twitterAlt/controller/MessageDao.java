package edu.umw.cpsc.twitterAlt.controller;

import java.util.List;
import java.util.Set;

import com.mongodb.DB;
import com.mongodb.DBCollection;

import edu.umw.cpsc.twitterAlt.model.Message;
import edu.umw.cpsc.twitterAlt.model.User;

public class MessageDao {
	private final DBCollection messagesCollection;

	public MessageDao(final DB db) {
		messagesCollection = db.getCollection("users");
	}

	public boolean postMessage(Message msg) {
		return true;
	}
	
	public boolean deleteMessage(Message msg){
		return true;
	}
	
	public List<Message> getMessages(User user){
		return null;
	}
	
	public List<Message> getAllMessages(){
		return null;
	}
	
	public List<Message> searchMessages(String textQuery, boolean isHashtag){
		return null;
	}
	
	public Set<String> getTrendingHashtags(){
		return null;
	}
}
