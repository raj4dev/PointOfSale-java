package ca.jbrains.pos.test;

public class Price {
    public static Price cents(int centsValue) {
        return new Price();
    }

    public static double priceInDollars(int priceInCents) {
        return priceInCents / 100.0d;
    }

    @Override
    public String toString() {
        return "a Price";
    }
}
