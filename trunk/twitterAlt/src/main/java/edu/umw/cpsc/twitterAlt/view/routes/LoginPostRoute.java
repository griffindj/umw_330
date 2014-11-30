package edu.umw.cpsc.twitterAlt.view.routes;

import spark.Request;
import spark.Response;
import spark.Route;
import edu.umw.cpsc.twitterAlt.controller.MessageDao;
import edu.umw.cpsc.twitterAlt.controller.UserDao;
import edu.umw.cpsc.twitterAlt.view.TwitterAltRoute;

/**
 * This route takes the submission of the Login form (displayed/handled in the
 * LoginGetRoute). It will call the userDao.login method to attempt to login and
 * if successful will create a session, and store the logged in user object in
 * that session, as well as the logged in user's messageFeed (which is a list of
 * messages that the user should see). Then when the session has be created, it
 * redirects the user to the profile page
 * 
 * @author davidgriffin
 * @author zachpayne
 */
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
			// the user doesn't exist so create a new LoginGetRoute, put the
			// // error message inside and call it's handle method
			TwitterAltRoute loginGetRoute = new LoginGetRoute();
			loginGetRoute.getAttributes().put("error", "user does not exist");
			return loginGetRoute.handle(request, response);
			//response.redirect("/login");
		}
		return "Here is where would check the credentials";
	}
}
