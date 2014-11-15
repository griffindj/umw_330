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
import edu.umw.cpsc.twitterAlt.view.routes.*;
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

	public static Configuration getCfg() {
		return cfg;
	}

	public static void start() {

		// initialize the GET routes, aka routes that display html templates
		get("/", new WelcomeGetRoute());
		get("/login", new LoginGetRoute());
		get("/profile", new ProfileGetRoute());
		get("/postMessage", new MessageGetRoute());
		get("/searchTag", new SearchTagGetRoute());

		// initialize the POST routes, aka routes that receive form submissions
		post("/register", new RegisterPostRoute());
		post("/login", new LoginPostRoute());
		post("/postMessage", new MessagePostRoute());
		post("/search", new SearchPostRoute());

	}

}
