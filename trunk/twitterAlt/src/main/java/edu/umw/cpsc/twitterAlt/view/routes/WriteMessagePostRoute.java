package edu.umw.cpsc.twitterAlt.view.routes;

import spark.Request;
import spark.Response;
import spark.Route;

public class WriteMessagePostRoute implements Route {

	@Override
	public Object handle(Request request, Response response) {
		return "Your message has been posted";
	}
}
