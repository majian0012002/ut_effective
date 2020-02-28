package com.ep;
public class ChildrensPrice extends Price {
    @Override
    double getCharge(int daysRented) {
        double amount = 1.5;
        if (daysRented > 2) // *was 3*
            amount += (daysRented - 2) * 1.5;
        return amount;
    }
}