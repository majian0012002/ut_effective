import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

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
    public void allRentalTypesStatement() {
        assertEquals(
                "Rental record for Pat\n" +
                        "\tGodfather 4 9.0\n" +
                        "\tScarface 3.5\n" +
                        "\tLion King 1.5\n" +
                        "Amount owed is 14.0\n" +
                        "You earned 4 frequent renter points",
                a.customer.w("Pat").w(
                        a.rental.w(a.movie.w(Type.NEW_RELEASE)),
                        a.rental.w(
                                a.movie.w("Scarface").w(Type.REGULAR)),
                        a.rental.w(
                                a.movie.w("Lion King").w(
                                        Type.CHILDREN))).build().statement());
    }



    @Test
    public void allRentalTypesHtmlStatement() {
        assertEquals(
                "<h1>Rental record for <em>Pat</em>" +
                        "</h1>\n<p>Godfather 4 9.0</p>\n" +
                        "<p>Scarface 3.5</p>\n<p>Lion King" +
                        " 1.5</p>\n<p>Amount owed is <em>" +
                        "14.0</em></p>\n<p>You earned <em>" +
                        "4 frequent renter points</em></p>",
                a.customer.w("Pat").w(
                        a.rental.w(a.movie.w(Type.NEW_RELEASE)),
                        a.rental.w(
                                a.movie.w("Scarface").w(Type.REGULAR)),
                        a.rental.w(
                                a.movie.w("Lion King").w(
                                        Type.CHILDREN))).build()
                        .htmlStatement());
    }

}