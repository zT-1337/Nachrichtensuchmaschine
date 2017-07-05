package test.controller.NewsCreator;

import static org.junit.Assert.*;

import java.util.ArrayList;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Test;
import org.junit.*;

import application.controller.NewsCreator.XML_Parser;

public class XML_ParserText {
	
	private XML_Parser parser;
	private ArrayList<String> newsContentParse;
	private String filePath;
	
	@Before
	public void init(){
		try {
			parser = new XML_Parser();
		} catch (ParserConfigurationException e) {
			parser = null;
		}
		newsContentParse = new ArrayList<String>();
	}
	

	@Test
	public void testSpiegelParse() {
		if(parser == null)fail("Parser nicht initialisiert");
		filePath = "C:/Users/kkaufmann/git/Nachrichtensuchmaschine/testfiles/news/SpiegelTest.xml";
		parser.parse(filePath, newsContentParse);
		System.out.println(newsContentParse.size());
		assertTrue("Test Content extrahierung unvollständig", newsContentParse.size() == 5);
	}
	
	@Test
	public void testCNNParse() {
		if(parser == null)fail("Parser nicht initialisiert");
		filePath = "C:/Users/kkaufmann/git/Nachrichtensuchmaschine/testfiles/news/CNNTest.xml";
		parser.parse(filePath, newsContentParse);
		System.out.println(newsContentParse.size());
		assertTrue("Test Content extrahierung unvollständig", newsContentParse.size() == 5);
	}
	
	@Test
	public void testFAZParse() {
		if(parser == null)fail("Parser nicht initialisiert");
		filePath = "C:/Users/kkaufmann/git/Nachrichtensuchmaschine/testfiles/news/FAZTest.xml";
		parser.parse(filePath, newsContentParse);
		System.out.println(newsContentParse.size());
		assertTrue("Test Content extrahierung unvollständig", newsContentParse.size() == 5);
	}
	
	@Test
	public void testUnvollstaendigParse(){
		if(parser == null)fail("Parser nicht initialisiert");
		filePath = "C:/Users/kkaufmann/git/Nachrichtensuchmaschine/testfiles/news/unbollstaendigTest.xml";
		parser.parse(filePath, newsContentParse);
		System.out.println(newsContentParse.size());
		assertTrue("Test unvollstaendiger Content", newsContentParse.size() != 5);
	}
}
