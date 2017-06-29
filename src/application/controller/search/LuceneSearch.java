/*
 * LuceneSearch
 * 
 * Version: 1.0
 * 
 * Datum: 29.05.2017
 */
package application.controller.search;

import java.util.StringTokenizer;

import org.apache.lucene.document.Document;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery.Builder;
import org.apache.lucene.search.Query;

import application.model.index.Index;
import application.model.news.News;
import application.model.news.NewsFields;
import application.model.news.NewsSimilarity;
import application.model.newsresult.NewsResult;
import application.model.newsresult.NewsResultLuceneAdapter;

/**
 * Das Suchen von Nachrichten mittels Lucene.
 * 
 * @author Thomas Zerr
 * @version 1.0
 * @see <a href="https://lucene.apache.org/core/6_5_0/core/org/apache/lucene/search/BooleanQuery.html">BooleanQuery</a>
 * @see <a href="https://lucene.apache.org/core/6_5_0/core/org/apache/lucene/search/BooleanClause.html">BooleanClause</a>
 * @see <a href="https://lucene.apache.org/core/6_5_0/core/org/apache/lucene/search/TermQuery.html">TermQuery</a>
 * @see <a href="https://lucene.apache.org/core/6_5_0/core/org/apache/lucene/search/TermRangeQuery.html">TermRangeQuery</a>
 *
 */
public class LuceneSearch implements Search {
	
	/**
	 * Mindestanzahl von BooleanClauses, welche als Teil eines BooleanQuery gefunden werden müssen.
	 */
	private final static int minimum = 1;

	/**
	 * Der Index, indem die Nachrichten gesucht werden. Es wird erwartet, dass der Index auf Lucene basiert.
	 */
	private Index index_;
	
	/**
	 * Dient zum Erzeugen von Query's aus Suchbegriffen, Themen oder einer Nachricht.
	 */
	private QueryCreator termCreator;
	
	/**
	 * Dient zum Erzeugen Query's aus Zeiträumen.
	 */
	private QueryCreator dateCreator;
	
	/**
	 * Erzeugt ein LuceneSearch mit dem übergebenen Index.
	 * @param index Setzt den Index, welcher verwendet werden soll.
	 */
	public LuceneSearch(Index index) {
		index_ = index;
		
		termCreator = new TermCreator();
		dateCreator = new DateCreator();
	}
	
	/**
	 * 
	 */
	@Override
	public NewsResult search(String terms, String dates, String topics, String news, int n) {
		Builder outerBuilder = new Builder();
		
		//Überprüfung ob statt einen leeren String, null überliefert wurde
		if(terms == null)
			terms = "";
		
		if(dates == null)
			dates = "";
		
		if(topics == null)
			topics = "";
		
		if(news == null)
			news = "";
		
		//Falls der Paramter übergeben wurde, wird eine entsprechende BooleanClause erzeugt
		if(terms.length() != 0) {
			outerBuilder.add(createTermsClause(terms.toLowerCase(), isComparingNews(news)));
		}
		
		if(dates.length() != 0)
			outerBuilder.add(createDatesClause(dates.toLowerCase()));
		
		if(topics.length() != 0)
			outerBuilder.add(createTopicsClause(topics.toLowerCase()));
		
		if(news.length() != 0)
			outerBuilder.add(createNewsClause(news.toLowerCase()));
		
		NewsResult result = index_.searchFor(outerBuilder.build(), n);
		
		//Falls nach ähnlichen Nachrichten gesucht wird, sollen auch nur Nachrichten zurückgeliefert werden, welche ähnlich sind
		if(isComparingNews(news))
			return filterSimilarNews(result);
		
		return result;
	}
	
	/**
	 * Liefert einen NewsResult, indem lediglich ähnliche Nachrichtten gespeichert sind.
	 * Es wird davon ausgegangen, dass der übergebene NewsResult aus einer Suche nach ähnlichen Nachrichten stammt.
	 * 
	 * @param result Die NewsResult aus der die ähnlichen Nachrichten gefiltert werden
	 * @return NewsResult, welcher ausschließlich ähnliche Nachrichten enthält
	 */
	private NewsResult filterSimilarNews(NewsResult result) {
		int n = 0;
		
		//Zählt wie viele ähnliche Nachrichten vorhanden sind
		for(int i = 0; i < result.getSize(); ++i) {
			if(result.getScore(i) < NewsSimilarity.similar)
				break;
			
			++n;
		}
		
		//Erzeugen des neuen NewsResult
		Document[] docs = new Document[n];
		float[] scores = new float[n];
		
		for(int i = 0; i < n; ++i) {
			docs[i] = (Document) result.getNews(i).getDataStructure();
			scores[i] = result.getScore(i);
		}
		
		return new NewsResultLuceneAdapter(docs, scores);
			
	}
	
	/**
	 * Ob nach ähnlichen Nachrichten gesucht wird.
	 * Solange der übergebene String nicht leer ist, wird nach ähnlichen Nachrichten gesucht.
	 * 
	 * @param news Der Text der Nachricht, nach der gesucht wird.
	 * @return true - Es wird nach ähnlicher Nachricht gesucht.
	 */
	
	private boolean isComparingNews(String news) {
		return news.length() != 0;
	}
	
