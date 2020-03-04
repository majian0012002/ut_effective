package com.ep;

public abstract class Price {
    abstract Money getCharge(int daysRented);

    int getPoints(int daysRented) {
        return 1;
    }
}
