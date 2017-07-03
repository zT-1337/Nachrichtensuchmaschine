/**
 * CreatorController
 * 
 * Version: 1.0
 * 
 * Datum: 3.07.2017
 */

package application.controller.NewsCreator;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Map;
import java.util.StringTokenizer;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.ParserConfigurationException;

import application.controller.NewsCreator.*;
import application.controller.search.LuceneSearch;
import application.model.index.LuceneIndex;
import application.model.index.ResultIndex;
import application.model.news.News;
import application.model.news.NewsLuceneAdapter;
import application.model.newsresult.NewsResult;
import application.model.newsresult.NewsResultLuceneAdapter;
import application.model.news.*;

/**
 * Koordiniert alle Komponenten zum erzeugen neuer Newsobjekte. Startet den XML_Parser, DirectoryListener und NotifivationListener.
 * 
 * @author Kevin Kaufmann
 * @version 1.0
 */

public class CreatorController {
	
	/**
	 * Das Verzeichnis indem die Notifications, des RSS-Crawlers, stehen.
	 */
	private String directory;
	
	/**
	 * Eine Map, die die Wortliste repräsentiert
	 */
	private Map<String,AtomicInteger> wordlist;
	
	/**
	 * Wert der zeigt ob die start-methode gerade arbeitet.
	 */
	private volatile boolean alive;
	
	
	/**
	 * Zwischenspeicher einer neuen News die in den Index geschrieben wird.
	 */
	private NewsLuceneAdapter news;
	
	/**
	 * Referenz für den verwendeten XML_Parser, zum parsen neuer Files.
	 */
	private XML_Parser parser;
	
	/**
	 * Referenz für den verwendeten NotificationReader, zum extrahieren der Dateipfade.
	 */
	private NotificationReader reader;
	
	/**
	 * Referenz für den verwendeten DirectoryListener, zum überwachen eines Dateiverzeichnisses.
	 */
	private DirectoryListener listener;
	
	
	/**
	 * Zwischensprecher für alle Inhalte einer neuen News.
	 */
	private ArrayList<String> newsContent = new ArrayList<String>();
	
	/**
	 * Speicher aller Dateipfade neuer XML-Dateien, die eingelesen werden müssen.
	 */
	private ArrayList<String> xmlFiles = new ArrayList<String>();
	
	/**
	 * Speicher für neue Viewernotification die vom DirectoryListener gefunden wurden.
	 */
	private ArrayList<String> notifications = new ArrayList<String>();
	
	/**
	 * Zwischenspeicher für neu erstellt News, bevor Sie überprüft werden auf neue News.
	 */
	private ArrayList<News> createdNews = new ArrayList<News>();
	
	/**
	 * Zwischenspeicher aller neuen News, die dem Index übergeben werden.
	 */
	private ArrayList<News> newNewsList = new ArrayList<News>();
	
	
	/**
	 * Referenz für einen Index, in den die News geschrieben werden.
	 */
	private LuceneIndex index;
	
	/**
	 * Zwischenspeicher für News, die endgültig in den Index übernommen werden.
	 */
	private NewsLuceneAdapter newNews;
	
	/**
	 * Referenz für einen NewsResult, der nach eine Nachrichtensuche erhalten wird.
	 */
	private NewsResult newsResult;
	
	/**
	 * Referenz für den Suchkomponente, die benoetigt wird um nach News zu suchen.
	 */
	private LuceneSearch search;
	
