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

public class DirectoryListener {
	
	String path;
	WatchService watchService;
	WatchKey watchKey;
	
	public DirectoryListener(String a_Path) throws IOException{
		path = a_Path;
		watchService = FileSystems.getDefault().newWatchService();
		watchKey = Paths.get(a_Path).register(watchService, new Kind <?>[] {ENTRY_MODIFY} );
	}
	
	public void newNotification(ArrayList<String> a_url) throws InterruptedException{
		
		watchKey = watchService.take();
		 
        for (WatchEvent<?> watchEvent : watchKey.pollEvents()) {
           if(watchEvent.kind().equals(ENTRY_MODIFY)) a_url.add(path + watchEvent.context());
        }
        
        watchKey.reset();
       
	}

}