package solitary;

import builder.a;
import com.ep.Customer;
import com.ep.Movie;
import com.ep.Rental;
import com.ep.Type;
import org.junit.Assert;
import org.junit.Test;

import static com.ep.Type.UNKNOWN;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static util.Assert.assertMoney;
import static util.Assert.assertThrows;
import static util.MockitoExtensions.*;

public class CustomerTest {
    @Test
    public void getName() {
        assertEquals(
                "John",
                a.customer.w(
                        "John").build().getName());
    }


    @Test
    public void noRentalsStatement() {
        assertEquals(
                "Rental record for Jim\nAmount owed" +
                        " is 0.0\n" +
                        "You earned 0 frequent renter points",
                a.customer.build().statement());
    }

    @Test
    public void oneRentalStatement() {
        assertEquals(
                "Rental record for Jim\n\tnull\n" +
                        "Amount owed is 0.0\n" +
                        "You earned 0 frequent renter points",
                a.customer.w(
                        mock(Rental.class)).build()
                        .statement());
    }

    @Test
    public void twoRentalsStatement() {
        assertEquals(
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
        assertEquals(
                "<h1>Rental record for <em>Jim</em>" +
                        "</h1>\n<p>Amount owed is <em>0.0" +
                        "</em></p>\n<p>You earned <em>0 " +
                        "frequent renter points</em></p>",
                a.customer.build().htmlStatement());
    }

    @Test
    public void oneRentalHtmlStatement() {
        Rental rental = mock(Rental.class);
        assertEquals(
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
        assertEquals(
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
    public void chargeForNoRentals() {
        assertMoney(
                0.0,
                a.customer.build().getTotalCharge());
    }
    @Test
    public void chargeForOneRental() {
        Rental rental = mock(Rental.class);
        when(rental.getCharge())
                .thenReturn(a.money.w(2.0).build());
        assertMoney(
                2.0,
                a.customer.w(
                        rental).build().getTotalCharge());
    }

    @Test
    public void chargeForTwoRentals_old() {
        Rental rental1 = mock(Rental.class);
        when(rental1.getCharge())
                .thenReturn(a.money.w(2.2).build());
        Rental rental2 = mock(Rental.class);
        when(rental2.getCharge())
                .thenReturn(a.money.w(3.5).build());
        assertMoney(
                5.7,
                a.customer.w(
                        rental1,
                        rental2).build().getTotalCharge());
    }

    @Test
    public void chargeForTwoRentals_new1() {
        Rental rental1 = mock(Rental.class);
        when(rental1.getCharge())
                .thenReturn(a.money.w(2.2).build());
        Rental rental2 = mock(Rental.class);
        when(rental2.getCharge())
                .thenReturn(a.money.w(3.5).build());
        Customer customer = a.customer.build();
        customer.addRental(rental1);
        customer.addRental(rental2);
        assertMoney(
                5.7, customer.getTotalCharge());
    }

    @Test
    public void chargeForTwoRentals_new2() {
        Rental rental1 = mock(Rental.class);
        when(rental1.getCharge())
                .thenReturn(a.money.w(2.2).build());
        Rental rental2 = mock(Rental.class);
        when(rental2.getCharge())
                .thenReturn(a.money.w(3.5).build());
        assertMoney(
                5.7,
                a.customer.build().addRentals(
                        rental1, rental2).getTotalCharge());
    }

    @Test
    public void chargeForTwoRentals_new3() {
        assertMoney(
                5.7,
                a.customer.build().addRentals(
                        create(
                                stub(Rental.class)
                                        .returning(a.money.w(2.2).build())
                                        .from().getCharge()),
                        create(
                                stub(Rental.class)
                                        .returning(a.money.w(3.5).build())
                                        .from().getCharge()))
                        .getTotalCharge());
    }

    @Test
    public void chargeForTwoRentals() {
        assertMoney(
                5.7,
                a.customer.build().addRentals(
                        stub(Rental.class,
                                s -> s.getCharge(),
                                a.money.w(2.2).build()),
                        stub(Rental.class,
                                s -> s.getCharge(),
                                a.money.w(3.5).build()))
                        .getTotalCharge());
    }

    @Test
    public void noRentalsPoints() {
        assertEquals(
                0,
                a.customer.build().getTotalPoints());
    }

    @Test
    public void twoRentalsPoints() {
        Rental rental = mock(Rental.class);
        when(rental.getPoints()).thenReturn(2);
        assertEquals(
                4,
                a.customer.w(
                        rental,
                        rental).build().getTotalPoints());
    }

    @Test
    public void threeRentalsPoints() {
        Rental rental = mock(Rental.class);
        when(rental.getPoints()).thenReturn(2);
        assertEquals(
                6,
                a.customer.w(
                        rental,
                        rental,
                        rental).build().getTotalPoints());
    }

    @Test
    public void recentRentals0Rentals() {
        assertEquals(
                "Recent rentals:",
                a.customer.build().recentRentals());
    }
    @Test
    public void recentRentals1Rental() {
        assertEquals(
                "Recent rentals:\nnull",
                a.customer.w(
                        mock(Rental.class)).build()
                        .recentRentals());
    }
    @Test
    public void recentRentals2Rental() {
        assertEquals(
                "Recent rentals:\nnull\nnull",
                a.customer.w(
                        mock(Rental.class),
                        mock(Rental.class)).build()
                        .recentRentals());
    }
    @Test
    public void recentRentals3Rental() {
        assertEquals(
                "Recent rentals:\nnull\nnull\nnull",
                a.customer.w(
                        mock(Rental.class),
                        mock(Rental.class),
                        mock(Rental.class)).build()
                        .recentRentals());
    }
    @Test
    public void recentRentals4Rental() {
        assertEquals(
                "Recent rentals:\nnull\nnull\nnull",
                a.customer.w(
                        mock(Rental.class),
                        mock(Rental.class),
                        mock(Rental.class),
                        mock(Rental.class)).build()
                        .recentRentals());
    }

    @Test
    public void invalidTitleCustomAssertion() {
        assertThrows(
                IllegalArgumentException.class,
                () -> a.movie.w(UNKNOWN).build());
    }
    @Test
    public void invalidTitleWithoutCA() {
        Exception e = null;
        try {
            a.movie.w(UNKNOWN).build();
        } catch (Exception ex) {
            e = ex;
        }
        assertEquals(
                IllegalArgumentException.class,
                e.getClass());
    }


}