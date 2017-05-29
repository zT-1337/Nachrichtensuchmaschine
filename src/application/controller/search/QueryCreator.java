package application.controller.search;

import org.apache.lucene.search.Query;

public interface QueryCreator {

	public Query create(String field, String value);
}
