package assignment4.gui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;

import static assignment4.driver.TestDriver.runTests;

public class TestGUI extends Application {

    @Override
    public void start(Stage applicationStage) {
        // TODO: Implement this method
        Text text = new Text();

        //Setting the text to be added.
        text.setText("Hello how are you");

        //setting the position of the text
        text.setX(50);
        text.setY(50);

        //Creating a Group object
        GridPane root = new GridPane();
        //Creating a scene object
        root.add(text, 0, 0);

        Button b = new Button("Run");
        File directory = new File("src/test/");
        String [] contents = directory.list();

        EventHandler<ActionEvent> event = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e)
            {
                runTests("test.TestA".split(" "));
            }
        };

        b.setOnAction(event);
        root.add(b, 5, 5);

        ListView<String> list = new ListView<>();
        ObservableList<String> items = FXCollections.observableArrayList (contents);
        list.setItems(items);

        root.getChildren().add(list);

        Scene scene = new Scene(root, 600, 600);

        //Setting title to the Stage
        applicationStage.setTitle("JUNIT Application");

        //Adding scene to the stage
        applicationStage.setScene(scene);

        //Displaying the contents of the stage
        applicationStage.show();
    }

    public static void main(String[] args) {
        launch(args); // Launch application
    }
}
