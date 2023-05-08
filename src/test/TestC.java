package test;

import assignment4.annotations.Test;
import assignment4.assertions.Assert;

public class TestC {
    @Test
    public void test4() {
        Assert.assertEquals(3, 2);
    }
    @Test
    public void test5() {
        Assert.assertEquals(3, 1 + 2);
    }
    @Test
    public void test6() {
        Assert.assertEquals(2, 2);
    }
}
