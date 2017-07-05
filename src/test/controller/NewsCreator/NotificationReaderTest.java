package test.controller.NewsCreator;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;

import org.junit.*;

import application.controller.NewsCreator.NotificationReader;

public class NotificationReaderTest {

	private NotificationReader reader;
	private ArrayList<String> pathList;
	
	@Before
	public void init(){
		reader = new NotificationReader();
		pathList = new ArrayList<String>();
	}
	
	@Test
	public void testReadFileTestfileOne() {
		String filePath = "C:/Users/kkaufmann/git/Nachrichtensuchmaschine/testfiles/notifications/NotificationTestOne.txt";

		try {
			reader.readFile(filePath, pathList);
		} catch (IOException e) {
			e.printStackTrace();
			fail("Fehlgeschlagenes lesen, IOException");
		}
		
		assertTrue("Richtige anzahl an Pfaden eingelesen", pathList.size() == 21 );
	}
	
	@Test
	public void testReadFileTestfileTwo() {
		String filePath = "C:/Users/kkaufmann/git/Nachrichtensuchmaschine/testfiles/notifications/NotificationTestTwo.txt";

		try {
			reader.readFile(filePath, pathList);
		} catch (IOException e) {
			e.printStackTrace();
			fail("Fehlgeschlagenes lesen, IOException");
		}
		
		assertTrue("Richtige anzahl an Pfaden eingelesen", pathList.size() == 15 );
	}
	
	@Test
	public void testReadFileTestfileThree() {
		String filePath = "C:/Users/kkaufmann/git/Nachrichtensuchmaschine/testfiles/notifications/NotificationTestThree.txt";

		try {
			reader.readFile(filePath, pathList);
		} catch (IOException e) {
			e.printStackTrace();
			fail("Fehlgeschlagenes lesen, IOException");
		}
		
		assertTrue("Richtige anzahl an Pfaden eingelesen", pathList.size() == 20 );
	}
	
	@Test
	public void testReadFileTestfileEmpty() {
		String filePath = "C:/Users/kkaufmann/git/Nachrichtensuchmaschine/testfiles/notifications/NotificationTestEmpty.txt";

		try {
			reader.readFile(filePath, pathList);
		} catch (IOException e) {
			e.printStackTrace();
			fail("Fehlgeschlagenes lesen, IOException");
		}
		
		assertTrue("Richtige anzahl an Pfaden eingelesen", pathList.size() == 0 );
	}

}
