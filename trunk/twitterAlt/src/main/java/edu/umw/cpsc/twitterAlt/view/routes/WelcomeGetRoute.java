package edu.umw.cpsc.twitterAlt.view.routes;

import java.io.StringWriter;

import spark.Request;
import spark.Response;
import spark.Route;

public class WelcomeGetRoute implements Route{

	@Override
	public Object handle(Request request, Response response) {
		StringWriter html = new StringWriter();
		/*Template template = null;
		try {
			template = HttpServer.getCfg().getTemplate("welcome.ftl");
			template.process(new HashMap<>(), html);
		} catch (IOException | TemplateException e) {
			System.out.println("Cannot find the template!");
		}*/
		response.redirect("/login");
		return html;
	}

}
