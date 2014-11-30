package edu.umw.cpsc.twitterAlt.view.routes;

import edu.umw.cpsc.twitterAlt.controller.MessageDao;
import edu.umw.cpsc.twitterAlt.controller.UserDao;
import edu.umw.cpsc.twitterAlt.model.User;
import spark.Request;
import spark.Response;
import spark.Route;

/**
 * This route accepts a form submission from the profile page and based on the
 * form constructs a new message object and adds it to the current logged in
 * user's list of messages and calls userDao to update the database
 * 
 * @author davidgriffin
 * @author zachpayne
 * @author evenmay
 */
public class WriteMessagePostRoute implements Route {

	@Override
	public Object handle(Request request, Response response) {
		UserDao userDao = new UserDao();
		MessageDao messageDao = new MessageDao();
		String text = request.queryParams("text");
		// by default all messages are "private/not public"
		boolean isPublic = false;
		// however if isPublic checkbox is set to true, then set public
		if (request.queryParams("isPublic") != null) {
			isPublic = request.queryParams("isPublic").equals("true") ? true
					: false;
		}
		String username = ((User) request.session().attribute("user"))
				.getUsername();
		if (messageDao.postMessage(username, text, isPublic)) {
			// message was posted, so update the session and redirect
			request.session().attribute("user", userDao.getUser(username));
			response.redirect("/profile");
			// should never reach here
			return "should never reach here";
		} else {
			return "message failed to post";
		}
	}
}
