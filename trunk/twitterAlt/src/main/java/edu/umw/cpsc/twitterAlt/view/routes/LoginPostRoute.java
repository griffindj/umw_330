package edu.umw.cpsc.twitterAlt.view.routes;

import java.util.List;

import spark.Request;
import spark.Response;
import spark.Route;
import edu.umw.cpsc.twitterAlt.controller.MessageDao;
import edu.umw.cpsc.twitterAlt.controller.UserDao;
import edu.umw.cpsc.twitterAlt.model.Message;

public class LoginPostRoute implements Route {

	@Override
	public Object handle(Request request, Response response) {
		UserDao userDao = new UserDao();
		MessageDao messageDao = new MessageDao();
		String username = request.queryParams("email");
		String password = request.queryParams("password");
		if (userDao.login(username, password)) {
			// the user does exist, so establish a new session
			request.session(true);
			// put the current user into the session for using profile data
			request.session().attribute("user", userDao.getUser(username));
			// populate the user's message feed
			request.session()
					.attribute(
							"messageFeed",
							messageDao.getMessages(request.session().attribute(
									"user")));

			// and redirect to their profile page to view and write
			response.redirect("/profile");
		} else {
			// the user doesn't exist so redirect back to login page
			response.redirect("/login");
		}
		return "Here is where would check the credentials";
	}
}
