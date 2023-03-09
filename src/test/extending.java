package test;

import assignment4.annotations.Test;
import assignment4.assertions.Assert;

public class extending extends TestA{
    @Test
    @Override
    public void test1() {
        Assert.assertTrue(false);
    }

    @Test
    public void test3(){
        Assert.assertTrue(true);
    }

    @Test
    public void test4(){
        super.test2();
    }

    @Test
    public void test5() {
        super.test1();
    }
}
