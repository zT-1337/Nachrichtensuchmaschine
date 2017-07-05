package application.view;

import java.net.URL;
import java.util.ResourceBundle;

import application.model.newsresult.NewsResult;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
*
* @author  Felix Bahr
*/
public class MainWindow {
	
	private MainController mainController;	

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;
    
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
    
    /**
	 * Sets the local variable {@link #mainController} with the parameter "mainController" for later use.
	 * 
	 * @param mainController an instance of MainController
	 * 
	 * @see MainController application.view.MainWindow.mainController
	 */
    public void setMainController(MainController mainController) {
    	this.mainController = mainController;
    }
    
    /**
     * This method prepares the main window by hiding unneeded elements.
     */
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
    /**
     * This method displays news in the main window.
     * 
     * @param result the result of a search
     */
    public void showNews(NewsResult result) {
    	if(result.getNews(mainController.getNewsWithPage(1))==null) {
    		news_Box_1.setVisible(false);
    		news_Box_1.setManaged(false);
    	} else {
    		news_Box_1.setVisible(true);
    		news_Box_1.setManaged(true);
    		title_label_1.setText(result.getNews(mainController.getNewsWithPage(1)).getTitle());
    		quelle_label_1.setText(result.getNews(mainController.getNewsWithPage(1)).getSource());
    		datum_label_1.setText(result.getNews(mainController.getNewsWithPage(1)).getPubDate());
    	}
    	
    	if(result.getNews(mainController.getNewsWithPage(2))==null) {
    		news_Box_2.setVisible(false);
    		news_Box_2.setManaged(false);
    	} else {
    		news_Box_2.setVisible(true);
    		news_Box_2.setManaged(true);
    		title_label_2.setText(result.getNews(mainController.getNewsWithPage(2)).getTitle());
    		quelle_label_2.setText(result.getNews(mainController.getNewsWithPage(2)).getSource());
    		datum_label_2.setText(result.getNews(mainController.getNewsWithPage(2)).getPubDate());
    	}
    	
    	if(result.getNews(mainController.getNewsWithPage(3))==null) {
    		news_Box_3.setVisible(false);
    		news_Box_3.setManaged(false);
    	} else {
    		news_Box_3.setVisible(true);
    		news_Box_3.setManaged(true);
    		title_label_3.setText(result.getNews(mainController.getNewsWithPage(3)).getTitle());
    		quelle_label_3.setText(result.getNews(mainController.getNewsWithPage(3)).getSource());
    		datum_label_3.setText(result.getNews(mainController.getNewsWithPage(3)).getPubDate());
    	}
    	
    	if(result.getNews(mainController.getNewsWithPage(4))==null) {
    		news_Box_4.setVisible(false);
    		news_Box_4.setManaged(false);
    	} else {
    		news_Box_4.setVisible(true);
    		news_Box_4.setManaged(true);
    		title_label_4.setText(result.getNews(mainController.getNewsWithPage(4)).getTitle());
    		quelle_label_4.setText(result.getNews(mainController.getNewsWithPage(4)).getSource());
    		datum_label_4.setText(result.getNews(mainController.getNewsWithPage(4)).getPubDate());
    	}
    	
    	if(result.getNews(mainController.getNewsWithPage(5))==null) {
    		news_Box_5.setVisible(false);
    		news_Box_5.setManaged(false);
    	} else {
    		news_Box_5.setVisible(true);
    		news_Box_5.setManaged(true);
    		title_label_5.setText(result.getNews(mainController.getNewsWithPage(5)).getTitle());
    		quelle_label_5.setText(result.getNews(mainController.getNewsWithPage(5)).getSource());
    		datum_label_5.setText(result.getNews(mainController.getNewsWithPage(5)).getPubDate());
    	}
    	
    	if(result.getNews(mainController.getNewsWithPage(6))==null) {
    		news_Box_6.setVisible(false);
    		news_Box_6.setManaged(false);
    	} else {
    		news_Box_6.setVisible(true);
    		news_Box_6.setManaged(true);
    		title_label_6.setText(result.getNews(mainController.getNewsWithPage(6)).getTitle());
    		quelle_label_6.setText(result.getNews(mainController.getNewsWithPage(6)).getSource());
    		datum_label_6.setText(result.getNews(mainController.getNewsWithPage(6)).getPubDate());
    	}
    	
    	if(result.getNews(mainController.getNewsWithPage(7))==null) {
    		news_Box_7.setVisible(false);
    		news_Box_7.setManaged(false);
    	} else {
    		news_Box_7.setVisible(true);
    		news_Box_7.setManaged(true);
    		title_label_7.setText(result.getNews(mainController.getNewsWithPage(7)).getTitle());
    		quelle_label_7.setText(result.getNews(mainController.getNewsWithPage(7)).getSource());
    		datum_label_7.setText(result.getNews(mainController.getNewsWithPage(7)).getPubDate());
    	}
    	
    	if(result.getNews(mainController.getNewsWithPage(8))==null) {
    		news_Box_8.setVisible(false);
    		news_Box_8.setManaged(false);
    	} else {
    		news_Box_8.setVisible(true);
    		news_Box_8.setManaged(true);
    		title_label_8.setText(result.getNews(mainController.getNewsWithPage(8)).getTitle());
    		quelle_label_8.setText(result.getNews(mainController.getNewsWithPage(8)).getSource());
    		datum_label_8.setText(result.getNews(mainController.getNewsWithPage(8)).getPubDate());
    	}
    	
    	if(result.getNews(mainController.getNewsWithPage(9))==null) {
    		news_Box_9.setVisible(false);
    		news_Box_9.setManaged(false);
    	} else {
    		news_Box_9.setVisible(true);
    		news_Box_9.setManaged(true);
    		title_label_9.setText(result.getNews(mainController.getNewsWithPage(9)).getTitle());
    		quelle_label_9.setText(result.getNews(mainController.getNewsWithPage(9)).getSource());
    		datum_label_9.setText(result.getNews(mainController.getNewsWithPage(9)).getPubDate());
    	}
    	
    	if(result.getNews(mainController.getNewsWithPage(10))==null) {
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
    

    
    //Cache Buttons
    /**
     * Prompts the mainControler to do a text extraction on the specified news.
     * @param event JavaFX event
     * @see MainController application.view.MainWindow.mainController
     */
    @FXML
    void cache_button_1_press(ActionEvent event) {
    	mainController.doTextExtraction(mainController.getNewsWithPage(1));
    }
    @FXML
    void cache_button_2_press(ActionEvent event) {
    	mainController.doTextExtraction(mainController.getNewsWithPage(2));
    }    
    @FXML
    void cache_button_3_press(ActionEvent event) {
    	mainController.doTextExtraction(mainController.getNewsWithPage(3));
    }    
    @FXML
    void cache_button_4_press(ActionEvent event) {
    	mainController.doTextExtraction(mainController.getNewsWithPage(4));
    }    
    @FXML
    void cache_button_5_press(ActionEvent event) {
    	mainController.doTextExtraction(mainController.getNewsWithPage(5));
    }    
    @FXML
    void cache_button_6_press(ActionEvent event) {
    	mainController.doTextExtraction(mainController.getNewsWithPage(6));
    }    
    @FXML
    void cache_button_7_press(ActionEvent event) {
    	mainController.doTextExtraction(mainController.getNewsWithPage(7));
    }    
    @FXML
    void cache_button_8_press(ActionEvent event) {
    	mainController.doTextExtraction(mainController.getNewsWithPage(8));
    }    
    @FXML
    void cache_button_9_press(ActionEvent event) {
    	mainController.doTextExtraction(mainController.getNewsWithPage(9));
    }    
    @FXML
    void cache_button_10_press(ActionEvent event) {
    	mainController.doTextExtraction(mainController.getNewsWithPage(10));
    }    
    
    
    //Similar Buttons
    /**
     * Prompts the mainControler to do a similar search on the specified news.
     * @param event JavaFX event
     * @see MainController application.view.MainWindow.mainController
     */
    @FXML
    void similar_button_1_press(ActionEvent event) {
    	mainController.doSearch(stichwort_tf.getText(), zeitraum_tf.getText(), thema_tf.getText(), mainController.getNews(1).getReducedText(), 100);
    }
    @FXML
    void similar_button_2_press(ActionEvent event) {
    	mainController.doSearch(stichwort_tf.getText(), zeitraum_tf.getText(), thema_tf.getText(), mainController.getNews(2).getReducedText(), 100);
    }
    @FXML
    void similar_button_3_press(ActionEvent event) {
    	mainController.doSearch(stichwort_tf.getText(), zeitraum_tf.getText(), thema_tf.getText(), mainController.getNews(3).getReducedText(), 100);
    }
    @FXML
    void similar_button_4_press(ActionEvent event) {
    	mainController.doSearch(stichwort_tf.getText(), zeitraum_tf.getText(), thema_tf.getText(), mainController.getNews(4).getReducedText(), 100);
    }
    @FXML
    void similar_button_5_press(ActionEvent event) {
    	mainController.doSearch(stichwort_tf.getText(), zeitraum_tf.getText(), thema_tf.getText(), mainController.getNews(5).getReducedText(), 100);
    }
    @FXML
    void similar_button_6_press(ActionEvent event) {
    	mainController.doSearch(stichwort_tf.getText(), zeitraum_tf.getText(), thema_tf.getText(), mainController.getNews(6).getReducedText(), 100);
    }
    @FXML
    void similar_button_7_press(ActionEvent event) {
    	mainController.doSearch(stichwort_tf.getText(), zeitraum_tf.getText(), thema_tf.getText(), mainController.getNews(7).getReducedText(), 100);
    }
    @FXML
    void similar_button_8_press(ActionEvent event) {
    	mainController.doSearch(stichwort_tf.getText(), zeitraum_tf.getText(), thema_tf.getText(), mainController.getNews(8).getReducedText(), 100);
    }
    @FXML
    void similar_button_9_press(ActionEvent event) {
    	mainController.doSearch(stichwort_tf.getText(), zeitraum_tf.getText(), thema_tf.getText(), mainController.getNews(9).getReducedText(), 100);
    }
    @FXML
    void similar_button_10_press(ActionEvent event) {
    	mainController.doSearch(stichwort_tf.getText(), zeitraum_tf.getText(), thema_tf.getText(), mainController.getNews(10).getReducedText(), 100);
    }
    
    
    @FXML
    /**
     * Fills the text fields for the search with some examples.
     * 
     * @param event JavaFX event
     */
    void help(ActionEvent event) {
    	stichwort_tf.setText("ein paar tolle Stichwörter für die Suche");
    	thema_tf.setText("computer");
    	zeitraum_tf.setText("01.05.2017-20.05.2017");
    }

    @FXML
    /**
     * Not in use.
     * @param event JavaFX event
     */
    void page_button_current_press(ActionEvent event) {
    	//do Nothing
    }
    
    /**
     * Updates the number on the {@link #page_button_current} with "number".
     * @param number the number to be displayed by the button
     */
    public void updatePageButton(int number) {
    	page_button_current.setText(number+"");
    }
    
    /**
     * Prompts the mainControler to display the next page of news.
     * @param event JavaFX event
     * @see MainController application.view.MainWindow.mainController
     */
    @FXML
    void page_button_next_press(ActionEvent event) {
    	mainController.nextPage();
    }
    
    /**
     * Prompts the mainControler to display the previous page of news.
     * @param event JavaFX event
     * @see MainController application.view.MainWindow.mainController
     */
    @FXML
    void page_button_previous_press(ActionEvent event) {
    	mainController.previousPage();
    }
    
    /**
     * Prompts the mainControler to search by the values in the text fields {@link #stichwort_tf}, {@link #zeitraum_tf} and {@link #thema_tf}.
     * @param event JavaFX event
     * @see MainController application.view.MainWindow.mainController
     */
    @FXML
    void search_button_press(ActionEvent event) {
    	mainController.doSearch(stichwort_tf.getText(), zeitraum_tf.getText(), thema_tf.getText(), "", 100);
    }
    
    /**
     * Prompts the mainControler to open the source page on the specified news.
     * @param event JavaFX event
     * @see MainController application.view.MainWindow.mainController
     */
    @FXML
    void title_label_1_click(MouseEvent event) {
    	mainController.doSourceOpen(mainController.getNewsWithPage(1));}
    @FXML
    void title_label_2_click(MouseEvent event) {
    	mainController.doSourceOpen(mainController.getNewsWithPage(2));}
    @FXML
    void title_label_3_click(MouseEvent event) {
    	mainController.doSourceOpen(mainController.getNewsWithPage(3));}
    @FXML
    void title_label_4_click(MouseEvent event) {
    	mainController.doSourceOpen(mainController.getNewsWithPage(4));}
    @FXML
    void title_label_5_click(MouseEvent event) {
    	mainController.doSourceOpen(mainController.getNewsWithPage(5));}
    @FXML
    void title_label_6_click(MouseEvent event) {
    	mainController.doSourceOpen(mainController.getNewsWithPage(6));}
    @FXML
    void title_label_7_click(MouseEvent event) {
    	mainController.doSourceOpen(mainController.getNewsWithPage(7));}
    @FXML
    void title_label_8_click(MouseEvent event) {
    	mainController.doSourceOpen(mainController.getNewsWithPage(8));}
    @FXML
    void title_label_9_click(MouseEvent event) {
    	mainController.doSourceOpen(mainController.getNewsWithPage(9));}
    @FXML
    void title_label_10_click(MouseEvent event) {
    	mainController.doSourceOpen(mainController.getNewsWithPage(10));}
    
    /**
     * Auto generated JavaFX method.
     */
    @FXML
    void initialize() {
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