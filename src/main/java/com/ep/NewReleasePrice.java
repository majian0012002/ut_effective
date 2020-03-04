package com.ep;

public class NewReleasePrice extends Price {
    @Override
    public Money getCharge(int daysRented) {
        return new Money(daysRented * 3);
    }

    @Override
    int getPoints(int daysRented) {
        if (daysRented > 1)
            return 2;
        return 1;
    }
}
