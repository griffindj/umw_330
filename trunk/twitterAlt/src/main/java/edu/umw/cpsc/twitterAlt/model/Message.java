package edu.umw.cpsc.twitterAlt.model;

import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Message implements Comparable<Message> {
	private String text; // this is trimmed at 140 characters
	private boolean isPublic;
	private Date date = new Date();
	private Set<String> hashtags = new HashSet<String>();
	private Set<String> mentions = new HashSet<String>();

	public Message(String text, boolean isPublic) {
		setText(text);
		setPublic(isPublic);
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		// this line will get the first 140 characters of the string
		this.text = text != null ? text.substring(0,
				Math.min(text.length(), 139)) : null;
		// here is also where we set the hashtags and mentions
		// start by splitting the text into words by whitespace
		String[] words = text.split("\\s+");
		// for each word
		for (String word : words) {
			// if starts with @, then add the name to our Set of mentions
			if (word.startsWith("@")) {
				this.mentions.add(word.substring(1, word.length()));
			}
			// if starts with #, then add the name to our Set of hashtags
			if (word.startsWith("#")) {
				this.hashtags.add(word.substring(1, word.length()));
			}
		}
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

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	@Override
	public int compareTo(Message o) {
		return Comparators.DATE.compare(this, o);
	}

	public static class Comparators {
		public static final Comparator<Message> DATE = (Message o1, Message o2) -> o1.date
				.compareTo(o2.date);
	}

}
