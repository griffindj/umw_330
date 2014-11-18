package edu.umw.cpsc.twitterAlt.view.routes;

import spark.Request;
import spark.Response;
import spark.Route;
import edu.umw.cpsc.twitterAlt.controller.MessageDao;

/**
 * This route handles the search form submission and will call the messageDao to
 * get a list of matching messages and will update the session's messageFeed
 * object with that list of matched messages. If the query is empty (user
 * clicked search with nothing in the search box) then the messageFeed will be
 * reset back to the user's "default" view of their subscriptions.
 * 
 * @author davidgriffin
 *
 */
public class SearchPostRoute implements Route {

	@Override
	public Object handle(Request request, Response response) {

		MessageDao messageDao = new MessageDao();
		String query = request.queryParams("query");
		if (query.isEmpty()) {
			// if the query is empty, then just return user's feed
			request.session()
					.attribute(
							"messageFeed",
							messageDao.getMessages(request.session().attribute(
									"user")));
		} else {
			// otherwise filter feed based on query
			request.session().attribute("messageFeed",
					messageDao.searchMessages(query));
		}
		// re-populate/filter the user's message feed
		// and send them back to the user's profile page
		response.redirect("/profile");
		return "you should never see this";
	}
}
