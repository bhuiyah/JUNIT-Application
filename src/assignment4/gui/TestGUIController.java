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

import assignment4.annotations.Test;
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
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.Stage;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import javafx.beans.value.ObservableValue;
import javafx.stage.StageStyle;

import java.nio.file.Path;
import java.nio.file.Paths;

import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import static assignment4.driver.TestDriver.addListener;
import static assignment4.driver.TestDriver.runTests;

public class TestGUIController implements Initializable{
    //These are for the Listview
    @FXML private ListView<String> listView;
    @FXML private TextField searchBar;
    @FXML private Button RunButton;
    @FXML private TextFlow runningTests;
    @FXML private Button RerunButton;
    @FXML private Button StopButton;
    @FXML private ListView testmethods;
    @FXML private ScrollPane scrollpane;

    String classpath;
    String path;
    String method;
    String current_Path;
    @Override
    public void initialize(URL url, ResourceBundle rb){
        String userDirectory = System.getProperty("user.dir");
        classpath = userDirectory.replaceAll("\\\\", "/");
        //.substring(userDirectory.lastIndexOf("/"), userDirectory.length());
//        classpath = classpath.substring(classpath.lastIndexOf("/"));
        path = "";
        File directory = new File(classpath);
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
                    if(updatemethods() != 0){
                        if(path.contains("src.")){
                            StringBuilder sb = new StringBuilder(path);
                            sb = sb.replace(path.indexOf("src."), path.indexOf("src.") + 4, "");
                            path = sb.toString();
                        }
                        searchBar.setText(path);
                        String temp = "";
                        temp += path;
                        current_Path = temp;
                        path = path.substring(0, path.lastIndexOf("."));
                        classpath = classpath.substring(0, classpath.lastIndexOf("/"));
                    }
                    else{
                        path = path.substring(0, path.lastIndexOf("."));
                        classpath = classpath.substring(0, classpath.lastIndexOf("/"));
                    }
                }
                else {
                    if(updatemethods() != 0){
                        if(path.contains("src.")){
                            StringBuilder sb = new StringBuilder(path);
                            sb = sb.replace(path.indexOf("src."), path.indexOf("src.") + 4, "");
                            path = sb.toString();
                        }
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
                    else{
                        path = path.substring(0, path.lastIndexOf("."));
                        classpath = classpath.substring(0, classpath.lastIndexOf("/"));
                    }
                }
            }
        }

    }

    /*
        When a new class is selected, update the test methods listview
        to show that class's methods
     */
    public int updatemethods(){
        testmethods.getItems().clear();
        int count = 0;
        try {
            if(path.contains("src.")){
                StringBuilder sb = new StringBuilder(path);
                sb = sb.replace(path.indexOf("src."), path.indexOf("src.") + 4, "");
                path = sb.toString();
            }
            Class<?> tc = Class.forName(path);

            for(Method methods : tc.getDeclaredMethods()){
                if(!methods.getName().contains("parameter") && methods.isAnnotationPresent(Test.class)) {
                    count++;
                    testmethods.getItems().add(methods.getName());
                }
            }
        }catch(ClassNotFoundException ce){
            System.out.println("Class not found");
        }
        return count;
    }
     public void selectmethod(){
         method = (String) testmethods.getSelectionModel().getSelectedItem();
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
    public void RunButtonSelected() throws IOException {
        runningTests.getChildren().clear();
        String [] selectedTest = searchBar.getText().split(" ");
        GUITestListener guiTestListener = new GUITestListener(this);
        addListener(guiTestListener);
        runTests(selectedTest);
    }

    public void ReRunButtonSelected() throws IOException {
        runningTests.getChildren().clear();
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
        String userDirectory = System.getProperty("user.dir");
        classpath = userDirectory.replaceAll("\\\\", "/");
        path = "";
        File directory = new File(classpath);
        String [] contents = directory.list();
        listView.getItems().addAll(contents);
        searchBar.clear();
        runningTests.getChildren().clear();
        printInstructions();
    }

    public void StopButtonSelected() throws IOException {
        Stage stage = (Stage) StopButton.getScene().getWindow();
        stage.close();
    }

    //get the textarea
    public TextFlow getTextArea(){
        return runningTests;
    }

    //get scrollpane
    public ScrollPane getScrollPane(){
        return scrollpane;
    }

    public void printInstructions() {
        runningTests.getChildren().add(new Text("Instructions:\n\n Specify the class path by clicking through the directories\n\nThe tests can be found in test directory "));
        delay();
        runningTests.getChildren().add(new Text("To delete a test, just remove the test from the search bar \nbelow using your keyboard\n\n"));
        delay();
        runningTests.getChildren().add(new Text("Run: Runs the selected tests\n\n"));
        delay();
        runningTests.getChildren().add(new Text("Rerun: Reruns the selected tests\n\n"));
        delay();
        runningTests.getChildren().add(new Text("Reset: Clears the previous test summary\n\n"));
        delay();
        runningTests.getChildren().add(new Text("Stop: Closes the gui\n\n"));
    }

    public static void delay(){
        Stage stage = new Stage(StageStyle.TRANSPARENT);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                stage.close();
            }
        });
        stage.showAndWait();
    }
}