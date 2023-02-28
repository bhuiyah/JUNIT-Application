package assignment4.driver;

import assignment4.runners.TestRunner;

public class TestDriver {

    public static void runTests(String[] testclasses) throws ClassNotFoundException {
        // TODO: complete this method
        for(String testclass: testclasses){
            Class test = Class.forName(testclass);
            TestRunner runner = new TestRunner(test);
        }
        // We will call this method from our JUnit test cases.
    }

    public static void main(String[] args) {
        // Use this for your testing.  We will not be calling this method.
        runTests(args);
    }
}
