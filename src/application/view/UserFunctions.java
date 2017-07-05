package application.view;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import application.model.news.News;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

public class UserFunctions {
	
	//Entwurf 9. extractText(...)
	/**
	 * This method takes a News-object and a static path to create a file at the specified path.
	 * The content of the file is the full-length text extracted from the News-object.
	 * 
	 * @param news	a News Object
	 * @param path	the static path where to save the file
	 */
	public static void extractText(News news, String path) {
		try {
			if(path != null) {
				FileWriter fw = new FileWriter(new File(path));
				String text = news.getText();
				fw.write(text,0,text.length());
				fw.flush();
				fw.close();
			}
		} catch (IOException e) {
			Alert alert = new Alert(AlertType.ERROR, "Beim schreiben der Datei ist etwas fehlgschlagen.\n"
					+ e.getMessage(), ButtonType.OK);
			alert.showAndWait();
			e.printStackTrace();
		}
	}
	
	//Entwurf 9. openSource(...)
	/**
	 * Windows: Takes a News-Object and opens its source in the users' standard browser via the Desktop {@link java.awt.Desktop.getDesktop()#desktop} class.
	 * <p>
	 * Linux: Takes a News-Object and opens its source in the users' standard browser via the 'xdg-open' command.
	 * 
	 * @param news	a single News-object
	 */
	public static void openSource(News news) {
		String source = news.getURL();
		System.out.println("@UserFunctions: opening source: " + source);
		
		if(Desktop.isDesktopSupported()){
            Desktop desktop = Desktop.getDesktop();
            try {
                desktop.browse(new URI(source));
            } catch (IOException e) {
            	Alert alert = new Alert(AlertType.ERROR, "Beim öffnen der Quelle ist etwas fehlgeschlagen.\n"
            			+ e.getMessage(), ButtonType.OK);
    			alert.showAndWait();
    			e.printStackTrace();
            } catch(URISyntaxException e) {
            	Alert alert = new Alert(AlertType.ERROR, "Beim öffnen der Quelle ist etwas fehlgeschlagen.\n"
            			+ e.getMessage(), ButtonType.OK);
    			alert.showAndWait();
    			e.printStackTrace();
            }
        } else {
            Runtime runtime = Runtime.getRuntime();
            try {
                runtime.exec("xdg-open " + source);
            } catch (IOException e) {
            	Alert alert = new Alert(AlertType.ERROR, "Beim öffnen der Quelle ist etwas fehlgeschlagen.\n"
            			+ e.getMessage(), ButtonType.OK);
    			alert.showAndWait();
    			e.printStackTrace();
            }
        }
	}
	
	
	
	
}
