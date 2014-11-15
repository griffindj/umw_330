package edu.umw.cpsc.twitterAlt.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Message {
	private String text; // this is trimmed at 140 characters
	private Date date = new Date();
	private Set<String> hashtags = new HashSet<String>();
	private Set<String> mentions = new HashSet<String>();

	/**
	 * @return the text
	 */
	public String getText() {
		return text;
	}

	/**
	 * @param text
	 *            the text to set
	 */
	public void setText(String text) {
		// this line will get the first 140 characters of the string
		this.text = text != null ? text.substring(0, 139) : null;
		//here is also where we set the hashtags and mentions
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @return the hashtags
	 */
	public Set<String> getHashtags() {
		return hashtags;
	}

	/**
	 * @return the mentions
	 */
	public Set<String> getMentions() {
		return mentions;
	}

}
