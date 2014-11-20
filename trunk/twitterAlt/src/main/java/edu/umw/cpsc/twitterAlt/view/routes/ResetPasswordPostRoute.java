package edu.umw.cpsc.twitterAlt.view.routes;

import edu.umw.cpsc.twitterAlt.controller.UserDao;
import edu.umw.cpsc.twitterAlt.model.User;
import java.io.StringWriter;

import spark.Request;
import spark.Response;
import spark.Route;

/**
 * This route takes the submission of the Reset Password form from the ResetPasswordGetRoute.
 * 
 * @author evanmay
 *
 */
public class ResetPasswordPostRoute implements Route {

	@Override
	public Object handle(Request request, Response response) {
                UserDao userDao = new UserDao();
            
                String newPassword = request.queryParams("newPassword");
		String oldPassword = request.queryParams("oldPassword");
                String confirmPassword = request.queryParams("confirmPassword");
		
                User currentUser = request.session().attribute("user");
                
                
                if (oldPassword.equals(confirmPassword) && currentUser.getPassword().equals(oldPassword))
                {
//                    if (currentUser.setPassword(newPassword)) {
                    	// send to homePage or postMessage page
                        userDao.resetPassword(request.session().attribute("user"), newPassword);
                        
			response.redirect("/profile");
			return "password has been reset";
//                    } else {
//			// reset returned false, return to reset password form
//			response.redirect("/resetPassword");
//			return null;
//                    }
		}
                else
                {
                    response.redirect("/profile");
                    return "passwords do not match";
                }
	}

}
