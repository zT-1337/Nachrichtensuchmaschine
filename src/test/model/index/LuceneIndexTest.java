package test.model.index;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;

import org.junit.After;
import org.junit.Test;

import application.controller.search.LuceneSearch;
import application.model.index.LuceneIndex;
import application.model.index.ResultIndex;
import application.model.news.News;
import application.model.news.NewsLuceneAdapter;
import application.model.newsresult.NewsResult;

public class LuceneIndexTest {

	private LuceneIndex index = new LuceneIndex();
	private LuceneSearch search = new LuceneSearch(index);
	private News newsNull = null;
	private NewsLuceneAdapter newsLucene = new NewsLuceneAdapter();
	private TestNewsClass newsWrongType = new TestNewsClass();
	
	@Test
	public void addNewsWrongType() {
		ResultIndex result = index.addNews(newsWrongType);
		
		if(result == ResultIndex.IOEXCEPTION)
			fail("IOException beim hinzufügen der Nachricht");
		
		assertTrue(result == ResultIndex.WRONGNEWSTYPE);
	}

	@Test
	public void addNewsNull() {
		ResultIndex result = index.addNews(newsNull);
		
		if(result == ResultIndex.IOEXCEPTION)
			fail("IOException beim hinzufügen der Nachricht");
		
		assertTrue(result == ResultIndex.NULLPARAM);
	}
	
	@Test
	public void addSearchValidNews() {
		String testString = "Test Text";
		newsLucene.setText(testString);
		ResultIndex result = index.addNews(newsLucene);
		
		if(result == ResultIndex.IOEXCEPTION)
			fail("IOException beim hinzufügen der Nachricht");
		
		assertTrue(result == ResultIndex.SUCCESS);
		
		NewsResult newsResult = search.search("test", "", "", "", 1);
		
		System.out.println(newsResult.getSize());
		assertTrue(newsResult.getNews(0).getText().equals(testString));
		
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
