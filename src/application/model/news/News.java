/*
 * News
 * 
 * Version: 1.0
 * 
 * Datum: 23.05.2017
 */
package application.model.news;

/**
 * Dieses Interface definiert die Schnittstellen auf eine Nachricht.
 * Der Anwender kann sich entweder die unterschiedlichen Informationen zu einer Nachricht geben oder setzten lassen.
 * Alle Informationen werden als Strings gespeichert. 
 * 
 * @author Thomas Zerr
 * @version 1.0
 *
 */
public interface News {

	/**
	 * 
	 * @return Liefert den Titel der Nachricht als String zurück
	 */
	public String getTitle();
	
	/**
	 * 
	 * @return Liefert das Thema der Nachricht als String zurück
	 */
	public String getTopic();
	
	/**
	 * 
	 * @return Liefert das Publikationsdatum der Nachricht als String zurück
	 */
	public String getPubDate();
	
	/**
	 * Die Quelle der Nachricht. 
	 * Bei der Quelle handelt es sich um den Namen des Nachrichtenportals.
	 * 
	 * @return Liefert die Quelle der Nachricht als String zurück
	 */
	public String getSource();
	
	/**
	 * Die URL an der die Nachricht vom RSSCrawler gefunden wurde.
	 *  
	 * @return Liefert die URL der Nachricht als String zurück
	 */
	public String getURL();
	
	/**
	 * Der Text der Nachricht.
	 * Der Text, welcher vom RSSCrawler, als Nachrichtentext extrahiert wurde.
	 * 
	 * @return Liefert den Text der Nachricht als String zurück
	 */
	public String getText();
	
	/**
	 * Die wichtigen Woerter des Textes
	 *
	 * @return Liefert die wichtigen Woerter des Nachrichtentextes als String zurück
	 */
	public String getReducedText();
	
	/**
	 * Den Titel der Nachricht setzen
	 * 
	 * @param value Der String der als Titel gespeichert werden soll
	 */
	public void setTitle(String value);
	
	/**
	 * Das Thema der Nachricht setzen
	 * 
	 * @param value Der String der als das Thema der Nachricht gespeichert werden soll
	 */
	public void setTopic(String value);
	
	/**
	 * Das Publikationsdatum der Nachricht setzen
	 * 
	 * @param value Der String der als das Datum gespeichert werden soll
	 */
	public void setPubDate(String value);
	
	/**
	 * Die Quelle der Nachricht setzen
	 * 
	 * @param value Der String der als Quelle gespeichert werden soll
	 */
	public void setSource(String value);
	
	/**
	 * Die URL der Nachricht setzen
	 * 
	 * @param value Der String der als URL gespeichert werden soll
	 */
	public void setURL(String value);
	
	/**
	 * Der Text der Nachricht setzen
	 * 
	 * @param value Der String der als Nachrichtentext gespeichert werden soll
	 */
	public void setText(String value);
	
	/**
	 * Die wichtigen Woerter der Nachricht setzen
	 * 
	 * @param value Der String aller wichtigen Woerter die gespeichert werden soll
	 */
	public void setReducedText(String value);
	
	
	/**
	 * Die Datenstruktur, in der alle Informationen der Nachricht gespeichert sind.
	 * Der konkrete Typ der Datenstruktur bleibt der Implementierung dieser Schnittstelle überlassen.
	 * Dies soll die Austauschbarkeit, der verwendeten Suchmaschinen Bibliothek gewährleisten.
	 * Es wird davon ausgegangen, dass der Anwender weiß, welchen konreten Typ er von einer konkreten Implementierung erhält.
	 * 
	 * @return Liefert die Datenstruktur der Nachricht zurück.
	 */
	public Object getDataStructure();
}
