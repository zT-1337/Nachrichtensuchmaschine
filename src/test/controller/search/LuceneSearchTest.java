package test.controller.search;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.controller.search.LuceneSearch;
import application.model.index.LuceneIndex;
import application.model.index.ResultIndex;
import application.model.news.News;
import application.model.news.NewsLuceneAdapter;
import application.model.newsresult.NewsResult;

public class LuceneSearchTest {

	LuceneIndex index;
	LuceneSearch search;
	String testText1;
	String testText2;
	String testText3;
	
	@Before
	public void setUp() {
		//Init Index and Search
		index = new LuceneIndex();
		search = new LuceneSearch(index);
		
		//Init News
		testText1 = "Java ist auch eine Insel";
		testText2 = "Java ist auch eine Programmiersprache";
		testText3 = "Etwas ganz anderes";
		
		News n1 = new NewsLuceneAdapter();
		News n2 = new NewsLuceneAdapter();
		News n3 = new NewsLuceneAdapter();
		
		n1.setText(testText1);
		n1.setPubDate("01.06.1997");
		
		n2.setText(testText2);
		n2.setPubDate("01.06.2017");
		
		n3.setText(testText3);
		n3.setPubDate("01.06.2000");
		
		//Adding news to the Index
		List<News> list = new ArrayList<News>();
		list.add(n1);
		list.add(n2);
		list.add(n3);
		
		ResultIndex rc = index.addNews(list);
		
		if(rc == ResultIndex.IOEXCEPTION)
			fail("IOException beim Hinzufügen von Nachrichten");
	}
	
	@Test
	public void testSearchEmptyParameters() {
		String terms = "";
		String dates = "";
		String topics = "";
		String news = "";
				
		NewsResult result = search.search(terms, dates, topics, news, 1);
		
		assertNotNull(result);
		assertTrue(result.getSize() == 0);
	}
	
	@Test
	public void testSearchNullParameters() {
		String terms = null;
		String dates = null;
		String topics = null;
		String news = null;
				
		NewsResult result = search.search(terms, dates, topics, news, 1);
		
		assertNotNull(result);
		assertTrue(result.getSize() == 0);
	}
	
	@Test
	public void testSearchOnlyTerms() {
		//Gebe mir die maximal drei Nachrichten mit dem höchsten Score zur Suchanfrage:
		//Terms: java insel
		String terms = "java insel";		
		NewsResult result = search.search(terms, null, null, null, 3);
		assertTrue("Sollte 1 sein, ist aber: " + result.getSize(), result.getSize() == 1);
		assertTrue("Text soll " + testText1 + "entsprechen, ist aber:" + result.getNews(0).getText(),
					result.getNews(0).getText().equals(testText1));
		
		//Gebe mir die maximal drei Nachrichten mit dem höchsten Score zur Suchanfrage:
		//Terms: insel java
		terms = "insel java";		
		result = search.search(terms, null, null, null, 3);
		assertTrue("Sollte 1 sein, ist aber: " + result.getSize(), result.getSize() == 1);
		assertTrue("Text soll " + testText1 + "entsprechen, ist aber:" + result.getNews(0).getText(),
					result.getNews(0).getText().equals(testText1));
		
		//Gebe mir die maximal drei Nachrichten mit dem höchsten Score zur Suchanfrage:
		//Terms: java
		terms = "java";
		result = search.search(terms, null, null, null, 3);
		assertTrue("Sollte 2 sein, ist aber: " + result.getSize(), result.getSize() == 2);
		assertTrue("Text soll " + testText1 + "entsprechen, ist aber:" + result.getNews(0).getText(),
				result.getNews(0).getText().equals(testText1));
		assertTrue("Text soll " + testText2 + "entsprechen, ist aber:" + result.getNews(1).getText(),
				result.getNews(1).getText().equals(testText2));
		
		//Gebe mir die maximal eine Nachricht mit dem höchsten Score zur Suchanfrage:
		//Terms: java
		terms = "java";
		result = search.search(terms, null, null, null, 1);
		assertTrue("Sollte 1 sein, ist aber: " + result.getSize(), result.getSize() == 1);
		assertTrue("Text soll " + testText1 + "entsprechen, ist aber:" + result.getNews(0).getText(),
				result.getNews(0).getText().equals(testText1));
		
		//Gebe mir die maximal drei Nachrichten mit dem höchsten Score zur Suchanfrage:
		//Terms: anderes
		terms = "anderes";
		result = search.search(terms, null, null, null, 3);
		assertTrue("Sollte 1 sein, ist aber: " + result.getSize(), result.getSize() == 1);
		assertTrue("Text soll " + testText3 + "entsprechen, ist aber:" + result.getNews(0).getText(),
				result.getNews(0).getText().equals(testText3));
		
		//Gebe mir die maximal drei Nachrichten mit dem höchsten Score zur Suchanfrage:
		//Terms: java anderes
		terms = "java anderes";
		result = search.search(terms, null, null, null, 3);
		assertTrue("Sollte 0 sein, ist aber: " + result.getSize(), result.getSize() == 0);

	}
	
