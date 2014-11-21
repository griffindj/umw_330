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
 *
 */
public class SubscribePostRoute implements Route {

	@Override
	public Object handle(Request request, Response response) {
		UserDao userDao = new UserDao();
		String subscription = request.queryParams("subscribee");
                System.out.println(subscription);
		String username = ((User) request.session().attribute("user"))
				.getUsername();
		if (userDao.subscribeToUser(username, subscription)) {
			// subscribed to user, so update the session and redirect
			request.session().attribute("user", userDao.getUser(username));
			response.redirect("/profile");
			// should never reach here
			return "should never reach here";
		} else {
			return "failed to subscribe";
		}
	}
}
