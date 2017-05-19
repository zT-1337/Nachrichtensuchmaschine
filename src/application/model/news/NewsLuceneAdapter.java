package application.model.news;

import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.StringField;
import org.apache.lucene.document.TextField;

public class NewsLuceneAdapter implements News {
	
	private Document doc_;
	
	public NewsLuceneAdapter() {
		doc_ = new Document();
	}
	
	public NewsLuceneAdapter(Document doc) {
		doc_ = doc;
	}

	@Override
	public String getTitle() {
		// TODO Auto-generated method stub
		return doc_.get(NewsFields.TITLE);
	}
	
	@Override
	public String getTopic() {
		// TODO Auto-generated method stub
		return doc_.get(NewsFields.TOPIC);
	}

	@Override
	public String getPubDate() {
		// TODO Auto-generated method stub
		return doc_.get(NewsFields.PUBDATE);
	}

	@Override
	public String getSource() {
		// TODO Auto-generated method stub
		return doc_.get(NewsFields.SOURCE);
	}

	@Override
	public String getURL() {
		// TODO Auto-generated method stub
		return doc_.get(NewsFields.URL);
	}

	@Override
	public String getText() {
		// TODO Auto-generated method stub
		return doc_.get(NewsFields.TEXT);
	}

	@Override
	public String getReducedText() {
		// TODO Auto-generated method stub
		return doc_.get(NewsFields.REDUCEDTEXT);
	}

	@Override
	public void setTitle(String value) {
		// TODO Auto-generated method stub
		TextField titel = new TextField(NewsFields.TITLE, value, Field.Store.YES);
		doc_.add(titel);
	}
	
	@Override
	public void setTopic(String value) {
		// TODO Auto-generated method stub
		StringField topic = new StringField(NewsFields.TOPIC, value, Field.Store.YES);
		doc_.add(topic);
	}

	@Override
	public void setPubDate(String value) {
		// TODO Auto-generated method stub
		StringField pubdate = new StringField(NewsFields.PUBDATE, value, Field.Store.YES);
		doc_.add(pubdate);
	}

	@Override
	public void setSource(String value) {
		// TODO Auto-generated method stub
		//doc_.removeField(NewsFields.SOURCE);
		StringField source = new StringField(NewsFields.SOURCE, value, Field.Store.YES);
		doc_.add(source);
	}

	@Override
	public void setURL(String value) {
		// TODO Auto-generated method stub
		StringField url = new StringField(NewsFields.URL, value, Field.Store.YES);
		doc_.add(url);
	}

	@Override
	public void setText(String value) {
		// TODO Auto-generated method stub
		TextField text = new TextField(NewsFields.TEXT, value, Field.Store.NO);
		doc_.add(text);
	}

	@Override
	public void setReducedText(String value) {
		// TODO Auto-generated method stub
		TextField reducedText = new TextField(NewsFields.REDUCEDTEXT, value, Field.Store.NO);
		doc_.add(reducedText);
	}

	@Override
	public Object getDataStructure() {
		// TODO Auto-generated method stub
		return doc_;
	}

}
