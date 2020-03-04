package com.ep;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Customer {
    private String name;
    private List<Rental> rentals =
            new ArrayList<Rental>();

    public Customer(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void addRental(Rental rental) {
        rentals.add(rental);
    }

    public Customer addRentals(
            Rental... newRentals) {
        rentals.addAll(Arrays.asList(newRentals));
        return this;
    }

    public String statement() {
        String result =
                "Rental record for " + getName() + "\n";
        for (Rental rental : rentals)
            result +=
                    "\t" + rental.getLineItem() + "\n";
        result +=
                "Amount owed is " + getTotalCharge() +
                        "\n" + "You earned " +
                        getTotalPoints() +
                        " frequent renter points";
        return result;
    }

    public String htmlStatement() {
        String result =
                "<h1>Rental record for <em>" +
                        getName() + "</em></h1>\n";
        for (Rental rental : rentals)
            result += "<p>" + rental.getLineItem() +
                    "</p>\n";
        result +=
                "<p>Amount owed is <em>" +
                        getTotalCharge() + "</em></p>\n" +
                        "<p>You earned <em>" +
                        getTotalPoints() +
                        " frequent renter points</em></p>";
        return result;
    }

    public Money getTotalCharge() {
        Money total = new Money(0.0);
        for (Rental rental : rentals)
            total = total.add(rental.getCharge());
        return total;
    }

    public int getTotalPoints() {
        int total = 0;
        for (Rental rental : rentals)
            total += rental.getPoints();
        return total;
    }

    public String recentRentals() {
        String result = "Recent rentals:";
        for (int i=0;
             i < rentals.size() && i < 3;
             i++) {
            result +=
                    "\n" + rentals.get(i).getTitle();
        }
        return result;
    }

}