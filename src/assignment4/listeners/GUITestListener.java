package assignment4.listeners;

import assignment4.gui.TestGUIController;
import assignment4.results.TestMethodResult;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class GUITestListener implements TestListener {
    FXMLLoader loader;
    Parent root;
    TestGUIController controller;

    //have a constructor that is able to have access to the elements in the fxml file
    public GUITestListener() throws IOException {
        loader = new FXMLLoader(getClass().getResource("../gui/TestGUI.fxml"));
        root = loader.load();
        controller = loader.getController();
    }

    // Call this method right before the test method starts running
    @Override
    public void testStarted(String testMethod) {
//        Text text = new Text("Starting " + testMethod + "...");
//        root.getChildren().add(text);
//        stage.show();
//        controller.printToScreen("Running...");
        //print the testMethod to the TextArea in the GUI
        controller.printToScreen("Running " + testMethod);
    }

    // Call this method right after the test method finished running successfully
    @Override
    public void testSucceeded(TestMethodResult testMethodResult) {

//        Text text = new Text("PASS\n");
//        root.getChildren().add(text);
//        stage.show();
        //print to the TextArea in the GUI that the test passed
        TestGUIController controller = loader.getController();
        controller.printToScreen("Test " + testMethodResult.getName() + " passed");
    }

    // Call this method right after the test method finished running and failed
    @Override
    public void testFailed(TestMethodResult testMethodResult) {
//        Text text = new Text("FAIL\n");
//        root.getChildren().add(text);
//        stage.show();
        //print to the TextArea in the GUI that the test failed
        controller.printToScreen("Test " + testMethodResult.getName() + " failed");
    }
}
