package solitary;
import org.junit.Assert;
import org.junit.Test;
import com.ep.*;

import static com.ep.Type.CHILDREN;
import static org.junit.Assert.assertEquals;
import builder.*;
public class MovieTest {

    @Test
    public void getChargeForRegular() {
        assertEquals(
                2.0,
                a.movie.w(
                        Type.REGULAR).build().getCharge(1),
                0);
        assertEquals(
                2.0,
                a.movie.w(
                        Type.REGULAR).build().getCharge(2),
                0);
        assertEquals(
                3.5,
                a.movie.w(
                        Type.REGULAR).build().getCharge(3),
                0);
        assertEquals(
                5.0,
                a.movie.w(
                        Type.REGULAR).build().getCharge(4),
                0);
    }

    @Test
    public void getChargeForChildrens1Day() {
        assertEquals(
                1.5,
                a.movie.w(
                        CHILDREN).build().getCharge(1),
                0);
    }
    @Test
    public void getChargeForChildrens2Day() {
        assertEquals(
                1.5,
                a.movie.w(
                        CHILDREN).build().getCharge(2),
                0);
    }
    @Test
    public void getChargeForChildrens3Day() {
        assertEquals(
                1.5,
                a.movie.w(
                        CHILDREN).build().getCharge(3),
                0);
    }
    @Test
    public void getChargeForChildrens4Day() {
        assertEquals(
                3.0,
                a.movie.w(
                        CHILDREN).build().getCharge(4),
                0);
    }
    @Test
    public void getChargeForChildrens5Day() {
        assertEquals(
                4.5,
                a.movie.w(
                        CHILDREN).build().getCharge(5),
                0);
    }


    @Test
    public void getChargeForNewRelease() {
        assertEquals(
                3.0,
                a.movie.w(
                        Type.NEW_RELEASE).build().getCharge(1),
                0);
        assertEquals(
                6.0,
                a.movie.w(
                        Type.NEW_RELEASE).build().getCharge(2),
                0);
        assertEquals(
                9.0,
                a.movie.w(
                        Type.NEW_RELEASE).build().getCharge(3),
                0);
    }

    @Test
    public void getPointsForChildrens() {
        assertEquals(
                1,
                a.movie.w(
                        CHILDREN).build().getPoints(1));
        assertEquals(
                1,
                a.movie.w(
                        CHILDREN).build().getPoints(2));
    }
    @Test
    public void getPointsForNewRelease() {
        assertEquals(
                1,
                a.movie.w(
                        Type.NEW_RELEASE).build().getPoints(1));
        assertEquals(
                2,
                a.movie.w(
                        Type.NEW_RELEASE).build().getPoints(2));
        assertEquals(
                2,
                a.movie.w(
                        Type.NEW_RELEASE).build().getPoints(3));
    }

    @Test
    public void getPointsForRegular() {
        assertEquals(
                1,
                a.movie.w(
                        Type.REGULAR).build().getPoints(1));
        assertEquals(
                1,
                a.movie.w(
                        Type.REGULAR).build().getPoints(2));
    }

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