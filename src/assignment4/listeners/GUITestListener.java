package assignment4.listeners;

import assignment4.gui.TestGUIController;
import assignment4.results.TestMethodResult;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

import java.io.IOException;

public class GUITestListener implements TestListener {
    FXMLLoader loader;
    Parent root;
    TestGUIController controller;

    //have a constructor that is able to have access to the elements in the fxml file
    public GUITestListener(TestGUIController testGUIController) throws IOException {
        controller = testGUIController;
    }

    // Call this method right before the test method starts running
    @Override
    public void testStarted(String testMethod) {
        //print the testMethod to the TextArea in the GUI
        controller.getTextArea().appendText(testMethod);

    }

    // Call this method right after the test method finished running successfully
    @Override
    public void testSucceeded(TestMethodResult testMethodResult) {
        //print to the TextArea in the GUI that the test passed
    }

    // Call this method right after the test method finished running and failed
    @Override
    public void testFailed(TestMethodResult testMethodResult) {
        //print to the TextArea in the GUI that the test failed
    }
}
