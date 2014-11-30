package edu.umw.cpsc.twitterAlt.view;

import java.util.HashMap;

import spark.Route;
import freemarker.template.Template;

/**
 * This abstract class is our basic implementation of a "Spark Route" We have a
 * template object which has our "preprocessed" html An attributes HashMap that
 * stores data that we can use during processing The handle(request,response)
 * method processes the template's html with our hashmap that results in a
 * combined view
 * 
 * @author davidgriffin
 *
 */
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
