package assignment4.driver;

import assignment4.annotations.Alphabetical;
import assignment4.annotations.Ordered;
import assignment4.results.TestClassResult;
import assignment4.results.TestMethodResult;
import assignment4.runners.AlphabeticalTestRunner;
import assignment4.runners.OrderedTestRunner;
import assignment4.runners.TestRunner;

import java.lang.reflect.InvocationTargetException;

public class TestDriver {

    public static void runTests(String[] testClasses) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
        //start with having all the results for each class in an array for organization
        TestClassResult[] results = new TestClassResult[testClasses.length];
        int i = 0;
        for(String testClass: testClasses){
            //first step is to make the string a class. Will be assumed that there will be valid names to find the class
            Class<?> test = Class.forName(testClass);
            TestRunner runner = new TestRunner(test);
            if(test.isAnnotationPresent(Alphabetical.class)){
                AlphabeticalTestRunner runner1 = new AlphabeticalTestRunner(test);
                results[i] = runner1.runAlphabetical();
            }
            if(test.isAnnotationPresent(Ordered.class)) {
                OrderedTestRunner runner2 = new OrderedTestRunner(test);
                results[i] = runner2.runOrdered();
            }else{
                //running will do all the methods that have the appropriate annotations and print
                results[i] = runner.run();
            }
            //proceed with the next class inputted
            i++;
        }
        //after going through all the classes, we have to find which methods resulted in an error and print them
        System.out.println("==========");
        System.out.println("FAILURES:");
        int failures = 0;
        int tests = 0;
        for(TestClassResult result : results){
            for(TestMethodResult method : result.getTestMethodResults()) {
                tests++;
                if(!method.isPass()){
                    failures++;
                    System.out.println("test." + result.getTestClassName() + "." + method.getName() + " :");
                    //printStackTrace was recommended to us
                    method.getException().printStackTrace();
                }
            }
        }
        System.out.println("==========");
        System.out.println("Tests run: " + tests + ", Failures: " + failures);
        // We will call this method from our JUnit test cases.
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
        // Use this for your testing.  We will not be calling this method.
        //only method we need in main
        runTests(args);
    }
}