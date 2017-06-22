package application.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

import application.controller.NewsCreator.CreatorController;
import application.controller.search.LuceneSearch;
import application.model.index.LuceneIndex;
import application.model.news.News;
import application.model.news.NewsLuceneAdapter;
import application.model.newsresult.NewsResult;
import javafx.stage.FileChooser;
import javafx.stage.Stage;



public class MainController extends Thread {
	
	private MainWindow mainWindow;
	private NewsResult result;
	private int currentPage = 1;
	private int maxNumberOfPages;
	
	private LuceneIndex myIndex;
	private LuceneSearch mySearch;
	
	Stage fileStage;
	
	public void setSearch(LuceneSearch search) {
		mySearch = search;
	}
	
	public void setIndex(LuceneIndex index) {
		myIndex = index;
	}
	
	public void closeIndex() {
		try {
			myIndex.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void run() {
		//Hier Crawler und co starten
		System.out.println("NewsCreator Thread before Try Catch");
		
		Path path = Paths.get("./src/application/config.txt");
		String[] pathArray = new String[3];
		
		try (BufferedReader reader = Files.newBufferedReader(path)) {
		    String line = null;
		    int i = 0;
		    while ((line = reader.readLine()) != null) {
		        pathArray[i++] = line;
		    }
		} catch (IOException x) {
		    System.err.format("IOException: %s%n", x);
		}
		
		pathArray[0] = pathArray[0].replace("notification path:","");
		pathArray[1] = pathArray[1].replace("wordlist path:","");
		pathArray[2] = pathArray[2].replace("rssCrawler path:","");
		
		System.out.println("notifications Path: "+pathArray[0]);
		System.out.println("wordlist path: "+pathArray[1]);
		System.out.println("rssCrawler path: "+pathArray[2]);
		
		try {
			String notific = pathArray[0];
			String wordlist = pathArray[1];
			
			CreatorController cContr = new CreatorController(notific, wordlist, myIndex);
			cContr.start(pathArray[2]);
		}
		catch (Exception e) {
			e.printStackTrace();
			//TODO
		}
		
	}
	
	public int getNewsWithPage(int number) {
		return ((currentPage-1)*10) + number -1;
	}
	
	public void nextPage() {
		System.out.println("@MainController: Button Next page");
		if(currentPage<maxNumberOfPages) {
			currentPage++;
			mainWindow.showNews(result);
			mainWindow.updatePageButton(currentPage);
		} else {
			//do nothing
		}
		
	}
	
	public void previousPage() {
		System.out.println("@MainController: Button Previous page");
		if(currentPage>1) {
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
		System.out.println("    terms:"+terms + ",dates:"+dates + ",topics:"+topics + ",news:"+news + ",n:"+n);
		result = mySearch.search(terms, dates, topics, news, n);
		
		int numberOfNews = result.getSize();
		maxNumberOfPages = (numberOfNews+9)/10;
		
		currentPage = 1;
		if(result!=null) System.out.println("gor a result");
		if(result.getSize()==0) System.out.println("but its empty");
		else System.out.println("and its not empty");
		mainWindow.showNews(result);
		if(result!=null) System.out.println("@MainController: got a result");
		if(result.getNews(1)==null) System.out.println("@MainController: but its empty");
	}
	
	//Entwurf 7. doTextExtraction(...)
	public void doTextExtraction(int number) {
		String path = createFile();		
		News news = result.getNews( ((currentPage-1)*10) + number );
		System.out.println("@MainController: doTextExtraction");
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
//	public void doSourceAdd(String name, String thema, String sprache, String land, String link) {
//		System.out.println("@MainController: Add Source with attributes: ");
//		UserFunctions.addSource(name, thema, sprache, land, link);
//	}
	
	//Entwurf 7. doSourceOpen(...)
	public void doSourceOpen(int number) {
		News news = result.getNews( ((currentPage-1)*10) + number );
		UserFunctions.openSource(news);
	}

	
	
	public void setMainWindow(MainWindow mw) {
		this.mainWindow = mw;
	}
	

	
	
}
