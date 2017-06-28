/**
 * DateCreator
 * 
 * Version: 1.2
 * 
 * Datum: 12.06.2017
 */
package application.controller.search;

import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermRangeQuery;

import application.util.date.DateConverter;
import application.util.date.DatePatternMatcher;

/**
 * Erzeugt eine Query, um nach einem Zeitraum zu suchen.
 * Wird nur ein Datum als Zeitraum geliefert, wird nach dieses Datum als Term behandelt und mittels TermQuery gesucht.
 * Werden zwei Daten als Zeitraum geliefert, werden beide Daten als Term behandelt und mittels TermRangeQuery gesucht.
 * 
 * @author zt
 * @version 1.2
 * @see <a href="https://lucene.apache.org/core/6_5_0/core/org/apache/lucene/search/TermRangeQuery.html">TermRangeQuery</a>
 * @see <a href="https://lucene.apache.org/core/6_5_0/core/org/apache/lucene/search/TermQuery.html">TermQuery</a>
 */
public class DateCreator implements QueryCreator {
	
	/**
	 * Überprüft ob die übergebenen Daten dem Muster für einen Zeitraum entsprechen
	 */
	private DatePatternMatcher matcher;
	
	/**
	 * Konvertiert ein Datum in das Format, indem ein PubDate in einer Nachricht gespeichert ist
	 */
	private DateConverter converter;
	
	/**
	 * Die Länge des Strings für ein Datum.
	 */
	private int lengthDate;
	
	/**
	 * Ob die untere Datumsgrenze für die Ergebnisse miteingeschlossen ist.
	 */
	private boolean includeLower;
	
	/**
	 * Ob die obere Datumsgrenze für die Ergebnisse miteingeschlossen ist.
	 */
	private boolean includeUpper;
	
	/**
	 * Erzeugt die TermQueries, falls für den Zeitraum nur ein Datum übergeben wurde.
	 */
	private TermCreator termCreator;
	
	/**
	 * Erzeugt einen DateCreator.
	 */
	public DateCreator() {
		matcher = new DatePatternMatcher();
		converter = new DateConverter();
		
		//Es wird davon ausgegangen das ein Datum 10 Zeichen lang ist
		lengthDate = 10;
		
		includeLower = true;
		includeUpper = true;
		termCreator = new TermCreator();
	}
	
	/**
	 * @param value Der Zeitraum nachdem gesucht werden soll.
	 */
	@Override
	public Query create(String field, String value) {
		//Überprüfung ob field und value gültig sind
		if(field == null || value == null)
			return null;
		if(field.length() == 0 || value.length() == 0)
			return null;
		
		if(matcher.oneDateMatches(value)) {
			return createTermQuery(field, value);
		}
		
		if(matcher.twoDatesMatches(value)) {
			return createTermRangeQuery(field, value);
		}
		
		return null;
	}

	/**
	 * 
	 * @param field Name des Lucenedocumentfields in dem gesucht werden soll
	 * @param value Der Zeitraum, bestehend aus einem Datum, nachdem gesucht werden soll
	 * @return Query ist eine TermQuery mit den übergebenen Daten
	 */
	private Query createTermQuery(String field, String value) {
		//Datum wird ins richtige Format konvertiert
		return termCreator.create(field, converter.dateToNumber(value));
	}
	
	/**
	 * 
	 * @param field Name des Lucenedocumentfields in dem gesucht werden soll
	 * @param value Der Zeitraum, bestehend aus zwei Daten, nachdem gesucht werden soll.
	 * @return TermRangeQuery mit den übergebenen Daten
	 */
	private TermRangeQuery createTermRangeQuery(String field, String value) {
		//01.06.1997-03.06.1997
		//firstDate soll "01.06.1997" entsprechen
		String firstDate = converter.dateToNumber(value.substring(0, lengthDate));	
		//secondDate sol "03.06.1997" entsprechen
		String secondDate = converter.dateToNumber(value.substring(lengthDate+1, lengthDate*2+1));
		
		return TermRangeQuery.newStringRange(field, firstDate, secondDate, includeLower, includeUpper);
	}
}
