package application.view;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import application.model.newsresult.NewsResult;
import application.view.AddSourceDialog;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class MainWindow {
	
	private Stage addSourceDialogStage;
	private MainController mainController;
	private AddSourceDialog addSourceDialogController;
	
	private NewsResult result;
	
	private Stage fileStage;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem add_source;
    @FXML
    private MenuItem quit_programm;
    
    @FXML
    private Button search_button_press;

    @FXML
    private Button cache_button_1;
    @FXML
    private Button cache_button_2;
    @FXML
    private Button cache_button_3;
    @FXML
    private Button cache_button_4;
    @FXML
    private Button cache_button_5;
    @FXML
    private Button cache_button_6;
    @FXML
    private Button cache_button_7;
    @FXML
    private Button cache_button_8;
    @FXML
    private Button cache_button_9;
    @FXML
    private Button cache_button_10;

    @FXML
    private Label datum_label_1;
    @FXML
    private Label datum_label_2;
    @FXML
    private Label datum_label_3;
    @FXML
    private Label datum_label_4;
    @FXML
    private Label datum_label_5;
    @FXML
    private Label datum_label_6;
    @FXML
    private Label datum_label_7;
    @FXML
    private Label datum_label_8;
    @FXML
    private Label datum_label_9;
    @FXML
    private Label datum_label_10;

    @FXML
    private Button page_button_current;
    @FXML
    private Button page_button_next;
    @FXML
    private Button page_button_previous;
    
    @FXML
    private Label quelle_label_1;
    @FXML
    private Label quelle_label_2;
    @FXML
    private Label quelle_label_3;
    @FXML
    private Label quelle_label_4;
    @FXML
    private Label quelle_label_5;
    @FXML
    private Label quelle_label_6;
    @FXML
    private Label quelle_label_7;
    @FXML
    private Label quelle_label_8;
    @FXML
    private Label quelle_label_9;
    @FXML
    private Label quelle_label_10;

    @FXML
    private Button similar_button_1;
    @FXML
    private Button similar_button_2;
    @FXML
    private Button similar_button_3;
    @FXML
    private Button similar_button_4;
    @FXML
    private Button similar_button_5;
    @FXML
    private Button similar_button_6;
    @FXML
    private Button similar_button_7;
    @FXML
    private Button similar_button_8;
    @FXML
    private Button similar_button_9;
    @FXML
    private Button similar_button_10;


    @FXML
    private Label title_label_1;
    @FXML
    private Label title_label_2;
    @FXML
    private Label title_label_3;
    @FXML
    private Label title_label_4;
    @FXML
    private Label title_label_5;
    @FXML
    private Label title_label_6;
    @FXML
    private Label title_label_7;
    @FXML
    private Label title_label_8;
    @FXML
    private Label title_label_9;
    @FXML
    private Label title_label_10;
    
    
    @FXML
    private VBox news_Box_1;
    @FXML
    private VBox news_Box_2;
    @FXML
    private VBox news_Box_3;
    @FXML
    private VBox news_Box_4;
    @FXML
    private VBox news_Box_5;
    @FXML
    private VBox news_Box_6;
    @FXML
    private VBox news_Box_7;
    @FXML
    private VBox news_Box_8;
    @FXML
    private VBox news_Box_9;
    @FXML
    private VBox news_Box_10;
    
    @FXML
    private HBox page_box;
    
    @FXML
    private TextField stichwort_tf;
    @FXML
    private TextField thema_tf;
    @FXML
    private TextField zeitraum_tf;
    
    
    

    
    
    //View and Controller Setup
    public void setAddSourceDialogStage(Stage addSourceDialogStage) {
    	this.addSourceDialogStage = addSourceDialogStage;
    }

    public void setAddSourceDialogController(AddSourceDialog addSourceDialogController) {
    	this.addSourceDialogController = addSourceDialogController;
    }
    
    public void setMainController(MainController mainController) {
    	this.mainController = mainController;
    }
    
    //alle News Boxen unsichtbar machen
    public void setInitState() {
    	news_Box_1.setVisible(false);
		news_Box_1.setManaged(false);
		news_Box_2.setVisible(false);
		news_Box_2.setManaged(false);
		news_Box_3.setVisible(false);
		news_Box_3.setManaged(false);
		news_Box_4.setVisible(false);
		news_Box_4.setManaged(false);
		news_Box_5.setVisible(false);
		news_Box_5.setManaged(false);
		news_Box_6.setVisible(false);
		news_Box_6.setManaged(false);
		news_Box_7.setVisible(false);
		news_Box_7.setManaged(false);
		news_Box_8.setVisible(false);
		news_Box_8.setManaged(false);
		news_Box_9.setVisible(false);
		news_Box_9.setManaged(false);
		news_Box_10.setVisible(false);
		news_Box_10.setManaged(false);
    }
    
    
    //Entwurf Seite 15 News Anzeigen
    public void showNews(NewsResult result) {
    	if(result.getNews(1)==null) {
    		news_Box_1.setVisible(false);
    		news_Box_1.setManaged(false);
    	} else {
    		news_Box_1.setVisible(true);
    		news_Box_1.setManaged(true);
    		title_label_1.setText(result.getNews(mainController.getNewsWithPage(1)).getTitle());
    		quelle_label_1.setText(result.getNews(mainController.getNewsWithPage(1)).getSource());
    		datum_label_1.setText(result.getNews(mainController.getNewsWithPage(1)).getPubDate());
    	}
    	
    	if(result.getNews(2)==null) {
    		news_Box_2.setVisible(false);
    		news_Box_2.setManaged(false);
    	} else {
    		news_Box_2.setVisible(true);
    		news_Box_2.setManaged(true);
    		title_label_2.setText(result.getNews(mainController.getNewsWithPage(2)).getTitle());
    		quelle_label_2.setText(result.getNews(mainController.getNewsWithPage(2)).getSource());
    		datum_label_2.setText(result.getNews(mainController.getNewsWithPage(2)).getPubDate());
    	}
    	
    	if(result.getNews(3)==null) {
    		news_Box_3.setVisible(false);
    		news_Box_3.setManaged(false);
    	} else {
    		news_Box_3.setVisible(true);
    		news_Box_3.setManaged(true);
    		title_label_3.setText(result.getNews(mainController.getNewsWithPage(3)).getTitle());
    		quelle_label_3.setText(result.getNews(mainController.getNewsWithPage(3)).getSource());
    		datum_label_3.setText(result.getNews(mainController.getNewsWithPage(3)).getPubDate());
    	}
    	
    	if(result.getNews(4)==null) {
    		news_Box_4.setVisible(false);
    		news_Box_4.setManaged(false);
    	} else {
    		news_Box_4.setVisible(true);
    		news_Box_4.setManaged(true);
    		title_label_4.setText(result.getNews(mainController.getNewsWithPage(4)).getTitle());
    		quelle_label_4.setText(result.getNews(mainController.getNewsWithPage(4)).getSource());
    		datum_label_4.setText(result.getNews(mainController.getNewsWithPage(4)).getPubDate());
    	}
    	
    	if(result.getNews(5)==null) {
    		news_Box_5.setVisible(false);
    		news_Box_5.setManaged(false);
    	} else {
    		news_Box_5.setVisible(true);
    		news_Box_5.setManaged(true);
    		title_label_5.setText(result.getNews(mainController.getNewsWithPage(5)).getTitle());
    		quelle_label_5.setText(result.getNews(mainController.getNewsWithPage(5)).getSource());
    		datum_label_5.setText(result.getNews(mainController.getNewsWithPage(5)).getPubDate());
    	}
    	
    	if(result.getNews(6)==null) {
    		news_Box_6.setVisible(false);
    		news_Box_6.setManaged(false);
    	} else {
    		news_Box_6.setVisible(true);
    		news_Box_6.setManaged(true);
    		title_label_6.setText(result.getNews(mainController.getNewsWithPage(6)).getTitle());
    		quelle_label_6.setText(result.getNews(mainController.getNewsWithPage(6)).getSource());
    		datum_label_6.setText(result.getNews(mainController.getNewsWithPage(6)).getPubDate());
    	}
    	
    	if(result.getNews(7)==null) {
    		news_Box_7.setVisible(false);
    		news_Box_7.setManaged(false);
    	} else {
    		news_Box_7.setVisible(true);
    		news_Box_7.setManaged(true);
    		title_label_7.setText(result.getNews(mainController.getNewsWithPage(7)).getTitle());
    		quelle_label_7.setText(result.getNews(mainController.getNewsWithPage(7)).getSource());
    		datum_label_7.setText(result.getNews(mainController.getNewsWithPage(7)).getPubDate());
    	}
    	
    	if(result.getNews(8)==null) {
    		news_Box_8.setVisible(false);
    		news_Box_8.setManaged(false);
    	} else {
    		news_Box_8.setVisible(true);
    		news_Box_8.setManaged(true);
    		title_label_8.setText(result.getNews(mainController.getNewsWithPage(8)).getTitle());
    		quelle_label_8.setText(result.getNews(mainController.getNewsWithPage(8)).getSource());
    		datum_label_8.setText(result.getNews(mainController.getNewsWithPage(8)).getPubDate());
    	}
    	
    	if(result.getNews(9)==null) {
    		news_Box_9.setVisible(false);
    		news_Box_9.setManaged(false);
    	} else {
    		news_Box_9.setVisible(true);
    		news_Box_9.setManaged(true);
    		title_label_9.setText(result.getNews(mainController.getNewsWithPage(9)).getTitle());
    		quelle_label_9.setText(result.getNews(mainController.getNewsWithPage(9)).getSource());
    		datum_label_9.setText(result.getNews(mainController.getNewsWithPage(9)).getPubDate());
    	}
    	
    	if(result.getNews(10)==null) {
    		news_Box_10.setVisible(false);
    		news_Box_10.setManaged(false);
    	} else {
    		news_Box_10.setVisible(true);
    		news_Box_10.setManaged(true);
    		title_label_10.setText(result.getNews(mainController.getNewsWithPage(10)).getTitle());
    		quelle_label_10.setText(result.getNews(mainController.getNewsWithPage(10)).getSource());
    		datum_label_10.setText(result.getNews(mainController.getNewsWithPage(10)).getPubDate());
    	}
    	
    }
    
    
    
    //Quelle hinzufügen
    @FXML
    void add_source_press(ActionEvent event) {
    	//open new dialog window
    	//addSourceDialogController.setInitDialog();
    	//addSourceDialogStage.showAndWait();
    }

    
    //Cache Buttons
    @FXML
    void cache_button_1_press(ActionEvent event) {
    	mainController.doTextExtraction(1);
    }
    @FXML
    void cache_button_2_press(ActionEvent event) {
    	mainController.doTextExtraction(2);
    }    
    @FXML
    void cache_button_3_press(ActionEvent event) {
    	mainController.doTextExtraction(3);
    }    
    @FXML
    void cache_button_4_press(ActionEvent event) {
    	mainController.doTextExtraction(4);
    }    
    @FXML
    void cache_button_5_press(ActionEvent event) {
    	mainController.doTextExtraction(5);
    }    
    @FXML
    void cache_button_6_press(ActionEvent event) {
    	mainController.doTextExtraction(6);
    }    
    @FXML
    void cache_button_7_press(ActionEvent event) {
    	mainController.doTextExtraction(7);
    }    
    @FXML
    void cache_button_8_press(ActionEvent event) {
    	mainController.doTextExtraction(8);
    }    
    @FXML
    void cache_button_9_press(ActionEvent event) {
    	mainController.doTextExtraction(9);
    }    
    @FXML
    void cache_button_10_press(ActionEvent event) {
    	mainController.doTextExtraction(10);
    }    
    
    @FXML
    void similar_button_1_press(ActionEvent event) {
    	mainController.doSearch(stichwort_tf.getText(), thema_tf.getText(), zeitraum_tf.getText(), result.getNews(mainController.getNewsWithPage(1)).getReducedText(), 100);
    }
    @FXML
    void similar_button_2_press(ActionEvent event) {
    	mainController.doSearch(stichwort_tf.getText(), thema_tf.getText(), zeitraum_tf.getText(), result.getNews(mainController.getNewsWithPage(2)).getReducedText(), 100);
    }
    @FXML
    void similar_button_3_press(ActionEvent event) {
    	mainController.doSearch(stichwort_tf.getText(), thema_tf.getText(), zeitraum_tf.getText(), result.getNews(mainController.getNewsWithPage(3)).getReducedText(), 100);
    }
    @FXML
    void similar_button_4_press(ActionEvent event) {
    	mainController.doSearch(stichwort_tf.getText(), thema_tf.getText(), zeitraum_tf.getText(), result.getNews(mainController.getNewsWithPage(4)).getReducedText(), 100);
    }
    @FXML
    void similar_button_5_press(ActionEvent event) {
    	mainController.doSearch(stichwort_tf.getText(), thema_tf.getText(), zeitraum_tf.getText(), result.getNews(mainController.getNewsWithPage(5)).getReducedText(), 100);
    }
    @FXML
    void similar_button_6_press(ActionEvent event) {
    	mainController.doSearch(stichwort_tf.getText(), thema_tf.getText(), zeitraum_tf.getText(), result.getNews(mainController.getNewsWithPage(6)).getReducedText(), 100);
    }
    @FXML
    void similar_button_7_press(ActionEvent event) {
    	mainController.doSearch(stichwort_tf.getText(), thema_tf.getText(), zeitraum_tf.getText(), result.getNews(mainController.getNewsWithPage(7)).getReducedText(), 100);
    }
    @FXML
    void similar_button_8_press(ActionEvent event) {
    	mainController.doSearch(stichwort_tf.getText(), thema_tf.getText(), zeitraum_tf.getText(), result.getNews(mainController.getNewsWithPage(8)).getReducedText(), 100);
    }
    @FXML
    void similar_button_9_press(ActionEvent event) {
    	mainController.doSearch(stichwort_tf.getText(), thema_tf.getText(), zeitraum_tf.getText(), result.getNews(mainController.getNewsWithPage(9)).getReducedText(), 100);
    }
    @FXML
    void similar_button_10_press(ActionEvent event) {
    	mainController.doSearch(stichwort_tf.getText(), thema_tf.getText(), zeitraum_tf.getText(), result.getNews(mainController.getNewsWithPage(10)).getReducedText(), 100);
    }
    
    
    @FXML
    void help(ActionEvent event) {
    	stichwort_tf.setText("ein paar tolle Stichwörter");
    	thema_tf.setText("career");
    	zeitraum_tf.setText("TODO zeitraum");
    }

    @FXML
    void page_button_current_press(ActionEvent event) {
    	//do Nothing
    }
    
    public void updatePageButton(int number) {
    	page_button_current.setText(number+"");
    }

    @FXML
    void page_button_next_press(ActionEvent event) {
    	mainController.nextPage();
    }

    @FXML
    void page_button_previous_press(ActionEvent event) {
    	mainController.previousPage();
    }
    
    
    @FXML
    void quit(ActionEvent event) {
    }

    @FXML
    void search_button_press(ActionEvent event) {
    	mainController.doSearch(stichwort_tf.getText(), thema_tf.getText(), zeitraum_tf.getText(), "", 100);
    }

    

    

    @FXML
    void title_label_1_click(MouseEvent event) {
    	mainController.doSourceOpen(1);}
    @FXML
    void title_label_2_click(MouseEvent event) {
    	mainController.doSourceOpen(2);}
    @FXML
    void title_label_3_click(MouseEvent event) {
    	mainController.doSourceOpen(3);}
    @FXML
    void title_label_4_click(MouseEvent event) {
    	mainController.doSourceOpen(4);}
    @FXML
    void title_label_5_click(MouseEvent event) {
    	mainController.doSourceOpen(5);}
    @FXML
    void title_label_6_click(MouseEvent event) {
    	mainController.doSourceOpen(6);}
    @FXML
    void title_label_7_click(MouseEvent event) {
    	mainController.doSourceOpen(7);}
    @FXML
    void title_label_8_click(MouseEvent event) {
    	mainController.doSourceOpen(8);}
    @FXML
    void title_label_9_click(MouseEvent event) {
    	mainController.doSourceOpen(9);}
    @FXML
    void title_label_10_click(MouseEvent event) {
    	mainController.doSourceOpen(10);}

    @FXML
    void initialize() {
        assert add_source != null : "fx:id=\"add_source\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert cache_button_1 != null : "fx:id=\"cache_button_1\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert cache_button_10 != null : "fx:id=\"cache_button_10\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert cache_button_2 != null : "fx:id=\"cache_button_2\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert cache_button_3 != null : "fx:id=\"cache_button_3\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert cache_button_4 != null : "fx:id=\"cache_button_4\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert cache_button_5 != null : "fx:id=\"cache_button_5\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert cache_button_6 != null : "fx:id=\"cache_button_6\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert cache_button_7 != null : "fx:id=\"cache_button_7\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert cache_button_8 != null : "fx:id=\"cache_button_8\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert cache_button_9 != null : "fx:id=\"cache_button_9\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert datum_label_1 != null : "fx:id=\"datum_label_1\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert datum_label_10 != null : "fx:id=\"datum_label_10\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert datum_label_2 != null : "fx:id=\"datum_label_2\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert datum_label_3 != null : "fx:id=\"datum_label_3\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert datum_label_4 != null : "fx:id=\"datum_label_4\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert datum_label_5 != null : "fx:id=\"datum_label_5\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert datum_label_6 != null : "fx:id=\"datum_label_6\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert datum_label_7 != null : "fx:id=\"datum_label_7\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert datum_label_8 != null : "fx:id=\"datum_label_8\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert datum_label_9 != null : "fx:id=\"datum_label_9\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert news_Box_1 != null : "fx:id=\"news_Box_1\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert news_Box_10 != null : "fx:id=\"news_Box_10\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert news_Box_2 != null : "fx:id=\"news_Box_2\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert news_Box_3 != null : "fx:id=\"news_Box_3\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert news_Box_4 != null : "fx:id=\"news_Box_4\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert news_Box_5 != null : "fx:id=\"news_Box_5\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert news_Box_6 != null : "fx:id=\"news_Box_6\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert news_Box_7 != null : "fx:id=\"news_Box_7\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert news_Box_8 != null : "fx:id=\"news_Box_8\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert news_Box_9 != null : "fx:id=\"news_Box_9\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert page_box != null : "fx:id=\"page_box\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert page_button_current != null : "fx:id=\"page_button_current\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert page_button_next != null : "fx:id=\"page_button_next\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert page_button_previous != null : "fx:id=\"page_button_previous\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert quelle_label_1 != null : "fx:id=\"quelle_label_1\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert quelle_label_10 != null : "fx:id=\"quelle_label_10\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert quelle_label_2 != null : "fx:id=\"quelle_label_2\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert quelle_label_3 != null : "fx:id=\"quelle_label_3\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert quelle_label_4 != null : "fx:id=\"quelle_label_4\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert quelle_label_5 != null : "fx:id=\"quelle_label_5\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert quelle_label_6 != null : "fx:id=\"quelle_label_6\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert quelle_label_7 != null : "fx:id=\"quelle_label_7\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert quelle_label_8 != null : "fx:id=\"quelle_label_8\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert quelle_label_9 != null : "fx:id=\"quelle_label_9\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert quit_programm != null : "fx:id=\"quit_programm\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert search_button_press != null : "fx:id=\"search_button_press\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert similar_button_1 != null : "fx:id=\"similar_button_1\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert similar_button_10 != null : "fx:id=\"similar_button_10\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert similar_button_2 != null : "fx:id=\"similar_button_2\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert similar_button_3 != null : "fx:id=\"similar_button_3\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert similar_button_4 != null : "fx:id=\"similar_button_4\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert similar_button_5 != null : "fx:id=\"similar_button_5\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert similar_button_6 != null : "fx:id=\"similar_button_6\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert similar_button_7 != null : "fx:id=\"similar_button_7\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert similar_button_8 != null : "fx:id=\"similar_button_8\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert similar_button_9 != null : "fx:id=\"similar_button_9\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert stichwort_tf != null : "fx:id=\"stichwort_tf\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert thema_tf != null : "fx:id=\"thema_tf\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert title_label_1 != null : "fx:id=\"title_label_1\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert title_label_10 != null : "fx:id=\"title_label_10\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert title_label_2 != null : "fx:id=\"title_label_2\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert title_label_3 != null : "fx:id=\"title_label_3\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert title_label_4 != null : "fx:id=\"title_label_4\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert title_label_5 != null : "fx:id=\"title_label_5\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert title_label_6 != null : "fx:id=\"title_label_6\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert title_label_7 != null : "fx:id=\"title_label_7\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert title_label_8 != null : "fx:id=\"title_label_8\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert title_label_9 != null : "fx:id=\"title_label_9\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert zeitraum_tf != null : "fx:id=\"zeitraum_tf\" was not injected: check your FXML file 'MainWindow.fxml'.";


    }

}