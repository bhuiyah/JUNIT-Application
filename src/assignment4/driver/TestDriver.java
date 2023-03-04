package assignment4.driver;

import assignment4.results.TestClassResult;
import assignment4.results.TestMethodResult;
import assignment4.runners.TestRunner;
import com.sun.scenario.effect.impl.sw.java.JSWBlend_SRC_OUTPeer;

import java.lang.reflect.InvocationTargetException;

public class TestDriver {

    public static void runTests(String[] testClasses) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
        TestClassResult[] results = new TestClassResult[testClasses.length];
        int i = 0;
        for(String testClass: testClasses){
            Class<?> test = Class.forName(testClass);
            TestRunner runner = new TestRunner(test);
            results[i] = runner.run();
            i++;
        }
        for(TestClassResult result : results){
            for(TestMethodResult method : result.getTestMethodResults()){
                if(method.isPass()){
                    System.out.println("test." + result.getTestClassName() + "." + method.getName() + " : PASS");
                }
                else{
                    System.out.println("test." + result.getTestClassName() + "." + method.getName() + " : FAIL");
                }
            }
        }
        // We will call this method from our JUnit test cases.
    }

    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException, InvocationTargetException {
        // Use this for your testing.  We will not be calling this method.
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
