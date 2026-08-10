package org.skypro.skyshop.product;

public class DiscountedProduct extends Product {
    private double basePrice;
    private int discountPercent;

    public DiscountedProduct(String name, double basePrice, int discountPercent) {
        super(name);

        if(basePrice <= 0) {
            throw new IllegalArgumentException("Цена должна быть больше 0");
        }
        if(discountPercent <= 0 || discountPercent >= 100) {
            throw new IllegalArgumentException("Скидка должна в диапазоне от 0 до 100");
        }
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
