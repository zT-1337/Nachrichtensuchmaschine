package application.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;


public class MainWindow {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem add_source;

    @FXML
    private Button cache_button_1;

    @FXML
    private Button cache_button_10;

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
    private Label datum_label_1;

    @FXML
    private Label datum_label_10;

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
    private Button page_button_1;

    @FXML
    private Button page_button_2;

    @FXML
    private Button page_button_3;

    @FXML
    private Button page_button_4;

    @FXML
    private Button page_button_5;

    @FXML
    private Label page_label_1;

    @FXML
    private Label page_label_2;

    @FXML
    private Label quelle_label_1;

    @FXML
    private Label quelle_label_10;

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
    private MenuItem quit_programm;

    @FXML
    private Button search_button_press;

    @FXML
    private Button similar_button_1;

    @FXML
    private Button similar_button_10;

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
    private TextField stichwort_tf;

    @FXML
    private TextField thema_tf;

    @FXML
    private Label title_label_1;

    @FXML
    private Label title_label_10;

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
    private TextField zeitraum_tf;


    @FXML
    void add_source_press(ActionEvent event) {
    	//open new dialog window
    }

    @FXML
    void cache_button_10_press(ActionEvent event) {
    }

    @FXML
    void cache_button_1_press(ActionEvent event) {
    }

    @FXML
    void cache_button_2_press(ActionEvent event) {
    }

    @FXML
    void cache_button_3_press(ActionEvent event) {
    }

    @FXML
    void cache_button_4_press(ActionEvent event) {
    }

    @FXML
    void cache_button_5_press(ActionEvent event) {
    }

    @FXML
    void cache_button_6_press(ActionEvent event) {
    }

    @FXML
    void cache_button_7_press(ActionEvent event) {
    }

    @FXML
    void cache_button_8_press(ActionEvent event) {
    }

    @FXML
    void cache_button_9_press(ActionEvent event) {
    }

    @FXML
    void page_button_1_press(ActionEvent event) {
    }

    @FXML
    void page_button_2_press(ActionEvent event) {
    }

    @FXML
    void page_button_3_press(ActionEvent event) {
    }

    @FXML
    void page_button_5_press(ActionEvent event) {
    }

    @FXML
    void quit(ActionEvent event) {
    }

    @FXML
    void search_button_press(ActionEvent event) {
    	MainController.doSearch(stichwort_tf.getText(), thema_tf.getText(), zeitraum_tf.getText(), "");
    	
    }

    @FXML
    void similar_button_10_press(ActionEvent event) {
    }

    @FXML
    void similar_button_1_press(ActionEvent event) {
    }

    @FXML
    void similar_button_2_press(ActionEvent event) {
    }

    @FXML
    void similar_button_3_press(ActionEvent event) {
    }

    @FXML
    void similar_button_4_press(ActionEvent event) {
    }

    @FXML
    void similar_button_5_press(ActionEvent event) {
    }

    @FXML
    void similar_button_6_press(ActionEvent event) {
    }

    @FXML
    void similar_button_7_press(ActionEvent event) {
    }

    @FXML
    void similar_button_8_press(ActionEvent event) {
    }

    @FXML
    void similar_button_9_press(ActionEvent event) {
    }

    @FXML
    void title_label_10_click(MouseEvent event) {
    }

    @FXML
    void title_label_1_click(MouseEvent event) {
    }

    @FXML
    void title_label_2_click(MouseEvent event) {
    }

    @FXML
    void title_label_3_click(MouseEvent event) {
    }

    @FXML
    void title_label_4_click(MouseEvent event) {
    }

    @FXML
    void title_label_5_click(MouseEvent event) {
    }

    @FXML
    void title_label_6_click(MouseEvent event) {
    }

    @FXML
    void title_label_7_click(MouseEvent event) {
    }

    @FXML
    void title_label_8_click(MouseEvent event) {
    }

    @FXML
    void title_label_9_click(MouseEvent event) {
    }

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
        assert page_button_1 != null : "fx:id=\"page_button_1\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert page_button_2 != null : "fx:id=\"page_button_2\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert page_button_3 != null : "fx:id=\"page_button_3\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert page_button_4 != null : "fx:id=\"page_button_4\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert page_button_5 != null : "fx:id=\"page_button_5\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert page_label_1 != null : "fx:id=\"page_label_1\" was not injected: check your FXML file 'MainWindow.fxml'.";
        assert page_label_2 != null : "fx:id=\"page_label_2\" was not injected: check your FXML file 'MainWindow.fxml'.";
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
        assert zeitraum_tf != null : "fx:id=\"zeitraum_label\" was not injected: check your FXML file 'MainWindow.fxml'.";


    }

}
