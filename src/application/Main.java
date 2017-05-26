package application;

import application.view.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;


public class Main extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader(MainWindow.class.getResource("MainWindow.fxml"));
			BorderPane root = (BorderPane) loader.load();
		    Scene scene = new Scene(root);
		    primaryStage.setScene(scene);
		    primaryStage.setTitle("Nachrichtensuchmaschine");
		    MainWindow mainController = (MainWindow) loader.getController();
		    primaryStage.show();
			
			
			
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
