package edu.umw.cpsc.twitterAlt.view.routes;

import edu.umw.cpsc.twitterAlt.view.HttpServer;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;

import spark.Request;
import spark.Response;
import spark.Route;

/**
 * This route displays a deletion form, asking the user to confirm the deletion
 * of their account.
 * 
 * @author evanmay
 *
 */
public class DeleteUserGetRoute implements Route {

	@Override
	public Object handle(Request request, Response response) {
		// Declare the html(simple string writer that we use to write our html)
		StringWriter html = new StringWriter();
		// Declare an empty template
		Template template = null;
                HashMap<String, Object> attributes = new HashMap<>();
		// put the session User into the Hashmap so the template can use
		attributes.put("user", request.session().attribute("user"));
                
		try {
			// Get the html that we wrote in resetPassword.ftl
			template = HttpServer.getCfg().getTemplate("deleteUser.ftl");
			// Process any objects that we reference in our deleteUser.ftl
			// (not used here so we just process an empty HashMap)
			template.process(attributes, html);
		} catch (IOException | TemplateException e) {
			System.out.println("Cannot find the Delete template!");
		}
		// return the written/process html to the browser which is displayed
		return html;
	}

}
