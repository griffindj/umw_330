package edu.umw.cpsc.twitterAlt.dataModel;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Message {
	String text; // this is trimmed at 140 characters
	String author;
	Date date = new Date();
	Set<String> hashtags = new HashSet<String>();
	Set<String> mentions = new HashSet<String>();

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
		// FYI: this statement is shorthand for
		// if(text != null){
		// this.text = text.substring(0,139);
		// }else{
		// this.text = null;
		// }
		// see https://en.wikipedia.org/wiki/%3F%3a or
		// https://stackoverflow.com/questions/10336899/what-is-a-question-mark-and-colon-operator-within-the-parentheses-of-a-p
	}

	/**
	 * @return the author
	 */
	public String getAuthor() {
		return author;
	}

	/**
	 * @param author
	 *            the author to set
	 */
	public void setAuthor(String author) {
		this.author = author;
	}

	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * @return the hashtags
	 */
	public Set<String> getHashtags() {
		return hashtags;
	}

	/**
	 * @param hashtags
	 *            the hashtags to set
	 */
	public void setHashtags(Set<String> hashtags) {
		this.hashtags = hashtags;
	}

	/**
	 * @return the mentions
	 */
	public Set<String> getMentions() {
		return mentions;
	}

	/**
	 * @param mentions
	 *            the mentions to set
	 */
	public void setMentions(Set<String> mentions) {
		this.mentions = mentions;
	}

}
