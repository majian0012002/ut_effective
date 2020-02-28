import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MovieTest {
    /*
    //违反了 Assert Last原则
    @Test(expected = IllegalArgumentException.class)
    public void invalidTitle() {
        a.movie.w(Type.UNKNOWN).build();
    }
    */

/*

    //仍然违反了 Assert Last: fail不是测试中最后的断言，而且容易忘记写
    @Test
    public void invalidTitle() {
        try {
            a.movie.w(Type.UNKNOWN).build();
            Assert.fail();
        } catch (Exception ex) {
            assertEquals(
                    IllegalArgumentException.class,
                    ex.getClass());
        }
    }
*/

/*
    // 为会出现误报，但是不优雅
    @Test
    public void invalidTitle() {
        Exception e = null;
        try {
            a.movie.w(Type.UNKNOWN).build();
        } catch (Exception ex) {
            e = ex;
        }
        assertEquals(
                IllegalArgumentException.class,
                e.getClass());
    }
*/

    @Test
    public void invalidTitle() {
        Runnable runnable = () ->
                a.movie.w(Type.UNKNOWN).build();
        assertThrows(
                IllegalArgumentException.class,
                runnable);
    }

    public void assertThrows(
            Class ex, Runnable runnable) {
        Exception exThrown = null;
        try {
            runnable.run();
        } catch (Exception exThrownActual) {
            exThrown = exThrownActual;
        }
        if (null == exThrown)
            Assert.fail("No exception thrown");
        else
            assertEquals(ex, exThrown.getClass());
    }

}