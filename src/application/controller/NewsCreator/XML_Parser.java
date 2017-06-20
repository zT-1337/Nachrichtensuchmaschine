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
	private NodeList nList;
	
	public XML_Parser() throws ParserConfigurationException{
		factory = DocumentBuilderFactory.newInstance();
		builder = factory.newDocumentBuilder();
	}
	
	public void parse(String a_Path, ArrayList<String> a_List){
		
		try {
			document = builder.parse(a_Path);
			nList = document.getElementsByTagName("channel");
			
			for(int i = 0; i < nList.getLength(); i++){
				Node item = nList.item(i);
				Element element = (Element) item;
				
				a_List.add(extractContent("title",element,0));
				a_List.add(extractContent("pubDate",element,1));
				a_List.add(extractContent("ExtractedText", element, 0));
				a_List.add(extractContent("title",element,2));
				a_List.add(extractContent("guid",element,0));
			}

		} catch (SAXException | IOException | NullPointerException e) {
		
		}
	}
	
	private String extractContent(String TagName, Element a_element, int Knoten){
		return a_element.getElementsByTagName(TagName).item(Knoten).getTextContent();
	}
	
}
