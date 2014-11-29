package edu.umw.cpsc.twitterAlt.view.routes;

import java.io.IOException;
import java.io.StringWriter;

import spark.Request;
import spark.Response;
import edu.umw.cpsc.twitterAlt.controller.MessageDao;
import edu.umw.cpsc.twitterAlt.model.Message;
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
		MessageDao messageDao = new MessageDao();
		try {
			// Get the html that we wrote in login.ftl
			setTemplate(HttpServer.getCfg().getTemplate("login.ftl"));

			// put our list of public messages in the Attributes to process
			getAttributes().put("publicMessageFeed", messageDao.getMessages());

			// process the attributes we added
			getTemplate().process(getAttributes(), html);
			
		} catch (IOException | TemplateException e) {
			System.out.println("Cannot find the Login template!");
		}
		// return the written/process html to the browser which is displayed
		return html;
	}
}