	/**
	 * 
	 * Erzeugt einen neuen CreatorController.
	 * 
	 * @param a_NotificationPath Dateipfad, des Ordners indem die Viewernotification, des RSS-Crawler geschrieben werden.
	 * @param a_WordlistPath Dateipfad, der verwendeten Wortliste.
	 * @param a_Index Index in den die neuen Newsobjekte geschrieben werden.
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	public CreatorController(String a_NotificationPath,String a_WordlistPath, LuceneIndex a_Index) throws ParserConfigurationException, IOException, ClassNotFoundException{
		parser = new XML_Parser();
		directory = a_NotificationPath;
		listener = new DirectoryListener(a_NotificationPath);
		reader = new NotificationReader();
		index = a_Index;
		search = new LuceneSearch(a_Index);
		createWordlist(a_WordlistPath);
		alive = true;
	}
	
	/**
	 * Startet das hinzufuegen neuer News in den Index.
	 * 
	 * @param directory - Das zu ueberwachende Verzeichnis.
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void start(String directory) throws InterruptedException, IOException{
		while(alive){
			System.out.println("Schleifenanfang");
			newsContent.clear();
			xmlFiles.clear();
			notifications.clear();
			newNewsList.clear();
			createdNews.clear();
			System.out.println("Listen geleert");
			listener.newNotification(notifications);
			Thread.sleep(2000);
			for(int i = 0; i < notifications.size(); i++){
				reader.ReadFile(notifications.get(i), xmlFiles);
			}
			System.out.println("Erstellte xml : " + xmlFiles.size());
			for(int i = 0; i < xmlFiles.size(); i++){
				newsContent.clear();
				parser.parse(xmlFiles.get(i), newsContent);
				System.out.println(xmlFiles.get(i));
				newsContent.add(xmlFiles.get(i));
				createDate(newsContent);
				if(newsContent.size() == 6){
					newsContent.add(doReduceString(newsContent.get(2)));
					createTopic(xmlFiles.get(i));
					newNews = createNews(newsContent);
					createdNews.add(newNews);
				}
			}
			for(int i = 0; i < createdNews.size(); i++){
				if(!isExist(createdNews.get(i))) newNewsList.add(createdNews.get(i));
			}
			System.out.println(newNewsList.size());
			if(newNewsList.size() > 0) {
				index.addNews(newNewsList);
				System.out.println("Hinzugefuegt " + newNewsList.size());
			}
			
		}
	}
	
	/**
	 * Wandelt einen uebergebenen String in das, fuer eine Suche, 
	 * 
	 * @param a_List - die zu uebergebende Liste(MewsContent) indem das Nachrichtendatum an Stelle 1 steht.
	 */
	private void createDate(ArrayList<String> a_List){
		String date = a_List.get(1);
		
		Pattern dateRegEx = Pattern.compile( "\\d{2}\\D{5}\\d{4}" );
		Matcher matcher = dateRegEx.matcher(date);
		
		if(matcher.find()){
			date = matcher.group();
			
			if(date.substring(3, 6).equals("Jan")) date = date.substring(0, 2) + "." + "01" + "." + date.substring(date.length()-4);
			if(date.substring(3, 6).equals("Feb")) date = date.substring(0, 2) + "." + "02" + "." + date.substring(date.length()-4);
			if(date.substring(3, 6).equals("Mar")) date = date.substring(0, 2) + "." + "03" + "." + date.substring(date.length()-4);
			if(date.substring(3, 6).equals("Apr")) date = date.substring(0, 2) + "." + "04" + "." + date.substring(date.length()-4);
			if(date.substring(3, 6).equals("May")) date = date.substring(0, 2) + "." + "05" + "." + date.substring(date.length()-4);
			if(date.substring(3, 6).equals("Jun")) date = date.substring(0, 2) + "." + "06" + "." + date.substring(date.length()-4);
			if(date.substring(3, 6).equals("Jul")) date = date.substring(0, 2) + "." + "07" + "." + date.substring(date.length()-4);
			if(date.substring(3, 6).equals("Aug")) date = date.substring(0, 2) + "." + "08" + "." + date.substring(date.length()-4);
			if(date.substring(3, 6).equals("Sep")) date = date.substring(0, 2) + "." + "09" + "." + date.substring(date.length()-4);
			if(date.substring(3, 6).equals("Oct")) date = date.substring(0, 2) + "." + "10" + "." + date.substring(date.length()-4);
			if(date.substring(3, 6).equals("Nov")) date = date.substring(0, 2) + "." + "11" + "." + date.substring(date.length()-4);
			if(date.substring(3, 6).equals("Dec")) date = date.substring(0, 2) + "." + "12" + "." + date.substring(date.length()-4);
			
			a_List.set(1, date);
			
		}else{
			a_List.remove(1);
		}
	}
	
