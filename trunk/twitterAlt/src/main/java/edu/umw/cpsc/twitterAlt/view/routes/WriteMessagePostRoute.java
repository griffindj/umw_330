package edu.umw.cpsc.twitterAlt.view.routes;

import edu.umw.cpsc.twitterAlt.controller.MessageDao;
import edu.umw.cpsc.twitterAlt.controller.UserDao;
import edu.umw.cpsc.twitterAlt.model.User;
import spark.Request;
import spark.Response;
import spark.Route;

public class WriteMessagePostRoute implements Route {

	@Override
	public Object handle(Request request, Response response) {
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
		UserDao userDao = new UserDao();

		MessageDao messageDao = new MessageDao();
		if (messageDao.postMessage(username, text, isPublic)) {
			// message was posted, so update the session and refresh
			request.session().attribute("user", userDao.getUser(username));
			request.session()
					.attribute(
							"messageFeed",
							messageDao.getMessages(request.session().attribute(
									"user")));
			response.redirect("/profile");
			// should never reach here
			return "should never reach here";
		} else {
			return "message failed to post";
		}
	}
}
