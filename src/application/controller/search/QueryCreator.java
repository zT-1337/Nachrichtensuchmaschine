/*
 * QueryCreator
 * 
 * Version: 1.0
 * 
 * Datum: 29.05.2017
 */
package application.controller.search;

import org.apache.lucene.search.Query;

/**
 * Definiert eine Schnittstelle zum Erzeugen von Lucene Querys
 * 
 * @author Thomas Zerr
 * @version 1.0
 * @see <a href="https://lucene.apache.org/core/6_5_0/core/org/apache/lucene/search/Query.html">Query</a>
 *
 */
public interface QueryCreator {

	/**
	 * Erzeugt eine Lucene Query.
	 * 
	 * @param field Document-Feld indem gesucht werden soll
	 * @param value Suchparameter als String
	 * @return
	 */
	public Query create(String field, String value);
}
