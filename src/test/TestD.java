package test;

import assignment4.annotations.Alphabetical;
import assignment4.annotations.Test;
import assignment4.assertions.Assert;

@Alphabetical
public class TestD {
    @Test
    public void testA() {
        Assert.assertEquals(1, 1);
    }
    @Test
    public void testC() {
        Assert.assertEquals(3, 1 + 2);
    }
    @Test
    public void atest() {
        Assert.assertEquals(2, 2);
    }
    @Test
    public void b0() {
    }
    @Test
    public void foo() {
    }
    @Test
    public void foo1() {
    }
}
