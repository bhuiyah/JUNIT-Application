package assignment4.driver;

import assignment4.results.TestClassResult;
import assignment4.results.TestMethodResult;
import assignment4.runners.TestRunner;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;

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
            //running will do all the methods that have the appropriate annotations and print
            results[i] = runner.run();
            //proceed with the next class inputted
            i++;
        }
        //after going through all the classes, we have to find which methods resulted in an error and print them
        System.out.println("==========");
        System.out.println("FAILURES:");
        for(TestClassResult result : results){
            for(TestMethodResult method : result.getTestMethodResults()) {
                if(!method.isPass()){
                    System.out.println("test." + result.getTestClassName() + "." + method.getName() + " :");
                    //printStackTrace was recommended to us
                    method.getException().printStackTrace();
                }
            }
        }
        System.out.println("==========");
        // We will call this method from our JUnit test cases.
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
        // Use this for your testing.  We will not be calling this method.
        //only method we need in main
        runTests(args);
    }
}


//terminal to specify what classes to run
//(string) - test class use java reflection library to pull the class to our files
//test class is found
//we can create instances of the class
//
/*
Class<?> temp = Class.forName(str)
temp

 */
