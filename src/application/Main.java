package application;

import application.view.*;
import application.controller.NewsCreator.*;
import application.controller.search.LuceneSearch;
import application.model.index.Index;
import application.model.index.LuceneIndex;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.layout.*;


public class Main extends Application {
	
	LuceneIndex myIndex;
	MainController mainController;
	
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
			
			
		    //Add-Source Stage
		    Stage dialogStage = new Stage(StageStyle.UTILITY);
		    dialogStage.initOwner(primaryStage);      
		    dialogStage.initModality(Modality.WINDOW_MODAL);
		    
		    dialogStage.setTitle("Quelle Hinzufügen");      
		    FXMLLoader dialogLoader = new FXMLLoader(AddSourceDialog.class.getResource("AddSourceDialog.fxml"));
		    dialogStage.setScene(new Scene((VBox) dialogLoader.load()));
		    
		    AddSourceDialog addSourceDialog = (AddSourceDialog) dialogLoader.getController();
		    mainWindow.setAddSourceDialogStage(dialogStage);
		    mainWindow.setAddSourceDialogController(addSourceDialog);
		    addSourceDialog.setStage(dialogStage);
		    
		    //Controller und co.
		    mainController = new MainController();
		    mainWindow.setMainController(mainController);
		    mainController.setMainWindow(mainWindow);
		    addSourceDialog.setMainController(mainController);
		    
		    //Set Initial (empty) window state
		    //mainWindow.setInitState();
		    
		    //Search Komponente an mainController weitergeben
		    LuceneSearch mySearch = new LuceneSearch(myIndex);
		    mainController.setIndex(myIndex);
		    mainController.setSearch(mySearch);
		    System.out.println("@Main: View Started and Controller initialized");
		    
		    //NewsCreator starten
		    //(new Thread(new MainController())).start();
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void stop(){
	    System.out.println("Stage is closing");
	    
	    
	    //Index schließen
	    mainController.closeIndex();
	}
	
}
