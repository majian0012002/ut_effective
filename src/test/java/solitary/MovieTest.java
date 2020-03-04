package solitary;
import org.junit.Assert;
import org.junit.Test;
import com.ep.*;

import static com.ep.Type.CHILDREN;
import static org.junit.Assert.assertEquals;
import static util.Assert.assertMoney;
import static util.Assert.assertThrows;

import builder.*;
public class MovieTest {


    @Test
    public void getChargeForChildrens1Day() {
        assertMoney(
                1.5,
                a.movie.w(
                        CHILDREN).build().getCharge(1));
    }
    @Test
    public void getChargeForChildrens2Day() {
        assertMoney(
                1.5,
                a.movie.w(
                        CHILDREN).build().getCharge(2));
    }
    @Test
    public void getChargeForChildrens3Day() {
        assertMoney(
                1.5,
                a.movie.w(
                        CHILDREN).build().getCharge(3));
    }
    @Test
    public void getChargeForChildrens4Day() {
        assertMoney(
                4.5,
                a.movie.w(
                        CHILDREN).build().getCharge(4));
    }

    @Test
    public void getChargeForChildrens5Day() {
        assertMoney(
                6.0,
                a.movie.w(
                        CHILDREN).build().getCharge(5));
    }


    @Test
    public void getChargeForNewRelease() {
        assertEquals(
                3.0,
                a.movie.w(
                        Type.NEW_RELEASE).build().getCharge(1).toDouble(),
                0);
        assertEquals(
                6.0,
                a.movie.w(
                        Type.NEW_RELEASE).build().getCharge(2).toDouble(),
                0);
        assertEquals(
                9.0,
                a.movie.w(
                        Type.NEW_RELEASE).build().getCharge(3).toDouble(),
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


}