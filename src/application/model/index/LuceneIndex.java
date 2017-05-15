package application.model.index;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;

public class LuceneIndex implements Index {

	private IndexWriter writer_;
	private IndexSearcher searcher_;
	
	
	@Override
	public void addNews(Document doc) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Document[] searchFor(Query querry, int n) {
		// TODO Auto-generated method stub
		return null;
	}

}
