package edu.umw.cpsc.twitterAlt.view.routes;

import java.util.List;

import spark.Request;
import spark.Response;
import spark.Route;
import edu.umw.cpsc.twitterAlt.controller.MessageDao;
import edu.umw.cpsc.twitterAlt.model.Message;

public class SearchPostRoute implements Route {

	@Override
	public Object handle(Request request, Response response) {

		String query = request.queryParams("query");
		MessageDao messageDao = new MessageDao();
		
		List<Message> foundMessages = messageDao.searchMessages(query, true);
		
		for(Message message : foundMessages){
			System.out.println(message.getText());
		}
		
		return foundMessages.toString();
	}
}
