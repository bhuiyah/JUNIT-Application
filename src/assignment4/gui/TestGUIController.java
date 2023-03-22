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

    String classpath;
    String path;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        classpath = "src";
        path = "";
        File directory = new File("src/");
        System.out.println(directory.isDirectory());
        String [] contents = directory.list();
        listView.getItems().addAll(contents);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    //This will take the tests that are selected and place them into the text box
    public void testselect(){
        if(listView.getSelectionModel().getSelectedItem().equals("..")){
            String [] pathArray = path.split("\\.");
            path = "";
            for(int i = 0; i < pathArray.length - 1; i++){
                if(i == 0){
                    path += pathArray[i];
                }
                else{
                    path += "." + pathArray[i];
                }
            }
            classpath = classpath.substring(0, classpath.lastIndexOf("/"));
            File classpathFile = new File(classpath);
            classpathFile = new File(classpathFile.getPath());
            String [] contents = classpathFile.list();
            listView.getItems().clear();
            if(classpathFile.getPath().equals("src")){
                listView.getItems().addAll(contents);
            }
            else{
                listView.getItems().add("..");
                listView.getItems().addAll(contents);
            }
        }
        else{
            if(Objects.equals(path, "")){
                path += listView.getSelectionModel().getSelectedItem();
            }
            else{
                path += "." + listView.getSelectionModel().getSelectedItem();
            }
            classpath += "/" + listView.getSelectionModel().getSelectedItem();
            File classpathFile = new File(classpath);
            classpathFile = new File(classpathFile.getPath());
            if(classpathFile.isDirectory()){
                String [] contents = classpathFile.list();
                listView.getItems().clear();
                listView.getItems().add("..");
                for(int i = 0; i < contents.length; i++){
                    if(!new File(classpath + "/" + contents[i]).isDirectory()){
                        contents[i] = contents[i].substring(0, contents[i].lastIndexOf("."));
                    }
                }
                listView.getItems().addAll(contents);
            }
            else{
                if(searchBar.getText().equals("")){
                    searchBar.setText(path);
                    path = path.substring(0, path.lastIndexOf("."));
                    classpath = classpath.substring(0, classpath.lastIndexOf("/"));
                }
                else{
                    searchBar.setText(searchBar.getText() + " " + path);
                    path = path.substring(0, path.lastIndexOf("."));
                    classpath = classpath.substring(0, classpath.lastIndexOf("/"));
                }
            }
        }
    }

    public void RunButtonSelected() throws IOException {
        runningTests.clear();
        String [] selectedTest = searchBar.getText().split(" ");
        GUITestListener guiTestListener = new GUITestListener(this);
        addListener(guiTestListener);
        runTests(selectedTest);
    }

    public void ReRunButtonSelected() throws IOException {
        runningTests.clear();
        String [] selectedTest = searchBar.getText().split(" ");
        GUITestListener guiTestListener = new GUITestListener(this);
        addListener(guiTestListener);
        runTests(selectedTest);
    }

    public void ResetButtonSelected() throws IOException {
        //reset classpath and path
        //clear the listview
        listView.getItems().clear();
        classpath = "src";
        path = "";
        File directory = new File("src/");
        String [] contents = directory.list();
        listView.getItems().addAll(contents);
        searchBar.clear();
        runningTests.clear();
    }

    public void StopButtonSelected() throws IOException {
        Stage stage = (Stage) StopButton.getScene().getWindow();
        stage.close();
    }

    //get the textarea
    public TextArea getTextArea(){
        return runningTests;
    }

}