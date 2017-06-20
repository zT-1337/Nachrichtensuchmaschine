package test.controller.search;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery;
import org.apache.lucene.search.BooleanQuery.Builder;
import org.apache.lucene.search.TermQuery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.controller.search.LuceneSearch;
import application.model.index.LuceneIndex;
import application.model.index.ResultIndex;
import application.model.news.News;
import application.model.news.NewsFields;
import application.model.news.NewsLuceneAdapter;
import application.model.newsresult.NewsResult;

public class LuceneSearchTest {

	LuceneIndex index;
	LuceneSearch search;
	
	@Before
	public void setUp() {
		index = new LuceneIndex();
		search = new LuceneSearch(index);
	}
	
	@Test
	public void EmptyParameters() {
		String terms = "";
		String dates = "";
		String topics = "";
		String news = "";
		
		List<News> list = new ArrayList<News>();
		News a = new NewsLuceneAdapter();
		list.add(a);
		ResultIndex rc = index.addNews(list);
		
		if(rc == ResultIndex.IOEXCEPTION)
			fail("IOException beim Hinzufügen von Nachrichten");
				
		NewsResult result = search.search(terms, dates, topics, news, 1);
		
		assertNotNull(result);
		assertTrue(result.getSize() == 0);
	}
	
	@Test
	public void NullParameters() {
		String terms = null;
		String dates = null;
		String topics = null;
		String news = null;
		
		List<News> list = new ArrayList<News>();
		News a = new NewsLuceneAdapter();
		list.add(a);
		ResultIndex rc = index.addNews(list);
		
		if(rc == ResultIndex.IOEXCEPTION)
			fail("IOException beim Hinzufügen von Nachrichten");
				
		NewsResult result = search.search(terms, dates, topics, news, 1);
		
		assertNotNull(result);
		assertTrue(result.getSize() == 0);
	}
	
	@Test
	public void TestTerms() {
		String testText1 = "Java ist auch eine Insel";
		String testText2 = "Java ist auch eine Programmiersprache";
		String testText3 = "Etwas ganz anderes";
		News n1 = new NewsLuceneAdapter();
		News n2 = new NewsLuceneAdapter();
		News n3 = new NewsLuceneAdapter();
		
		n1.setText(testText1);
		n2.setText(testText2);
		n3.setText(testText3);
		
		String terms = "java insel";
		
		List<News> list = new ArrayList<News>();
		list.add(n1);
		list.add(n2);
		list.add(n3);
		ResultIndex rc = index.addNews(list);
		
		if(rc == ResultIndex.IOEXCEPTION)
			fail("IOException beim Hinzufügen von Nachrichten");
		
		NewsResult result = search.search(terms, null, null, null, 3);
		
		assertTrue("Sollte 1 sein, ist aber: " + result.getSize(), result.getSize() == 1);
		assertTrue(result.getNews(0).getText().equals(testText1));
		
		terms = "java";
		
		result = search.search(terms, null, null, null, 3);
		
		assertTrue("Sollte 2 sein, ist aber: " + result.getSize(), result.getSize() == 2);
		assertTrue(result.getNews(0).getText().equals(testText1));
		
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
