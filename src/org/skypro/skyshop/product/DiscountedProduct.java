package org.skypro.skyshop.product;

public class DiscountedProduct extends Product {
    private double basePrice;
    private int discountPercent;

    public DiscountedProduct(String name, double basePrice, int discountPercent) {
        super(name);
        this.basePrice = basePrice;
        this.discountPercent = discountPercent;
    }

    @Override
    public double getPrice() {
        return basePrice * (1 - discountPercent / 100.0);
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

    @Override
    public String toString() {
        return getName() + ": " + getPrice() + " (" + discountPercent + "%)";
    }
}
