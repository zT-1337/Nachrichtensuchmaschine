/**
 * XML_Parser
 * 
 * Version: 1.0
 * 
 * Datum: 03.07.2016
 */

package application.controller.NewsCreator;

import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Parser zum extrahieren aller, fuer eine News, relevanten Informationen.
 * 
 * @author Kevin Kaufmann
 * 
 * @version 1.0
 */
public class XML_Parser {

	/**
	 * Referenz einer DocumentBuilderFactory
	 */
	private DocumentBuilderFactory factory;
	
	/**
	 * Referent eines DocumentBuilder
	 */
	private DocumentBuilder builder;
	
	/**
	 * Speicher für die zu parsende Datei
	 */
	private Document document;
	
	/**
	 * NodeList fuer das channel-attribut.
	 */
	private NodeList nList_Channel;
	
	/**
	 * NodeList fuer das Item-attribut.
	 */
	private NodeList nList_Item;
	
	/**
	 * Erzeugt einen neuen XML_Parser
	 * 
	 * @throws ParserConfigurationException
	 */
	public XML_Parser() throws ParserConfigurationException{
		factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
	}
	
	/**
	 * Methode zum parsen einer Datei
	 * 
	 * @param a_Path Der Dateipfad der zu parsenden XML-Datei
	 * @param a_List Die Liste in der die News-relevanten Informationen geschrieben werden.
	 */
	public void parse(String a_Path, ArrayList<String> a_List){
		
		try {
			document = builder.parse(a_Path);
			nList_Channel = document.getElementsByTagName("channel");
			nList_Item = document.getElementsByTagName("item");
			
			for(int i = 0; i < nList_Channel.getLength(); i++){
				Node item = nList_Channel.item(i);
				Element element = (Element) item;
				
				a_List.add(extractContent("title",element,0));
			}
			for(int i = 0; i < nList_Item.getLength(); i++){
				Node item = nList_Item.item(i);
				Element element = (Element) item;
				a_List.add(extractContent("title",element,0) );
				a_List.add(extractContent("link" ,element, 0));
				a_List.add(extractContent("pubDate", element, 0));
				a_List.add(extractContent("ExtractedText", element, 0));
			}
		} catch (SAXException | IOException | NullPointerException e) {
		
		}
	}
	
	/**
	 * Funktion zum extrahieren einer Information in einen String
	 * @param TagName Das Attribut das extrahiert werden soll.
	 * @param a_element das uebergebende element das extrahier werden soll.
	 * @param Knoten Der Knoten mit dem TagNamen der extrahiert werden soll.
	 * @return
	 */
	private String extractContent(String TagName, Element a_element, int Knoten){
		return a_element.getElementsByTagName(TagName).item(Knoten).getTextContent();
	}
	
}
