package edu.umw.cpsc.twitterAlt.view;

import static spark.Spark.setPort;

import java.io.IOException;

public class HttpServer {
	private HttpServer instance = new HttpServer();

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

		try {
			RequestRouter.getInstance().initializeRoutes();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
