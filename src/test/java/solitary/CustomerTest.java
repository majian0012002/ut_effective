package solitary;

import builder.a;
import com.ep.Rental;
import com.ep.Type;
import org.junit.Assert;
import org.junit.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomerTest {
    @Test
    public void getName() {
        Assert.assertEquals(
                "John",
                a.customer.w(
                        "John").build().getName());
    }


    @Test
    public void noRentalsStatement() {
        Assert.assertEquals(
                "Rental record for Jim\nAmount owed" +
                        " is 0.0\n" +
                        "You earned 0 frequent renter points",
                a.customer.build().statement());
    }

    @Test
    public void oneRentalStatement() {
        Assert.assertEquals(
                "Rental record for Jim\n\tnull\n" +
                        "Amount owed is 0.0\n" +
                        "You earned 0 frequent renter points",
                a.customer.w(
                        mock(Rental.class)).build()
                        .statement());
    }

    @Test
    public void twoRentalsStatement() {
        Assert.assertEquals(
                "Rental record for Jim\n\t" +
                        "null\n\tnull\n" +
                        "Amount owed is 0.0\n" +
                        "You earned 0 frequent renter points",
                a.customer.w(
                        mock(Rental.class),
                        mock(Rental.class)).build()
                        .statement());
    }

    @Test
    public void noRentalsHtmlStatement() {
        Assert.assertEquals(
                "<h1>Rental record for <em>Jim</em>" +
                        "</h1>\n<p>Amount owed is <em>0.0" +
                        "</em></p>\n<p>You earned <em>0 " +
                        "frequent renter points</em></p>",
                a.customer.build().htmlStatement());
    }

    @Test
    public void oneRentalHtmlStatement() {
        Rental rental = mock(Rental.class);
        Assert.assertEquals(
                "<h1>Rental record for <em>Jim</em>" +
                        "</h1>\n<p>null</p>\n<p>Amount owed " +
                        "is <em>0.0</em></p>\n<p>You earned " +
                        "<em>0 frequent renter points</em>" +
                        "</p>",
                a.customer.w(
                        mock(Rental.class)).build()
                        .htmlStatement());
    }

    @Test
    public void twoRentalsHtmlStatement() {
        Assert.assertEquals(
                "<h1>Rental record for <em>Jim</em>" +
                        "</h1>\n<p>null</p>\n<p>null</p>\n" +
                        "<p>Amount owed is <em>0.0</em></p>" +
                        "\n<p>You earned <em>0 frequent" +
                        " renter points</em></p>",
                a.customer.w(
                        mock(Rental.class),
                        mock(Rental.class)).build()
                        .htmlStatement());
    }

    @Test
    public void noRentalsCharge() {
        Assert.assertEquals(
                0.0,
                a.customer.build().getTotalCharge(),
                0);
    }

    @Test
    public void twoRentalsCharge() {
        Rental rental = mock(Rental.class);
        when(rental.getCharge()).thenReturn(2.0);
        Assert.assertEquals(
                4.0,
                a.customer.w(
                        rental,
                        rental).build().getTotalCharge(),
                0);
    }

    @Test
    public void threeRentalsCharge() {
        Rental rental = mock(Rental.class);
        when(rental.getCharge()).thenReturn(2.0);
        Assert.assertEquals(
                6.0,
                a.customer.w(
                        rental,
                        rental,
                        rental).build().getTotalCharge(),
                0);
    }

    @Test
    public void noRentalsPoints() {
        Assert.assertEquals(
                0,
                a.customer.build().getTotalPoints());
    }

    @Test
    public void twoRentalsPoints() {
        Rental rental = mock(Rental.class);
        when(rental.getPoints()).thenReturn(2);
        Assert.assertEquals(
                4,
                a.customer.w(
                        rental,
                        rental).build().getTotalPoints());
    }

    @Test
    public void threeRentalsPoints() {
        Rental rental = mock(Rental.class);
        when(rental.getPoints()).thenReturn(2);
        Assert.assertEquals(
                6,
                a.customer.w(
                        rental,
                        rental,
                        rental).build().getTotalPoints());
    }


}