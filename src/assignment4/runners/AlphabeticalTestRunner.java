package assignment4.runners;

import assignment4.annotations.Test;
import assignment4.assertions.AssertionException;
import assignment4.listeners.GUITestListener;
import assignment4.results.TestClassResult;
import assignment4.results.TestMethodResult;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class AlphabeticalTestRunner extends TestRunner {

    public AlphabeticalTestRunner(Class testClass, GUITestListener gui) throws InvocationTargetException, InstantiationException, IllegalAccessException, IOException {
        super(testClass, gui);
    }

    public TestClassResult runAlphabetical() throws InstantiationException, IllegalAccessException, InvocationTargetException, AssertionException {
        // TODO: complete this method
        //We need to document results from all the methods
        TestClassResult classResult = new TestClassResult(testClass.getName());
        Method[] methods = sortMethods(testClass.getDeclaredMethods());
        for(Method method: methods) {
            //we need to see if the method has the proper annotation; if so, run it.
            if (method.isAnnotationPresent(Test.class)) {
                //everytime we have a correct annotation, we need to create a new object of the class and the run the method
                Object obj = testClass.newInstance();
                TestMethodResult methodResult;
                //if there is no assertion error, the test passes, and we print it out and document it
                try {
                    method.invoke(obj);
                    methodResult = new TestMethodResult(method.getName(), true, null);
                    System.out.println(classResult.getTestClassName() + "." + method.getName() + " : PASS");
                }
                //if there is an any error, we get that assertion and document that there is an error and print that out
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

    public static Method[] sortMethods(Method[] methods){
        for(int i = 1; i < methods.length; i++){
            Method key = methods[i];
            int j = i - 1;
            while(j >= 0 && key.toString().compareTo(methods[j].toString()) < 0){
                methods[j + 1] = methods[j];
                j--;
            }
            methods[j + 1] = key;
        }
        return methods;
    }
}
