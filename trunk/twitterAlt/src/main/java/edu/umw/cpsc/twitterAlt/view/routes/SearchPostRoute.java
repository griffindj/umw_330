package edu.umw.cpsc.twitterAlt.view.routes;

import spark.Request;
import spark.Response;
import spark.Route;

public class SearchPostRoute implements Route {

	@Override
	public Object handle(Request request, Response response) {
		return "This is where we would post all tagged messages";
	}
}
