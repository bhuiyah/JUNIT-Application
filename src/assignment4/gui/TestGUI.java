package assignment4.gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import static assignment4.driver.TestDriver.runTests;
public class TestGUI extends Application {

    static Parent root;
    static FXMLLoader loader;
    static TestGUIController controller;
    @Override
    public void start(Stage applicationStage) throws Exception{
        // TODO: Implement this method
        loader = new FXMLLoader(getClass().getResource("../gui/TestGUI.fxml"));
        root = loader.load();
        applicationStage.setScene(new Scene(root));
        applicationStage.setTitle("JUNIT Application");
        applicationStage.show();
    }
    public static void main(String[] args) {
        launch(args); // Launch application
    }

}
