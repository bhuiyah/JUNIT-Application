package test ;
import assignment4 . annotations . Parameterized ;
import assignment4 . annotations . Parameters ;
import assignment4 . annotations . Test ;
import assignment4 . annotations . UseParameters ;
import assignment4 . assertions . Assert ;
@Parameterized
public class TestF {
    @Parameters
    public static double [] parameters () {
        return new double []{1 , 2, 3};
    }
    @Test
    public void test1 () {
        Assert . assertTrue ( true );
    }
    @Test
    @UseParameters
    public void test2 ( double i ) {
        Assert . assertEquals (i , i );
    }
}