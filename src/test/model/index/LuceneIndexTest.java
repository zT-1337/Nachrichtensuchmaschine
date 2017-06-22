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
	
	String newsText1;
	News n1;
	
	String newsText2;
	News n2;
	
	String newsText3;
	News n3;
	
	News newsWrongType;
	
	List<News> listOfNews;
	
	@Before
	public void setUp() {
		index = new LuceneIndex();
		
		newsText1 = "Java ist auch eine Insel";
		n1 = new NewsLuceneAdapter();
		n1.setText(newsText1);
		
		newsText2 = "Java ist auch eine Programmiersprache";
		n2 = new NewsLuceneAdapter();
		n2.setText(newsText2);
		
		newsText3 = "Etwas ganz anderes";
		n3 = new NewsLuceneAdapter();
		n3.setText(newsText3);
		
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
		
		listOfNews.add(n1);
		listOfNews.add(newsWrongType);
		result = index.addNews(listOfNews);
		if(result == ResultIndex.IOEXCEPTION)
			fail("Beim Hinzufügen kam es zu einer IOException");
		assertTrue(result == ResultIndex.WRONGNEWSTYPE);
		listOfNews.clear();
		
		//-----------------------------------------------------------------------
		
		listOfNews.add(n1);
		listOfNews.add(newsWrongType);
		listOfNews.add(n1);
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
		
		listOfNews.add(n1);
		listOfNews.add(null);
		result = index.addNews(listOfNews);
		if(result == ResultIndex.IOEXCEPTION)
			fail("Beim Hinzufügen kam es zu einer IOException");
		assertTrue(result == ResultIndex.NULLPARAM);
		listOfNews.clear();
		
		//-----------------------------------------------------------------------
		
		listOfNews.add(n1);
		listOfNews.add(null);
		listOfNews.add(n1);
		result = index.addNews(listOfNews);
		if(result == ResultIndex.IOEXCEPTION)
			fail("Beim Hinzufügen kam es zu einer IOException");
		assertTrue(result == ResultIndex.NULLPARAM);
		listOfNews.clear();
		
		//-----------------------------------------------------------------------
		
		
	}

	@Test
	public void testAddingSearchingOneValidNews() {
		String term;
		ResultIndex rc;
		NewsResult result;
		TermQuery termQuery;
		
		listOfNews.add(n1);
		listOfNews.add(n2);
		listOfNews.add(n3);
		rc = index.addNews(listOfNews);
		
		if(rc == ResultIndex.IOEXCEPTION)
			fail("Beim Hinzufügen der Nachrichten kam es zu einer IOException");
		
		term = "insel";
		termQuery = new TermQuery(new Term(NewsFields.TEXT, term));
		result = index.searchFor(termQuery, 3);
		assertTrue(result.getSize() == 1);
		assertTrue(result.getNews(0).getText().equals(newsText1));
		
		//----------------------------------------------------------------
		
		term = "programmiersprache";
		termQuery = new TermQuery(new Term(NewsFields.TEXT, term));
		result = index.searchFor(termQuery, 3);
		assertTrue(result.getSize() == 1);
		assertTrue(result.getNews(0).getText().equals(newsText2));
		
		//----------------------------------------------------------------
		
		term = "anderes";
		termQuery = new TermQuery(new Term(NewsFields.TEXT, term));
		result = index.searchFor(termQuery, 3);
		assertTrue(result.getSize() == 1);
		assertTrue(result.getNews(0).getText().equals(newsText3));
		
		//----------------------------------------------------------------
		
		term = "java";
		termQuery = new TermQuery(new Term(NewsFields.TEXT, term));
		result = index.searchFor(termQuery, 3);
		assertTrue(result.getSize() == 2);
		assertTrue(result.getNews(0).getText().equals(newsText1));
		assertTrue(result.getNews(1).getText().equals(newsText2));
		assertTrue(result.getScore(0) >= result.getScore(1));
		
		//----------------------------------------------------------------
		
		term = "java";
		termQuery = new TermQuery(new Term(NewsFields.TEXT, term));
		result = index.searchFor(termQuery, 1);
		assertTrue(result.getSize() == 1);
		assertTrue(result.getNews(0).getText().equals(newsText1));
		
		//----------------------------------------------------------------
		
		
	}
}
