package application.controller.search;

import application.model.newsresult.NewsResult;

public interface Search {

	public NewsResult search(String terms, String dates, 
								 String topics, String news, int n);
}
