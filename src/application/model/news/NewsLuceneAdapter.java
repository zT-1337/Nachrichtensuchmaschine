/*
 * NewsLuceneAdapter
 * 
 * Version: 1.1
 * 
 * Datum: 23.05.2017
 */
package application.model.news;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

import application.util.date.DateConverter;

/**
 * Diese Klasse repräsentiert eine News mittels Lucene Bibliothek.
 * Die Datenstruktur mit der eine Nachricht gespeichert wird, ist der Datentyp Document aus der Lucene Bibliothek.
 * Die Bezeichnungen für die einzelnen Felder des Documents, in denen die Informationen einer Nachricht gespeichert werden, findet man in NewsFields.
 * Diese Klasse funktioniert als ein Adapter zwischen Lucene und der restlichen Software.
 * 
 * @author Thomas Zerr
 * @version 1.1
 * @see <a href="https://lucene.apache.org/core/6_5_0/core/org/apache/lucene/document/Document.html">Document</a>
 * @see <a href="https://lucene.apache.org/core/6_5_0/core/index.html?org/apache/lucene/document/TextField.html">TextField</a>
 * @see <a href="https://lucene.apache.org/core/6_5_0/core/index.html?org/apache/lucene/document/StringField.html">StringField</a>
 * @see <a>NewsFields</a>
 *
 */
public class NewsLuceneAdapter implements News {
	
	/**
	 * Datenstruktur in der eine Nachricht abgelegt wird.
	 */
	private Document doc_;
	
	/**
	 * Konvertiert das übergebene Datum beim setzen der PubDate in das richtige Format
	 */
	private DateConverter converter_;
	
	/**
	 * Erzeugt eine leere Nachricht. Keine der Felder enthält einen Wert.
	 */
	public NewsLuceneAdapter() {
		doc_ = new Document();
		converter_ = new DateConverter();
	}
	
	/**
	 * Erzeugt eine Nachricht, mithilfe des übergebenen Document.
	 * 
	 * @param doc Das Document aus dem eine Nachricht erzeugt werden soll. Es wird keine Anforderungen an das Document gestell.
	 */
	public NewsLuceneAdapter(Document doc) {
		doc_ = doc;
		converter_ = new DateConverter();
	}

