package assignment4.runners;

import assignment4.annotations.Parameters;
import assignment4.annotations.Test;
import assignment4.annotations.UseParameters;
import assignment4.assertions.AssertionException;
import assignment4.results.TestClassResult;
import assignment4.results.TestMethodResult;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

public class ParameterizedTestRunner extends TestRunner {

    public ParameterizedTestRunner(Class testClass) {
        super(testClass);
    }

    public TestClassResult runParameterized() throws InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException {
        TestClassResult classResult = new TestClassResult(testClass.getName());
        Object parameters = null;
        Method[] methods = testClass.getDeclaredMethods();
        for (Method method : methods) {
            if (method.isAnnotationPresent(Parameters.class)) {
                try{
                    Object obj = testClass.newInstance();
                    parameters = method.invoke(obj);
                }
                catch(Exception E){
                    throw new RuntimeException(E);
                }
            }
        }
        for (Method method : methods) {
            if (method.isAnnotationPresent((Test.class)) && method.isAnnotationPresent(UseParameters.class)) {
                Object obj = testClass.newInstance();
                TestMethodResult methodResult = null;
                for (int i = 0; i < Array.getLength(parameters); i++) {
                    Object p = Array.get(parameters, i);
                    try {
                        method.invoke(obj, p);
                        methodResult = new TestMethodResult(method.getName(), true, null);
                        System.out.println(classResult.getTestClassName() + "." + method.getName() + "[" + p + "] : PASS");
                        classResult.addTestMethodResult(methodResult);
                    } catch (Exception I) {
                        Throwable T = I.getCause();
                        methodResult = new TestMethodResult(method.getName(), false, (AssertionException) T);
                        System.out.println(classResult.getTestClassName() + "." + method.getName() + "[" + p + "] : FAIL");
                        classResult.addTestMethodResult(methodResult);
                    }
                }
            }
            //we need to see if the method has the proper annotation; if so, run it.
            else if (method.isAnnotationPresent(Test.class)) {
                classResult = generalRunner(method, classResult);
            }
        }
        return classResult;
    }

    public TestClassResult generalRunner(Method method, TestClassResult classResult) throws InstantiationException, IllegalAccessException {
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
            //if there is any error, we get that assertion and document that there is an error and print that out
            catch (Exception I) {
                Throwable T = I.getCause();
                methodResult = new TestMethodResult(method.getName(), false, (AssertionException) T);
                System.out.println(classResult.getTestClassName() + "." + method.getName() + " : FAIL");
            }
            //after calling the method, we add the method attributes into our classResult
            classResult.addTestMethodResult(methodResult);
        }
        return classResult;
    }
}


//int = [I --
//char = [C
//boolean = [Z --
//byte = [B
//short = [S
//long = [J
//float = [F --
//double = [D --