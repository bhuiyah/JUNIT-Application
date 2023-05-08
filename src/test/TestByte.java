package test ;
import assignment4 . annotations . Parameterized ;
import assignment4 . annotations . Parameters ;
import assignment4 . annotations . Test ;
import assignment4 . annotations . UseParameters ;
import assignment4 . assertions . Assert ;
@Parameterized

public class TestByte {
    @Parameters
    public static byte[] parameters() {
        return new byte[]{-128, 127};
    }

    @Test
    @UseParameters
    public void testequals(byte b){
        Assert.assertEquals(b, b);
    }

    @Test
    @UseParameters
    public void testnotequal(byte b){
        Assert.assertEquals(b, b - b);
    }
}