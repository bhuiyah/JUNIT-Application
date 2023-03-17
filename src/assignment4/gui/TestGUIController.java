package assignment4.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableArray;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import static assignment4.driver.TestDriver.runTests;
public class TestGUIController implements Initializable{
    //These are for the Listview
    @FXML private ListView listView;
    @FXML private TextField searchBar;
    @FXML private Button RunButton;
    @FXML private TextArea runningTests;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        File directory = new File("src/test/");
        String [] contents = directory.list();
        ListView<String> list = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList (contents);
        list.setItems(items);
        list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        listView.getItems().add(list);
    }

    //This will take the tests that are selected and place them into the text box
    //Doesn't work
    public void testselect(){
        String selectedtest = "";
        ObservableList<String> listoftests = listView.getSelectionModel().getSelectedItems();
        for(Object test : listoftests){
            selectedtest += test.toString();
            selectedtest += " ";
            System.out.println(selectedtest);
        }
        searchBar.setText(selectedtest);
    }

    //Also doesn't work
    public void RunButtonSelected(){
        String[] selectedtest = new String[listView.getSelectionModel().getSelectedItems().size()];
        ObservableList<String> listoftests = listView.getSelectionModel().getSelectedItems();
        for(Object test : listoftests){
            selectedtest[listoftests.indexOf(test)] = test.toString();
        }
        runTests(selectedtest);
    }
}
