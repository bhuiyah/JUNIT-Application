package test ;
import assignment4 . annotations . Parameterized ;
import assignment4 . annotations . Parameters ;
import assignment4 . annotations . Test ;
import assignment4 . annotations . UseParameters ;
import assignment4 . assertions . Assert ;
@Parameterized

public class TestInteger {
    @Parameters
    public static int[] parameters() {
        return new int[]{0, 1, 2, 3};
    }

    @Test
    @UseParameters
    public void testeqauls(int i){
        Assert.assertEquals(i, i);
    }

    @Test
    @UseParameters
    public void testnotequals(int i){
        Assert.assertEquals(i, 5);
    }
}
