/*
 * ResultIndex
 * 
 * Version: 1.0
 * 
 * Date: 22.01.2017
 * 
 */

package application.model.index;

/**
 * Alle moeglichen Ergebnisse beim Hinzufuegen einer Nachricht
 * 
 * @author Thomas Zerr
 *
 */
public enum ResultIndex {
	/**
	 * Das Hinzufuegen war erfolgreich.
	 */
	SUCCESS,
	
	/**
	 * Beim Hinzufuegen kam es zu einer IOException. Nachricht wurde nicht Hinzugefuegt.
	 */
	IOEXCEPTION
}
