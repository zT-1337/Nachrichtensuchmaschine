package application.controller.search;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;

public class TermCreator implements QueryCreator {

	@Override
	public Query create(String field, String value) {
		// TODO Auto-generated method stub
		Term term = new Term(field, value);
		TermQuery query = new TermQuery(term);
		
		return query;
	}

}
