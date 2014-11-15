package edu.umw.cpsc.twitterAlt.view.routes;

import java.io.IOException;
import java.io.StringWriter;
import java.util.HashMap;

import spark.Request;
import spark.Response;
import spark.Route;
import edu.umw.cpsc.twitterAlt.view.HttpServer;
import freemarker.template.Template;
import freemarker.template.TemplateException;

public class SearchTagGetRoute implements Route{

	@Override
	public Object handle(Request request, Response response) {
		StringWriter signupHtml = new StringWriter();
		Template signupTemplate = null;
		try {
			signupTemplate = HttpServer.getCfg().getTemplate("search.ftl");
			signupTemplate.process(new HashMap<>(), signupHtml);
		} catch (IOException | TemplateException e) {
			System.out.println("Cannot find the Signup template!");
		}
		return signupHtml;
	}
}
