import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CustomerTest {
    Customer john, steve, pat, david;
    String johnName = "John",
            steveName = "Steve",
            patName = "Pat",
            davidName = "David";
    Customer[] customers;

    @Before
    public void setup() {
        david = ObjectMother
                .customerWithNoRentals(davidName);
        john = ObjectMother
                .customerWithOneNewRelease(johnName);
        pat = ObjectMother
                .customerWithOneOfEachRentalType(
                        patName);
        steve = ObjectMother
                .customerWithOneNewReleaseAndOneRegular(
                        steveName);
        customers = new Customer[]{
                david, john, steve, pat};
    }

    @Test
    public void davidStatement() {
        assertEquals(
                "Rental record for David\nAmount " +
                        "owed is 0.0\n" +
                        "You earned 0 frequent renter points",
                david.statement());
    }

    @Test
    public void johnStatement() {
        assertEquals(
                "Rental record for John\n\t" +
                        "Godfather 4\t9.0\n" +
                        "Amount owed is 9.0\n" +
                        "You earned 2 frequent renter points",
                john.statement());
    }

    @Test
    public void patStatement() {
        assertEquals(
                "Rental record for Pat\n\t" +
                        "Godfather 4\t9.0\n" +
                        "\tScarface\t3.5\n" +
                        "\tLion King\t1.5\n" +
                        "Amount owed is 14.0\n" +
                        "You earned 4 frequent renter points",
                pat.statement());
    }

    @Test
    public void steveStatement() {
        assertEquals(
                "Rental record for Steve\n\t" +
                        "Godfather 4\t9.0\n" +
                        "\tScarface\t3.5\n" +
                        "Amount owed is 12.5\n" +
                        "You earned 3 frequent renter points",
                steve.statement());
    }

    @Test
    public void patHtmlStatement() {
        assertEquals(
                "<h1>Rental record for <em>Pat</em></h1>\n" +
                        "<p>Godfather 4 9.0</p>\n" +
                        "<p>Scarface 3.5</p>\n" +
                        "<p>Lion King 1.5</p>\n" +
                        "<p>Amount owed is <em>14.0</em></p>\n" +
                        "<p>You earned <em>4 frequent renter points</em></p>",
                pat.htmlStatement());
    }

    @Test(expected = IllegalArgumentException.class)
    public void invalidTitle() {
        ObjectMother
                .customerWithNoRentals("Bob")
                .addRental(
                        new Rental(
                                new Movie("Crazy, Stupid, Love.",
                                        Type.UNKNOWN),
                                4));
    }

}