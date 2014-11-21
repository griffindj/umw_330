package edu.umw.cpsc.twitterAlt.view;

import java.util.HashMap;

import spark.Route;

public abstract class TwitterAltRoute implements Route{

	HashMap<String,Object> attributes = new HashMap<>();
	
	public HashMap<String, Object> getAttributes() {
		return attributes;
	}
}
