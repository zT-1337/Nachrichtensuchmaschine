package application.model.index;

import java.io.IOException;

import org.apache.lucene.search.Query;

import application.model.news.News;
import application.model.newsresult.NewsResult;

public interface Index {

	public ResultIndex addNews(News news) throws IOException;
	
	public NewsResult searchFor(Query querry, int n);
}
