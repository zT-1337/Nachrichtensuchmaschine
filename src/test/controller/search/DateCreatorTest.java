package test.controller.search;

import static org.junit.Assert.*;

import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.apache.lucene.search.TermRangeQuery;
import org.apache.lucene.util.BytesRef;
import org.junit.Test;

import application.controller.search.DateCreator;

public class DateCreatorTest {
	
	private DateCreator creator = new DateCreator();
	
	private String field = "Field";
	private String fieldEmpty = "";
	private String fieldNull = null;
	
	private String dateEmpty = "";
	private String dateNull = null;
	
	private String oneDate = "01.06.1997";
	private String secondDate = "02.06.1997";
	
	private String oneDateFalseFormat[] = new String[]{"1.6.97", "01.6.97", "1.06.97", "1.6.1997", 
														"o1.o6.o97", "o1.o6.97", "1a.2b.199c",
														"01-06-1997", "1-06-1997", "01-6-1997", "01-06-197", 
														"01/06/1997", "1/06/1997", "01/6/1997", "01/06/997",
														"01.06/1997", "01/06.1997", "01-06/1997", "01/06-1997",
														"01.06-1997", "01-06.1997"};
	
	
	
	private String twoDates = oneDate + "-" + secondDate;
	private String falseDateSeperators[] = new String[]{".", " ", "/"};

	@Test
	public void OneDateFalseFormat() {
		for(int i = 0; i < oneDateFalseFormat.length; ++i)
			assertNull("Testfall: " + oneDateFalseFormat[i], creator.create(field, oneDateFalseFormat[i]));
	}
	
	@Test
	public void TwoDatesFalseFormat() {
		for(int i = 0; i < falseDateSeperators.length; ++i) {
			String temp = oneDate + falseDateSeperators[i] + oneDate;
			
			assertNull("Testfall: " + temp, creator.create(field, temp));
		}
	}
	
	@Test
	public void DateEmpty() {
		assertNull(creator.create(field, dateEmpty));
	}
	
	@Test
	public void DateNull() {
		assertNull(creator.create(field, dateNull));
	}
	
	@Test
	public void FieldEmpty() {
		assertNull(creator.create(fieldEmpty, oneDate));
		assertNull(creator.create(fieldEmpty, twoDates));
	}
	
	@Test
	public void FieldNull() {
		assertNull(creator.create(fieldNull, oneDate));
		assertNull(creator.create(fieldNull, twoDates));
	}
	
	@Test
	public void ParamsEmpty() {
		assertNull(creator.create(fieldEmpty, dateEmpty));
	}
	
	@Test
	public void ParamsNull() {
		assertNull(creator.create(fieldNull, dateNull));
	}
	
	@Test
	public void ParamsValidOneDate() {
		Query result = creator.create(field, oneDate);
		
		assertNotNull(result);
		assertTrue(result instanceof TermQuery);
		
		TermQuery termQuery = (TermQuery) result;
		
		assertTrue(termQuery.getTerm().text().equals(oneDate));
		assertTrue(termQuery.getTerm().field().equals(field));
	}
	
	@Test
	public void ParamsValidTwoDates() {
		Query result = creator.create(field, twoDates);
		
		assertNotNull(result);
		assertTrue(result instanceof TermRangeQuery);
		
		TermRangeQuery termRangeQuery = (TermRangeQuery) result;
		BytesRef lowerTerm = new BytesRef(oneDate);
		BytesRef upperTerm = new BytesRef(secondDate);
		
		assertTrue(termRangeQuery.includesLower());
		assertTrue(termRangeQuery.includesUpper());
		assertTrue(termRangeQuery.getLowerTerm().equals(lowerTerm));
		assertTrue(termRangeQuery.getUpperTerm().equals(upperTerm));
	}

}
