package test.model.newsresult;

import static org.junit.Assert.*;

import org.apache.lucene.document.Document;
import org.junit.Test;

import application.model.newsresult.NewsResultLuceneAdapter;

public class NewsResultLuceneAdapterTest {
	
	@Test
	public void emptyResult() {
		NewsResultLuceneAdapter newsResult = new NewsResultLuceneAdapter(new Document[0], new float[0]);
		
		assertTrue(newsResult.getSize() == 0);
		
		assertNull(newsResult.getNews(0));
		assertNull(newsResult.getNews(1));
		assertNull(newsResult.getNews(-1));
		
		assertTrue(newsResult.getScore(0) == -1);
		assertTrue(newsResult.getScore(1) == -1);
		assertTrue(newsResult.getScore(-1) == -1);
	}
	
	@Test
	public void validResult() {
		Document[] docs = new Document[]{new Document(), new Document()};
		float[] scores = new float[]{3, 5};
		
		NewsResultLuceneAdapter newsResult = new NewsResultLuceneAdapter(docs, scores);
		
		assertTrue(newsResult.getSize() == 2);
		
		assertTrue(newsResult.getNews(0).getDataStructure().equals(docs[0]));
		assertTrue(newsResult.getNews(1).getDataStructure().equals(docs[1]));
		
		assertNull(newsResult.getNews(-1));
		assertNull(newsResult.getNews(2));
		
		assertTrue(newsResult.getScore(0) == 3);
		assertTrue(newsResult.getScore(1) == 5);
		assertTrue(newsResult.getScore(-1) == -1);
		assertTrue(newsResult.getScore(2) == -1);
	}

}
