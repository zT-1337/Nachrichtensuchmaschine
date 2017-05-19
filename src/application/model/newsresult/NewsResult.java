package application.model.newsresult;

import application.model.news.News;

public interface NewsResult {

	public News getNews(int index);
	public int getScore(int index);
}
