package application.model.news;

public interface News {

	public String getTitle();
	public String getTopic();
	public String getPubDate();
	public String getSource();
	public String getURL();
	public String getText();
	public String getReducedText();
	
	public void setTitle(String value);
	public void setTopic(String value);
	public void setPubDate(String value);
	public void setSource(String value);
	public void setURL(String value);
	public void setText(String value);
	public void setReducedText(String value);
	
	public Object getDataStructure();
}
