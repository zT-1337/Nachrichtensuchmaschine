package test.model.index;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.model.index.LuceneIndex;
import application.model.index.ResultIndex;
import application.model.news.News;
import application.model.news.NewsFields;
import application.model.news.NewsLuceneAdapter;
import application.model.newsresult.NewsResult;

public class LuceneIndexTest {

	LuceneIndex index;
	
	@Before
	public void initIndex() {
		index = new LuceneIndex();
	}
	
	@Test
	public void addNewsWrongType() {
		ArrayList<News> list = new ArrayList<News>();
		News newsWrongType = new TestNewsClass();
		News newsCorrectType = new NewsLuceneAdapter();
		
		list.add(newsWrongType);
		ResultIndex result = index.addNews(list);
		
		if(result == ResultIndex.IOEXCEPTION)
			fail("IOException beim hinzufügen der Nachricht");
		
		assertTrue(result == ResultIndex.WRONGNEWSTYPE);
		list.clear();
		
		
		
		list.add(newsCorrectType);
		list.add(newsWrongType);
		result = index.addNews(list);
		
		if(result == ResultIndex.IOEXCEPTION)
			fail("IOException beim hinzufügen der Nachricht");
		
		assertTrue(result == ResultIndex.WRONGNEWSTYPE);
		list.clear();
	}

	@Test
	public void addNewsNull() {
		ArrayList<News> list = new ArrayList<News>();
		News newsNull = null;
		News newsLucene = new NewsLuceneAdapter();
		
		list.add(newsNull);
		ResultIndex result = index.addNews(list);
		
		if(result == ResultIndex.IOEXCEPTION)
			fail("IOException beim hinzufügen der Nachricht");
		
		assertTrue(result == ResultIndex.NULLPARAM);
		list.clear();
		
		list.add(newsLucene);
		list.add(newsNull);
		result = index.addNews(list);
		
		if(result == ResultIndex.IOEXCEPTION)
			fail("IOException beim hinzufügen der Nachricht");
		
		assertTrue(result == ResultIndex.NULLPARAM);
	}
	
	@Test
	public void addSearchValidOneNews() {
		ArrayList<News> list = new ArrayList<News>();
		String testString = "Test Text";
		News newsLucene = new NewsLuceneAdapter();
		
		newsLucene.setText(testString);
		list.add(newsLucene);
		ResultIndex result = index.addNews(list);
		
		if(result == ResultIndex.IOEXCEPTION)
			fail("IOException beim hinzufügen der Nachricht");
		
		assertTrue(result == ResultIndex.SUCCESS);
		
		Query query = new TermQuery(new Term(NewsFields.TEXT, "test"));
		
		NewsResult newsResult = index.searchFor(query, 1);
		
		assertFalse(newsResult.getSize() == 0);
		assertTrue(newsResult.getNews(0).getText().equals(testString));
		System.out.println("Score:" + newsResult.getScore(0));
		
	}
	
	@Test
	public void addSearchValidTwoNews() {
		ArrayList<News> list = new ArrayList<News>();
		String testString1 = "Test Text";
		String testString2 = "Different Text";
		News newsLucene1 = new NewsLuceneAdapter();
		News newsLucene2 = new NewsLuceneAdapter();
		
		newsLucene1.setText(testString1);
		newsLucene2.setText(testString2);
		
		list.add(newsLucene1);
		list.add(newsLucene2);
		ResultIndex result = index.addNews(list);
		
		if(result == ResultIndex.IOEXCEPTION)
			fail("IOException beim hinzufügen der Nachricht");
		
		assertTrue(result == ResultIndex.SUCCESS);
		
		Query query1 = new TermQuery(new Term(NewsFields.TEXT, "test"));
		
		NewsResult newsResult = index.searchFor(query1, 2);
		
		assertTrue(newsResult.getSize() == 1);
		assertTrue(newsResult.getNews(0).getText().equals(testString1));
		
		
		Query query2 = new TermQuery(new Term(NewsFields.TEXT, "different"));
		
		newsResult = index.searchFor(query2, 2);
		
		assertTrue(newsResult.getSize() == 1);
		assertTrue(newsResult.getNews(0).getText().equals(testString2));
		
		Query query3 = new TermQuery(new Term(NewsFields.TEXT, "text"));
		
		newsResult = index.searchFor(query3, 1);
		
		assertTrue(newsResult.getSize() == 1);
		
		newsResult = index.searchFor(query3, 2);
		
		assertTrue(newsResult.getSize() == 2);
		assertTrue(newsResult.getScore(0) >= newsResult.getScore(1));
	}
	
	@After
	public void cleanUp() {
		try {
			index.close();
			File indexFolder = new File("./index");
			File[] files = indexFolder.listFiles();
			
			for(int i = 0; i < files.length; ++i)
				files[i].delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
