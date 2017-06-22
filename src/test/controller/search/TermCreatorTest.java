package test.controller.search;

import static org.junit.Assert.*;

import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.controller.search.TermCreator;
import application.model.news.NewsFields;

public class TermCreatorTest {

	private TermCreator creator_;
	
	@Before
	public void setUp() {
		creator_ = new TermCreator();
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void testValidTerms() {
		String field = NewsFields.TEXT;
		String terms;
		Query result;
		TermQuery termQuery;
		
		terms = "term";
		result = creator_.create(field, terms);
		assertTrue(result instanceof TermQuery);
		termQuery = (TermQuery) result;
		assertTrue(termQuery.getTerm().field().equals(field));
		assertTrue(termQuery.getTerm().text().equals(terms));
		
		terms = "Term";
		result = creator_.create(field, terms);
		assertTrue(result instanceof TermQuery);
		termQuery = (TermQuery) result;
		assertTrue(termQuery.getTerm().field().equals(field));
		assertTrue(termQuery.getTerm().text().equals(terms));
		
		terms = "TermA TermB";
		result = creator_.create(field, terms);
		assertTrue(result instanceof TermQuery);
		termQuery = (TermQuery) result;
		assertTrue(termQuery.getTerm().field().equals(field));
		assertTrue(termQuery.getTerm().text().equals(terms));
		
		
	}
	
	@Test
	public void testInvalidTerms() {
		String field = NewsFields.TEXT;
		String terms;
		Query result;
		
		terms = null;
		result = creator_.create(field, terms);
		assertNull(result);
		
		terms = "";
		result = creator_.create(field, terms);
		assertNull(result);
	}
	
	@Test
	public void testInvalidField() {
		String field;
		String terms = "term";
		Query result;
		
		field = null;
		result = creator_.create(field, terms);
		assertNull(result);
		
		field = "";
		result = creator_.create(field, terms);
		assertNull(result);
	}
}
