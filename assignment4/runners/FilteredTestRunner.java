package assignment4.runners;

import assignment4.annotations.Test;
import assignment4.assertions.AssertionException;
import assignment4.results.TestClassResult;
import assignment4.results.TestMethodResult;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

public class FilteredTestRunner extends TestRunner {

    List<String> testMethods;
    public FilteredTestRunner(Class testClass, List<String> testMethods) throws InvocationTargetException, InstantiationException, IllegalAccessException {
        super(testClass);
        this.testMethods = testMethods;
    }

    public TestClassResult runFiltered() throws InstantiationException, IllegalAccessException, InvocationTargetException, AssertionException {
        // TODO: complete this method
        //We need to document results from all the methods
        TestClassResult classResult = new TestClassResult(testClass.getName());
        for(Method method: testClass.getDeclaredMethods()) {
            //we need to see if the method has the proper annotation; if so, run it.
            if (method.isAnnotationPresent(Test.class) && testMethods.contains(method.getName())) {
                //everytime we have a correct annotation, we need to create a new object of the class and the run the method
                Object obj = testClass.newInstance();
                TestMethodResult methodResult;
                //if there is no assertion error, the test passes, and we print it out and document it
                try {
                    method.invoke(obj);
                    methodResult = new TestMethodResult(method.getName(), true, null);
                    System.out.println(classResult.getTestClassName() + "." + method.getName() + " : PASS");
                }
                //if there is any error, we get that assertion and document that there is an error and print that out
                catch (Exception I){
                    Throwable T = I.getCause();
                    methodResult = new TestMethodResult(method.getName(), false, (AssertionException) T);
                    System.out.println(classResult.getTestClassName() + "." + method.getName() + " : FAIL");
                }
                //after calling the method, we add the method attributes into our classResult
                classResult.addTestMethodResult(methodResult);
            }
        }
        return classResult;
    }
}
