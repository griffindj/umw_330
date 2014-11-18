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

/**
 * This route displays the user's profile (profile.ftl) page. The profile.ftl
 * page has a few forms and links to other routes, like the reset password link
 * which sends the user to the ResetPasswordGetRoute(not yet created, evan or
 * zach this would be a good thing to do). It also has a form for submitting a
 * new message to the WriteMessagePostRoute and will contain links to delete a
 * message (another good thing to work on/not yet implemented)
 * 
 * @author davidgriffin
 *
 */
public class ProfileGetRoute implements Route {

	public Object handle(Request request, Response response) {
		// declare our html string
		StringWriter html = new StringWriter();
		// declare an empty template that we will process
		Template template = null;
		// create a hashmap of values that the template has access to display
		// for instance putting the current user object into the HashMap allows
		// use to access it with ${user.username} to display the current user's
		// username on the profile.ftl template
		HashMap<String, Object> attributes = new HashMap<>();
		// put the session User into the Hashmap so the template can use
		attributes.put("user", request.session().attribute("user"));
		attributes.put("messageFeed", request.session()
				.attribute("messageFeed"));
		try {
			// get and process the template with the hashMap we created
			template = HttpServer.getCfg().getTemplate("profile.ftl");
			template.process(attributes, html);
		} catch (IOException | TemplateException e) {
			System.out.println("Cannot process the profile template!");
		}
		return html;
	}
}
