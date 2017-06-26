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

public class XML_Parser {

	private DocumentBuilderFactory factory;
	private DocumentBuilder builder;
	private Document document;
	private NodeList nList_Channel;
	private NodeList nList_Item;
	
	public XML_Parser() throws ParserConfigurationException{
		factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
	}
	
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
	
	private String extractContent(String TagName, Element a_element, int Knoten){
		return a_element.getElementsByTagName(TagName).item(Knoten).getTextContent();
	}
	
}
