package test.controller.search;

import static org.junit.Assert.*;

import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.util.BytesRef;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.controller.search.DateCreator;
import application.model.news.NewsFields;

public class DateCreatorTest {
	
	private DateCreator creator;
	
	@Before
	public void setUp() {
		creator = new DateCreator();
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void testOneValidDate() {
		String field = NewsFields.PUBDATE;
		String date;
		Query query;
		
		date = "01.06.1997";
		query = creator.create(field, date);
		assertTrue(query instanceof TermQuery);
		TermQuery termQuery = (TermQuery) query;
		assertTrue(termQuery.getTerm().text().equals("19970601"));
		
	}
	
	@Test
	public void testTwoValidDates() {
		String field = NewsFields.PUBDATE;
		String dates;
		Query query;
		
		dates = "01.01.1997-02.06.2000";
		query = creator.create(field, dates);
		assertTrue(query instanceof TermRangeQuery);
		TermRangeQuery termRangeQuery = (TermRangeQuery) query;
		assertTrue(termRangeQuery.getLowerTerm().equals(new BytesRef("19970101")));
		assertTrue(termRangeQuery.getUpperTerm().equals(new BytesRef("20000602")));
		
	}
	
	@Test
	public void testOneInvalidDate() {
		
	}
	
	@Test
	public void testTwoInvalidDates() {
		
	}
	
	@Test
	public void testInvalidField() {
		
	}

}
