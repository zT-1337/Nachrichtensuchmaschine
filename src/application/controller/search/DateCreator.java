/**
 * DateCreator
 * 
 * Version: 1.1
 * 
 * Datum: 12.06.2017
 */
package application.controller.search;

import java.util.regex.Pattern;

import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermRangeQuery;

/**
 * Erzeugt eine Query, um nach einem Zeitraum zu suchen.
 * Wird nur ein Datum als Zeitraum geliefert, wird nach dieses Datum als Term behandelt und mittels TermQuery gesucht.
 * Werden zwei Daten als Zeitraum geliefert, werden beide Daten als Term behandelt und mittels TermRangeQuery gesucht.
 * 
 * @author zt
 * @version 1.1
 * @see <a href="https://lucene.apache.org/core/6_5_0/core/org/apache/lucene/search/TermRangeQuery.html">TermRangeQuery</a>
 * @see <a href="https://lucene.apache.org/core/6_5_0/core/org/apache/lucene/search/TermQuery.html">TermQuery</a>
 */
public class DateCreator implements QueryCreator {
	
	/**
	 * Ist ein Regexpattern, dass dazu dient zu überprüfen, ob ein einzelnes Datum übergeben wurde.
	 * Das Datum muss der Form "dd.mm.yyyy" entsprechen.
	 */
	private String oneDate;
	/**
	 * Ist ein Regexpattern, dass dazu dient zu überprüfen, ob zwei Daten übergeben wurde.
	 * Die Daten müssen der From "dd.mm.yyyy-dd.mm.yyyy" entsprechen.
	 */
	private String twoDates;
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
		oneDate =  "\\d\\d\\.\\d\\d\\.\\d\\d\\d\\d";
		twoDates = oneDate + "-" + oneDate;
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
		if(field == null || value == null)
			return null;
		if(field.length() == 0 || value.length() == 0)
			return null;
		
		if(Pattern.matches(oneDate, value)) {
			return termCreator.create(field, value);
		}
		
		if(Pattern.matches(twoDates, value)) {
			return createTermRangeQuery(field, value);
		}
		
		return null;
	}

	/**
	 * 
	 * @param field Name des Lucenedocumentfields in dem gesucht werden soll
	 * @param value Der Zeitraum, bestehend aus zwei Daten, nachdem gesucht werden soll.
	 * @return TermRangeQuery mit den übergebenen Daten
	 */
	private TermRangeQuery createTermRangeQuery(String field, String value) {
		String firstDate = value.substring(0, lengthDate);	
		String secondDate = value.substring(lengthDate+1, lengthDate*2+1);
		
		return TermRangeQuery.newStringRange(field, firstDate, secondDate, includeLower, includeUpper);
	}
}
