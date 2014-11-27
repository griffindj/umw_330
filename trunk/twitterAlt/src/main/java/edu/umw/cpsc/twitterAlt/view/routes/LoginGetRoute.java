package edu.umw.cpsc.twitterAlt.view.routes;

import java.io.IOException;
import java.io.StringWriter;

import spark.Request;
import spark.Response;
import edu.umw.cpsc.twitterAlt.view.HttpServer;
import edu.umw.cpsc.twitterAlt.view.TwitterAltRoute;
import freemarker.template.TemplateException;

/**
 * This route is what displays the Login Page when a user types
 * "http://host:port/login". The login.ftl page contains a form with text inputs
 * for username and password and buttons to either login (post@login route) or
 * register(post@register route) or cancel (just clear the form and stay on the
 * login page)
 * 
 * @author davidgriffin
 * @author zachpayne
 * @author evenmay
 */
public class LoginGetRoute extends TwitterAltRoute {

	@Override
	public Object handle(Request request, Response response) {
		StringWriter html = new StringWriter();
		try {
			// Get the html that we wrote in login.ftl
			setTemplate(HttpServer.getCfg().getTemplate("login.ftl"));
			// Process any objects that we reference in our login.ftl (not used
			// here so we just process an empty HashMap)
			getTemplate().process(getAttributes(), html);
		} catch (IOException | TemplateException e) {
			System.out.println("Cannot find the Signup template!");
		}
		// return the written/process html to the browser which is displayed
		return html;
	}
}
