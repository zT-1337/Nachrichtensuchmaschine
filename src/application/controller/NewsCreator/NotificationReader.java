/**
 * NotificationReader
 * 
 * Version: 1.0
 * 
 * Datum: 03.07.2016
 */

package application.controller.NewsCreator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

import java.util.ArrayList;


/**
 * Extrahiert alle Dateipfade, neuer XML-Dateien.
 * 
 * @author Kevin Kaufmann
 *
 * @version 1.0
 */
public class NotificationReader {
	
	/**
	 * Zwischenspeicher fuer die neuen Notificationdateien
	 */
	private File notification;
	
	/**
	 * Referenz fuer einen BufferedReader zum einlesen der Notifications.
	 */
	private BufferedReader input;
	
	/**
	 * Erstellt einen neuen NotificationReader
	 */
	public NotificationReader(){

	}
	
	/**
	 * Extrahiert alle Dateipfade.
	 * 
	 * @param a_url Der Dateipfad der Notification aus der die Dateipfade extrahiert werden soll.
	 * @param a_List Die Liste in die die Dateipfade gespeichert werden.
	 * @throws IOException
	 */
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
