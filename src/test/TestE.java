package test;

import assignment4.annotations.Order;
import assignment4.annotations.Ordered;
import assignment4.annotations.Test;
import assignment4.assertions.Assert;

@Ordered
public class TestE {
    @Test
    @Order(3)
    public void testA() {
        Assert.assertEquals(1, 1);
    }
    @Test
    @Order(2)
    public void testC() {
        Assert.assertEquals(3, 1 + 2);
    }
    @Test
    @Order(1)
    public void atest() {
        Assert.assertEquals(3, 3);
    }
    @Test
    @Order(4)
    public void b0() {
    }
    @Test
    @Order(0)
    public void foo() {
    }
    @Test
    @Order(6)
    public void foo1() {
    }
}
