import org.junit.Test;

import static org.junit.Assert.assertEquals;
public class CustomerTest {
    @Test
    public void noRentalsStatement() {
        assertEquals(
                "Rental record for David\nAmount " +
                        "owed is 0.0\n" +
                        "You earned 0 frequent renter points",
                ObjectMother
                        .customerWithNoRentals(
                                "David").statement());
    }
    @Test
    public void oneNewReleaseStatement() {
        assertEquals(
                "Rental record for John\n\t" +
                        "Godfather 4 9.0\n" +
                        "Amount owed is 9.0\n" +
                        "You earned 2 frequent renter points",
                ObjectMother
                        .customerWithOneNewRelease(
                                "John").statement());
    }

    @Test
    public void allRentalTypesStatement() {
        assertEquals(
                "Rental record for Pat\n\t" +
                        "Godfather 4 9.0\n" +
                        "\tScarface 3.5\n\tLion King 1.5\n" +
                        "Amount owed is 14.0\n" +
                        "You earned 4 frequent renter points",
                ObjectMother
                        .customerWithOneOfEachRentalType(
                                "Pat").statement());
    }
    @Test
    public void
    newReleaseAndRegularStatement() {
        assertEquals(
                "Rental record for Steve\n\t" +
                        "Godfather 4 9.0\n" +
                        "\tScarface 3.5\n" +
                        "Amount owed is 12.5\n" +
                        "You earned 3 frequent renter points",
                ObjectMother
                        .customerWithOneNewReleaseAndOneRegular(
                                "Steve").statement());
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
                ObjectMother
                        .customerWithOneOfEachRentalType(
                                "Pat").htmlStatement());
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