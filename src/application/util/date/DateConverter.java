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
		//Validierung des Parameters
		if(date == null || !matcher.oneDateMatches(date))
			return null;
		
		//Umwandlung von dd.mm.yyyy zu yyyymmdd
		String result = date.substring(6, 10) + date.substring(3, 5) + date.substring(0, 2);
		
		
		return result;
	}
	
	/**
	 * Konvertiert eine Zahl mit 8 stellen in Datum. (Format dd.mm.yyyy)
	 * @param number Die zu konvertierende Zahl
	 * @return Die konvertierte Zahl oder null, falls die Zahl zu kurz war.
	 */
	public String numberToDate(String number) {
		//Validierung des Parameters
		if(number == null || number.length() != 8)
			return null;
		
		//Umwandlung von yyyymmdd zu dd.mm.yyyy
		String result = number.substring(6, 8) + "." + number.substring(4, 6) + "." + number.substring(0, 4);
		
		return result;
	}
}
