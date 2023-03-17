package assignment4.driver;

import assignment4.annotations.Alphabetical;
import assignment4.annotations.Ordered;
import assignment4.annotations.Parameterized;
import assignment4.results.TestClassResult;
import assignment4.results.TestMethodResult;
import assignment4.runners.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class TestDriver {

    public static void runTests(String[] testClasses) {
        //start with having all the results for each class in an array for organization
        ArrayList<TestClassResult> results = new ArrayList<>();
        for(String testClass: testClasses){
            //first step is to make the string a class. Will be assumed that there will be valid names to find the class
            if(testClass.contains("#")){
                String testName = testClass.substring(0, testClass.indexOf("#"));
                ArrayList<String> filteredMethods = new ArrayList<>();
                String temp = "";
                for(int j = testClass.indexOf("#") + 1; j < testClass.length(); j++){
                    if(testClass.charAt(j) == ','){
                        filteredMethods.add(temp);
                        temp = "";
                    }
                    else if(j == testClass.length() - 1){
                        temp += testClass.charAt(j);
                        filteredMethods.add(temp);
                    }
                    else{
                        temp += testClass.charAt(j);
                    }
                }
                try {
                    Class<?> test = Class.forName(testName);
                    FilteredTestRunner filteredTest = new FilteredTestRunner(test, filteredMethods);
                    results.add(filteredTest.runFiltered()) ;
                }
                catch(ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException ignored){

                }
            }
            else {
                try {
                    Class<?> test = Class.forName(testClass);
                    if (test.isAnnotationPresent(Parameterized.class)) {
                        ParameterizedTestRunner extraRunner = new ParameterizedTestRunner(test);
                        results.add(extraRunner.runParameterized());
                    } else if (test.isAnnotationPresent(Alphabetical.class)) {
                        AlphabeticalTestRunner runner1 = new AlphabeticalTestRunner(test);
                        results.add(runner1.runAlphabetical());
                    } else if (test.isAnnotationPresent(Ordered.class)) {
                        OrderedTestRunner runner2 = new OrderedTestRunner(test);
                        results.add(runner2.runOrdered());
                    } else {
                        //running will do all the methods that have the appropriate annotations and print
                        TestRunner runner = new TestRunner(test);
                        results.add(runner.run());
                    }
                    //proceed with the next class inputted
                }
                catch(ClassNotFoundException | InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchFieldException ignored){

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
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
                    System.out.println(result.getTestClassName() + "." + method.getName() + ":");
                    //printStackTrace was recommended to us
                    method.getException().printStackTrace();
                }
            }
        }
        System.out.println("==========");
        System.out.println("Tests run: " + tests + ", Failures: " + failures);
        // We will call this method from our JUnit test cases.
    }

//    public static void main(String[] args){
//        // Use this for your testing.  We will not be calling this method.
//        //only method we need in main
//        runTests(args);
//    }
}