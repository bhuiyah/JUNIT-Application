package test ;
import assignment4 . annotations . Parameterized ;
import assignment4 . annotations . Parameters ;
import assignment4 . annotations . Test ;
import assignment4 . annotations . UseParameters ;
import assignment4 . assertions . Assert ;
@Parameterized
public class TestDouble {
    @Parameters
    public static double[] parameters() {
        return new double[]{1, 2, 3};
    }

    @Test
    public void testtrue() {
        Assert.assertTrue(true);
    }

    @Test
    @UseParameters
    public void testequal(double i) {
        Assert.assertEquals(i, i);
    }

    @Test
    @UseParameters
    public void testnotequal(double i) {
        Assert.assertEquals(0, i);
    }
}