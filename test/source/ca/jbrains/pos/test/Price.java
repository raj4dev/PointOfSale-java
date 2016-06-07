package ca.jbrains.pos.test;

public class Price {
    private final int centsValue;

    public Price(int centsValue) {
        this.centsValue = centsValue;
    }

    public static Price cents(int centsValue) {
        return new Price(centsValue);
    }

    public static double priceInDollars(int priceInCents) {
        return Price.cents(priceInCents).dollarValue();
    }

    public double dollarValue() {
        return centsValue / 100.0d;
    }

    @Override
    public String toString() {
        return "a Price";
    }
}
