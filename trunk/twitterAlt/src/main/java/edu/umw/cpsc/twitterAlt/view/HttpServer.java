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
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateExceptionHandler;

public class HttpServer {
	private HttpServer instance = new HttpServer();

	private static Configuration cfg = new Configuration();
	public static final String MONGO_SERVER_ADDRESS = "mongodb://localhost";

	// this default constructor is private to prevent other classes from
	// creating multiple instances. Instead other classes must call
	// getInstance()
	private HttpServer() {
	}

	public HttpServer getInstance() {
		return instance;
	}

	public static void start() {
		// this line establishes a connection to our Database
		/*
		 * MongoClient mongoClient = null; try { mongoClient = new
		 * MongoClient(new MongoClientURI( MONGO_SERVER_ADDRESS)); } catch
		 * (UnknownHostException e) { // TODO Auto-generated catch block
		 * e.printStackTrace(); } final DB blogDatabase =
		 * mongoClient.getDB("blog");
		 */

		setPort(8082);
		staticFileLocation("resources");
		System.out.println(new File(HttpServer.class.getProtectionDomain().getCodeSource().getLocation().getPath()));
		
		try {
			cfg.setDirectoryForTemplateLoading(new File(
					"src/main/java/resources/templates/"));
		} catch (IOException e1) {
			Spark.halt("could not load templates");
			Spark.stop();
			e1.printStackTrace();
		}
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.HTML_DEBUG_HANDLER);
		
		//establish the routes that we will be listening on
		initializeRoutes();
	}
	
	private static void initializeRoutes(){
		//signup page
		get("/login", new Route() {
			@Override
			public Object handle(Request request, Response response) {
				StringWriter signupHtml = new StringWriter();
				Template signupTemplate = null;
				try {
					signupTemplate = cfg.getTemplate("login.ftl");
					signupTemplate.process(new HashMap<>(),signupHtml);
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
					signupTemplate.process(new HashMap<>(),signupHtml);
				} catch (IOException | TemplateException e) {
					System.out.println("Cannot find the Signup template!");
				}
				return signupHtml;
			}
		});
		
		post("/signup", new Route() {
			@Override
			public Object handle(Request request, Response response) {
				return "Here is where we call the code to save to Database";
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
		
	}

}
