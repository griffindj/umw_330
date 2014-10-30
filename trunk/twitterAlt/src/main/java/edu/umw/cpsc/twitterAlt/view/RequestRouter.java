package edu.umw.cpsc.twitterAlt.view;

import static spark.Spark.get;

import java.io.IOException;
import java.io.Writer;

import spark.Request;
import spark.Response;
import freemarker.template.SimpleHash;
import freemarker.template.TemplateException;

public class RequestRouter {
	static final RequestRouter instance = new RequestRouter();

	// this default constructor is private to prevent other classes from
	// creating multiple instances. Instead other classes must call
	// getInstance()
	private RequestRouter() {
	}

	public static RequestRouter getInstance() {
		return instance;
	}

	public void initializeRoutes() throws IOException {
		get(new FreemarkerBasedRoute("/", "index.ftl") {
			@Override
			public void doHandle(Request request, Response response,
					Writer writer) throws IOException, TemplateException {

				SimpleHash root = new SimpleHash();
				template.process(root, writer);
			}
		});

		get(new FreemarkerBasedRoute("/register", "register.ftl") {
			@Override
			public void doHandle(Request request, Response response,
					Writer writer) throws IOException, TemplateException {

				SimpleHash root = new SimpleHash();
				template.process(root, writer);
			}
		});

		get(new FreemarkerBasedRoute("/acceptRegistration", "acceptRegistration.ftl") {
			@Override
			public void doHandle(Request request, Response response,
					Writer writer) throws IOException, TemplateException {
				System.out.println("Hello app");
				System.out.println(request.queryParams("Username"));
				SimpleHash root = new SimpleHash();
				template.process(root, writer);
			}
		});
	}
}
