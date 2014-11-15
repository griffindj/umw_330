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

public class ProfileGetRoute implements Route {

	public Object handle(Request request, Response response) {
		StringWriter html = new StringWriter();
		Template template = null;
		try {
			template = HttpServer.getCfg().getTemplate("profile.ftl");
			template.process(new HashMap<>(), html);
		} catch (IOException | TemplateException e) {
			System.out.println("Cannot find the Signup template!");
		}
		return html;
	}
}
