package edu.umw.cpsc.twitterAlt.view;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.SparkBase.setPort;
import static spark.SparkBase.staticFileLocation;

import java.io.File;
import java.io.IOException;

import spark.Spark;
import edu.umw.cpsc.twitterAlt.view.routes.DeleteMessagePostRoute;
import edu.umw.cpsc.twitterAlt.view.routes.DeleteSubscriptionPostRoute;
import edu.umw.cpsc.twitterAlt.view.routes.DeleteUserGetRoute;
import edu.umw.cpsc.twitterAlt.view.routes.DeleteUserPostRoute;
import edu.umw.cpsc.twitterAlt.view.routes.LoginGetRoute;
import edu.umw.cpsc.twitterAlt.view.routes.LoginPostRoute;
import edu.umw.cpsc.twitterAlt.view.routes.ProfileGetRoute;
import edu.umw.cpsc.twitterAlt.view.routes.RegisterPostRoute;
import edu.umw.cpsc.twitterAlt.view.routes.ResetPasswordGetRoute;
import edu.umw.cpsc.twitterAlt.view.routes.ResetPasswordPostRoute;
import edu.umw.cpsc.twitterAlt.view.routes.SearchPostRoute;
import edu.umw.cpsc.twitterAlt.view.routes.SubscribePostRoute;
import edu.umw.cpsc.twitterAlt.view.routes.WelcomeGetRoute;
import edu.umw.cpsc.twitterAlt.view.routes.WriteMessagePostRoute;
import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

public class HttpServer {
	private static HttpServer instance;
	private static Configuration cfg = new Configuration();

	// this default constructor is private to prevent other classes from
	// creating multiple instances. Instead other classes must call
	// getInstance()
	private HttpServer() {
		setPort(8082);
		staticFileLocation("resources");
		System.out.println(new File(HttpServer.class.getProtectionDomain()
				.getCodeSource().getLocation().getPath()));
		try {
			cfg.setDirectoryForTemplateLoading(new File(
					"src/main/java/resources/templates/"));
		} catch (IOException e1) {
			Spark.halt("could not load templates");
			Spark.stop();
			e1.printStackTrace();
		}
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
	}

	/**
	 * @return the single instance of this HttpServer class (allows access to a
	 *         single instance of this class and prevents multiple
	 *         instantiations) google the singleton design pattern
	 */
	public static HttpServer getInstance() {
		return instance == null ? new HttpServer() : instance;
	}

	/**
	 * @return the freemarker template/html configuration needed for processing
	 *         templates
	 */
	public static Configuration getCfg() {
		return cfg;
	}

	/**
	 * This method "starts" the http server by initializing routes. Each route
	 * starts with a method of either get (displaying a template) or
	 * post(processing data from a template and then redirecting to a
	 * display/get route). The routes are custom implementations of the
	 * spark.Route interface and must implement the handle method which takes a
	 * spark request and returns a spark response. See individual routes for
	 * more details
	 */
	public static void start() {

		// initialize the GET routes, aka routes that display html templates
		get("/", new WelcomeGetRoute());
		get("/login", new LoginGetRoute());
		get("/profile", new ProfileGetRoute());
        get("/resetPassword", new ResetPasswordGetRoute());
        get("/deleteUser", new DeleteUserGetRoute());

		// initialize the POST routes, aka routes that receive form submissions
		post("/register", new RegisterPostRoute());
		post("/login", new LoginPostRoute());
		post("/postMessage", new WriteMessagePostRoute());
		post("/search", new SearchPostRoute());
        post("/resetPassword", new ResetPasswordPostRoute());
        post("/subscribe", new SubscribePostRoute());
        post("/deleteUser", new DeleteUserPostRoute());
        post("/deleteMessage", new DeleteMessagePostRoute());
        post("/deleteSubscription", new DeleteSubscriptionPostRoute());

	}

}
