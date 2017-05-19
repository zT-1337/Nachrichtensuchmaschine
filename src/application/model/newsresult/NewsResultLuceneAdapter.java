package application.model.newsresult;

import org.apache.lucene.search.ScoreDoc;

import application.model.news.News;

public class NewsResultLuceneAdapter implements NewsResult {

	private ScoreDoc[] docs;

	public NewsResultLuceneAdapter(ScoreDoc[] docs) {
		// TODO Auto-generated constructor stub
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
