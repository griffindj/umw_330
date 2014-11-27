package edu.umw.cpsc.twitterAlt.view.routes;

import spark.Request;
import spark.Response;
import spark.Route;
import edu.umw.cpsc.twitterAlt.controller.UserDao;
import edu.umw.cpsc.twitterAlt.model.User;

/**
 * This route deletes the user if the user confirms the deletion.
 * 
 * @author evanmay
 * @author zachpayne
 */
public class DeleteUserPostRoute implements Route {

	@Override
	public Object handle(Request request, Response response) {
		UserDao userDao = new UserDao();
                
                User currentUser = request.session().attribute("user");
                
		if (userDao.deleteUser(currentUser.getUsername())) {
                        response.redirect("/login");
                        // this return statement wont be reached because of redirect
                        return "account has been deleted";
		} else {
			// response.redirect("/profile");
			return "account could not be deleted";
		}
	}

}
