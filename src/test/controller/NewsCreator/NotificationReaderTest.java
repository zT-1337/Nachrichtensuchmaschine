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
	public void testReadFile() {
		String filePath = "";

		try {
			reader.ReadFile(filePath, pathList);
		} catch (IOException e) {
			e.printStackTrace();
			fail("Fehlgeschlagenes lesen, IOException");
		}
		
		fail("Not yet implemented");
	}

}
