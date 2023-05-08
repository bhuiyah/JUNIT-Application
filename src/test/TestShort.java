package test ;
import assignment4 . annotations . Parameterized ;
import assignment4 . annotations . Parameters ;
import assignment4 . annotations . Test ;
import assignment4 . annotations . UseParameters ;
import assignment4 . assertions . Assert ;
@Parameterized

public class TestShort {
    @Parameters
    public static short[] parameters() {
        return new short[]{123, 456, 789};
    }

    @Test
    @UseParameters
    public void testequals(short s){
        Assert.assertEquals(s, s);
    }

    @Test
    @UseParameters
    public void testnotequal(short s){
        Assert.assertEquals(s, 1011);
    }
}