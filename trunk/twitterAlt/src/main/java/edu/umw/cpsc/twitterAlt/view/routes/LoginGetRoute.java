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
 * This route is what displays the Login Page when a user types
 * "http://host:port/login". The login.ftl page contains a form with text inputs
 * for username and password and buttons to either login (post@login route) or
 * register(post@register route) or cancel (just clear the form and stay on the
 * login page)
 * 
 * @author davidgriffin
 *
 */
public class LoginGetRoute implements Route {
	@Override
	public Object handle(Request request, Response response) {
		// Declare the html(simple string writer that we use to write our html)
		StringWriter html = new StringWriter();
		// Declare an empty template
		Template template = null;
		try {
			// Get the html that we wrote in login.ftl
			template = HttpServer.getCfg().getTemplate("login.ftl");
			// Process any objects that we reference in our login.ftl (not used
			// here so we just process an empty HashMap)
			template.process(new HashMap<>(), html);
		} catch (IOException | TemplateException e) {
			System.out.println("Cannot find the Signup template!");
		}
		// return the written/process html to the browser which is displayed
		return html;
	}
}
