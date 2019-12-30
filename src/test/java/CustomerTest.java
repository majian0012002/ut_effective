import org.junit.Before;
import org.junit.Test;

import java.util.List;

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
                expStatement(
                        "Rental record for %s\n%sAmount " +
                                "owed is %s\nYou earned %s " +
                                "frequent renter points",
                        david,
                        rentalInfo(
                                "\t", "", david.getRentals())),
                david.statement());
    }

    @Test
    public void johnStatement() {
        assertEquals(
                expStatement(
                        "Rental record for %s\n%sAmount " +
                                "owed is %s\nYou earned %s " +
                                "frequent renter points",
                        john,
                        rentalInfo(
                                "\t", "", john.getRentals())),
                john.statement());
    }

    @Test
    public void patStatement() {
        assertEquals(
                expStatement(
                        "Rental record for %s\n%sAmount " +
                                "owed is %s\nYou earned %s " +
                                "frequent renter points",
                        pat,
                        rentalInfo(
                                "\t", "", pat.getRentals())),
                pat.statement());
    }

    @Test
    public void steveStatement() {
        assertEquals(
                expStatement(
                        "Rental record for %s\n%s" +
                                "Amount owed is %s\nYou earned %s " +
                                "frequent renter points",
                        steve,
                        rentalInfo(
                                "\t", "", steve.getRentals())),
                steve.statement());
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


    public static String rentalInfo(
            String startsWith,
            String endsWith,
            List<Rental> rentals) {
        String result = "";
        for (Rental rental : rentals)
            result += String.format(
                    "%s%s\t%s%s\n",
                    startsWith,
                    rental.getMovie().getTitle(),
                    rental.getCharge(),
                    endsWith);
        return result;
    }

    public static String expStatement(
            String formatStr,
            Customer customer,
            String rentalInfo) {
        return String.format(
                formatStr,
                customer.getName(),
                rentalInfo,
                customer.getTotalCharge(),
                customer.getTotalPoints());
    }
}