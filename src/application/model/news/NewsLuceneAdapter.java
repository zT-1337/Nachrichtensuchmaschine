package application.model.news;

import org.apache.lucene.document.Document;

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

	}

	@Override
	public void setPubDate(String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setSource(String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setURL(String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setText(String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public void setReducedText(String value) {
		// TODO Auto-generated method stub

	}

	@Override
	public Object getDataStructure() {
		// TODO Auto-generated method stub
		return doc_;
	}

}
