package test ;
import assignment4 . annotations . Parameterized ;
import assignment4 . annotations . Parameters ;
import assignment4 . annotations . Test ;
import assignment4 . annotations . UseParameters ;
import assignment4 . assertions . Assert ;
@Parameterized

public class TestBoolean {
    @Parameters
    public static Boolean[] parameters() {
        return new Boolean[]{true, false};
    }

    @Test
    @UseParameters
    public void testexpectedtrue(Boolean b){
        Assert.assertEquals(true, b);
    }

    @Test
    @UseParameters
    public void testexpectedfalse(Boolean b){
        Assert.assertEquals(false, b);
    }
}
