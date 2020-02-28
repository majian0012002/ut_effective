import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MovieTest {
    /*
    //Υ���� Assert Lastԭ��
    @Test(expected = IllegalArgumentException.class)
    public void invalidTitle() {
        a.movie.w(Type.UNKNOWN).build();
    }
    */

/*

    //��ȻΥ���� Assert Last: fail���ǲ��������Ķ��ԣ�������������д
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
    // Ϊ������󱨣����ǲ�����
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