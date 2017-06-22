package application.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;
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
	
	private CreatorController cContr;
	
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
	
	public News getNews(int number) {
		int newsNumber = getNewsWithPage(number);
		return result.getNews(newsNumber);
	}
	
	public void run() {
		//Hier ird des NewsCreator gestartet
		//System.out.println("@mainController: CreatorController Thread before Try Catch");
		
		String[] pathArray = new String[3];
		
		pathArray = getPaths();
		
		pathArray[0] = pathArray[0].replace("notification path:","");
		pathArray[1] = pathArray[1].replace("wordlist path:","");
		pathArray[2] = pathArray[2].replace("rssCrawler path:","");
		
		System.out.println("notifications Path: "+pathArray[0]);
		System.out.println("wordlist path: "+pathArray[1]);
		System.out.println("rssCrawler path: "+pathArray[2]);
		
		try {
			String notific = pathArray[0];
			String wordlist = pathArray[1];
			
			cContr = new CreatorController(notific, wordlist, myIndex);
			cContr.start(pathArray[2]);
		}
		catch (Exception e) {
			e.printStackTrace();
			//TODO
		}
		System.out.println("@mainController: CreatorController started");
		
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
		
		System.out.println("@mainController: number of news: " + numberOfNews);
		maxNumberOfPages = (numberOfNews+9)/10;
		System.out.println("@mainController: maxNumberOfPages: " + maxNumberOfPages);
		
		currentPage = 1;
		mainWindow.updatePageButton(currentPage);
		
		mainWindow.showNews(result);
	}
	
	//Entwurf 7. doTextExtraction(...)
	public void doTextExtraction(int number) {
		String path = createFile();		
		News news = result.getNews(number);
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
		News news = result.getNews(number);
		UserFunctions.openSource(news);
	}

	
	
	public void setMainWindow(MainWindow mw) {
		this.mainWindow = mw;
	}
	
	public String[] getPaths() {
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
		
		return pathArray;
	}
	
	public void startCrawler() {
		String[] pathArray = new String[3];
		pathArray = getPaths();
		
		String rssPath = pathArray[2];
		rssPath = rssPath.replace("RSS/rssfiles/","");
		rssPath = rssPath.replace("rssCrawler path:","\"");
		rssPath += "ServerRSS.jar\"";
		System.out.println("string to serverRSS: " + rssPath);
		
		try {
			ProcessBuilder pb = new ProcessBuilder("java", "-jar", rssPath);
			Process p = pb.start();
			System.out.println("@mainController: RSScrawler gestartet");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void stopCrawler() {
		String[] pathArray = new String[3];
		pathArray = getPaths();
		
		String path = pathArray[2];
		path = path.replace("rssCrawler path:","");
		int index = path.indexOf('/', 9);
		path = path.substring(0,index+1);
		path = path + "stop";
		
		File f = new File(path);
		if (f != null) {
    		try {
        		FileWriter fw = new java.io.FileWriter(f);
        		fw.flush();
				fw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	}
	}

	
	public void stopCController() {
		if(cContr!=null) {
			cContr.closeCreator();
			System.out.println("Creator Controller Stopped");
		}
	}
	
	
}
