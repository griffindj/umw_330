package edu.umw.cpsc.twitterAlt.view.routes;

import java.io.IOException;

import spark.Request;
import spark.Response;
import edu.umw.cpsc.twitterAlt.view.HttpServer;
import edu.umw.cpsc.twitterAlt.view.TwitterAltRoute;
import freemarker.template.TemplateException;

/**
 * This route displays a deletion form, asking the user to confirm the deletion
 * of their account.
 * 
 * @author evanmay
 *
 */
public class DeleteUserGetRoute extends TwitterAltRoute {

	@Override
	public Object handle(Request request, Response response) {
		// put the session User into the Hashmap so the template can use
		getAttributes().put("user", request.session().attribute("user"));

		try {
			// Get the html that we wrote in resetPassword.ftl
			setTemplate(HttpServer.getCfg().getTemplate("deleteUser.ftl"));
			// Process any objects that we reference in our deleteUser.ftl
			// (not used here so we just process an empty HashMap)
			getTemplate().process(getAttributes(), getHtml());
		} catch (IOException | TemplateException e) {
			System.out.println("Cannot find the Delete template!");
		}
		// return the written/process html to the browser which is displayed
		return getHtml();
	}

}
