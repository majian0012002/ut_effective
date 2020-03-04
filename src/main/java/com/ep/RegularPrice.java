package com.ep;

public class RegularPrice extends Price {
    @Override
    public Money getCharge(int daysRented) {
        double amount = 2;
        if (daysRented > 2)
            amount += (daysRented - 2) * 1.5;
        return new Money(amount);
    }
}
