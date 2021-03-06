package builder;

import com.ep.*;
public class ObjectMother {
    public static Customer
    customerWithOneOfEachRentalType(
            String name) {
        Customer result =
                customerWithOneNewReleaseAndOneRegular(
                        name);
        result.addRental(
                new Rental(
                        new Movie("Lion King", Type.CHILDREN), 3));
        return result;
    }

    public static Customer
    customerWithOneNewReleaseAndOneRegular(
            String n) {
        Customer result =
                customerWithOneNewRelease(n);
        result.addRental(
                new Rental(
                        new Movie("Scarface", Type.REGULAR), 3));
        return result;
    }

    public static Customer
    customerWithOneNewRelease(
            String name) {
        Customer result =
                customerWithNoRentals(name);
        result.addRental(
                new Rental(
                        new Movie(
                                "Godfather 4", Type.NEW_RELEASE), 3));
        return result;
    }

    public static Customer
    customerWithNoRentals(String name) {
        return new Customer(name);
    }
}
