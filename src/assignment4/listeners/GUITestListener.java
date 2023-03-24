package assignment4.listeners;

import assignment4.gui.TestGUI;
import assignment4.gui.TestGUIController;
import assignment4.results.TestClassResult;
import assignment4.results.TestMethodResult;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;

//import javax.xml.soap.Text;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import javafx.scene.text.Text;
import javafx.scene.paint.Color;

import static assignment4.gui.TestGUI.scene;
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
        controller.getTextArea().getChildren().add(new Text("Starting: \n"));
        update_display();
        controller.getTextArea().getChildren().add(new Text(testMethod));
        update_display();
    }

    // Call this method right after the test method finished running successfully
    @Override
    public void testSucceeded(TestMethodResult testMethodResult) {
        //print to the TextArea in the GUI that the test passed
        Text pass = new Text("PASS\n");
        pass.setFill(Color.GREEN);
        //set the font to courier new
        pass.setStyle("-fx-font-family: courier new;");
        controller.getTextArea().getChildren().add(pass);
        update_display();
    }

    // Call this method right after the test method finished running and failed
    @Override
    public void testFailed(TestMethodResult testMethodResult) {
        //print to the TextArea in the GUI that the test failed
        Text fail = new Text("FAIL\n");
        fail.setStyle("-fx-font-family: courier new;");
        fail.setFill(Color.RED);
        controller.getTextArea().getChildren().add(fail);
        update_display();
    }

    public void printSum(ArrayList<TestClassResult> results){
        controller.getTextArea().getChildren().add(new Text ("----------------------------------------\n"));
        update_display();
        controller.getTextArea().getChildren().add(new Text ("FAILURES:\n"));
        update_display();
        int failures = 0;
        int tests = 0;
        for(TestClassResult result : results){
            for(TestMethodResult method : result.getTestMethodResults()) {
                tests++;
                if(!method.isPass()){
                    failures++;
                    controller.getTextArea().getChildren().add(new Text(result.getTestClassName() + "." + method.getName() + ":\n"));
                    update_display();
                    //printStackTrace was recommended to us
                    StringWriter sw = new StringWriter();
                    method.getException().printStackTrace(new PrintWriter(sw));
                    controller.getTextArea().getChildren().add(new Text(sw.toString()));
                    update_display();
                }
            }
        }
        controller.getTextArea().getChildren().add(new Text("----------------------------------------\n"));

        controller.getTextArea().getChildren().add(new Text("Tests run: " + tests + ", Failures: " + failures + "\n"));
        update_display();
        controller.getTextArea().getChildren().add(new Text("========================================\n"));
        update_display();
        // We will call this method from our JUnit test cases.

    }

    public void update_display(){
        TestGUIController.delay();
        controller.getScrollPane().setVvalue(1.0);
    }
}
