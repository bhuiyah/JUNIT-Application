package test;

import assignment4.annotations.Test;
import assignment4.assertions.Assert;
import assignment4.annotations.Alphabetical;

@Alphabetical
public class TestAlphabetical {
    @Test
    public void aaa(){
        Assert.assertTrue(true);
    }
    @Test
    public void a(){
        Assert.assertTrue(true);
    }
    @Test
    public void aa(){
        Assert.assertTrue(true);
    }

    @Test
    public void abc(){
        Assert.assertTrue(true);
    }
    @Test
    public void abb(){
        Assert.assertTrue(true);
    }
    @Test
    public void aba(){
        Assert.assertTrue(true);
    }
}