	/**
	 * Titel der Nachricht
	 * Der Titel wird aus einem Feld des Documents geholt.
	 * Wurde der Nachricht kein Titel vergeben, wird null als Ergebnis geliefert.
	 * 
	 * @return Titel der Nachricht als String
	 */
	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return doc_.get(NewsFields.TITLE);
	}
	
	/**
	 * Thema der Nachricht
	 * Das Thema wird aus einem Feld des Documents geholt.
	 * Wurde der Nachricht kein Thema vergeben, wird null als Ergebnis geliefert.
	 * 
	 * @return Thema der Nachricht als String
	 */
	@Override
	public String getTopic() {
		// TODO Auto-generated method stub
		return doc_.get(NewsFields.TOPIC);
	}

	/**
	 * Publizierdatum der Nachricht
	 * Das Datum wird aus einem Feld des Documents geholt.
	 * Wurde der Nachricht kein Datum vergeben, wird null als Ergebnis geliefert.
	 * 
	 * @return Publizierdatum der Nachricht als String
	 */
	@Override
	public String getPubDate() {
		// TODO Auto-generated method stub
		return doc_.get(NewsFields.PUBDATE);
	}

	/**
	 * Quelle der Nachricht
	 * Die Quelle wird aus einem Feld des Documents geholt.
	 * Wurde der Nachricht keine Quelle vergeben, wird null als Ergebnis geliefert.
	 * 
	 * @return Quelle der Nachricht als String
	 */
	@Override
	public String getSource() {
		// TODO Auto-generated method stub
		return doc_.get(NewsFields.SOURCE);
	}

	/**
	 * URL der Nachricht
	 * Die URL wird aus einem Feld des Documents geholt.
	 * Wurde der Nachricht keine URL vergeben, wird null als Ergebnis geliefert.
	 * 
	 * @return URL der Nachricht als String
	 */
	@Override
	public String getURL() {
		// TODO Auto-generated method stub
		return doc_.get(NewsFields.URL);
	}

	/**
	 * Text der Nachricht
	 * Der Text wird aus einem Feld des Documents geholt.
	 * Wurde der Nachricht kein Text vergeben, wird null als Ergebnis geliefert.
	 * 
	 * @return Text der Nachricht als String
	 */
	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return doc_.get(NewsFields.TEXT);
	}

	/**
	 * Reduzierte Text der Nachricht
	 * Der reduzierte Text wird aus einem Feld des Documents geholt.
	 * Wurde der Nachricht kein reduzierte Text vergeben, wird null als Ergebnis geliefert.
	 * 
	 * @return Reduzierte Text der Nachricht als String
	 */
	@Override
	public String getReducedText() {
		// TODO Auto-generated method stub
		return doc_.get(NewsFields.REDUCEDTEXT);
	}

	/**
	 * Setzten des Titels der Nachricht.
	 * Der Titel wird als ein TextField im Document gespeichert.
	 * 
	 * @param value Der Titel der Nachricht als String
	 */
	@Override
	public void setTitle(String value) {
		// TODO Auto-generated method stub
		TextField titel = new TextField(NewsFields.TITLE, value, Field.Store.YES);
		doc_.add(titel);
	}
	
	/**
	 * Setzten des Themas der Nachricht.
	 * Das Thema wird als ein StringField im Document gespeichert.
	 * 
	 * @param value Das Thema der Nachricht als String
	 */
	@Override
	public void setTopic(String value) {
		// TODO Auto-generated method stub
		StringField topic = new StringField(NewsFields.TOPIC, value, Field.Store.YES);
		doc_.add(topic);
	}

	/**
	 * Setzten des Publikationsdatums der Nachricht.
	 * Das Publikationsdatum wird als ein StringField im Document gespeichert.
	 * 
	 */
	@Override
	public void setPubDate(String value) {
		// TODO Auto-generated method stub
		StringField pubdate = new StringField(NewsFields.PUBDATE, converter_.dateToNumber(value), Field.Store.YES);
		doc_.add(pubdate);
	}

	/**
	 * Setzten der Quelle der Nachricht.
	 * Die Quelle wird als ein StringField im Document gespeichert.
	 * 
	 * @param value Die Quelle der Nachricht als String
	 */
	@Override
	public void setSource(String value) {
		// TODO Auto-generated method stub
		StringField source = new StringField(NewsFields.SOURCE, value, Field.Store.YES);
		doc_.add(source);
	}

	/**
	 * Setzten der URL der Nachricht.
	 * Die URL wird als ein StringField im Document gespeichert.
	 * 
	 * @param value Die URL der Nachricht als String
	 */
	@Override
	public void setURL(String value) {
		// TODO Auto-generated method stub
		StringField url = new StringField(NewsFields.URL, value, Field.Store.YES);
		doc_.add(url);
	}

	/**
	 * Setzten des Textes der Nachricht.
	 * Der Text wird als ein TextField im Document gespeichert.
	 * 
	 * @param value Der Text der Nachricht als String
	 */
	@Override
	public void setText(String value) {
		// TODO Auto-generated method stub
		TextField text = new TextField(NewsFields.TEXT, value, Field.Store.YES);
		doc_.add(text);
	}

	/**
	 * Setzten des reduzierten Textes der Nachricht.
	 * Der reduzierte Text wird als ein TextField im Document gespeichert.
	 * 
	 * @param value Der reduzierte Text der Nachricht als String
	 */
	@Override
	public void setReducedText(String value) {
		// TODO Auto-generated method stub
		TextField reducedText = new TextField(NewsFields.REDUCEDTEXT, value, Field.Store.YES);
		doc_.add(reducedText);
	}

	/**
	 * Datenstruktur in der die Informationen der Nachricht gespeichert sind.
	 * Es wird die Membervariable doc_ zurückgeliefert.
	 * Dem Anwender dieser konkreten Schnittstelle muss klar sein, dass der konkrete Typ ein Lucene Document ist.
	 * 
	 * @return Liefert die Datenstruktur der Nachricht.
	 */
	@Override
	public Object getDataStructure() {
		// TODO Auto-generated method stub
		return doc_;
	}

}
