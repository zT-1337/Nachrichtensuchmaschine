/**
 * DateConverter
 * 
 * Version: 1.0
 * 
 * Datum: 21.06.2017
 */
package application.util.date;

/**
 * Konvertiert ein Datum der Form dd.mm.yyyy in die Form yyyymmdd.
 * 
 * @author Thomas Zerr
 * @version 1.0
 *
 */
public class DateConverter {

	/**
	 * Überprüft, ob das übergebene Datum überhaupt im richtigen Format ist.
	 */
	private DatePatternMatcher matcher;
	
	/**
	 * Initialisert den DatePatternMatcher.
	 */
	public DateConverter() {
		matcher = new DatePatternMatcher();
	}
	
	/**
	 * Konvertiert das Datum in das entsprechende Format
	 * @param date Das zu konvertierende Datum
	 * @return Das konvertierte Datum oder null, falls das übergebene Datum, nicht im richtigen Format war.
	 */
	public String dateToNumber(String date) {
		if(date == null || !matcher.oneDateMatches(date))
			return null;
		
		String result = date.substring(6, 10) + date.substring(3, 5) + date.substring(0, 2);
		
		
		return result;
	}
}
