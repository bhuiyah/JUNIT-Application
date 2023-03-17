package assignment4.gui;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Objects;
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
    @FXML private ListView<String> listView;
    @FXML private TextField searchBar;
    @FXML private Button RunButton;
    @FXML private TextArea runningTests;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        File directory = new File("src/test/");
        String [] contents = directory.list();
        for(int i = 0; i < Objects.requireNonNull(contents).length; i++){
            contents[i] = contents[i].substring(0, contents[i].indexOf(".java"));
        }
//        ListView<String> list = new ListView<>();
//        ObservableList<String> items = FXCollections.observableArrayList (contents);
//        list.setItems(items);
//        list.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
//        listView.getItems().add(String.valueOf(list));
        listView.getItems().addAll(contents);

    }

    //This will take the tests that are selected and place them into the text box
    public void testselect(){
        String selectedTest = listView.getSelectionModel().getSelectedItem();
        if(searchBar.getText().equals("")){
            searchBar.setText(searchBar.getText() + selectedTest);
        }
        else{
            searchBar.setText(searchBar.getText() + " " + selectedTest);
        }
    }

    public void RunButtonSelected(){
//        String[] selectedtest = new String[listView.getSelectionModel().getSelectedItems().size()];
        String [] selectedTest = searchBar.getText().split(" ");
        for(int i = 0; i<selectedTest.length; i++){
            selectedTest[i] = "test." + selectedTest[i];
        }
        runTests(selectedTest);
    }

    public void printToScreen(String text){
        runningTests.setText(runningTests.getText() + text);
    }
}