	@Test
	public void testSearchOnlyValidDates() {
		String date;
		NewsResult result;
		
		//Ergebnis eine Nachricht mit der Suchanfrage:
		//Date:01.06.1997
		//Maximal: 3
		date = "01.06.1997";
		result = search.search(null, date, null, null, 3);
		assertTrue("Sollte 1 sein, ist aber " + result.getSize(), result.getSize() == 1);
		assertTrue("Sollte 0 sein, ist aber " + result.getScore(0), result.getScore(0) == 0);
		assertTrue("Text soll " + testText1 + "entsprechen, ist aber:" + result.getNews(0).getText(),
				result.getNews(0).getText().equals(testText1));
		
		//Ergebnis eine Nachricht mit der Suchanfrage:
		//Date:01.06.2017
		//Maximal: 3
		date = "01.06.2017";
		result = search.search(null, date, null, null, 3);
		assertTrue("Sollte 1 sein, ist aber " + result.getSize(), result.getSize() == 1);
		assertTrue("Sollte 0 sein, ist aber " + result.getScore(0), result.getScore(0) == 0);
		assertTrue("Text soll " + testText2 + "entsprechen, ist aber:" + result.getNews(0).getText(),
				result.getNews(0).getText().equals(testText2));
		
		//Ergebnis eine mit der Suchanfrage:
		//Date:01.06.2000
		//Maximal: 3
		date = "01.06.2000";
		result = search.search(null, date, null, null, 3);
		assertTrue("Sollte 1 sein, ist aber " + result.getSize(), result.getSize() == 1);
		assertTrue("Sollte 0 sein, ist aber " + result.getScore(0), result.getScore(0) == 0);
		assertTrue("Text soll " + testText3 + "entsprechen, ist aber:" + result.getNews(0).getText(),
				result.getNews(0).getText().equals(testText3));
		
		//---------------------------------------------------------------------------------------------
		
		//Ergebnis drei Nachrichten mit der Suchanfrage:
		//Date:01.06.1997-01.06.2017
		//Maximal: 3
		date = "01.06.1997-01.06.2017";
		result = search.search(null, date, null, null, 3);
		assertTrue("Sollte 3 sein, ist aber " + result.getSize(), result.getSize() == 3);
	
		//Ergebnis zwei Nachrichten mit der Suchanfrage:
		//Date:01.06.1997-01.06.2017
		//Maximal: 2
		date = "01.06.1997-01.06.2017";
		result = search.search(null, date, null, null, 2);
		assertTrue("Sollte 2 sein, ist aber " + result.getSize(), result.getSize() == 2);
		
		//Ergebnis eine Nachricht mit der Suchanfrage:
		//Date:01.06.1997-01.06.2017
		//Maximal: 1
		date = "01.06.1997-01.06.2017";
		result = search.search(null, date, null, null, 1);
		assertTrue("Sollte 1 sein, ist aber " + result.getSize(), result.getSize() == 1);
		
		//---------------------------------------------------------------------------------------------
		
		//Ergebnis zwei Nachrichten mit der Suchanfrage:
		//Date:01.06.1997-01.06.2000
		//Maximal: 3
		date = "01.06.1997-01.06.2000";
		result = search.search(null, date, null, null, 3);
		assertTrue("Sollte 2 sein, ist aber " + result.getSize(), result.getSize() == 2);
		
		//Ergebnis zwei Nachrichten mit der Suchanfrage:
		//Date:01.06.1997-01.06.2000
		//Maximal: 2
		date = "01.06.1997-01.06.2000";
		result = search.search(null, date, null, null, 2);
		assertTrue("Sollte 2 sein, ist aber " + result.getSize(), result.getSize() == 2);
		
		//Ergebnis eine Nachrichten mit der Suchanfrage:
		//Date:01.06.1997-01.06.2000
		//Maximal: 1
		date = "01.06.1997-01.06.2000";
		result = search.search(null, date, null, null, 1);
		assertTrue("Sollte 1 sein, ist aber " + result.getSize(), result.getSize() == 1);
		
		//---------------------------------------------------------------------------------------------
		
		//Ergebnis zwei Nachrichten mit der Suchanfrage:
		//Date:01.06.2000-01.06.2017
		//Maximal: 3
		date = "01.06.2000-01.06.2017";
		result = search.search(null, date, null, null, 3);
		assertTrue("Sollte 3 sein, ist aber " + result.getSize(), result.getSize() == 2);
		
		//Ergebnis zwei Nachrichten mit der Suchanfrage:
		//Date:01.06.2000-01.06.2017
		//Maximal: 2
		date = "01.06.2000-01.06.2017";
		result = search.search(null, date, null, null, 2);
		assertTrue("Sollte 2 sein, ist aber " + result.getSize(), result.getSize() == 2);
		
		//Ergebnis eine Nachrichten mit der Suchanfrage:
		//Date:01.06.2000-01.06.2017
		//Maximal: 1
		date = "01.06.2000-01.06.2017";
		result = search.search(null, date, null, null, 1);
		assertTrue("Sollte 1 sein, ist aber " + result.getSize(), result.getSize() == 1);
		
		//---------------------------------------------------------------------------------------------
		
		//Ergebnis null Nachrichten mit der Suchanfrage:
		//Date:02.06.1997-31.05.2000
		//Maximal: 3
		date = "02.06.1997-31.05.2000";
		result = search.search(null, date, null, null, 3);
		assertTrue("Sollte 0 sein, ist aber " + result.getSize(), result.getSize() == 0);
		
		//Ergebnis null Nachrichten mit der Suchanfrage:
		//Date:02.06.2000-31.05.2017
		//Maximal: 3
		date = "02.06.2000-31.05.2017";
		result = search.search(null, date, null, null, 3);
		assertTrue("Sollte 0 sein, ist aber " + result.getSize(), result.getSize() == 0);
	
		//---------------------------------------------------------------------------------------------
		
		//Ergebnis eine Nachrichten mit der Suchanfrage:
		//Date:02.06.1997-31.05.2000 01.06.1997
		//Maximal: 3
		date = "02.06.1997-31.05.2000 01.06.1997";
		result = search.search(null, date, null, null, 3);
		assertTrue("Sollte 1 sein, ist aber " + result.getSize(), result.getSize() == 1);
		
		//Ergebnis eine Nachrichten mit der Suchanfrage:
		//Date:02.06.1997-31.05.2000 01.06.2000
		//Maximal: 3
		date = "02.06.1997-31.05.2000 01.06.2000";
		result = search.search(null, date, null, null, 3);
		assertTrue("Sollte 1 sein, ist aber " + result.getSize(), result.getSize() == 1);
		
		//Ergebnis eine Nachrichten mit der Suchanfrage:
		//Date:02.06.1997-31.05.2000 01.06.2017
		//Maximal: 3
		date = "02.06.1997-31.05.2000 01.06.2017";
		result = search.search(null, date, null, null, 3);
		assertTrue("Sollte 1 sein, ist aber " + result.getSize(), result.getSize() == 1);
			
		//---------------------------------------------------------------------------------------------
		
		//Ergebnis drei Nachrichten mit der Suchanfrage:
		//Date:01.01.1997-01.01.2018.
		//Maximal: 3
		date = "01.01.1997-01.01.2018";
		result = search.search(null, date, null, null, 3);
		assertTrue("Sollte 3 sein, ist aber " + result.getSize(), result.getSize() == 3);
		
		//Ergebnis eine Nachrichten mit der Suchanfrage:
		//Date:01.01.1997-01.01.2018.
		//Maximal: 3
		date = "01.01.1998-01.01.2017";
		result = search.search(null, date, null, null, 3);
		assertTrue("Sollte 1 sein, ist aber " + result.getSize(), result.getSize() == 1);
					
		//---------------------------------------------------------------------------------------------

	}
	
	@After
	public void cleanUp() {
		try {
			index.close();
			File indexFolder = new File("./index");
			File[] files = indexFolder.listFiles();
		
			for(int i = 0; i < files.length; ++i)
				files[i].delete();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}

}
