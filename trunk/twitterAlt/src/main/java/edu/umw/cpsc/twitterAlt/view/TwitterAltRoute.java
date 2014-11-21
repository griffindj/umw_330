package edu.umw.cpsc.twitterAlt.view;

import java.io.StringWriter;
import java.util.HashMap;

import freemarker.template.Template;
import spark.Route;

public abstract class TwitterAltRoute implements Route {
	// declare our html string (that will contain the result of processing out
	// template and hashmap)
	private StringWriter html = new StringWriter();
	// declare an empty template that we will process
	private Template template = null;
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

	public StringWriter getHtml() {
		return html;
	}
}
