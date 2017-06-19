package test.controller.search;

import static org.junit.Assert.*;

import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.junit.Test;

import application.controller.search.TermCreator;

public class TermCreatorTest {

	private TermCreator creator_ = new TermCreator();
	
	@Test
	public void EmptyField() {
		String field = "";
		String value = "Term";
		
		Query result = creator_.create(field, value);
		
		assertNull(result);
	}
	
	@Test
	public void EmptyValue() {
		String field = "field";
		String value = "";
		
		Query result = creator_.create(field, value);
		
		assertNull(result);
	}
	
	@Test
	public void EmptyParams() {
		String field = "";
		String value = "";
		
		Query result = creator_.create(field, value);
		
		assertNull(result);
	}
	
	@Test
	public void NullField() {
		String field = null;
		String value = "Term";
		
		Query result = creator_.create(field, value);
		
		assertNull(result);
	}
	
	@Test
	public void NullValue() {
		String field = "field";
		String value = null;
		
		Query result = creator_.create(field, value);
		
		assertNull(result);
	}
	
	@Test
	public void NullParams() {
		String field = null;
		String value = null;
		
		Query result = creator_.create(field, value);
		
		assertNull(result);
	}
	
	@Test
	public void BothParamsValid() {
		String field = "field";
		String value = "Term";
		
		Query result = creator_.create(field, value);
		
		assertTrue(result instanceof TermQuery);
		
		TermQuery queryResult = (TermQuery) result;
		
		assertTrue(queryResult.getTerm().text().equals(value));
		assertTrue(queryResult.getTerm().field().equals(field));
		
	}

}
