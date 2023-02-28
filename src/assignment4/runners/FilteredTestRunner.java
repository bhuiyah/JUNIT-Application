package assignment4.runners;

import java.util.List;

public class FilteredTestRunner extends TestRunner {

    public FilteredTestRunner(Class testClass, List<String> testMethods) {
        super(testClass);
    }
}
