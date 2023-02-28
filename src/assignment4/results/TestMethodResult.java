package assignment4.results;

import assignment4.assertions.AssertionException;

public class TestMethodResult {

    private String testName;
    private boolean isPass;
    private AssertionException exception;

    public TestMethodResult(String testName, boolean isPass, AssertionException exception) {
        this.testName = testName;
        this.isPass = isPass;
        this.exception = exception;
    }

    public String getName() {
        return this.testName;
    }

    public boolean isPass() {
        return this.isPass;
    }

    public AssertionException getException() {
        return this.exception;
    }
}
