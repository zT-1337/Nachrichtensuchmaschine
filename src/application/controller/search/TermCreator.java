/*
 * TermCreator
 * 
 * Version: 1.0
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
 * @version 1.0
 *
 */
public class TermCreator implements QueryCreator {

	/**
	 * @param value Der Begriff aus dem eine TermQuery erzeugt wird
	 */
	@Override
	public Query create(String field, String value) {
		// TODO Auto-generated method stub
		Term term = new Term(field, value);
		TermQuery query = new TermQuery(term);
		
		return query;
	}

}
