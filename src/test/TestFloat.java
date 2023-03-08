package test ;
import assignment4 . annotations . Parameterized ;
import assignment4 . annotations . Parameters ;
import assignment4 . annotations . Test ;
import assignment4 . annotations . UseParameters ;
import assignment4 . assertions . Assert ;
@Parameterized

public class TestFloat {
    @Parameters
    public static float[] parameters() {
        return new float[]{1.1f, 2.2f, 3.3f};
    }

    @Test
    @UseParameters
    public void testequals(float f){
        Assert.assertEquals(f, f);
    }

    @Test
    @UseParameters
    public void testnotequal(float f){
        Assert.assertEquals(f, 0.0f);
    }
}
