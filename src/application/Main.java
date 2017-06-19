package application;

import application.view.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.scene.Scene;
import javafx.scene.layout.*;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		
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
		    
		    
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
	
		
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
