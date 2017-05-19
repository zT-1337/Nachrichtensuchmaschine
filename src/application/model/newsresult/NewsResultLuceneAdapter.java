package application.model.newsresult;

import org.apache.lucene.search.ScoreDoc;

import application.model.news.News;
import application.model.news.NewsLuceneAdapter;

public class NewsResultLuceneAdapter implements NewsResult {

	private ScoreDoc[] docs_;

	public NewsResultLuceneAdapter(ScoreDoc[] docs) {
		docs_ = docs;
	}

	@Override
	public News getNews(int index) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int getScore(int index) {
		// TODO Auto-generated method stub
		return 0;
	}
}
