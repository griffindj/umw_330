package edu.umw.cpsc.twitterAlt.view;

import java.util.HashMap;

import spark.Route;
import freemarker.template.Template;

public abstract class TwitterAltRoute implements Route {
	// declare an empty template that we will process
	private Template template;
	// this hashmap contains values that can be accessed by the template
	private HashMap<String, Object> attributes = new HashMap<>();

	public HashMap<String, Object> getAttributes() {
		return attributes;
	}

	public Template getTemplate() {
		return template;
	}

	public void setTemplate(Template template) {
		this.template = template;
	}
}
