package edu.umw.cpsc.twitterAlt.view.routes;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;

import spark.Request;
import spark.Response;
import spark.Route;
import edu.umw.cpsc.twitterAlt.view.HttpServer;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class ProfileGetRoute implements Route {

	public Object handle(Request request, Response response) {
		StringWriter html = new StringWriter();
		Template template = null;
		HashMap<String, Object> attributes = new HashMap<>();
		// put the session User into the Hashmap so the template can use
		attributes.put("user", request.session().attribute("user"));
		attributes.put("messageFeed", request.session().attribute("messageFeed"));
		try {
			template = HttpServer.getCfg().getTemplate("profile.ftl");
			template.process(attributes, html);
		} catch (IOException | TemplateException e) {
			System.out.println("Cannot process the profile template!");
		}
		return html;
	}
}
