package solitary;
import builder.a;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RegularPriceTest {

    private double basePrice;
    private double multiplier;

    @Before
    public void setUp() throws Exception {
        basePrice = 2.0;
        multiplier = 1.5;
    }

    @Test
    public void chargeWithStaticVal() {
        assertEquals(
                basePrice,
                a.regularPrice.build().getCharge(2),
                0);
    }
    @Test
    public void chargeWithLocalVal() {
        int daysRented = 4;
        double charge =
                basePrice + (
                        daysRented - 2) * multiplier;
        assertEquals(
                charge,
                a.regularPrice.build().getCharge(
                        daysRented),
                0);
    }
    @Test
    public void chargeWithLiteral() {
        assertEquals(
                5.0,
                a.regularPrice.build().getCharge(4),
                0);
    }
}