package application.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import javafx.stage.Stage;


public class AddSourceDialog {
	
	private Stage myStage;
	private MainController mainController;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextField name_tf;
    @FXML
    private TextField thema_tf;
    @FXML
    private TextField sprache_tf;
    @FXML
    private TextField land_tf;
    @FXML
    private TextField link_tf;
    


    public void setStage(Stage stage) {
    	myStage = stage;
    }
    
    public void setInitDialog() {
    	land_tf.setText("");
    	link_tf.setText("");
    	name_tf.setText("");
    	sprache_tf.setText("");
    	thema_tf.setText("");
    }
    
    public void setMainController(MainController mainController) {
    	this.mainController = mainController;
    }
    
    
    @FXML
    void cancel(ActionEvent event) {
    	setInitDialog();
    	myStage.hide();
    	System.out.println("cancel AddSourceDialog");
    }

    @FXML
    void finish(ActionEvent event) {
    	if(name_tf.getText().equals("")|| thema_tf.getText().equals("")|| sprache_tf.getText().equals("")|| land_tf.getText().equals("")|| link_tf.getText().equals("")) {
    		Alert alert = new Alert(AlertType.ERROR, "Alle Felder sind Pflichtfelder");
    		alert.showAndWait();
    	} else {
    		//mainController.doSourceAdd(name_tf.getText(), thema_tf.getText(), sprache_tf.getText(), land_tf.getText(), link_tf.getText());
        	myStage.hide();
    	}
    }
    
    @FXML
    void help(ActionEvent event) {
    	land_tf.setText("germany");
    	link_tf.setText("http://www.faz.net/rss/aktuell/beruf-chance/");
    	name_tf.setText("FAZ");
    	sprache_tf.setText("de");
    	thema_tf.setText("career");
    }


    @FXML
    void initialize() {
        assert land_tf != null : "fx:id=\"land_tf\" was not injected: check your FXML file 'AddSourceDialog.fxml'.";
        assert link_tf != null : "fx:id=\"link_tf\" was not injected: check your FXML file 'AddSourceDialog.fxml'.";
        assert name_tf != null : "fx:id=\"name_tf\" was not injected: check your FXML file 'AddSourceDialog.fxml'.";
        assert sprache_tf != null : "fx:id=\"sprache_tf\" was not injected: check your FXML file 'AddSourceDialog.fxml'.";
        assert thema_tf != null : "fx:id=\"thema_tf\" was not injected: check your FXML file 'AddSourceDialog.fxml'.";


    }

}
