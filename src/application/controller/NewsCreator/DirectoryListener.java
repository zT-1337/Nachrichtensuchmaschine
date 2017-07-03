/**
 * DirectoryListener
 * 
 * Version: 1.0
 * 
 * Datum: 03.07.2016
 */

package application.controller.NewsCreator;

import static java.nio.file.StandardWatchEventKinds.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.WatchEvent.Kind;
import java.util.ArrayList;

/**
 * Ueberwacht ein Verzeichnis auf Dateiaenderungen.
 * 
 * @author kkaufmann
 * @version 1.0
 *
 */

public class DirectoryListener {
	
	/**
	 * Der Pfad des Verzeichnisses, welches ueberwacht werden soll.
	 */
	String path;
	/**
	 * Die Referenz des WatchServices.
	 */
	WatchService watchService;
	/**
	 * Speicher für den Key der Verzeichnisaktivitaet.
	 */
	WatchKey watchKey;
	
	
	/**
	 * Erstellt einen neuen DirectoryListener
	 * 
	 * @param a_Path Der Pfad des zu ueberwachenden Verzeichnisses.
	 * @throws IOException
	 */
	public DirectoryListener(String a_Path) throws IOException{
		path = a_Path;
		watchService = FileSystems.getDefault().newWatchService();
		watchKey = Paths.get(a_Path).register(watchService, new Kind <?>[] {ENTRY_MODIFY} );
	}
	
	/**
	 * Fuegt die Pfade neuer Notifications einer Liste hinzu.
	 * 
	 * @param a_url Die Liste in die die neuen Pfade geschrieben werden.
	 * @throws InterruptedException
	 */
	public void newNotification(ArrayList<String> a_url) throws InterruptedException{
		
		watchKey = watchService.take();
		 
        for (WatchEvent<?> watchEvent : watchKey.pollEvents()) {
           if(watchEvent.kind().equals(ENTRY_MODIFY)) a_url.add(path + watchEvent.context());
        }
        
        watchKey.reset();
       
	}

}