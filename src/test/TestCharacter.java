package test ;
import assignment4 . annotations . Parameterized ;
import assignment4 . annotations . Parameters ;
import assignment4 . annotations . Test ;
import assignment4 . annotations . UseParameters ;
import assignment4 . assertions . Assert ;
@Parameterized

public class TestCharacter {
    @Parameters
    public static char[] parameters() {
        return new char[]{'a', 'b', 'c'};
    }

    @Test
    @UseParameters
    public void testequals(char c){
        Assert.assertEquals(c, c);
    }

    @Test
    @UseParameters
    public void testnotequal(char c){
        Assert.assertEquals(c, 'd');
    }
}
