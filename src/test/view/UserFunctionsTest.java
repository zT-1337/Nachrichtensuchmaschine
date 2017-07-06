package test.view;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import application.model.news.News;
import application.model.news.NewsLuceneAdapter;
import application.view.UserFunctions;

public class UserFunctionsTest {
	
	News news;
	String source;
	String text;
	
	@Before
	public void setUp() {
		source = "google.com";
		text = "text of this news";
		
		news = new NewsLuceneAdapter();
		news.setSource(source);
		news.setText(text);
		
	}
	
	@After
	public void tearDown() {
		File file = new File("./testfiles/UserFunctions/extractexText.txt");
		if(file != null) {
			file.delete();
		}
	}

	@Test
	public void extractTextTest() {
		String result = "";
		File file = new File("./testfiles/UserFunctions/extractexText.txt");
		String path = file.getPath();
		
		UserFunctions.extractText(news, path);
		
		try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
		    result = reader.readLine();
		} catch (IOException e) {
			
		}
		
		assertEquals(text, result);
	}
	
	@Test
	@Ignore ("Keine Möglichkeit für test gefunden")
	public void openSourceTest() {
		fail("Not implemented");
	}
	
	
	
}
