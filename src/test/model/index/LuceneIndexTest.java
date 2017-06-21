package test.model.index;

import static org.junit.Assert.*;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.lucene.index.Term;
import org.apache.lucene.search.Query;
import org.apache.lucene.search.TermQuery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import application.model.index.LuceneIndex;
import application.model.index.ResultIndex;
import application.model.news.News;
import application.model.news.NewsFields;
import application.model.news.NewsLuceneAdapter;
import application.model.newsresult.NewsResult;

public class LuceneIndexTest {

	LuceneIndex index;
	
	@Before
	public void initIndex() {
		index = new LuceneIndex();
	}

	@After
	public void tearDown() {
		try {
			index.close();
			File indexFolder = new File("./index");
			File[] files = indexFolder.listFiles();
			
			for(int i = 0; i < files.length; ++i)
				files[i].delete();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
