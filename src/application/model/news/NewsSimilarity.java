/**
 * NewsSimilarity
 * 
 * Version: 1.0
 * 
 * Datum: 29.05.2017
 */
package application.model.news;

/**
 * Scores, welche ein Suchergebnis erreichen muss, damit es als ähnlich oder gleich erachtet wird.
 * @author zt
 * @version 1.0
 *
 */
public class NewsSimilarity {

	/**
	 * Mindestscore, für ähnliche Nachrichten
	 */
	public final static float similar = 0.3f;
	
	/**
	 * Mindestscore, für gleiche Nachrichten
	 */
	public final static float equal = 0.9f;
}
