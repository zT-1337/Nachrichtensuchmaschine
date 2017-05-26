/*
 * Search
 * 
 * Version: 1.0
 * 
 * Datum: 26.05.2017
 */
package application.controller.search;

import application.model.newsresult.NewsResult;

/**
 * Dieses Interface definiert die Schnittstelle zum Suchen nach Nachrichten.
 * Nachrichten können anhand von Begriffen, Datum, Thema und ähnlichkeit zu einer anderen Nachricht gesucht werden.
 * 
 * @author Thomas Zerr
 * @version 1.0
 *
 */
public interface Search {

	/**
	 * Suchen von Nachrichten.
	 * Anhand der übergebenen Parameter werden nach Nachrichten gesucht.
	 * Das Ergebnis besteht aus den Nachrichten, welche den höchsten Score für die Suche erreicht haben.
	 * 
	 * @param terms Begriffe nach denen gesucht wird.
	 * @param dates Zeiträume, indenen eine Nachricht publiziert sein muss, damit sie für die Suche akzeptiert wird.
	 * @param topics Das Thema, zudem eine Nachricht gehören muss, damit sie für die Suche akzeptiert wird.
	 * @param news Der reduzierte Text einer Nachricht, zu der ähnliche Nachrichten gesucht werden soll.
	 * @param n Anzahl der Nachrichten, welche höchstens geliefert werden soll.
	 * @return Die höchstens n Nachrichten mit den größten Scores.
	 */
	public NewsResult search(String terms, String dates, 
								 String topics, String news, int n);
}
