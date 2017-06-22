/*
 * Index
 * 
 * Version: 1.1
 * 
 * Date: 22.01.2017
 * 
 */

package application.model.index;

import java.util.List;

import org.apache.lucene.search.Query;

import application.model.news.News;
import application.model.newsresult.NewsResult;

/**
 * Dieses Interface definiert die Schnittstellen zum Zugrif auf den Index.
 * Nachrichten koennen hinzugefuegt und gesucht werden.
 * 
 * @author Thomas Zerr
 * @version 1.1,
 * @see <a href="https://lucene.apache.org/core/6_5_0/core/org/apache/lucene/search/Query.html">Query</a>
 *
 */
public interface Index {
	
	/**
	 * Schnittstelle zum Hinzufuegen von Nachrichten.
	 * 
	 * @param news Liste von News, welche hinzugefügt werden soll..
	 * 
	 * @return Einen "Returncode" der Beschreibt was beim Hinzufuegen vorgefallen ist.
	 */
	public ResultIndex addNews(List<News> news);
	
	/**
	 * Schnittstelle zum Suchen von Nachrichten.
	 *
	 * @param querry Anhand dieser Query werden die Nachrichten gesucht.
	 * @param n Anzahl der Nachrichten, welche höchstens als Ergebnis geliefert werden.
	 * @return Liefert ein NewsResult, dass alle Nachrichten und den entsprechenden Score speichert.
	 */
	public NewsResult searchFor(Query querry, int n);
}
