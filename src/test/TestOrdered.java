package test;

import assignment4.annotations.Order;
import assignment4.annotations.Ordered;
import assignment4.annotations.Test;
import assignment4.assertions.Assert;
@Ordered
public class TestOrdered extends TestE {
    @Test
    @Order(0)
    public void a(){
    }

    @Test
    public void b(){
    }

    @Test
    @Order(0)
    public void c(){
    }

    @Test
    @Order(1)
    public void d(){
    }

    @Test
    @Order(2)
    public void e(){
    }

    @Test
    @Order(2)
    public void f(){
    }

    @Test
    @Order(3)
    public void g(){
    }

}
