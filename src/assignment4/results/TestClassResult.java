package assignment4.results;

import java.util.ArrayList;
import java.util.List;

public class TestClassResult {

    private String testClassName;
    private List<TestMethodResult> testMethodResults;

    public TestClassResult(String testClassName) {
        this.testClassName = testClassName;
        this.testMethodResults = new ArrayList<>();
    }

    public String getTestClassName() {
        return this.testClassName;
    }

    public List<TestMethodResult> getTestMethodResults() {
        return this.testMethodResults;
    }

    public void addTestMethodResult(TestMethodResult tmr) {
        this.testMethodResults.add(tmr);
    }
}
