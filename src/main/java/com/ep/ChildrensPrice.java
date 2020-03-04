package com.ep;
public class ChildrensPrice extends Price {
    @Override
    Money getCharge(int daysRented) {
        double amount = 1.5;
        if (daysRented > 3)
            amount += (daysRented - 2) * 1.5;
        return new Money(amount);
    }
}
