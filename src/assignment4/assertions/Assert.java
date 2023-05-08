package assignment4.assertions;

/**
 * DO NOT MODIFY THIS CLASS
 */

public class Assert {

    // Simple true/false assertion checks

    public static void assertTrue(String message, boolean condition) {
        if (!condition) {
            fail(message);
        }
    }

    public static void assertTrue(boolean condition) {
        assertTrue(null, condition);
    }

    public static void assertFalse(String message, boolean condition) {
        if (condition) {
            fail(message);
        }
    }

    public static void assertFalse(boolean condition) {
        assertFalse(null, condition);
    }

    // Comparing equality assertion checks

    public static void assertEquals(String message, Object expected, Object actual) {
        if ((expected == null && actual != null) || (expected != null && actual == null)) {
            failNotEquals(message, expected, actual);
        } else if (expected != null && actual != null) {
            if (!expected.equals(actual)) {
                failNotEquals(message, expected, actual);
            }
        }
    }

    public static void assertEquals(Object expected, Object actual) {
        assertEquals(null, expected, actual);
    }

    public static void assertEquals(String message, byte expected, byte actual) {
        if (expected != actual) {
            failNotEquals(message, Byte.valueOf(expected), Byte.valueOf(actual));
        }
    }

    public static void assertEquals(byte expected, byte actual) {
        assertEquals(null, expected, actual);
    }

    public static void assertEquals(String message, char expected, char actual) {
        if (expected != actual) {
            failNotEquals(message, Character.valueOf(expected), Character.valueOf(actual));
        }
    }

    public static void assertEquals(char expected, char actual) {
        assertEquals(null, expected, actual);
    }

    public static void assertEquals(String message, short expected, short actual) {
        if (expected != actual) {
            failNotEquals(message, Short.valueOf(expected), Short.valueOf(actual));
        }
    }

    public static void assertEquals(short expected, short actual) {
        assertEquals(null, expected, actual);
    }

    public static void assertEquals(String message, int expected, int actual) {
        if (expected != actual) {
            failNotEquals(message, Integer.valueOf(expected), Integer.valueOf(actual));
        }
    }

    public static void assertEquals(int expected, int actual) {
        assertEquals(null, expected, actual);
    }

    public static void assertEquals(String message, long expected, long actual) {
        if (expected != actual) {
            failNotEquals(message, Long.valueOf(expected), Long.valueOf(actual));
        }
    }

    public static void assertEquals(long expected, long actual) {
        assertEquals(null, expected, actual);
    }

    public static void assertEquals(String message, float expected, float actual) {
        if (expected != actual) {
            failNotEquals(message, Float.valueOf(expected), Float.valueOf(actual));
        }
    }

    public static void assertEquals(float expected, float actual) {
        assertEquals(null, expected, actual);
    }

    public static void assertEquals(String message, double expected, double actual) {
        if (expected != actual) {
            failNotEquals(message, Double.valueOf(expected), Double.valueOf(actual));
        }
    }

    public static void assertEquals(double expected, double actual) {
        assertEquals(null, expected, actual);
    }

    private static void fail(String message) {
        if (message == null) {
            throw new AssertionException();
        }
        throw new AssertionException(message);
    }

    private static void failNotEquals(String message, Object expected, Object actual) {
        if (message == null) {
            throw new ComparisonException(expected, actual);
        }
        throw new ComparisonException(message, expected, actual);
    }
}
