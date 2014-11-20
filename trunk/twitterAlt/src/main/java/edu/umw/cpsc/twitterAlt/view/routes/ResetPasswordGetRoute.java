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
 * This route displays a password reset form with entries for a new password, old password,
 * and a confirmation of the old password.
 * 
 * @author evanmay
 *
 */
public class ResetPasswordGetRoute implements Route {

	@Override
	public Object handle(Request request, Response response) {
		// Declare the html(simple string writer that we use to write our html)
		StringWriter html = new StringWriter();
		// Declare an empty template
		Template template = null;
		try {
			// Get the html that we wrote in resetPassword.ftl
			template = HttpServer.getCfg().getTemplate("resetPassword.ftl");
			// Process any objects that we reference in our resetPassword.ftl 
			// (not used here so we just process an empty HashMap)
			template.process(new HashMap<>(), html);
		} catch (IOException | TemplateException e) {
			System.out.println("Cannot find the Reset template!");
		}
		// return the written/process html to the browser which is displayed
		return html;
	}

}
