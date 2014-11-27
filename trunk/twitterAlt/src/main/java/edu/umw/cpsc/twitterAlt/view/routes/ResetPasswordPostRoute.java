package edu.umw.cpsc.twitterAlt.view.routes;

import spark.Request;
import spark.Response;
import spark.Route;
import edu.umw.cpsc.twitterAlt.controller.UserDao;
import edu.umw.cpsc.twitterAlt.model.User;

/**
 * This route takes the submission of the Reset Password form from the
 * ResetPasswordGetRoute.
 * 
 * @author evanmay
 * @author zachpayne
 */
public class ResetPasswordPostRoute implements Route {

	@Override
	public Object handle(Request request, Response response) {
		UserDao userDao = new UserDao();

		String newPassword = request.queryParams("newPassword");
		String oldPassword = request.queryParams("oldPassword");
		String confirmPassword = request.queryParams("confirmPassword");

		User currentUser = request.session().attribute("user");

		if (oldPassword.equals(confirmPassword)
				&& currentUser.getPassword().equals(oldPassword)) {
			if (userDao.resetPassword(currentUser, newPassword)) {
				response.redirect("/profile");
				// this return statement wont be reached because of redirect
				return "password has been reset";
			} else {
				return "password not reset, something bad happened in DAO";
			}
		} else {
			response.redirect("/resetPassword");
			return "passwords do not match";
		}
	}

}
