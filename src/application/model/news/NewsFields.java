/*
 * NewsFields
 * 
 * Version: 1.0
 * 
 * Datum: 23.05.2017
 */
package application.model.news;

/**
 * Diese Klasse enthaelt alle Bezeichnungen, fuer die einzelnen Felder, welche im Lucene Document gespeichert werden.
 * 
 * @author Thomas Zerr
 * @version 1.0
 * @see <a>Document</a>
 */
public class NewsFields {

	/**
	 * Feldbezeichnung für den Titel
	 */
	public static final String TITLE = "title";
	
	/**
	 * Feldbezeichnung für das Thema
	 */
	public static final String TOPIC = "topic";
	
	/**
	 * Feldbezeichnung für das Publikationsdatum
	 */
	public static final String PUBDATE = "pubdate";
	
	/**
	 * Feldbezeichnung für die Quelle
	 */
	public static final String SOURCE = "source";
	
	/**
	 * Feldbezeichnung für die URL
	 */
	public static final String URL = "url";
	
	/**
	 * Feldbezeichnung für den Text
	 */
	public static final String TEXT = "text";
	
	/**
	 * Feldbezeichnung für den reduzierten Text
	 */
	public static final String REDUCEDTEXT = "reducedtext";
	
}
