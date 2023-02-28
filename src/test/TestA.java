package test;

import assignment4.annotations.Test;
import assignment4.assertions.Assert;

public class TestA {
    @Test
    public void test1() {
        Assert.assertTrue(true);
    }
    @Test
    public void test2() {
        Assert.assertEquals(3, 1 + 2);
    }
}
