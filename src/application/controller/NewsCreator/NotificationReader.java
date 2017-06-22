package application.controller.NewsCreator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

import java.util.ArrayList;

public class NotificationReader {
	
	private File notification;
	private BufferedReader input;
	
	public NotificationReader(){

	}
	
	public void ReadFile(String a_url, ArrayList<String> a_List) throws IOException{
		try{
			notification = new File(a_url);
			input = new BufferedReader(new FileReader(a_url));
			
			while(input.ready()){
				a_List.add(input.readLine());
				System.out.println("Neue notification : " + a_List.size());
			}
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}
		
	}
	
}
