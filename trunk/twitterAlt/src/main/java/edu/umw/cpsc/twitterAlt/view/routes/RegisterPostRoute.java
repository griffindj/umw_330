package edu.umw.cpsc.twitterAlt.view.routes;

import spark.Request;
import spark.Response;
import spark.Route;
import edu.umw.cpsc.twitterAlt.controller.MessageDao;
import edu.umw.cpsc.twitterAlt.controller.UserDao;
import edu.umw.cpsc.twitterAlt.model.User;

/**
 * This route accepts a username and password from the login/register form. It
 * will call the userDao class to attempt to register the user and if successful
 * will "login" that user by starting a new session and putting them in the
 * session. If unsuccessful it redirects back to the loginGetRoute
 * 
 * @author davidgriffin
 *
 */
public class RegisterPostRoute implements Route {

	@Override
	public Object handle(Request request, Response response) {
		UserDao userDao = new UserDao();
		MessageDao messageDao = new MessageDao();
		String username = request.queryParams("email");
		String password = request.queryParams("password");
		User newUser = new User(username, password);
		if (userDao.registerUser(newUser)) {
			// we were able to register the user so create session
			request.session(true);
			// put the new user into the session
			request.session().attribute("user", userDao.getUser(username));
			// populate the user's message feed
			request.session()
					.attribute(
							"messageFeed",
							messageDao.getMessages(request.session().attribute(
									"user")));
			// send to homePage or postMessage page
			response.redirect("/profile");
			return "user has been saved to mongo";
		} else {
			// register returned false, send back to login with message
			response.redirect("/login");
			return null;
		}
	}
}
