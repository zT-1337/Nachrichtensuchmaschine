package application.model.newsresult;

import org.apache.lucene.document.Document;

import application.model.news.News;
import application.model.news.NewsLuceneAdapter;

public class NewsResultLuceneAdapter implements NewsResult {

	private Document[] docs_;
	private int[] scores_;

	public NewsResultLuceneAdapter(Document[] docs, int[] scores) {
		docs_ = docs;
		scores_ = scores;
	}

	@Override
	public News getNews(int index) {
		// TODO Auto-generated method stub
		if(index > -1 && index < docs_.length)
			return new NewsLuceneAdapter(docs_[index]);
		
		return null;
	}

	@Override
	public int getScore(int index) {
		// TODO Auto-generated method stub
		if(index > -1 && index < scores_.length)
			return scores_[index];
		
		return -1;
	}
}
