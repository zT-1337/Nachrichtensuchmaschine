package application.controller.search;

public interface Search {

	public NewsController search(String terms, String dates, 
								 String topics, String news, int n);
}
