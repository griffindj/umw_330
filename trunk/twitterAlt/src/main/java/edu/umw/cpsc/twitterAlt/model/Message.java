package edu.umw.cpsc.twitterAlt.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Message {
	private String text; // this is trimmed at 140 characters
	private Date date = new Date();
	private Set<String> hashtags = new HashSet<String>();
	private Set<String> mentions = new HashSet<String>();

	public Message(String text) {
		System.out.println(text);
		setText(text);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		// this line will get the first 140 characters of the string
		this.text = text != null ? text.substring(0, Math.min(text.length(), 139)) : null;
		// here is also where we set the hashtags and mentions
	}

	public Date getDate() {
		return date;
	}

	public Set<String> getHashtags() {
		return hashtags;
	}

	public Set<String> getMentions() {
		return mentions;
	}

}
