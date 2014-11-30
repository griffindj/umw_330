/**
 * 
 */
package edu.umw.cpsc.twitterAlt.view.routes;

import spark.Request;
import spark.Response;
import edu.umw.cpsc.twitterAlt.controller.MessageDao;
import edu.umw.cpsc.twitterAlt.controller.UserDao;
import edu.umw.cpsc.twitterAlt.model.User;
import edu.umw.cpsc.twitterAlt.view.TwitterAltRoute;

/**
 * @author davidgriffin
 *
 */
public class DeleteMessagePostRoute extends TwitterAltRoute {

	@Override
	public Object handle(Request request, Response response) {
		System.out.println(request.queryParams("postedDate"));

		MessageDao messageDao = new MessageDao();
		UserDao userDao = new UserDao();
		User currentUser = request.session().attribute("user");
		String postedDate = request.queryParams("postedDate");

		if (messageDao.deleteMessage(currentUser.getUsername(), postedDate)) {
			// message delted so update session and redirect
			request.session().attribute("user",
					userDao.getUser(currentUser.getUsername()));
			response.redirect("/profile");
		}

		return "You should be redirected and never reach here";
	}

}
