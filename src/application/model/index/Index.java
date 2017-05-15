package application.model.index;

import java.io.IOException;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.ScoreDoc;

public interface Index {

	public void addNews(Document doc) throws IOException;
	
	public ScoreDoc[] searchFor(Query querry, int n);
}
