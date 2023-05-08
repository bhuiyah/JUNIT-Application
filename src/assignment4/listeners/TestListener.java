package assignment4.listeners;

import assignment4.results.TestMethodResult;

public interface TestListener {

    // Call this method right before the test method starts running
    public void testStarted(String testMethod);

    // Call this method right after the test method finished running successfully
    public void testSucceeded(TestMethodResult testMethodResult);

    // Call this method right after the test method finished running and failed
    public void testFailed(TestMethodResult testMethodResult);
}
