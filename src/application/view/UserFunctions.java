package application.view;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import application.model.news.News;

public class UserFunctions {
	
	//Entwurf 9. extractText(...)
	public static void extractText(News news, String path) {
		try {
			FileWriter fw = new FileWriter(new File(path));
			String text = news.getText();
			fw.write(text,0,text.length());
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Entwurf 9. addSource(...)
	public static void addSource(String name, String thema, String sprache, String land, String link) {
		try {
			FileWriter fw = new FileWriter(new File("C:/Users/-Felix/Desktop/"+name+".xml"));
			
			String textToWrite;
			textToWrite = "<?xml version=\"1.0\" encoding=\"ASCII\"?>"+System.lineSeparator()
					+ System.lineSeparator()
					+ "<sites"+System.lineSeparator()
					+ "  xmlns=\"http://www.iisys.de/mmis/rssapp\""+System.lineSeparator()
					+ "  xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\""+System.lineSeparator()
					+ "  xsi:schemaLocation=\"http://www.iisys.de/mmis/rssapp RSSFeeds.xsd\">"+System.lineSeparator()
					+ ""+System.lineSeparator()
					+ "  <address ttl=\"2\" topic=\""+thema+"\" country=\""+land+"\" language=\""+sprache+"\" link=\""+link+"\"/>"		
					+ ""+System.lineSeparator()
					+ "</sites>";
			
			fw.write(textToWrite);
			fw.flush();
			fw.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	//Entwurf 9. openSource(...)
	public static void openSource(News news) {
		String source = news.getSource();
		
		if(Desktop.isDesktopSupported())
		{
			try {
				Desktop.getDesktop().browse(new URI(source));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (URISyntaxException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	
	
}
