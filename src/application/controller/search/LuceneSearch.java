package application.controller.search;

import java.util.StringTokenizer;

import org.apache.lucene.search.BooleanClause;
import org.apache.lucene.search.BooleanClause.Occur;
import org.apache.lucene.search.BooleanQuery.Builder;
import org.apache.lucene.search.Query;

import application.model.index.Index;
import application.model.news.NewsFields;
import application.model.newsresult.NewsResult;

public class LuceneSearch implements Search {
	
	private final static int minimum = 1;

	private Index index_;
	private QueryCreator termCreator;
	private QueryCreator dateCreator;
	
	public LuceneSearch(Index index) {
		index_ = index;
		
		termCreator = new TermCreator();
		dateCreator = new DateCreator();
	}
	
	@Override
	public NewsResult search(String terms, String dates, String topics, String news, int n) {
		// TODO Auto-generated method stub		
		Builder builder = new Builder();
		
		if(terms.length() != 0)
			builder.add(createTermsClause(terms, isComparingNews(news)));
		
		if(dates.length() != 0)
			builder.add(createDatesClause(dates));
		
		if(topics.length() != 0)
			builder.add(createTopicsClause(topics));
		
		if(news.length() != 0)
			builder.add(createNewsClause(news));
		
		return index_.searchFor(builder.build(), n);
	}
	
	private boolean isComparingNews(String news) {
		return news.length() != 0;
	}
	
	private BooleanClause createTermsClause(String terms, boolean comparingNews) {
		Occur occur;
		
		if(comparingNews)
			occur = Occur.FILTER;
		else
			occur = Occur.MUST;

		return createBooleanClause(terms, NewsFields.TEXT, termCreator, occur, occur);
	}
	
	private BooleanClause createDatesClause(String dates) {
		Occur occurBoolean = Occur.FILTER;
		Occur occurTerm = Occur.SHOULD;
		
		return createBooleanClause(dates, NewsFields.PUBDATE, dateCreator, occurBoolean, occurTerm);
	}
	
	private BooleanClause createTopicsClause(String topics) {
		Occur occurBoolean = Occur.FILTER;
		Occur occurTerm = Occur.SHOULD;
		
		return createBooleanClause(topics, NewsFields.TOPIC, termCreator, occurBoolean, occurTerm);
	}
	
	private BooleanClause createNewsClause(String news) {
		Occur occurBoolean = Occur.MUST;
		Occur occurTerms = Occur.SHOULD;
		
		return createBooleanClause(news, NewsFields.REDUCEDTEXT, termCreator, occurBoolean, occurTerms);
		
	}
	
	private BooleanClause createBooleanClause(String str, String field, QueryCreator creator, Occur occurBoolean, Occur occurTerms) {		
		Builder builder = new Builder();
		builder.setMinimumNumberShouldMatch(LuceneSearch.minimum);
		
		StringTokenizer st = new StringTokenizer(str);
		
		while(st.hasMoreTokens()) {
			Query query = creator.create(field, st.nextToken());
			builder.add(query, occurTerms);
		}
		
		
		return new BooleanClause(builder.build(), occurBoolean);
	}

}
