package assignment4.runners;

import assignment4.annotations.Parameters;
import assignment4.annotations.Test;
import assignment4.annotations.UseParameters;
import assignment4.assertions.AssertionException;
import assignment4.results.TestClassResult;
import assignment4.results.TestMethodResult;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class ParameterizedTestRunner extends TestRunner {

    public ParameterizedTestRunner(Class testClass) {
        super(testClass);
    }

    public TestClassResult runParameterized() throws InstantiationException, IllegalAccessException, InvocationTargetException {
        TestClassResult classResult = new TestClassResult(testClass.getName());
//        for(Method method: testClass.getDeclaredMethods()){
//            if(method.isAnnotationPresent(Parameters.class)){
//                Object obj = testClass.newInstance();
//                Object [] Parameters = method.invoke(obj);
//            }
//        }

        for(Method method: testClass.getDeclaredMethods()) {
            if(method.isAnnotationPresent((Test.class)) && method.isAnnotationPresent(UseParameters.class)){
                Object obj = testClass.newInstance();
                TestMethodResult methodResult = null;
                try{
                    for(Object parameter: Parameters){
                        method.invoke(obj, parameter);
                        methodResult = new TestMethodResult(method.getName(), true, null);
                        System.out.println(classResult.getTestClassName() + "." + method.getName() + "[" + parameter + "] : PASS");
                    }
                }
                catch (Exception I){
                    Throwable T = I.getCause();
                    methodResult = new TestMethodResult(method.getName(), false, (AssertionException) T);
                    System.out.println(classResult.getTestClassName() + "." + method.getName() + " : FAIL");
                }
                //after calling the method, we add the method attributes into our classResult
                classResult.addTestMethodResult(methodResult);
                }
            //we need to see if the method has the proper annotation; if so, run it.
            else if (method.isAnnotationPresent(Test.class)) {
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
