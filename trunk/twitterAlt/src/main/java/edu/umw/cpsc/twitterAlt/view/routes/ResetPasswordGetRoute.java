package edu.umw.cpsc.twitterAlt.view.routes;

import java.io.IOException;
import java.util.HashMap;

import spark.Request;
import spark.Response;
import edu.umw.cpsc.twitterAlt.view.HttpServer;
import edu.umw.cpsc.twitterAlt.view.TwitterAltRoute;
import freemarker.template.TemplateException;

/**
 * This route displays a password reset form with entries for a new password, old password,
 * and a confirmation of the old password.
 * 
 * @author evanmay
 *
 */
public class ResetPasswordGetRoute extends TwitterAltRoute {

	@Override
	public Object handle(Request request, Response response) {
		try {
			// Get the html that we wrote in resetPassword.ftl
			setTemplate(HttpServer.getCfg().getTemplate("resetPassword.ftl"));
			// Process any objects that we reference in our resetPassword.ftl 
			// (not used here so we just process an empty HashMap)
			getTemplate().process(new HashMap<>(), getHtml());
		} catch (IOException | TemplateException e) {
			System.out.println("Cannot find the Reset template!");
		}
		// return the written/process html to the browser which is displayed
		return getHtml();
	}

}
