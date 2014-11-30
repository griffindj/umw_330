package edu.umw.cpsc.twitterAlt;

import edu.umw.cpsc.twitterAlt.view.HttpServer;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) {
		System.out.println("\n\n\n\n\n");
		System.out.println("Hello World!");
		System.out.println("If you're seeing this, then odds are you"
				+ " can open http://localhost:8082 in your web browser"
				+ " and see our homepage");
		System.out.println("\n\n\n\n\n");

		// this starts the spark server to begin listening on port 8082;
		HttpServer.getInstance();
		HttpServer.start();

	}
}
