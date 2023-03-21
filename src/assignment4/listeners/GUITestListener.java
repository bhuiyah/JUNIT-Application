package assignment4.listeners;

import assignment4.gui.TestGUIController;
import assignment4.results.TestClassResult;
import assignment4.results.TestMethodResult;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;

//import javax.xml.soap.Text;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

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
        controller.getTextArea().appendText("PASS\n");
    }

    // Call this method right after the test method finished running and failed
    @Override
    public void testFailed(TestMethodResult testMethodResult) {
        //print to the TextArea in the GUI that the test failed
        controller.getTextArea().appendText("FAIL\n");
    }

    public void printSum(ArrayList<TestClassResult> results){
        controller.getTextArea().appendText("----------\n");
        controller.getTextArea().appendText("FAILURES:\n");
        int failures = 0;
        int tests = 0;
        for(TestClassResult result : results){
            for(TestMethodResult method : result.getTestMethodResults()) {
                tests++;
                if(!method.isPass()){
                    failures++;
                    controller.getTextArea().appendText(result.getTestClassName() + "." + method.getName() + ":\n");
                    //printStackTrace was recommended to us
                    StringWriter sw = new StringWriter();
                    method.getException().printStackTrace(new PrintWriter(sw));
                    controller.getTextArea().appendText(sw.toString());
                }
            }
        }
        controller.getTextArea().appendText("----------\n");
        controller.getTextArea().appendText("Tests run: " + tests + ", Failures: " + failures + "\n");
        controller.getTextArea().appendText("==========\n");
        // We will call this method from our JUnit test cases.
    }
}
