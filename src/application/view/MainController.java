package application.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import application.model.news.News;
import application.model.newsresult.NewsResult;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class MainController implements application.controller.search.Search {
	
	private MainWindow mainWindow;
	private NewsResult result;
	private int currentPage = 1;
	
	Stage fileStage;
	
	public int getNewsWithPage(int number) {
		return ((currentPage-1)*10) + number;
	}
	
	//Entwurf 7. doSearch(...)
	public void doSearch(String terms, String dates, String topics, String news, int n) {
		System.out.println("@MainController: Incoming search from View:");
		System.out.println("terms:"+terms + "dates:"+dates + "topics:"+topics + "news:"+news + "n:"+n);
		result = search(terms, dates, topics, news, n);
		currentPage = 1;
		mainWindow.showNews(result);
	}
	
	//Entwurf 7. doTextExtraction(...)
	public void doTextExtraction(int number) {
		String path = createFile();		
		News news = result.getNews( ((currentPage-1)*10) + number );
		UserFunctions.extractText(news, path);		
	}
	
	private String createFile() {
	    FileChooser fileChooser = new FileChooser();
	    fileChooser.setTitle("Text Extrahieren");

	    File file = fileChooser.showSaveDialog(fileStage);
	    	if (file != null) {
	    		try {
	        		FileWriter fw = new java.io.FileWriter(file);
	        		fw.flush();
					fw.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    		return file.getPath();
	    	}
	        
	    	return null;
	}
	
	//Entwurf 7. doSourceAdd(...)
	public void doSourceAdd(String name, String thema, String sprache, String land, String link) {
		System.out.println("@MainController: Add Source with attributes: ");
		UserFunctions.addSource(name, thema, sprache, land, link);
	}
	
	//Entwurf 7. doSourceOpen(...)
	public void doSourceOpen(int number) {
		News news = result.getNews( ((currentPage-1)*10) + number );
		UserFunctions.openSource(news);
	}

	
	
	public void setMainWindow(MainWindow mw) {
		this.mainWindow = mw;
	}
	
	
	@Override
	public NewsResult search(String terms, String dates, String topics, String news, int n) {
		System.out.println("Search with attributes: Stichwort: "+terms + " Thema: "+topics + " Zeitraum: "+dates + " Similar: "+news);
		System.out.println("Max # of results:"+n);
		
		
		
		return null;
	}

	


	
	
	
	
}
