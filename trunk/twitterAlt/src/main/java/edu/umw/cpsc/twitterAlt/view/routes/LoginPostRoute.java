package edu.umw.cpsc.twitterAlt.view.routes;

import spark.Request;
import spark.Response;
import spark.Route;
import edu.umw.cpsc.twitterAlt.controller.UserDao;

public class LoginPostRoute implements Route{

	@Override
	public Object handle(Request request, Response response) {
		UserDao userDao = new UserDao();
		String username = request.queryParams("email");
		String password = request.queryParams("password");
		if (userDao.login(username, password)) {
			response.redirect("/profile");

		} else {
			response.redirect("/login");

		}
		return "Here is where would check the credentials";
	}
}
