package application.model.index;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.Query;

public interface Index {

	public void addNews(Document doc);
	
	public Document[] searchFor(Query querry, int n);
}
