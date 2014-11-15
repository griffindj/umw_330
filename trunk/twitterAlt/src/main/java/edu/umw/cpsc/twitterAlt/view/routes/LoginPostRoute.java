package edu.umw.cpsc.twitterAlt.view.routes;

import spark.Request;
import spark.Response;
import spark.Route;
import edu.umw.cpsc.twitterAlt.controller.UserDao;
import edu.umw.cpsc.twitterAlt.model.User;

public class LoginPostRoute implements Route{

	@Override
	public Object handle(Request request, Response response) {
		UserDao userDao = new UserDao();
		String username = request.queryParams("email");
		String password = request.queryParams("password");
		if (userDao.login(username, password)) {
			//the user does exist, so establish a new session
			request.session(true);
			request.session().attribute("user", userDao.getUser(username));
			//and redirect to their profile page to view and write
			response.redirect("/profile");
		} else {
			//the user doesn't exist so redirect back to login page
			response.redirect("/login");
		}
		return "Here is where would check the credentials";
	}
}
