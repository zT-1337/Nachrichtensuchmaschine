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
			BorderPane root = (BorderPane) loader.load();
		    Scene scene = new Scene(root);
		    primaryStage.setScene(scene);
		    primaryStage.setTitle("Nachrichtensuchmaschine");
		    MainWindow mainWindow = (MainWindow) loader.getController();
		    primaryStage.show();
			
			
		    //Add-Source Stage
		    Stage dialogStage = new Stage(StageStyle.UTILITY);
		    dialogStage.initOwner(primaryStage);      
		    dialogStage.initModality(Modality.WINDOW_MODAL);
		    
		    dialogStage.setTitle("Quelle Hinzufügen");      
		    FXMLLoader dialogLoader = new FXMLLoader(AddSourceDialog.class.getResource("AddSourceDialog.fxml"));      
		    VBox dialogRoot = (VBox) dialogLoader.load();
		    Scene dialogScene = new Scene(dialogRoot);
		    dialogStage.setScene(dialogScene);
		    
		    AddSourceDialog addSourceDialogController = (AddSourceDialog) dialogLoader.getController();
		    mainWindow.setAddSourceDialogStage(dialogStage);
		    mainWindow.setAddSourceDialogController(addSourceDialogController);
		    addSourceDialogController.setStage(dialogStage);
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
