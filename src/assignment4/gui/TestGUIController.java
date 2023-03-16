package assignment4.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.stage.Stage;
public class TestGUIController implements Initializable{
    //These are for the Listview
    @FXML private ListView listView;

    @Override
    public void initialize(URL url, ResourceBundle rb){
        File directory = new File("src/test/");
        String [] contents = directory.list();
        ListView<String> list = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList (contents);
        list.setItems(items);
        listView.getItems().add(list);
    }
}
