package solitary;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import builder.a;
public class MoneyTest {
    @Test
    public void doubleAddition() {
        assertEquals(
                11.0,
                a.money.w(1.0).build().add(
                        10.0).toDouble(),
                0);
    }
    @Test
    public void moneyAddition() {
        assertEquals(
                11.0,
                a.money.w(1.0).build().add(
                        a.money.w(10.0).build()).toDouble(),
                0);
    }
    @Test
    public void oneDecimalToDouble() {
        assertEquals(
                1.0,
                a.money.w(1.0).build().toDouble(),
                0);
    }

    @Test
    public void twoDecimalToDouble() {
        assertEquals(
                1.12,
                a.money.w(1.12).build().toDouble(),
                0);
    }
    @Test
    public void thrDecimalDownToDouble() {
        assertEquals(
                1.12,
                a.money.w(1.123).build().toDouble(),
                0);
    }
    @Test
    public void thrDecimalUpToDouble() {
        assertEquals(
                1.13,
                a.money.w(1.125).build().toDouble(),
                0);
    }
}