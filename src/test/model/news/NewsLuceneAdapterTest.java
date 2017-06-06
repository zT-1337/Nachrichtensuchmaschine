package test.model.news;

import static org.junit.Assert.*;

import org.apache.lucene.document.Document;
import org.junit.Before;
import org.junit.Test;

import application.model.news.NewsLuceneAdapter;

public class NewsLuceneAdapterTest {
	
	private NewsLuceneAdapter adapter_ = new NewsLuceneAdapter();
	
	@Test
	public void TitleTest() {
		String testTitle = "Test title";
		
		adapter_.setTitle(testTitle);
		assertEquals(testTitle, adapter_.getTitle());
	}
	
	@Test
	public void TopicTest() {
		String testTopic = "Test topic";
		
		adapter_.setTopic(testTopic);
		assertEquals(testTopic, adapter_.getTopic());
	}
	
	@Test
	public void PubDateTest() {
		String testPubDate = "01.06.1997";
		
		adapter_.setPubDate(testPubDate);
		assertEquals(testPubDate, adapter_.getPubDate());
	}
	
	@Test
	public void SourceTest() {
		String testSource = "Test source";
		
		adapter_.setSource(testSource);
		assertEquals(testSource, adapter_.getSource());
	}

	@Test
	public void URLTest() {
		String testURL = "www.test-url.com";
		
		adapter_.setURL(testURL);
		assertEquals(testURL, adapter_.getURL());
	}
	
	@Test
	public void TextTest() {
		String testText = "Test text";
		
		adapter_.setText(testText);
		assertEquals(testText, adapter_.getText());
	}
	
	@Test
	public void ReducedTextTest() {
		String testReducedText = "Test reduced text";
		
		adapter_.setReducedText(testReducedText);
		assertEquals(testReducedText, adapter_.getReducedText());
	}
	
	@Test
	public void GetDataStructureTest() {
		Object dataStructure = adapter_.getDataStructure();
		
		assertTrue(dataStructure instanceof Document);
	}
}
