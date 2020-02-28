package sociable;

import org.junit.Assert;
import org.junit.Test;
import com.ep.*;
import builder.a;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CustomerTest {

    @Test
    public void allRentalTypesStatement() {
        Assert.assertEquals(
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
        Assert.assertEquals(
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