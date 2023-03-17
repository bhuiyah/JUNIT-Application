package assignment4.listeners;

import assignment4.gui.TestGUIController;
import assignment4.results.TestMethodResult;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUITestListener implements TestListener {
    TestGUIController controller;

    public GUITestListener(){
        controller = new TestGUIController();
    }

    // Call this method right before the test method starts running
    @Override
    public void testStarted(String testMethod) {
//        Text text = new Text("Starting " + testMethod + "...");
//        root.getChildren().add(text);
//        stage.show();
//        controller.printToScreen("Running...");
    }

    // Call this method right after the test method finished running successfully
    @Override
    public void testSucceeded(TestMethodResult testMethodResult) {

//        Text text = new Text("PASS\n");
//        root.getChildren().add(text);
//        stage.show();
    }

    // Call this method right after the test method finished running and failed
    @Override
    public void testFailed(TestMethodResult testMethodResult) {
//        Text text = new Text("FAIL\n");
//        root.getChildren().add(text);
//        stage.show();
    }
}
