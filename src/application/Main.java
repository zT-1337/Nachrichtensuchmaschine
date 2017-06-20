package application;

import application.view.*;
import application.controller.NewsCreator.*;
import application.model.index.Index;
import application.model.index.LuceneIndex;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.layout.*;


public class Main extends Application implements Runnable {
	@Override
	public void start(Stage primaryStage) {
		
		//Start des Crawlers
		new Thread(new Main()).start();
		
		//Index und Search Komponenten initialisieren
		final LuceneIndex myIndex;
		
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
		    MainController mainController = new MainController();
		    mainWindow.setMainController(mainController);
		    mainController.setMainWindow(mainWindow);
		    addSourceDialog.setMainController(mainController);
		    
		    //mainWindow.setInitState();
		    
		    //Search Komponente an mainController weitergeben
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
	
	public void run() {
		//Hier Crawler und co starten
		try {
			String notific = "C:/Users/-Felix/Desktop/Studium/Semester 4/Praktikum Software Entwicklung/RSSArchive/RSS/viewernotification";
			String wordlist = "C:/Users/-Felix/git/Nachrichtensuchmaschine/WordStatisticsLetter1";
			
			CreatorController cContr = new CreatorController(notific, wordlist, myIndex);
		}
		catch (Exception e) {
			//TODO
		}
		
	}
}
