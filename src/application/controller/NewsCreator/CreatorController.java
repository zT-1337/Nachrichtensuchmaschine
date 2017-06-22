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

public class CreatorController {
	
	private String directory;
	private Map<String,AtomicInteger> wordlist;
	
	private NewsLuceneAdapter news;
	private XML_Parser parser;
	private NotificationReader reader;
	private DirectoryListener listener;
	
	private ArrayList<String> newsContent = new ArrayList<String>();
	private ArrayList<String> xmlFiles = new ArrayList<String>();
	private ArrayList<String> notifications = new ArrayList<String>();
	private ArrayList<News> createdNews = new ArrayList<News>();
	private ArrayList<News> newNewsList = new ArrayList<News>();
	
	private LuceneIndex index;
	private NewsLuceneAdapter newNews;
	private NewsResult newsResult;
	private LuceneSearch search;
	
	public CreatorController(String a_NotificationPath,String a_WordlistPath, LuceneIndex a_Index) throws ParserConfigurationException, IOException, ClassNotFoundException{
		parser = new XML_Parser();
		directory = a_NotificationPath;
		listener = new DirectoryListener(a_NotificationPath);
		reader = new NotificationReader();
		index = a_Index;
		search = new LuceneSearch(a_Index);
		createWordlist(a_WordlistPath);
	}
	
	public void start(String directory) throws InterruptedException, IOException{
		while(true){
			System.out.println("Schleifenanfang");
			clearArrayList(newsContent);
			clearArrayList(xmlFiles);
			clearArrayList(notifications);
			System.out.println("Listen geleert");
			listener.newNotification(notifications);
			Thread.sleep(2000);
			for(int i = 0; i < notifications.size(); i++){
				reader.ReadFile(notifications.get(i), xmlFiles);
			}
			System.out.println("Erstellte xml : " + xmlFiles.size());
			for(int i = 0; i < xmlFiles.size(); i++){
				clearArrayList(newsContent);
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
			if(newNewsList.size() > 0) index.addNews(newNewsList);
			
		}
	}
	
	private void clearArrayList(ArrayList<?> a_List){
		while(a_List.size() != 0){
			a_List.remove(0);
		}
	}
	
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
	
	private void createTopic(String a_String){
		Pattern topicRegEx = Pattern.compile( "((/\\w*){3}/)\\w*");
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
	
	private NewsLuceneAdapter createNews(ArrayList<String> a_List){
		news = new NewsLuceneAdapter();
		
		news.setSource(a_List.get(0));
		news.setPubDate(a_List.get(1));
		news.setText(a_List.get(2));
		news.setTitle(a_List.get(3));
		news.setURL(a_List.get(4));
		news.setReducedText(a_List.get(6));
		news.setTopic(a_List.get(7));
		
		return news;
	}
	
	private boolean isExist(News a_News){
		System.out.println(a_News.getReducedText());
		newsResult = search.search("", "", "", newNews.getReducedText(), 1);
		if((newsResult.getSize() != 0) && (newsResult.getScore(0) >= 0.9f))return true;
		
		return false;
	}
	
	private void createWordlist(String a_Path) throws FileNotFoundException, IOException, ClassNotFoundException{
		File wordlistFile = new File(a_Path);
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(wordlistFile));
		wordlist = (Map<String, AtomicInteger>) ois.readObject();
	}
	
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
			String key = allWords.get(i);
			
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
	
}
