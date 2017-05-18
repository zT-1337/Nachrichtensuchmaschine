package application.view;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;


public class MainController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private MenuItem beenden_menu_item;

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
    private MenuItem quelle_hinzufuegen_menu_item;

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
    private Button silimar_button_1;

    @FXML
    private Button silimar_button_10;

    @FXML
    private Button silimar_button_2;

    @FXML
    private Button silimar_button_3;

    @FXML
    private Button silimar_button_4;

    @FXML
    private Button silimar_button_5;

    @FXML
    private Button silimar_button_6;

    @FXML
    private Button silimar_button_7;

    @FXML
    private Button silimar_button_8;

    @FXML
    private Button silimar_button_9;

    @FXML
    private TextField stichwort_label;

    @FXML
    private Button suche_button;

    @FXML
    private TextField thema_label;

    @FXML
    private Label titel_label_1;

    @FXML
    private Label titel_label_10;

    @FXML
    private Label titel_label_2;

    @FXML
    private Label titel_label_3;

    @FXML
    private Label titel_label_4;

    @FXML
    private Label titel_label_5;

    @FXML
    private Label titel_label_6;

    @FXML
    private Label titel_label_7;

    @FXML
    private Label titel_label_8;

    @FXML
    private Label titel_label_9;

    @FXML
    private TextField zeitraum_label;


    @FXML
    void initialize() {
        assert beenden_menu_item != null : "fx:id=\"beenden_menu_item\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert cache_button_1 != null : "fx:id=\"cache_button_1\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert cache_button_10 != null : "fx:id=\"cache_button_10\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert cache_button_2 != null : "fx:id=\"cache_button_2\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert cache_button_3 != null : "fx:id=\"cache_button_3\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert cache_button_4 != null : "fx:id=\"cache_button_4\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert cache_button_5 != null : "fx:id=\"cache_button_5\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert cache_button_6 != null : "fx:id=\"cache_button_6\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert cache_button_7 != null : "fx:id=\"cache_button_7\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert cache_button_8 != null : "fx:id=\"cache_button_8\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert cache_button_9 != null : "fx:id=\"cache_button_9\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert datum_label_1 != null : "fx:id=\"datum_label_1\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert datum_label_10 != null : "fx:id=\"datum_label_10\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert datum_label_2 != null : "fx:id=\"datum_label_2\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert datum_label_3 != null : "fx:id=\"datum_label_3\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert datum_label_4 != null : "fx:id=\"datum_label_4\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert datum_label_5 != null : "fx:id=\"datum_label_5\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert datum_label_6 != null : "fx:id=\"datum_label_6\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert datum_label_7 != null : "fx:id=\"datum_label_7\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert datum_label_8 != null : "fx:id=\"datum_label_8\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert datum_label_9 != null : "fx:id=\"datum_label_9\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert page_button_1 != null : "fx:id=\"page_button_1\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert page_button_2 != null : "fx:id=\"page_button_2\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert page_button_3 != null : "fx:id=\"page_button_3\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert page_button_4 != null : "fx:id=\"page_button_4\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert page_button_5 != null : "fx:id=\"page_button_5\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert page_label_1 != null : "fx:id=\"page_label_1\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert page_label_2 != null : "fx:id=\"page_label_2\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert quelle_hinzufuegen_menu_item != null : "fx:id=\"quelle_hinzufuegen_menu_item\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert quelle_label_1 != null : "fx:id=\"quelle_label_1\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert quelle_label_10 != null : "fx:id=\"quelle_label_10\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert quelle_label_2 != null : "fx:id=\"quelle_label_2\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert quelle_label_3 != null : "fx:id=\"quelle_label_3\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert quelle_label_4 != null : "fx:id=\"quelle_label_4\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert quelle_label_5 != null : "fx:id=\"quelle_label_5\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert quelle_label_6 != null : "fx:id=\"quelle_label_6\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert quelle_label_7 != null : "fx:id=\"quelle_label_7\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert quelle_label_8 != null : "fx:id=\"quelle_label_8\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert quelle_label_9 != null : "fx:id=\"quelle_label_9\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert silimar_button_1 != null : "fx:id=\"silimar_button_1\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert silimar_button_10 != null : "fx:id=\"silimar_button_10\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert silimar_button_2 != null : "fx:id=\"silimar_button_2\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert silimar_button_3 != null : "fx:id=\"silimar_button_3\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert silimar_button_4 != null : "fx:id=\"silimar_button_4\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert silimar_button_5 != null : "fx:id=\"silimar_button_5\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert silimar_button_6 != null : "fx:id=\"silimar_button_6\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert silimar_button_7 != null : "fx:id=\"silimar_button_7\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert silimar_button_8 != null : "fx:id=\"silimar_button_8\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert silimar_button_9 != null : "fx:id=\"silimar_button_9\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert stichwort_label != null : "fx:id=\"stichwort_label\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert suche_button != null : "fx:id=\"suche_button\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert thema_label != null : "fx:id=\"thema_label\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert titel_label_1 != null : "fx:id=\"titel_label_1\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert titel_label_10 != null : "fx:id=\"titel_label_10\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert titel_label_2 != null : "fx:id=\"titel_label_2\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert titel_label_3 != null : "fx:id=\"titel_label_3\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert titel_label_4 != null : "fx:id=\"titel_label_4\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert titel_label_5 != null : "fx:id=\"titel_label_5\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert titel_label_6 != null : "fx:id=\"titel_label_6\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert titel_label_7 != null : "fx:id=\"titel_label_7\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert titel_label_8 != null : "fx:id=\"titel_label_8\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert titel_label_9 != null : "fx:id=\"titel_label_9\" was not injected: check your FXML file 'MainFXML.fxml'.";
        assert zeitraum_label != null : "fx:id=\"zeitraum_label\" was not injected: check your FXML file 'MainFXML.fxml'.";


    }

}
