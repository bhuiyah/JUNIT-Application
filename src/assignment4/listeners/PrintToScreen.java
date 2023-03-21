package assignment4.listeners;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class PrintToScreen extends Service<String> {

    private final String stringToPrint;

    // Constructor
    public PrintToScreen(String stringToPrint) {
        this.stringToPrint = stringToPrint;
    }


    @Override
    protected Task<String> createTask() {
        return new Task<String>() {
            @Override
            protected String call() throws Exception {
                updateValue(stringToPrint);
                return stringToPrint;
            }
        };
    }
}
