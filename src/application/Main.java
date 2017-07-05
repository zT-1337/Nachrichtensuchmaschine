package application;

import application.view.*;
import application.controller.search.LuceneSearch;
import application.model.index.LuceneIndex;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;

/**
*
* @author  Felix Bahr
*/
public class Main extends Application {
	
	LuceneIndex myIndex;
	MainController mainController;
	
	/**
	 * This method prepares the primary launch of the application.
	 */
	@Override
	public void start(Stage primaryStage) {
		
		//Index und Search Komponenten initialisieren
		myIndex = new LuceneIndex();
		
		//JavaFX
		try {
			//Main-Stage
			FXMLLoader loader = new FXMLLoader(MainWindow.class.getResource("MainWindow.fxml"));
		    primaryStage.setScene(new Scene((BorderPane) loader.load()));
		    primaryStage.setTitle("Nachrichtensuchmaschine");
		    MainWindow mainWindow = (MainWindow) loader.getController();
		    primaryStage.show();
		    
		    //Controller und co.
		    mainController = new MainController();
		    mainWindow.setMainController(mainController);
		    mainController.setMainWindow(mainWindow);
		    
		    //Set Initial (empty) window state
		    mainWindow.setInitState();
		    
		    //Search Komponente an mainController weitergeben
		    LuceneSearch mySearch = new LuceneSearch(myIndex);
		    mainController.setIndex(myIndex);
		    mainController.setSearch(mySearch);
		    System.out.println("@Main: View Started and Controller initialized");
		    
		    //NewsCreator starten
		    (new Thread(mainController)).start();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		mainController.startCrawler();
		
	}
	
	/**
	 * Entry point of this application.
	 * @param args null
	 */
	public static void main(String[] args) {
		launch(args);
	}
	
	/**
	 * This method is invoked when the user closes the main application window.
	 * It tells all components to quit themselves.
	 * @see MainController application.Main.mainController#closeIndex()
	 * @see MainController application.Main.mainController#stopCrawler()
	 * @see MainController application.Main.mainController#stopCController() 
	 */
	@Override
	public void stop(){
	    System.out.println("@Main: Stage is closing");
	    
	    //Index schlieﬂen
	    mainController.closeIndex();
	    System.out.println("@Main: Prompted Index to close.");
	    
	    //Crawler beenden
	    mainController.stopCrawler();
	    System.out.println("@Main: Prompted RSSCrawler to stop.");
	    
	    //NewsCreator beenden
	    mainController.stopCController();
	    System.out.println("@Main: Prompted CreatorController to stop.");
	}
	
}
