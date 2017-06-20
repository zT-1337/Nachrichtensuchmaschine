package test.model.index;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.junit.After;
import org.junit.Test;

import application.model.index.LuceneIndex;
import application.model.index.ResultIndex;
import application.model.news.News;
import application.model.news.NewsFields;
import application.model.news.NewsLuceneAdapter;
import application.model.newsresult.NewsResult;

public class LuceneIndexTest {

	private LuceneIndex index = new LuceneIndex();
	private News newsNull = null;
	private NewsLuceneAdapter newsLucene = new NewsLuceneAdapter();
	private TestNewsClass newsWrongType = new TestNewsClass();
	
	@Test
	public void addNewsWrongType() {
		ArrayList<News> list = new ArrayList<News>();
		list.add(newsWrongType);
		ResultIndex result = index.addNews(list);
		
		if(result == ResultIndex.IOEXCEPTION)
			fail("IOException beim hinzufügen der Nachricht");
		
		assertTrue(result == ResultIndex.WRONGNEWSTYPE);
	}

	@Test
	public void addNewsNull() {
		ArrayList<News> list = new ArrayList<News>();
		list.add(newsNull);
		ResultIndex result = index.addNews(list);
		
		if(result == ResultIndex.IOEXCEPTION)
			fail("IOException beim hinzufügen der Nachricht");
		
		assertTrue(result == ResultIndex.NULLPARAM);
	}
	
	@Test
	public void addSearchValidNews() {
		ArrayList<News> list = new ArrayList<News>();
		String testString = "Test Text";
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
