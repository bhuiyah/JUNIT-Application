package assignment4.runners;

import assignment4.annotations.Test;
import assignment4.assertions.Assert;
import assignment4.assertions.AssertionException;
import assignment4.listeners.TestListener;
import assignment4.results.TestClassResult;
import assignment4.results.TestMethodResult;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TestRunner {
    Class<?> testClass;

    public TestRunner(Class testClass) {
        // TODO: complete this constructor
        this.testClass = testClass;
    }

    public TestClassResult run() throws InstantiationException, IllegalAccessException, InvocationTargetException, AssertionException {
        // TODO: complete this method
        TestClassResult classResult = new TestClassResult(testClass.getName());
        for(Method method: testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Test.class)) {
                Object obj = testClass.newInstance();
                TestMethodResult methodResult;
                try {
                    method.invoke(obj);
                    methodResult = new TestMethodResult(method.getName(), true, null);
                }
                catch(AssertionException E){
                    methodResult = new TestMethodResult(method.getName(), false, E);
                }
                classResult.addTestMethodResult(methodResult);
            }
        }
        return classResult;
    }

    public void addListener(TestListener listener) {
        // Do NOT implement this method for Assignment 4
    }
}
