package application.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import application.controller.NewsCreator.CreatorController;
import application.controller.search.LuceneSearch;
import application.model.index.LuceneIndex;
import application.model.news.News;
import application.model.newsresult.NewsResult;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



public class MainController extends Thread {
	
	private MainWindow mainWindow;
	private NewsResult result;
	private int currentPage = 1;
	
	private LuceneIndex myIndex;
	private LuceneSearch mySearch;
	
	Stage fileStage;
	
	public void setSearch(LuceneSearch search) {
		mySearch = search;
	}
	
	public void setIndex(LuceneIndex index) {
		myIndex = index;
	}
	
	public void run() {
		//Hier Crawler und co starten
		try {
			String notific = "C:/Users/-Felix/Desktop/Studium/Semester 4/Praktikum Software Entwicklung/RSSArchive/RSS/viewernotification";
			String wordlist = "C:/Users/-Felix/git/Nachrichtensuchmaschine/WordStatisticsLetter1";
			
			CreatorController cContr = new CreatorController(notific, wordlist, myIndex);
			cContr.start("C:/Users/-Felix/Desktop/Studium/Semester 4/Praktikum Software Entwicklung/RSSArchive/RSS/rssfiles");
		}
		catch (Exception e) {
			System.out.println("error: "+e);
			//TODO
		}
		
	}
	
	public int getNewsWithPage(int number) {
		return ((currentPage-1)*10) + number -1;
	}
	
	public void nextPage() {
		currentPage++;
		mainWindow.showNews(result);
		mainWindow.updatePageButton(currentPage);
	}
	
	public void previousPage() {
		if(currentPage>1==true) {
			currentPage--;
			mainWindow.showNews(result);
			mainWindow.updatePageButton(currentPage);
		} else {
			//do nothing
		}
			
	}
	
	//Entwurf 7. doSearch(...)
	public void doSearch(String terms, String dates, String topics, String news, int n) {
		System.out.println("@MainController: Incoming search from View:");
		System.out.println("terms:"+terms + ",dates:"+dates + ",topics:"+topics + ",news:"+news + ",n:"+n);
		result = mySearch.search(terms, dates, topics, news, n);
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
	

	
	
}
