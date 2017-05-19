package application.model.index;

import java.io.Closeable;
import java.io.IOException;
import java.nio.file.FileSystems;

import org.apache.lucene.document.Document;
import org.apache.lucene.index.DirectoryReader;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.search.IndexSearcher;
import org.apache.lucene.search.Query;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.SimpleFSDirectory;

import application.model.news.News;
import application.model.newsresult.NewsResult;
import application.model.newsresult.NewsResultLuceneAdapter;

public class LuceneIndex implements Index, Closeable {

	private FSDirectory directory_;
	private IndexWriter writer_;
	private IndexSearcher searcher_;
	
	public LuceneIndex() {
			initDirectory();
			initIndexWriter();		
			initIndexSearcher();
	}
	
	@Override
	public ResultIndex addNews(News news) {
		// TODO Auto-generated method stub
		Document doc = (Document) news.getDataStructure();
		try {
			writer_.addDocument(doc);
			return ResultIndex.SUCCESS;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return ResultIndex.IOEXCEPTION;
		}
		
	}

	@Override
	public NewsResult searchFor(Query querry, int n) {
		// TODO Auto-generated method stub
		try {
			return new NewsResultLuceneAdapter(searcher_.search(querry, n).scoreDocs);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	@Override
	public void close() throws IOException {
		// TODO Auto-generated method stub
		writer_.close();
		searcher_.getIndexReader().close();
		directory_.close();
	}
	
	private void initDirectory() {
		String path = "index";
		
		try {
			directory_ = new SimpleFSDirectory(FileSystems.getDefault().getPath(path));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void initIndexWriter() {
		IndexWriterConfig conf = new IndexWriterConfig();
		
		try {
			writer_ = new IndexWriter(directory_, conf);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void initIndexSearcher() {
		try {
			IndexReader reader = DirectoryReader.open(directory_);
			searcher_ = new IndexSearcher(reader);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
