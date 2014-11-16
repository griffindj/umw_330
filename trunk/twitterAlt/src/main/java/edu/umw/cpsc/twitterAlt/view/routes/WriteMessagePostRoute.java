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
		MessageDao messageDao = new MessageDao();
		UserDao userDao = new UserDao();
		String text = request.queryParams("text");
		String username = ((User) request.session().attribute("user"))
				.getUsername();
		System.out.println(text + username);

		if (messageDao.postMessage(username, text)) {
			System.out.println(text + username);
			// message was posted, so update the session and refresh
			request.session().attribute("user", userDao.getUser(username));
			response.redirect("/profile");
			// should never reach here
			return "asdfasdf";
		} else {
			return "message failed to post";
		}
	}
}
