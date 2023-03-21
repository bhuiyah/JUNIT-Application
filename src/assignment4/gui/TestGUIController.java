package assignment4.gui;

import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

import assignment4.listeners.GUITestListener;
import assignment4.listeners.PrintToScreen;
import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;
import javafx.application.Platform;
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
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.beans.value.ObservableValue;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import static assignment4.driver.TestDriver.addListener;
import static assignment4.driver.TestDriver.runTests;

public class TestGUIController implements Initializable{
    //These are for the Listview
    @FXML private ListView<String> listView;
    @FXML private TextField searchBar;
    @FXML private Button RunButton;
    @FXML private TextArea runningTests;
    @FXML private Button RerunButton;

    @FXML private Button StopButton;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        File directory = new File("src/test/");
        String [] contents = directory.list();
        for(int i = 0; i < Objects.requireNonNull(contents).length; i++){
            contents[i] = contents[i].substring(0, contents[i].indexOf(".java"));
        }
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

    public void RunButtonSelected() throws IOException {
        runningTests.clear();
        String [] selectedTest = searchBar.getText().split(" ");
        for(int i = 0; i<selectedTest.length; i++){
            selectedTest[i] = "test." + selectedTest[i];
        }
        GUITestListener guiTestListener = new GUITestListener(this);
        addListener(guiTestListener);
        runTests(selectedTest);
    }

    public void ReRunButtonSelected() throws IOException {
        runningTests.clear();
        String [] selectedTest = searchBar.getText().split(" ");
        for(int i = 0; i<selectedTest.length; i++){
            selectedTest[i] = "test." + selectedTest[i];
        }
        GUITestListener guiTestListener = new GUITestListener(this);
        addListener(guiTestListener);
        runTests(selectedTest);
    }

    public void ResetButtonSelected() throws IOException {
        searchBar.clear();
        runningTests.clear();
    }

    public void StopButtonSelected() throws IOException {
        Stage stage = (Stage) StopButton.getScene().getWindow();
        stage.close();
    }

    public void printToScreen(String text){
        runningTests.appendText(text);
    }

    //get the textarea
    public TextArea getTextArea(){
        return runningTests;
    }

}