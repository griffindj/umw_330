package edu.umw.cpsc.twitterAlt.view.routes;

import java.io.IOException;

import spark.Request;
import spark.Response;
import edu.umw.cpsc.twitterAlt.view.HttpServer;
import edu.umw.cpsc.twitterAlt.view.TwitterAltRoute;
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
public class ProfileGetRoute extends TwitterAltRoute {

	public Object handle(Request request, Response response) {
		// put the session User into the Hashmap so the template can use
		getAttributes().put("user", request.session().attribute("user"));
		getAttributes().put("messageFeed", request.session()
				.attribute("messageFeed"));
		try {
			// get and process the template with the hashMap we created
			setTemplate(HttpServer.getCfg().getTemplate("profile.ftl"));
			getTemplate().process(getAttributes(), getHtml());
		} catch (IOException | TemplateException e) {
			System.out.println("Cannot process the profile template!");
		}
		return getHtml();
	}
}
