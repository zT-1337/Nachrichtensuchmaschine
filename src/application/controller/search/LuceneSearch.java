/*
 * LuceneSearch
 * 
 * Version: 1.0
 * 
 * Datum: 29.05.2017
 */
package application.controller.search;

import java.util.StringTokenizer;

import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery.Builder;
import org.apache.lucene.search.Query;

import application.model.index.Index;
import application.model.news.NewsFields;
import application.model.newsresult.NewsResult;

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
		// TODO Auto-generated method stub		
		Builder builder = new Builder();
		
		if(terms.length() != 0)
			builder.add(createTermsClause(terms, isComparingNews(news)));
		
		if(dates.length() != 0)
			builder.add(createDatesClause(dates));
		
		if(topics.length() != 0)
			builder.add(createTopicsClause(topics));
		
		if(news.length() != 0)
			builder.add(createNewsClause(news));
		
		return index_.searchFor(builder.build(), n);
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
		Builder builder = new Builder();
		builder.setMinimumNumberShouldMatch(LuceneSearch.minimum);
		
		StringTokenizer st = new StringTokenizer(str);
		
		while(st.hasMoreTokens()) {
			Query query = creator.create(field, st.nextToken());
			
			if(query != null)
				builder.add(query, occurSubQuerys);
		}
		
		
		return new BooleanClause(builder.build(), occurBoolean);
	}

}
