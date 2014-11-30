/**
 * 
 */
package edu.umw.cpsc.twitterAlt.view.routes;

import spark.Request;
import spark.Response;
import edu.umw.cpsc.twitterAlt.controller.UserDao;
import edu.umw.cpsc.twitterAlt.model.User;
import edu.umw.cpsc.twitterAlt.view.TwitterAltRoute;

/**
 * @author davidgriffin
 *
 */
public class DeleteSubscriptionPostRoute extends TwitterAltRoute {

	@Override
	public Object handle(Request request, Response response) {
		UserDao userDao = new UserDao();
		User currentUser = request.session().attribute("user");
		String unsubscribee = request.queryParams("username");

		if (userDao.unsubscribeToUser(currentUser.getUsername(), unsubscribee)) {
			// message deleted so update session and redirect
			request.session().attribute("user",
					userDao.getUser(currentUser.getUsername()));
			response.redirect("/profile");
		}

		return "You should be redirected and never reach here";
	}

}
