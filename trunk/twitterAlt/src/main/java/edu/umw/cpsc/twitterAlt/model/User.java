package edu.umw.cpsc.twitterAlt.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class User {
	String username;
	String password;
	List<Message> postedMessages;
	Set<String> subscriptions = new HashSet<String>();

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Set<String> getSubscriptions() {
		return subscriptions;
	}

	public void setSubscriptions(Set<String> subscriptions) {
		this.subscriptions = subscriptions;
	}

	public List<Message> getPostedMessages() {
		return postedMessages;
	}

	public void setPostedMessages(List<Message> postedMessages) {
		this.postedMessages = postedMessages;
	}

}
