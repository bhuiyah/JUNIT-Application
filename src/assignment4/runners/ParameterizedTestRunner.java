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
        int found = 0;
        for (Method method : testClass.getDeclaredMethods()) {
            if (method.isAnnotationPresent(Parameters.class)) {
                found = 1;
                Object obj = testClass.newInstance();
                String type = method.getReturnType().toString();
                switch (type) {
                    case "class [I":
                        int[] intArr = (int[]) method.invoke(obj);
                        classResult = runInt(testClass.getDeclaredMethods(), intArr, classResult);
                        break;

                    case "class [C":
                        char[] charArr = (char[]) method.invoke(obj);
                        classResult = runChar(testClass.getDeclaredMethods(), charArr, classResult);
                        break;

                    case "class [Z":
                        boolean[] boolArr = (boolean[]) method.invoke(obj);
                        classResult = runBool(testClass.getDeclaredMethods(), boolArr, classResult);
                        break;

                    case "class [B":
                        byte[] byteArr = (byte[]) method.invoke(obj);
                        classResult = runByte(testClass.getDeclaredMethods(), byteArr, classResult);
                        break;

                    case "class [S":
                        short[] shortArr = (short[]) method.invoke(obj);
                        classResult = runShort(testClass.getDeclaredMethods(), shortArr, classResult);
                        break;

                    case "class [J":
                        long[] longArr = (long[]) method.invoke(obj);
                        classResult = runLong(testClass.getDeclaredMethods(), longArr, classResult);
                        break;

                    case "class [F":
                        float[] floatArr = (float[]) method.invoke(obj);
                        classResult = runFloat(testClass.getDeclaredMethods(), floatArr, classResult);
                        break;

                    case "class [D":
                        double[] doubleArr = (double[]) method.invoke(obj);
                        classResult = runDouble(testClass.getDeclaredMethods(), doubleArr, classResult);
                        break;
                }
            }
        }
        if(found == 0) super.run();
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

    public TestClassResult runInt(Method[] methods, int[] parameters, TestClassResult classResult) throws InstantiationException, IllegalAccessException {
        for (Method method : methods) {
            if (method.isAnnotationPresent((Test.class)) && method.isAnnotationPresent(UseParameters.class)) {
                Object obj = testClass.newInstance();
                TestMethodResult methodResult = null;
                try {
                    for (int i = 0; i < parameters.length; i++) {
                        method.invoke(obj, parameters[i]);
                        methodResult = new TestMethodResult(method.getName(), true, null);
                        System.out.println(classResult.getTestClassName() + "." + method.getName() + "[" + parameters[i] + "] : PASS");
                        classResult.addTestMethodResult(methodResult);
                    }
                } catch (Exception I) {
                    Throwable T = I.getCause();
                    methodResult = new TestMethodResult(method.getName(), false, (AssertionException) T);
                    System.out.println(classResult.getTestClassName() + "." + method.getName() + " : FAIL");
                    classResult.addTestMethodResult(methodResult);
                }
                //after calling the method, we add the method attributes into our classResult

            }
            //we need to see if the method has the proper annotation; if so, run it.
            else if (method.isAnnotationPresent(Test.class)) {
                classResult = generalRunner(method, classResult);
            }
        }
        return classResult;
    }

    public TestClassResult runChar(Method[] methods, char[] parameters, TestClassResult classResult) throws InstantiationException, IllegalAccessException {
        for (Method method : methods) {
            if (method.isAnnotationPresent((Test.class)) && method.isAnnotationPresent(UseParameters.class)) {
                Object obj = testClass.newInstance();
                TestMethodResult methodResult = null;
                try {
                    for (int i = 0; i < parameters.length; i++) {
                        method.invoke(obj, parameters[i]);
                        methodResult = new TestMethodResult(method.getName(), true, null);
                        System.out.println(classResult.getTestClassName() + "." + method.getName() + "[" + parameters[i] + "] : PASS");
                        classResult.addTestMethodResult(methodResult);
                    }
                } catch (Exception I) {
                    Throwable T = I.getCause();
                    methodResult = new TestMethodResult(method.getName(), false, (AssertionException) T);
                    System.out.println(classResult.getTestClassName() + "." + method.getName() + " : FAIL");
                    classResult.addTestMethodResult(methodResult);
                }
                //after calling the method, we add the method attributes into our classResult

            }
            //we need to see if the method has the proper annotation; if so, run it.
            else if (method.isAnnotationPresent(Test.class)) {
                classResult = generalRunner(method, classResult);
            }
        }
        return classResult;
    }

    public TestClassResult runBool (Method[] methods, boolean[] parameters, TestClassResult classResult) throws InstantiationException, IllegalAccessException {
        for (Method method : methods) {
            if (method.isAnnotationPresent((Test.class)) && method.isAnnotationPresent(UseParameters.class)) {
                Object obj = testClass.newInstance();
                TestMethodResult methodResult = null;
                try {
                    for (int i = 0; i < parameters.length; i++) {
                        method.invoke(obj, parameters[i]);
                        methodResult = new TestMethodResult(method.getName(), true, null);
                        System.out.println(classResult.getTestClassName() + "." + method.getName() + "[" + parameters[i] + "] : PASS");
                        classResult.addTestMethodResult(methodResult);
                    }
                } catch (Exception I) {
                    Throwable T = I.getCause();
                    methodResult = new TestMethodResult(method.getName(), false, (AssertionException) T);
                    System.out.println(classResult.getTestClassName() + "." + method.getName() + " : FAIL");
                    classResult.addTestMethodResult(methodResult);
                }
                //after calling the method, we add the method attributes into our classResult

            }
            //we need to see if the method has the proper annotation; if so, run it.
            else if (method.isAnnotationPresent(Test.class)) {
                classResult = generalRunner(method, classResult);
            }
        }
        return classResult;
    }

    public TestClassResult runByte (Method[] methods, byte[] parameters, TestClassResult classResult) throws InstantiationException, IllegalAccessException {
        for (Method method : methods) {
            if (method.isAnnotationPresent((Test.class)) && method.isAnnotationPresent(UseParameters.class)) {
                Object obj = testClass.newInstance();
                TestMethodResult methodResult = null;
                try {
                    for (int i = 0; i < parameters.length; i++) {
                        method.invoke(obj, parameters[i]);
                        methodResult = new TestMethodResult(method.getName(), true, null);
                        System.out.println(classResult.getTestClassName() + "." + method.getName() + "[" + parameters[i] + "] : PASS");
                        classResult.addTestMethodResult(methodResult);
                    }
                } catch (Exception I) {
                    Throwable T = I.getCause();
                    methodResult = new TestMethodResult(method.getName(), false, (AssertionException) T);
                    System.out.println(classResult.getTestClassName() + "." + method.getName() + " : FAIL");
                    classResult.addTestMethodResult(methodResult);
                }
                //after calling the method, we add the method attributes into our classResult

            }
            //we need to see if the method has the proper annotation; if so, run it.
            else if (method.isAnnotationPresent(Test.class)) {
                classResult = generalRunner(method, classResult);
            }
        }
        return classResult;
    }

    public TestClassResult runShort (Method[] methods, short[] parameters, TestClassResult classResult) throws InstantiationException, IllegalAccessException {
        for (Method method : methods) {
            if (method.isAnnotationPresent((Test.class)) && method.isAnnotationPresent(UseParameters.class)) {
                Object obj = testClass.newInstance();
                TestMethodResult methodResult = null;
                try {
                    for (int i = 0; i < parameters.length; i++) {
                        method.invoke(obj, parameters[i]);
                        methodResult = new TestMethodResult(method.getName(), true, null);
                        System.out.println(classResult.getTestClassName() + "." + method.getName() + "[" + parameters[i] + "] : PASS");
                        classResult.addTestMethodResult(methodResult);
                    }
                } catch (Exception I) {
                    Throwable T = I.getCause();
                    methodResult = new TestMethodResult(method.getName(), false, (AssertionException) T);
                    System.out.println(classResult.getTestClassName() + "." + method.getName() + " : FAIL");
                    classResult.addTestMethodResult(methodResult);
                }
                //after calling the method, we add the method attributes into our classResult

            }
            //we need to see if the method has the proper annotation; if so, run it.
            else if (method.isAnnotationPresent(Test.class)) {
                classResult = generalRunner(method, classResult);
            }
        }
        return classResult;
    }

    public TestClassResult runLong (Method[] methods, long[] parameters, TestClassResult classResult) throws InstantiationException, IllegalAccessException {
        for (Method method : methods) {
            if (method.isAnnotationPresent((Test.class)) && method.isAnnotationPresent(UseParameters.class)) {
                Object obj = testClass.newInstance();
                TestMethodResult methodResult = null;
                try {
                    for (int i = 0; i < parameters.length; i++) {
                        method.invoke(obj, parameters[i]);
                        methodResult = new TestMethodResult(method.getName(), true, null);
                        System.out.println(classResult.getTestClassName() + "." + method.getName() + "[" + parameters[i] + "] : PASS");
                        classResult.addTestMethodResult(methodResult);
                    }
                } catch (Exception I) {
                    Throwable T = I.getCause();
                    methodResult = new TestMethodResult(method.getName(), false, (AssertionException) T);
                    System.out.println(classResult.getTestClassName() + "." + method.getName() + " : FAIL");
                    classResult.addTestMethodResult(methodResult);
                }
                //after calling the method, we add the method attributes into our classResult

            }
            //we need to see if the method has the proper annotation; if so, run it.
            else if (method.isAnnotationPresent(Test.class)) {
                classResult = generalRunner(method, classResult);
            }
        }
        return classResult;
    }

    public TestClassResult runFloat (Method[] methods, float[] parameters, TestClassResult classResult) throws InstantiationException, IllegalAccessException {
        for (Method method : methods) {
            if (method.isAnnotationPresent((Test.class)) && method.isAnnotationPresent(UseParameters.class)) {
                Object obj = testClass.newInstance();
                TestMethodResult methodResult = null;
                try {
                    for (int i = 0; i < parameters.length; i++) {
                        method.invoke(obj, parameters[i]);
                        methodResult = new TestMethodResult(method.getName(), true, null);
                        System.out.println(classResult.getTestClassName() + "." + method.getName() + "[" + parameters[i] + "] : PASS");
                        classResult.addTestMethodResult(methodResult);
                    }
                } catch (Exception I) {
                    Throwable T = I.getCause();
                    methodResult = new TestMethodResult(method.getName(), false, (AssertionException) T);
                    System.out.println(classResult.getTestClassName() + "." + method.getName() + " : FAIL");
                    classResult.addTestMethodResult(methodResult);
                }
                //after calling the method, we add the method attributes into our classResult

            }
            //we need to see if the method has the proper annotation; if so, run it.
            else if (method.isAnnotationPresent(Test.class)) {
                classResult = generalRunner(method, classResult);
            }
        }
        return classResult;
    }

    public TestClassResult runDouble (Method[] methods, double[] parameters, TestClassResult classResult) throws InstantiationException, IllegalAccessException {
        for (Method method : methods) {
            if (method.isAnnotationPresent((Test.class)) && method.isAnnotationPresent(UseParameters.class)) {
                Object obj = testClass.newInstance();
                TestMethodResult methodResult = null;
                try {
                    for (int i = 0; i < parameters.length; i++) {
                        method.invoke(obj, parameters[i]);
                        methodResult = new TestMethodResult(method.getName(), true, null);
                        System.out.println(classResult.getTestClassName() + "." + method.getName() + "[" + parameters[i] + "] : PASS");
                        classResult.addTestMethodResult(methodResult);
                    }
                } catch (Exception I) {
                    Throwable T = I.getCause();
                    methodResult = new TestMethodResult(method.getName(), false, (AssertionException) T);
                    System.out.println(classResult.getTestClassName() + "." + method.getName() + " : FAIL");
                    classResult.addTestMethodResult(methodResult);
                }
                //after calling the method, we add the method attributes into our classResult

            }
            //we need to see if the method has the proper annotation; if so, run it.
            else if (method.isAnnotationPresent(Test.class)) {
                classResult = generalRunner(method, classResult);
            }
        }
        return classResult;
    }
}


//int = [I
//char = [C
//boolean = [Z
//byte = [B
//short = [S
//long = [J
//float = [F
//double = [D
