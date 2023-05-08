package assignment4.assertions;

import java.io.PrintWriter;

/**
 * DO NOT MODIFY THIS CLASS
 */

public class AssertionException extends AssertionError {

    protected String message;

    public AssertionException() {
        this(null);
    }

    public AssertionException(String message) {
        super(message);
    }

    @Override
    public void printStackTrace() {
        System.out.println(this.getClass().getName());
        for (StackTraceElement ste : this.getStackTrace()) {
            System.out.println("    at " + ste.toString());
            if (!ste.getClassName().equals("assignment4.assertions.Assert")) {
                break;
            }
        }
    }

    @Override
    public void printStackTrace(PrintWriter pw) {
        pw.append(this.getClass().getName()+"\n");
        for (StackTraceElement ste : this.getStackTrace()) {
            pw.append("    at " + ste.toString()+"\n");
            if (!ste.getClassName().equals("assignment4.assertions.Assert")) {
                break;
            }
        }
    }
}
