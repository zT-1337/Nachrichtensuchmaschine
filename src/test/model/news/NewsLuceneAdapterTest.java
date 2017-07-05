package test.model.news;

import static org.junit.Assert.*;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;
import org.apache.lucene.index.IndexableField;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.model.news.NewsFields;
import application.model.news.NewsLuceneAdapter;

/**
 * 
 * @author Thomas Zerr
 *
 */
public class NewsLuceneAdapterTest {
	
	private NewsLuceneAdapter news = new NewsLuceneAdapter();
	
	@Before
	public void setUp() {
		news = new NewsLuceneAdapter();
	}
	
	@After
	public void tearDown() {
		
	}
	
	 @Test
	 public void testTitle() {
		 String testTitle = "TestTitle";
		 
		 news.setTitle(testTitle);
		 
		 assertTrue("Erwartet wurde: " + testTitle + " erhalten wurde: " + news.getTitle(), 
				 	news.getTitle().equals(testTitle));
		 assertTrue("Erwartet wurde ein Document", news.getDataStructure() instanceof Document);
		 Document doc = (Document) news.getDataStructure();
		 IndexableField field = doc.getField(NewsFields.TITLE);
		 assertTrue("Erwartet wurde ein TextField", field instanceof TextField);
	 }
	 
	 @Test
	 public void testTopic() {
		 String testTopic = "TestTopic";
		 
		 news.setTopic(testTopic);
		 
		 assertTrue("Erwartet wurde: " + testTopic.toLowerCase() + " erhalten wurd: " + news.getTopic(),
				 	news.getTopic().equals(testTopic.toLowerCase()));
		 assertTrue("Erwartet wurde ein Document", news.getDataStructure() instanceof Document);
		 Document doc = (Document) news.getDataStructure();
		 IndexableField field = doc.getField(NewsFields.TOPIC);
		 assertTrue("Erwartet wurde ein StringField", field instanceof StringField);
	 }
	 
	 @Test
	 public void testValidPubDate() {
		 String pubDate = "01.06.1997";
		 String answer = "01.06.1997";
		 
		 news.setPubDate(pubDate);
		 
		 assertTrue("Erwartet wurde " + answer + " erhalten wurde: " + news.getPubDate(),
				 	news.getPubDate().equals(answer));
		 assertTrue("Erwartet wurde ein Document", news.getDataStructure() instanceof Document);
		 Document doc = (Document) news.getDataStructure();
		 IndexableField field = doc.getField(NewsFields.PUBDATE);
		 assertTrue("Erwartet wurde ein StringField", field instanceof StringField);
	 }
	 
	 @Test
	 public void testInvalidPubDate() {
		 String pubDate = "01.06.yyyy";
		 String answer = "";
		 
		 news.setPubDate(pubDate);
		 
		 assertTrue("Erwartet wurde " + answer + " erhalten wurde: " + news.getPubDate(),
				 	news.getPubDate().equals(answer));
		 assertTrue("Erwartet wurde ein Document", news.getDataStructure() instanceof Document);
		 Document doc = (Document) news.getDataStructure();
		 IndexableField field = doc.getField(NewsFields.PUBDATE);
		 assertTrue("Erwartet wurde ein StringField", field instanceof StringField);
	 }
	 
	 @Test
	 public void testSource() {
		 String testSource = "TestSource";
		 
		 news.setSource(testSource);
		 
		 assertTrue("Erwartet wurde " + testSource + " erhalten wurde " + news.getSource(),
				 	news.getSource().equals(testSource));
		 assertTrue("Erwartet wurde ein Document", news.getDataStructure() instanceof Document);
		 Document doc = (Document) news.getDataStructure();
		 IndexableField field = doc.getField(NewsFields.SOURCE);
		 assertTrue("Erwartet wurde ein StringField", field instanceof StringField);
	 }
	 
	 @Test
	 public void testURL() {
		 String testURL = "TestURL";
		 
		 news.setURL(testURL);
		 
		 assertTrue("Erwartet wurde " + testURL + " erhalten wurde " + news.getURL(),
				 	news.getURL().equals(testURL));
		 assertTrue("Erwartet wurde ein Document", news.getDataStructure() instanceof Document);
		 Document doc = (Document) news.getDataStructure();
		 IndexableField field = doc.getField(NewsFields.URL);
		 assertTrue("Erwartet wurde ein StringField", field instanceof StringField);
		 
	 }
	 
	 @Test
	 public void testText() {
		 String testText = "Test Text";
		 
		 news.setText(testText);
		 
		 assertTrue("Erwartet wurde " + testText + " erhalten wurde " + news.getText(),
				 	news.getText().equals(testText));
		 assertTrue("Erwartet wurde ein Document", news.getDataStructure() instanceof Document);
		 Document doc = (Document) news.getDataStructure();
		 IndexableField field = doc.getField(NewsFields.TEXT);
		 assertTrue("Erwartet wurde ein TextField", field instanceof TextField);
	 }
	 
	 @Test
	 public void testReducedText() {
		 String testReducedText = "Test reduzierter Text";
		 
		 news.setReducedText(testReducedText);
		 
		 assertTrue("Erwartet wurde " + testReducedText + " erhalten wurde " + news.getReducedText(),
				 	news.getReducedText().equals(testReducedText));
		 assertTrue("Erwartet wurde ein Document", news.getDataStructure() instanceof Document);
		 Document doc = (Document) news.getDataStructure();
		 IndexableField field = doc.getField(NewsFields.REDUCEDTEXT);
		 assertTrue("Erwartet wurde ein TextField", field instanceof TextField);
	 }
}
