/*
 * TermCreator
 * 
 * Version: 1.1
 * 
 * Datum: 29.05.2017
 */
package application.controller.search;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;

/**
 * Erzeugt eine TermQuery.
 * 
 * @author Thomas Zerr
 * @version 1.1
 *
 */
public class TermCreator implements QueryCreator {

	/**
	 * @param value Der Begriff aus dem eine TermQuery erzeugt wird
	 */
	@Override
	public Query create(String field, String value) {
		if(field == null || value == null)
			return null;
		
		if(field.length() == 0 || value.length() == 0)
			return null;
		
		Term term = new Term(field, value);
		TermQuery query = new TermQuery(term);
		
		return query;
	}

}
