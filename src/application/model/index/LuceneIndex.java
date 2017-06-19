/*
 * LuceneIndex
 * 
 * Version: 1.1
 * 
 * Date: 22.01.2017
 * 
 */

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
import org.apache.lucene.search.TopDocs;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.SimpleFSDirectory;

import application.model.news.News;
import application.model.news.NewsLuceneAdapter;
import application.model.newsresult.NewsResult;
import application.model.newsresult.NewsResultLuceneAdapter;
/**
 * Diese Klasse ist ein Index zum Speichern und Suchen von Nachrichten.
 * Diese Klasse kapselt dazu die Funktionalitäten die Lucene dafür anbietet.
 * 
 * @author Thomas Zerr
 * @version 1.1
 * 
 * @see <a href="https://lucene.apache.org/core/6_5_0/core/org/apache/lucene/search/package-summary.html">Suche in Lucene</a>
 * @see <a href="https://lucene.apache.org/core/6_5_0/core/org/apache/lucene/document/Document.html">Document</a>
 * @see <a href="https://lucene.apache.org/core/6_5_0/core/org/apache/lucene/index/package-summary.html">Index in Lucene</a>
 * @see <a href="https://lucene.apache.org/core/6_5_0/core/org/apache/lucene/index/IndexWriterConfig.html">IndexWriterConfig</a>
 * @see <a href="https://lucene.apache.org/core/6_5_0/core/org/apache/lucene/search/TopDocs.html">TopDocs</a>
 */
public class LuceneIndex implements Index, Closeable {

	/**
	 * Repräsentiert den Pfad, indem der Index gespeichert wird.
	 */
	private FSDirectory directory_;
	
	/**
	 * Übernimmt das Speichern der Nachrichten in den Index.
	 */
	private IndexWriter writer_;
	
	/**
	 * Übernimmt das Suchen der Nachrichten im Index
	 */
	private IndexSearcher searcher_;
	
	/**
	 * Erzeugt einen LuceneIndex mit dem Pfad "./Nachrichtensuchmaschine/index"
	 */
	public LuceneIndex() {
			initDirectory();
			initIndexWriter();		
			initIndexSearcher();
	}
	
	/**
	 * Fügt die Nachricht zum Index hinzu.
	 * Die Nachricht wird durch den 'writer_' zum Index hinzugefügt. 
	 * Die 'news' muss ein NewsLuceneAdapter sein, um die richtige Datenstruktur zu erhalten, in der die Nachricht gespeichert ist.
	 * In diesem Falls wäre es der Typ Document von Lucene
	 * 
	 * @param news Die Nachricht, welche hinzugefügt werden soll. Von der Nachricht wird erwartet, dass sie vom konkreten Typ NewsLuceneAdapter ist
	 * 
	 * @return Einen "Returncode" der Beschreibt was beim Hinzufügen vorgefallen ist.
	 * 
	 */
	@Override
	public ResultIndex addNews(News news) {
		if(news == null)
			return ResultIndex.NULLPARAM;
		
		if(!(news instanceof NewsLuceneAdapter))
			return ResultIndex.WRONGNEWSTYPE;
		
		Document doc = (Document) news.getDataStructure();
		
		try {
			writer_.addDocument(doc);
			writer_.commit();
			return ResultIndex.SUCCESS;
		} catch (IOException e) {
			e.printStackTrace();
			return ResultIndex.IOEXCEPTION;
		}
		
	}
	
	/**
	 * Sucht nach Nachrichten im Index.
	 * Es wird anhand der Query nach Nachrichten gesucht. 
	 * Die erhaltenen Nachrichten werden zusammen mit dem Score zurückgelieftert
	 * 
	 * @param querry Die Query nach der Nachrichten gesucht werden.
	 * @param n Die Anzahl der Nachrichten, welche als Ergebnis zurückgeliefert werden soll
	 * 
	 * @return Die gefundenen Nachrichten und ihren jeweiligen Score.
	 */
	@Override
	public NewsResult searchFor(Query query, int n) {
		try {
			TopDocs top = searcher_.search(query, n);
			return initNewsResult(top);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * Schließt den IndexWriter, IndexSearcher und FSDirectory
	 * 
	 * @return void
	 * 
	 * @throws IOException
	 */
	@Override
	public void close() throws IOException {
		writer_.close();
		searcher_.getIndexReader().close();
		directory_.close();
	}
	
	/**
	 * Initialisiert den Pfad des Indexes.
	 * Der Pfad für den Index lautet "./index", dieser muss auch existieren.
	 * 
	 * @return void
	 */
	private void initDirectory() {
		String path = "index";
		
		try {
			directory_ = new SimpleFSDirectory(FileSystems.getDefault().getPath(path));
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Initialisiert den IndexWriter.
	 * Der IndexWriter wird mithilfe einer IndexWriterConfig und einem FSDirectory initialisiert.
	 * Für den IndexWriterConfig werden die Standardkonfiguration genutzt.
	 * Es wird directory_ als FSDirectory verwendet
	 * 
	 * 
	 * @return void
	 */
	private void initIndexWriter() {
		IndexWriterConfig conf = new IndexWriterConfig();
		
		try {
			writer_ = new IndexWriter(directory_, conf);
			writer_.commit();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Initialisiert den IndexSearcher searcher_.
	 * 
	 * @return void
	 */
	private void initIndexSearcher() {
		try {
			IndexReader reader = DirectoryReader.open(directory_);
			searcher_ = new IndexSearcher(reader);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Erzeugt eine NewsResult aus einem Suchergebnis.
	 * Das Suchergebnis von Lucene wird in ein NewsResult umgewandelt.
	 * 
	 * @param top In diesem Object kapselt Lucene seine Suchergebnisse
	 * @param n Anzahl der Nachrichten, welche als Ergebnis zurückgeliefert werden soll
	 * 
	 * @return Ein NewsResult mit den Nachrichten und den Scores aus dem Parameter top
	 * 
	 */
	private NewsResult initNewsResult(TopDocs top) throws IOException {
		//TODO Auto-generated method stub
		int n = top.scoreDocs.length;
		Document[] docs = new Document[n];
		float[] scores = new float[n];
		
		for(int i = 0; i < n; ++i) {
			docs[i] = searcher_.doc(top.scoreDocs[i].doc);
			scores[i] = top.scoreDocs[i].score;
		}
		
		return new NewsResultLuceneAdapter(docs, scores);
	}
}
