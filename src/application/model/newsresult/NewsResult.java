package application.model.newsresult;

import application.model.news.News;

public interface NewsResult {

	public News getNews(int index);
	public float getScore(int index);
}
