package assignment4.listeners;

import assignment4.results.TestMethodResult;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class GUITestListener implements TestListener {
    Stage stage;
    VBox root;
    GridPane grid;
    Scene scene;

    public GUITestListener(){
        grid = new GridPane();
        stage = new Stage();
        root = new VBox();
        scene = new Scene(root,600, 600);
        stage.setTitle("Tests and Results");
        stage.setScene(scene);
    }


    // Call this method right before the test method starts running
    @Override
    public void testStarted(String testMethod) {
        Text text = new Text("Starting " + testMethod + "...");
        root.getChildren().add(text);
        stage.show();
    }

    // Call this method right after the test method finished running successfully
    @Override
    public void testSucceeded(TestMethodResult testMethodResult) {
        Text text = new Text("PASS\n");
        root.getChildren().add(text);
        stage.show();
    }

    // Call this method right after the test method finished running and failed
    @Override
    public void testFailed(TestMethodResult testMethodResult) {
        Text text = new Text("FAIL\n");
        root.getChildren().add(text);
        stage.show();
    }
}
