/*
 * NewsResult
 * 
 * Version: 1.0
 * 
 * Datum: 24.05.2017
 */
package application.model.newsresult;

import application.model.news.News;

/**
 * Dieses Interface definiert Schnittstellen zum Zugriff auf Suchergebisse.
 * Ein NewsResult ist das Ergebnis einer Suche.
 * Als Ergebnis enthält es alle Nachrichten und die entsprechenden Scores, welche vom Index geliefert wurden.
 * 
 * @author Thomas Zerr
 * @version 1.0
 * @see <a>Search</a>
 * @see <a>Index</a>
 * 
 */
public interface NewsResult {
	/**
	 * Liefert die Nachricht an der position Index.
	 * Die Nachrichten sind anhand des jeweiligen Scores absteigend sortiert.
	 * Das heißt die Nachricht an der Position 0 hat den höchsten Score.
	 * 
	 * @param index Position der Nachricht
	 * @return Nachricht an der Position 'index'.
	 */
	public News getNews(int index);
	
	/**
	 * Liefert den Score der Nachricht an der position Index.
	 * @param index Position der Nachricht
	 * @return Score der Nachricht an der Position 'index'
	 */
	public float getScore(int index);
	
	/**
	 * Liefert die Anzahl der Nachrichten, welche sich aktuell in diesem NewsResult befinden.
	 * @return Anzahl der Nachrichten
	 */
	public int getSize();
}
