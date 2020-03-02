package solitary;

import builder.a;
import com.ep.Movie;
import com.ep.Rental;
import com.ep.Type;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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
    public void noRentalsCharge() {
        assertEquals(
                0.0,
                a.customer.build().getTotalCharge(),
                0);
    }

    @Test
    public void twoRentalsCharge() {
        Rental rental = mock(Rental.class);
        when(rental.getCharge()).thenReturn(2.0);
        assertEquals(
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
        assertEquals(
                6.0,
                a.customer.w(
                        rental,
                        rental,
                        rental).build().getTotalCharge(),
                0);
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
    public void recentRentalsWith2Rentals() {
        Movie godfather = mock(Movie.class);
        when(godfather
                .getTitle("%s starring %s %s", 2))
                .thenReturn("Godfather 4");
        Rental godfatherRental =
                mock(Rental.class);
        when(godfatherRental.getMovie(true))
                .thenReturn(godfather);
        Movie lionKing = mock(Movie.class);
        when(lionKing
                .getTitle("%s starring %s %s", 2))
                .thenReturn("Lion King");
        Rental lionKingRental =
                mock(Rental.class);
        when(lionKingRental.getMovie(true))
                .thenReturn(lionKing);
        assertEquals(
                "Recent rentals:\nGodfather 4\n" +
                        "Lion King",
                a.customer.w(
                        godfatherRental, lionKingRental)
                        .build().recentRentals());
    }

    @Test
    public void recentRentalsWith3Rentals() {
// same structure as above, with
// 8 more lines of mocking code,
// 25% longer expected value, and
// 2 lines of adding rentals to customer
    }

    @Test
    public void recentRentalsWith4Rentals() {
        Movie godfather = mock(Movie.class);
        when(godfather
                .getTitle("%s starring %s %s", 2))
                .thenReturn("Godfather 4");
        Rental godfatherRental =
                mock(Rental.class);
        when(godfatherRental.getMovie(true))
                .thenReturn(godfather);
        Movie lionKing = mock(Movie.class);
        when(lionKing
                .getTitle("%s starring %s %s", 2))
                .thenReturn("Lion King");
        Rental lionKingRental =
                mock(Rental.class);
        when(lionKingRental.getMovie(true))
                .thenReturn(lionKing);
        Movie scarface = mock(Movie.class);
        when(scarface
                .getTitle("%s starring %s %s", 2))
                .thenReturn("Scarface");
        Rental scarfaceRental =
                mock(Rental.class);
        when(scarfaceRental.getMovie(true))
                .thenReturn(scarface);
        Movie notebook = mock(Movie.class);
        when(notebook
                .getTitle("%s starring %s %s", 2))
                .thenReturn("Notebook");
        Rental notebookRental =
                mock(Rental.class);
        when(notebookRental.getMovie(true))
                .thenReturn(notebook);
        assertEquals(
                "Recent rentals:"+
                        "\nGodfather 4\nLion King" +
                        "\nScarface",
                a.customer.w(
                        godfatherRental, lionKingRental,
                        scarfaceRental, notebookRental)
                        .build().recentRentals());
    }
}