/*
 * NewsResultLuceneAdapter
 * 
 * Version: 1.1
 * 
 * Datum: 24.05.2017
 */
package application.model.newsresult;

import org.apache.lucene.document.Document;

import application.model.news.News;
import application.model.news.NewsLuceneAdapter;

/**
 * Ein Konkreter NewsResult, welcher auf der Bibliothek Lucene basiert.
 * 
 * @author Thomas Zerr
 * @version 1.1
 * @see <a>News</a>
 * @see <a>NewsLuceneAdapter</a>
 * @see <a>LuceneIndex</a>
 *
 */
public class NewsResultLuceneAdapter implements NewsResult {

	/**
	 * Die Documents, sind die Ergebnisse einer Suche durch Lucene.
	 */
	private Document[] docs_;
	
	/**
	 * Die Scores, die ein Document bei der Suche von Lucene erhalten hat.
	 */
	private float[] scores_;
	
	/**
	 * Alle gefundenen Nachrichten. 
	 * Beim konkreten Typ handelt es sich um 'NewsLuceneAdapter'.
	 */
	private News[] news_;

	/**
	 * Erzeugt ein Suchergebnis aus einer Suche mit Lucene.
	 * 
	 * @param docs Die von Lucene gefundenen Documents
	 * @param scores Die von Lucene vergebenen Scores
	 */
	public NewsResultLuceneAdapter(Document[] docs, float[] scores) {
		docs_ = docs;
		scores_ = scores;
		news_ = new News[docs_.length];
	}

	/**
	 * @return Der konkrete Typ der Nachricht ist der NewsLuceneAdapter.
	 */
	@Override
	public News getNews(int index) {
		// TODO Auto-generated method stub
		if(getSize() == 0)
			return null;
		
		if(index < 0 || index > getSize()-1)
			return null;
		
		if(news_[index] == null) {
			news_[index] = new NewsLuceneAdapter(docs_[index]);
		}
		
		return news_[index];
	}

	/**
	 * 
	 */
	@Override
	public float getScore(int index) {
		// TODO Auto-generated method stub
		if(index > -1 && index < scores_.length)
			return scores_[index];
		
		return -1;
	}

	/**
	 * 
	 */
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return docs_.length;
	}
}
