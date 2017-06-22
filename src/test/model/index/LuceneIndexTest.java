package test.model.index;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
	News n;
	News newsWrongType;
	List<News> listOfNews;
	
	@Before
	public void initIndex() {
		index = new LuceneIndex();
		
		n = new NewsLuceneAdapter();
		n.setText("Kevin the Lord Kaufmann");
		
		newsWrongType = new TestNewsClass();
		
		listOfNews = new ArrayList<News>();
	}

	@After
	public void tearDown() {
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
	
	@Test
	public void testAddingInvalidNews() {
		ResultIndex result;
		
		listOfNews.add(newsWrongType);
		result = index.addNews(listOfNews);
		if(result == ResultIndex.IOEXCEPTION)
			fail("Beim Hinzufügen kam es zu einer IOException");
		assertTrue(result == ResultIndex.WRONGNEWSTYPE);
		listOfNews.clear();
		
		//-----------------------------------------------------------------------
		
		listOfNews.add(n);
		listOfNews.add(newsWrongType);
		result = index.addNews(listOfNews);
		if(result == ResultIndex.IOEXCEPTION)
			fail("Beim Hinzufügen kam es zu einer IOException");
		assertTrue(result == ResultIndex.WRONGNEWSTYPE);
		listOfNews.clear();
		
		//-----------------------------------------------------------------------
		
		listOfNews.add(n);
		listOfNews.add(newsWrongType);
		listOfNews.add(n);
		result = index.addNews(listOfNews);
		if(result == ResultIndex.IOEXCEPTION)
			fail("Beim Hinzufügen kam es zu einer IOException");
		assertTrue(result == ResultIndex.WRONGNEWSTYPE);
		listOfNews.clear();
		
		//-----------------------------------------------------------------------
	
		listOfNews.add(null);
		result = index.addNews(listOfNews);
		if(result == ResultIndex.IOEXCEPTION)
			fail("Beim Hinzufügen kam es zu einer IOException");
		assertTrue(result == ResultIndex.NULLPARAM);
		listOfNews.clear();
		
		//-----------------------------------------------------------------------
		
		listOfNews.add(n);
		listOfNews.add(null);
		result = index.addNews(listOfNews);
		if(result == ResultIndex.IOEXCEPTION)
			fail("Beim Hinzufügen kam es zu einer IOException");
		assertTrue(result == ResultIndex.NULLPARAM);
		listOfNews.clear();
		
		//-----------------------------------------------------------------------
		
		listOfNews.add(n);
		listOfNews.add(null);
		listOfNews.add(n);
		result = index.addNews(listOfNews);
		if(result == ResultIndex.IOEXCEPTION)
			fail("Beim Hinzufügen kam es zu einer IOException");
		assertTrue(result == ResultIndex.NULLPARAM);
		listOfNews.clear();
		
		//-----------------------------------------------------------------------
		
		
	}

	@Test
	public void testAddingSearchingValidNews() {
		
	}
}
