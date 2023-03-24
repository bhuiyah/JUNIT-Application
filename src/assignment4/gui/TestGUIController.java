package assignment4.gui;

import java.beans.MethodDescriptor;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
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
import javafx.scene.input.KeyEvent;
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
    @FXML private ListView testmethods;

    String classpath;
    String path;
    String method;
    String current_Path;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        classpath = "src";
        path = "";
        File directory = new File("src/");
        System.out.println(directory.isDirectory());
        String [] contents = directory.list();
        listView.getItems().addAll(contents);
        listView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        testmethods.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        printInstructions();
    }

    //This will take the tests that are selected and place them into the text box
    public void testselect(){
        //The user wants to go back to the parent directory
        if(listView.getSelectionModel().getSelectedItem().equals("..")){
            testmethods.getItems().clear();
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
            //See the directories contents
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
                //Test class was selected, now place it in the search bar
                if(searchBar.getText().equals("")){
                    updatemethods();
                    searchBar.setText(path);
                    String temp = "";
                    temp += path;
                    current_Path = temp;
                    path = path.substring(0, path.lastIndexOf("."));
                    classpath = classpath.substring(0, classpath.lastIndexOf("/"));
                }
                else {
                    updatemethods();
                    if(searchBar.getText().contains(path)) {
                        String tmp = searchBar.getText().substring(searchBar.getText().indexOf(path));
                        if(tmp.contains("#")) {
                            tmp = tmp.substring(0, tmp.indexOf("#"));
                        }
                        if (!tmp.equals(path)) {
                            searchBar.setText(searchBar.getText() + " " + path);
                        }
                    }else{
                        searchBar.setText(searchBar.getText() + " " + path);
                    }
                    String temp = "";
                    temp += path;
                    current_Path = temp;
                    path = path.substring(0, path.lastIndexOf("."));
                    classpath = classpath.substring(0, classpath.lastIndexOf("/"));
                }
            }
        }

    }

    /*
        When a new class is selected, update the test methods listview
        to show that class's methods
     */
    public void updatemethods(){
        testmethods.getItems().clear();
        try {
            Class<?> tc = Class.forName(path);
            for(Method methods : tc.getDeclaredMethods()){
                if(!methods.getName().contains("parameter")) {
                    testmethods.getItems().add(methods.getName());
                }
            }
        }catch(ClassNotFoundException ce){
            System.out.println("Class not found");
        }
    }
     public void selectmethod(){
         method = (String) testmethods.getSelectionModel().getSelectedItem();
         if(!searchBar.getText().contains(method)) {
             if (searchBar.getText().contains(current_Path)) {
                 if (current_Path.contains("#")) {
                     searchBar.setText(searchBar.getText().replace(current_Path, current_Path + "," + method));
                 } else {
                     current_Path += "#";
                     String [] t = searchBar.getText().split(" ");
                     for(int i = 0; i < t.length; i++){
                         if(t[i].contains("#")) {
                             if (t[i].substring(0, t[i].indexOf("#")).equals(current_Path.substring(0, current_Path.indexOf("#")))) {
                                 t[i] += "," + method;
                                 current_Path = t[i];
                             }
                         }else if(t[i].equals(current_Path.substring(0, current_Path.indexOf("#")))){
                             t[i] = current_Path + method;
                             current_Path = t[i];
                         }
                     }
                     String temp = "";
                     for(int i = 0; i < t.length; i++){
                         temp += t[i] + " ";
                     }
                     searchBar.setText(temp);

                 }
             } else {
                 searchBar.setText(searchBar.getText() + " " + current_Path + "#" + method);
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
        testmethods.getItems().clear();
        classpath = "src";
        path = "";
        File directory = new File("src/");
        String [] contents = directory.list();
        listView.getItems().addAll(contents);
        searchBar.clear();
        runningTests.clear();
        printInstructions();
    }

    public void StopButtonSelected() throws IOException {
        Stage stage = (Stage) StopButton.getScene().getWindow();
        stage.close();
    }

    //get the textarea
    public TextArea getTextArea(){
        return runningTests;
    }

    public void printInstructions() {
        runningTests.setText("Instructions:\n\nSpecify the class path by clicking through the directories\n\nThe tests can be found in test directory\n\n");
        runningTests.appendText("To delete a test, just remove the test from the search bar\nbelow using your keyboard\n\n");
        runningTests.appendText("Run: Runs the selected tests\n\nRerun: Reruns the selected tests\n\nReset: Clears the previous test summary\n\nStop: Closes the gui");
    }

}