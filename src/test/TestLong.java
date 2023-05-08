package test ;
import assignment4 . annotations . Parameterized ;
import assignment4 . annotations . Parameters ;
import assignment4 . annotations . Test ;
import assignment4 . annotations . UseParameters ;
import assignment4 . assertions . Assert ;
@Parameterized

public class TestLong {
    @Parameters
    public static long[] parameters() {
        return new long[]{123456789, 987654321};
    }

    @Test
    @UseParameters
    public void testequals(long l){
        Assert.assertEquals(l, l);
    }

    @Test
    @UseParameters
    public void testnotequal(long l){
        Assert.assertEquals(l, l + l);
    }
}