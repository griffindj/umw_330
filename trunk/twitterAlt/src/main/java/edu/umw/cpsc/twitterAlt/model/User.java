package edu.umw.cpsc.twitterAlt.model;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author davidgriffin
 *
 */
public class User {
	private String username;
	private String password;
	private List<Message> messages = new ArrayList<Message>();
	private Set<String> subscriptions = new HashSet<String>();

	/**
	 * default constructor which creates a blank User object
	 */
	public User() {
	}

	/**
	 * Custom constructor for a new User
	 * @param username of the new user
	 * @param password of the new user
	 */
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

	public List<Message> getMessages() {
		return messages;
	}

	public void setMessages(List<Message> messages) {
		this.messages = messages;
	}

}
