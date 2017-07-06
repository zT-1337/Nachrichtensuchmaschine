package test.view;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import application.controller.search.LuceneSearch;
import application.model.index.LuceneIndex;
import application.model.news.News;
import application.model.news.NewsLuceneAdapter;
import application.model.newsresult.NewsResult;
import application.view.MainController;

public class MainControllerTest {

	MainController mainController;
	LuceneIndex index;
	LuceneSearch search;
	
	News news;
	NewsResult result;
	
	@Before
	public void setUp() {
		mainController = new MainController();
		
		news = new NewsLuceneAdapter();
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void setIndexTest() {
		mainController.setIndex(index);
		LuceneIndex result = mainController.getIndex();
		assertTrue(index == result);
	}

	@Test
	public void setSearchTest() {
		mainController.setSearch(search);
		LuceneSearch result = mainController.getSearch();
		assertTrue(search == result);
	}
	
	@Test
	@Ignore ("no possibility for testing; only sets a private variable")
	public void setMainWindowTest() {
		fail("Not implemented");
	}
	
	@Test
	@Ignore ("no possibility for testing; only prompts another method")
	public void closeIndexTest() {
		fail("Not implemented");
	}
	
	@Test
	@Ignore ("no possibility for testing; cannot create newsresult without actual search")
	public void getNewsTest() {
		fail("Not implemented");		
	}
	
	@Test
	public void getNewsWithPageTest() {
		mainController.setCurrentPage(1);
		assertEquals(0, mainController.getNewsWithPage(1));		//expected 0
		assertEquals(9, mainController.getNewsWithPage(10));	//expected 9
		
		mainController.setCurrentPage(2);
		assertEquals(10, mainController.getNewsWithPage(1));	//expected 10
		assertEquals(19, mainController.getNewsWithPage(10));	//expected 19
	}
	
	@Test
	@Ignore ("no possibility for testing; starts CreatorController in a new Thread")
	public void runTest() {
		fail("Not implemented");		
	}
	
	@Test
	@Ignore ("no possibility for testing; mainWindow used by 'nextPage' does not exist")
	public void nextPageTest() {
		fail("Not implemented");
	}
	
	@Test
	@Ignore ("no possibility for testing; mainWindow used by 'previousPage' does not exist")
	public void previousPageTest() {
		fail("Not implemented");
	}
	
	@Test
	@Ignore ("no possibility for testing; only prompts another method tested in Search and sets private variable")
	public void doSearchTest() {
		fail("Not implemented");
	}
	
	@Test
	@Ignore ("no possibility for testing; only prompts another method tested in UserFuntions ( extractText(...) )")
	public void doTextExtractionTest() {
		fail("Not implemented");
	}
	
	@Test
	@Ignore ("no possibility for testing; only prompts another method tested in UserFunctions ( openSource(...) )")
	public void doSourceOpen() {
		fail("Not implemented");
	}
	
	@Test
	@Ignore ("no possibility for testing; starts an external part of the application (RSSCrawler) in a new process")
	public void startCrawlerTest() {
		fail("Not implemented");
	}
	
	@Test
	@Ignore ("no possibility for testing; stops an external part of the application (RSSCrawler)")
	public void stopCrawlerTest() {
		fail("Not implemented");
	}
	
	@Test
	@Ignore ("no possibility for testing; prompts CreatorController to stop")
	public void stopCControllerTest() {
		fail("Not implemented");
	}
	
	
	
	
	
	
	
}