	/**
	 * Extrahiert aus einem uebergebenden Dateipfad das Topic.
	 * 
	 * @param a_String Dateipfad aus dem das Tpic extrahiert werden soll.
	 */
	private void createTopic(String a_String){
		Pattern topicRegEx = Pattern.compile( "((/\\w*){4}/)\\w*");
		String reverse = new StringBuilder(a_String).reverse().toString();
		Matcher matcher = topicRegEx.matcher(reverse);
		String topic = null;
		if(matcher.find()){
			reverse = matcher.group();
			reverse = new StringBuilder(reverse).reverse().toString();
			topic = reverse.substring(0, reverse.indexOf('/'));
		}
		System.out.println(topic);
		newsContent.add(topic);
		
	}
	
	/**
	 * Erzeugt eine neue News
	 * 
	 * @param a_List Eine Liste(NewsContent) in der alle Inhalte der zu erstellenden News stehen.
	 * @return
	 */
	private NewsLuceneAdapter createNews(ArrayList<String> a_List){
		news = new NewsLuceneAdapter();
		
		news.setSource(a_List.get(0));
		news.setPubDate(a_List.get(3));
		news.setText(a_List.get(4));
		news.setTitle(a_List.get(1));
		news.setURL(a_List.get(2));
		news.setReducedText(a_List.get(6));
		news.setTopic(a_List.get(7));
		
		return news;
	}
	
	/**
	 * Ueberprueft ob eine News bereicht im Index vorhanden ist.
	 * 
	 * @param a_News die News die ueberprueft werden soll.
	 * @return
	 */
	private boolean isExist(News a_News){
		System.out.println(a_News.getReducedText());
		newsResult = search.search("", "", "", newNews.getReducedText(), 1);
		if((newsResult.getSize() != 0) && (newsResult.getScore(0) >= NewsSimilarity.equal))return true;
		
		return false;
	}

	/**
	 * Laedt die Wortliste in den Speicher und stellt Sie zur verfuegung.
	 * 
	 * @param a_Path Der Dateipfad, der zu verwendenden Wortliste.
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws ClassNotFoundException
	 */
	private void createWordlist(String a_Path) throws FileNotFoundException, IOException, ClassNotFoundException{
		File wordlistFile = new File(a_Path);
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(wordlistFile));
		wordlist = (Map<String, AtomicInteger>) ois.readObject();
	}
	
	/**
	 * Reduziert einen uebergebenen String auf seine bedeutenden Woerter.
	 * 
	 * @param a_String Der zu reduzierende String.
	 * @return Der String enthaelt nur noch die bedeutenden Woerter des ausgangs Strings.
	 */
	private String doReduceString(String a_String){
		StringTokenizer tokenizer = new StringTokenizer(a_String);
		ArrayList<String> allWords = new ArrayList<String>();
		ArrayList<String> reduceWords = new ArrayList<String>();
		boolean isExist = false;
		String token = "";
		String reduceText = "";
		
		while(tokenizer.hasMoreTokens()){
			isExist = false;
			token = tokenizer.nextToken();
			//System.out.println(token);
			for(int i = 0; i < allWords.size(); i++){
				if(allWords.get(i).equals(token)) isExist = true;
			}
			
			if(!isExist)allWords.add(token);
		}
		System.out.println("Enthaltene Woerter : " + allWords.size());
		for(int i = 0; i < allWords.size(); i++){
			allWords.set(i, allWords.get(i).toLowerCase());
		}
		
		for(int i = 1; i < allWords.size(); i++){
			
			if((wordlist.get(allWords.get(i)) == null)||(wordlist.get(allWords.get(i)).intValue() == 250)){
				reduceWords.add(allWords.get(i));
			}
			
		}
		//System.out.println("Reduzierten Wörter : " + reduceWords.size());
		for(int i = 0; i < reduceWords.size(); i++){
			reduceText = reduceText + reduceWords.get(i) + " ";
		}
		//System.out.println(reduceText);
		return  reduceText;
	}
	
	/**
	 * Stopt das hinzufürgen neuer Newsobjekte.
	 */
	public void closeCreator(){
		alive = false;
		parser = null;
		index = null;
		listener = null;
		search = null;
		wordlist = null;
		news = null;
		newsContent = null;
		xmlFiles = null;
		notifications = null;
		newNews = null;
		newsResult = null;
		createdNews = null;
		newNewsList = null;
		alive = false;
	}
	
}
