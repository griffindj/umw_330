package edu.umw.cpsc.twitterAlt.view.routes;

import spark.Request;
import spark.Response;
import spark.Route;
import edu.umw.cpsc.twitterAlt.controller.UserDao;
import edu.umw.cpsc.twitterAlt.model.User;

public class RegisterPostRoute implements Route {

	@Override
	public Object handle(Request request, Response response) {
		String username = request.queryParams("email");
		String password = request.queryParams("password");
		System.out.println(username + password);
		User newUser = new User(username, password);
		UserDao userDao = new UserDao();
		if (userDao.registerUser(newUser)) {
			// we were able to register the user so create session
			request.session(true);
			// put the new user into the session
			request.session().attribute("currentUser",
					userDao.getUser(username));
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
