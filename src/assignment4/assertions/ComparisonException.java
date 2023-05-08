package assignment4.assertions;

/**
 * DO NOT MODIFY THIS CLASS
 */

public class ComparisonException extends AssertionException {

    protected Object expected;
    protected Object actual;

    public ComparisonException(Object expected, Object actual) {
        this(null, expected, actual);
    }

    public ComparisonException(String message, Object expected, Object actual) {
        super(message);
        this.expected = expected;
        this.actual = actual;
    }
}
