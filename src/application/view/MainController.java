package application.view;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import application.controller.NewsCreator.CreatorController;
import application.controller.search.LuceneSearch;
import application.model.index.LuceneIndex;
import application.model.news.News;
import application.model.newsresult.NewsResult;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


/**
*	This class reprenents the 'MainController' component of the design (see external: Entwurf 7. MainController)
* @author  Felix Bahr
*/
public class MainController extends Thread {
	
	private MainWindow mainWindow;
	private NewsResult result;
	private int currentPage = 1;
	private int maxNumberOfPages;
	
	private LuceneIndex myIndex;
	private LuceneSearch mySearch;
	
	private CreatorController cContr;
	
	Stage fileStage;
	
	/**
	 * Sets the local variable {@link #mySearch} with the parameter "search" for later use.
	 * 
	 * @param search an instance of LuceneSearch
	 * 
	 * @see LuceneSearch application.view.MainController.mySearch
	 */
	public void setSearch(LuceneSearch search) {
		mySearch = search;
	}
	
	/**
	 * Sets the local variable {@link #myIndex} with the parameter "index" for later use.
	 * 
	 * @param index instance of LuceneIndex
	 * 
	 * @see LuceneIndex application.view.MainController.myIndex
	 */
	public void setIndex(LuceneIndex index) {
		myIndex = index;
	}
	
	/**
	 * Sets the local variable {@link #mainWindow} with the parameter "nw" for later use.
	 * 
	 * @param mw instance of MainWindow
	 * 
	 * @see MainWindow application.view.MainController.mainWindow
	 */
	public void setMainWindow(MainWindow mw) {
		this.mainWindow = mw;
	}
	
	/**
	 * Returns the instance of LuceneSearch.
	 * <p>
	 * Only used for testing!
	 * @return instance of LuceneSearch
	 */
	public LuceneSearch getSearch() {
		return mySearch;
	}
	
	/**
	 * Returns the instande of LuceneIndex.
	 * <p>
	 * Only used for testing!
	 * @return instance of luceneIndex
	 */
	public LuceneIndex getIndex() {
		return myIndex;
	}
	
