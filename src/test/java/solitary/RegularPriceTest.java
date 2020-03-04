package solitary;
import builder.a;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static util.Assert.assertMoney;

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
        assertMoney(
                basePrice,
                a.regularPrice.build().getCharge(2));
    }
    @Test
    public void chargeWithLocalVal() {
        int daysRented = 4;
        double charge =
                basePrice + (
                        daysRented - 2) * multiplier;
        assertMoney(
                charge,
                a.regularPrice.build().getCharge(
                        daysRented));
    }
    @Test
    public void chargeWithLiteral() {
        assertMoney(
                5.0,
                a.regularPrice.build().getCharge(4));
    }
}