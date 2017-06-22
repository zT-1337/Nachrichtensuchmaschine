/**
 * DatePatternMatcher
 * 
 * Version: 1.0
 * 
 * Datum: 21.06.2017
 */
package application.util.date;

import java.util.regex.Pattern;

/**
 * Überprüft, ob ein Datum einem Muster entspricht.
 * 
 * @author Thomas Zerr
 * @version 1.0
 *
 */
public class DatePatternMatcher {

	/**
	 * Regex-Muster das einem Datum entspricht
	 */
	private String oneDate;
	
	/**
	 * Regex_Muster das einem Zeitraum aus zwei Daten entspricht
	 */
	private String twoDate;
	
	/**
	 * Initialisiert die Regex-Muster
	 */
	public DatePatternMatcher() {
		oneDate = "\\d\\d\\.\\d\\d\\.\\d\\d\\d\\d";
		twoDate = oneDate + "-" + oneDate;
	}
	
	/**
	 * Überprüft, ob das Datum dem Muster eines Datums entspricht (Format: dd.mm.yyyy)
	 * @param date Das zu überprüfende Datum
	 * @return Ob es Matched
	 */
	public boolean oneDateMatches(String date) {
		return Pattern.matches(oneDate, date);
	}
	
	/**
	 * Überprüft, ob der Zeitraum dem Muster eines Zeitraums entspricht (Format: dd.mm.yyyy-dd.mm.yyyy)
	 * @param dates
	 * @return Ob es Matched
	 */
	public boolean twoDatesMatches(String dates) {
		return Pattern.matches(twoDate, dates);
	}
}