	/**
	 * Signals the instance of LuceneIndex {@link #myIndex} to close.
	 */
	public void closeIndex() {
		try {
			myIndex.close();
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR, "Beim schlieﬂen des Index' ist etwas fehlgeschlagen.\n"
        			+ e.getMessage(), ButtonType.OK);
			alert.showAndWait();
			e.printStackTrace();
		}
	}
	
	/**
	 * Returns a news-object at the designated index "number".
	 * <p>
	 * "number" is relative to page number
	 * example: first news, first page: number = 1
	 * second page, first news: number = 1
	 * 
	 * @param number the relative index of the news to be returned (first news has index 1!)
	 * @return an instance of News
	 * 
	 * @see application.model.news.News
	 */
	public News getNews(int number) {
		int newsNumber = getNewsWithPage(number);
		return result.getNews(newsNumber);
	}
	
	/**
	 * Returns the absolute index of the news pointed at by "number".
	 * <p>
	 * example:
	 * currentPage = 2
	 * number = 1
	 * return = 10
	 * 
	 * @param number the relative index of the news (first news has index 1!)
	 * @return	absolute index of news
	 */
	public int getNewsWithPage(int number) {
		return ((currentPage-1)*10) + number -1;
	}
	
	/**
	 * Sets the variable {@link #currentPage}.
	 * <p>
	 * Only used for testing!
	 * @param number the current page ht view is on
	 */
	public void setCurrentPage(int number) {
		currentPage = number;
	}
	
	/**
	 * Returns the variable {@link #currentPage}.
	 * <p>
	 * Only used for testing!
	 * @return the current page the view is on
	 */
	public int getCurrentPage() {
		return currentPage;
	}
	
	/** 
	 * Sets the variable {@link #maxNumberOfPages}
	 * <p>
	 * Only used for testing!
	 * @param number the maxium number of pages for the current search result
	 */
	public void setMaxPage(int number) {
		maxNumberOfPages = number;
	}
	
	/**
	 * This method starts the NewsCreator.
	 * 
	 * Firstly the config file is read and the paths contained are extracted.
	 * After that the CreatorController {@link #cContr} will be initialized and started.
	 * 
	 * @see application.controller.NewsCreator.CreatorController#start(String)
	 */
	public void run() {
		
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
			Alert alert = new Alert(AlertType.ERROR, "Beim Starten des CreatorControllers ist etwas fehlgeschlagen.\n"
        			+ e.getMessage(), ButtonType.OK);
			alert.showAndWait();
			e.printStackTrace();
		}
		System.out.println("@mainController: told CreatorController to start");
		
	}
	
	
	/**
	 * Flips to the next page of news and refreshes page.
	 */
	public void nextPage() {
		System.out.println("@mainController: Button Next page");
		if(currentPage<maxNumberOfPages) {
			currentPage++;
			mainWindow.showNews(result);
			mainWindow.updatePageButton(currentPage);
		} else {
			//do nothing
		}
		
	}
	
	/**
	 * Flips to the previous page of news and refreshes page.
	 */
	public void previousPage() {
		System.out.println("@mainController: Button Previous page");
		if(currentPage>1) {
			currentPage--;
			mainWindow.showNews(result);
			mainWindow.updatePageButton(currentPage);
		} else {
			//do nothing
		}
			
	}
	
	/**
	 * Takes in the parameters from the view and send them to {@link #mySearch} to search for a news.
	 * 
	 * @param terms keyword(s) to be searched for
	 * @param dates perid to be searched for
	 * @param topics topics to be searched for
	 * @param news news which contains the extracted text
	 * @param n maximum number of news that should be returned
	 */
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
	
	/**
	 * Sends the call to do a text extraction to UserFuntions.
	 * 
	 * @param number index of news where the text is to be extracted
	 * 
	 * @see application.view.UserFunctions
	 */
	public void doTextExtraction(int number) {
		String path = createFile();		
		News news = result.getNews(number);
		System.out.println("@MainController: doTextExtraction");
		UserFunctions.extractText(news, path);
	}
	
	/**
	 * Helping methof to create a file with the FileChooser and return the path of the newly created file.
	 * @return path of the newly created file
	 * 
	 * @see javafx.stage.FileChooser
	 */
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
					Alert alert = new Alert(AlertType.ERROR, "Beim Erstellen einer Datei ist etwas fehlgeschlagen.\n"
	            			+ e.getMessage(), ButtonType.OK);
	    			alert.showAndWait();
	    			e.printStackTrace();
				}
	    		return file.getPath();
	    	}
	        
	    	return null;
	}
	
	/**
	 * Sends the call to open a source to UserFuntions.
	 * 
	 * @param number index of news to be opened
	 * 
	 * @see application.view.UserFunctions
	 */
	public void doSourceOpen(int number) {
		News news = result.getNews(number);
		UserFunctions.openSource(news);
	}

	/**
	 * This method reads the config.txt file and writes its contents into an array of strings.
	 * 
	 * @return a String-array with the contents of config.txt
	 */
	private String[] getPaths() {
		Path path = Paths.get("./src/application/config.txt");
		String[] pathArray = new String[3];
		
		try (BufferedReader reader = Files.newBufferedReader(path)) {
		    String line = null;
		    int i = 0;
		    while ((line = reader.readLine()) != null) {
		        pathArray[i++] = line;
		    }
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR, "Beim Lesen der config-Datei ist etwas fehlgeschlagen.\n"
        			+ e.getMessage(), ButtonType.OK);
			alert.showAndWait();
			e.printStackTrace();		
		}
		
		return pathArray;
	}
	
	/**
	 * This method starts the RSSCrawler in a new process.
	 * 
	 * @see java.lang.ProcessBuilder
	 */
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
			pb.start();
			System.out.println("@mainController: RSScrawler gestartet");
		} catch (Exception e) {
			Alert alert = new Alert(AlertType.ERROR, "Beim Starten des RSSCrawlers mit folgendem Kommando ist etwas fehlgeschlagen:\n"
					+ "java -jar " + rssPath + "\n"
        			+ e.getMessage(), ButtonType.OK);
			alert.showAndWait();
			e.printStackTrace();
		}
	}
	
	/**
	 * This method creates a new file called "stop" in the user directory to stop the RSSCrawler.
	 */
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
				Alert alert = new Alert(AlertType.ERROR, "Beim Erstellen der stop-Datei ist etwas fehlgeschlagen.\n"
						+ "Um de RSSCrawler manuell zu beenden muss ein Datei mit Namen \"stop\" im Nutzerverzeichnis erstellt werden.", ButtonType.OK);
    			alert.showAndWait();
    			e.printStackTrace();
			}
    	}
	}

	/**
	 * This method tells the Creator controller to quit itself.
	 * 
	 * @see CreatorController application.view.MainController.cContr
	 */
	public void stopCController() {
		if(cContr!=null) {
			cContr.closeCreator();
			System.out.println("Creator Controller Stopped");
		}
	}
	
	
}