	/**
	 * Erzeugt eine Suchanfrage aus den übergebenen Suchbegriffen.
	 * Diese Methode soll nur aufgerufen werden, falls Suchbegriffe vorhanden sind.
	 * Falls nach ähnlichen Nachrichten gesucht wird, beeinflussen die Begriffe nicht den Score eines Ergebnisses.
	 * Allerdings müssen in beiden Fälle, alle Suchbegriffe im Text der gesuchten Nachricht enthalten sein.
	 * 
	 * @param terms Alle Suchbegriffe.
	 * @param comparingNews Ob nach ähnlichen Nachrichten gesucht wird
	 * @return Eine BooleanClause, welche für jeden Suchbegriff eine TermQuery enthält.
	 */
	
	private BooleanClause createTermsClause(String terms, boolean comparingNews) {
		Occur occur;
		
		//Sollte man ähnliche achrichten suchen, dürfen die Begriffe keinen Einfluss auf das Scoring haben
		if(comparingNews)
			occur = Occur.FILTER;
		else
			occur = Occur.MUST;

		return createBooleanClause(terms, NewsFields.TEXT, termCreator, occur, occur);
	}
	
	/**
	 * Erzeugt eine Suchanfrage aus den übergebenen Zeiträumen.
	 * Diese Methode soll nur aufgerufen werden, falls Zeiträume vorhanden sind.
	 * Die Suche nach Zeiträumen, beeinflussen den Score für ein Ergebnis nicht.
	 * Alle Nachrichten müssen aus einem der Zeiträume stammen.
	 * 
	 * @param dates Alle Zeiträume.
	 * @return Eine BooleanClause, welche für jeden Zeitraum eine TermRangQuery enthält.
	 */
	
	private BooleanClause createDatesClause(String dates) {
		//Es reicht wenn ein Zeitraum matched. Ein Datum darf kein Einfluss auf das Scoring haben
		Occur occurBoolean = Occur.FILTER;
		Occur occurTerm = Occur.SHOULD;
		
		return createBooleanClause(dates, NewsFields.PUBDATE, dateCreator, occurBoolean, occurTerm);
	}

	/**
	 * Erzeugt eine Suchanfrage aus den übergebenen Themen.
	 * Diese Methode soll nur aufgerufen werden, falls Themen vorhanden sind.
	 * Die Suche nach Themen, beeinflussen den Score für ein Ergebnis nicht.
	 * Alle Nachrichten müssen aus einem der Themen stammen.
	 * 
	 * @param topics Alle Themen
	 * @return Eine BooleanClause, welche für jedes Thema eine TermQuery enthält.
	 */
	
	private BooleanClause createTopicsClause(String topics) {
		//Es reicht wenn ein Thema matched. Ein Thema darf kein Einfluss auf das Scoring haben
		Occur occurBoolean = Occur.FILTER;
		Occur occurTerm = Occur.SHOULD;
		
		return createBooleanClause(topics, NewsFields.TOPIC, termCreator, occurBoolean, occurTerm);
	}
	
	/**
	 * Erzeugt eine Suchanfrage aus einer Nachricht.
	 * Diese Methode soll nur aufgerufen werden, falls nach ähnlichen Nachrichten gesucht werden soll.
	 * Alle Nachrichten müssen ähnlich zu der Nachricht sein, nach der gesucht wird.
	 * 
	 * @param news Die Nachricht, nach der ähnliche Nachrichten gesucht werden
	 * @return Eine BooleanClause, welche für jeden Begriff im reduzierten Text der Nachricht, eine TermQuery enthält.
	 */
	
	private BooleanClause createNewsClause(String news) {
		//Es müssen nicht alle Begriffe des Reduzierten Textes matchen, es muss aber Einfluss auf das Scoring haben.
		Occur occurBoolean = Occur.MUST;
		Occur occurTerms = Occur.SHOULD;
		
		return createBooleanClause(news, NewsFields.REDUCEDTEXT, termCreator, occurBoolean, occurTerms);
		
	}
	
	/**
	 * Erzeugt eine BooleanClause mit einer BooleanQuery aus den übergebenen Parametern.
	 * @param str Ein Suchparameter als String
	 * @param field Das Feld eines Lucene Documents indem gesucht werden soll
	 * @param creator Der QueryCreator, welche konkrete Querys aus einem Zeitraum oder Begriff erzeugt.
	 * @param occurBoolean Spezifiziert, wie die BooleanQuery im Dokument erscheinen muss.
	 * @param occurSubQuerys Spezifiziert, wie die einzelnen Querys in der BooleanQuery erscheinen muss.
	 * @return BooleanClause mit einer BooleanQuery
	 */
	
	private BooleanClause createBooleanClause(String str, String field, QueryCreator creator, Occur occurBoolean, Occur occurSubQuerys) {		
		Builder innerBuilder = new Builder();
		
		//setMinimumNumberShouldMatch darf nur gesetzt werden, falls auch eine Shouldclause dabei ist.
		if(occurSubQuerys == Occur.SHOULD)
			innerBuilder.setMinimumNumberShouldMatch(LuceneSearch.minimum);
		
		StringTokenizer st = new StringTokenizer(str);
		
		while(st.hasMoreTokens()) {
			Query query = creator.create(field, st.nextToken());
			
			if(query != null)
				innerBuilder.add(query, occurSubQuerys);
		}
		
		return new BooleanClause(innerBuilder.build(), occurBoolean);
	}
	
	/**
	 * 
	 */
	public boolean isExisting(News n) {
		NewsResult result = search("", "", "", n.getReducedText(), 1);
		
		if(result.getSize() == 0) {
			return false;
		}
		
		return result.getScore(0) >= NewsSimilarity.equal;
	}

}
