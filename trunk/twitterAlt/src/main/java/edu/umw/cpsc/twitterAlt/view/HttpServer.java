package edu.umw.cpsc.twitterAlt.view;

import static spark.Spark.*;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.Spark;
import edu.umw.cpsc.twitterAlt.controller.UserDao;
import edu.umw.cpsc.twitterAlt.model.User;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
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

	public static HttpServer getInstance() {
		return instance == null ? new HttpServer() : instance;
	}

	public static void start() {

		get("/", new Route() {
			@Override
			public Object handle(Request request, Response response) {
				response.redirect("/postMessage");
				return "unable to redirect - something's wrong if you see this";
			}
		});
		
		// signup page
		get("/login", new Route() {
			@Override
			public Object handle(Request request, Response response) {
				StringWriter signupHtml = new StringWriter();
				Template signupTemplate = null;
				try {
					signupTemplate = cfg.getTemplate("login.ftl");
					signupTemplate.process(new HashMap<>(), signupHtml);
				} catch (IOException | TemplateException e) {
					System.out.println("Cannot find the Signup template!");
				}
				return signupHtml;
			}
		});

		get("/postMessage", new Route() {
			@Override
			public Object handle(Request request, Response response) {
				StringWriter signupHtml = new StringWriter();
				Template signupTemplate = null;
				try {
					signupTemplate = cfg.getTemplate("postMessage.ftl");
					signupTemplate.process(new HashMap<>(), signupHtml);
				} catch (IOException | TemplateException e) {
					System.out.println("Cannot find the Signup template!");
				}
				return signupHtml;
			}
		});

		get("/searchTag", new Route() {
			@Override
			public Object handle(Request request, Response response) {
				StringWriter signupHtml = new StringWriter();
				Template signupTemplate = null;
				try {
					signupTemplate = cfg.getTemplate("search.ftl");
					signupTemplate.process(new HashMap<>(), signupHtml);
				} catch (IOException | TemplateException e) {
					System.out.println("Cannot find the Signup template!");
				}
				return signupHtml;
			}
		});

		post("/register", new Route() {
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
					response.redirect("/postMessage");
					return "user has been saved to mongo";
				} else {
					// register returned false, send back to login with message
					response.redirect("/login");
					return null;
				}
			}
		});

		post("/login", new Route() {
			@Override
			public Object handle(Request request, Response response) {
				return "Here is where would check the credentials";
			}
		});

		post("/postMessage", new Route() {
			@Override
			public Object handle(Request request, Response response) {
				return "Your message has been posted";
			}
		});

		post("/search", new Route() {
			@Override
			public Object handle(Request request, Response response) {
				return "This is where we would post all tagged messages";
			}
		});

	}

}
