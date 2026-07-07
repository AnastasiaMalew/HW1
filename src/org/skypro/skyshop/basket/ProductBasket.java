package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductBasket {
    private final List<Product> items;

    public ProductBasket() {
        this.items = new ArrayList<>();
    }

    public void add(Product product) {
        items.add(product);
    }

    public double getTotalCost() {
        double total = 0.0;
        for (Product p : items) {
            total += p.getPrice();
        }
        return total;
    }

    public void printBasketContents() {
        if (items.isEmpty()) {
            System.out.println("В корзине пусто");
            return;
        }

        double total = 0.0;
        int specialCount = 0;

        for (Product p : items) {
            System.out.println(p.toString());
            total += p.getPrice();
            if (p.isSpecial()) {
                specialCount++;
            }
        }

        System.out.println("Итого: " + total);
        System.out.println("Специальных товаров: " + specialCount);
    }

    public boolean containsProductByName(String name) {
        if (name == null) {
            return false;
        }
        for (Product p: items) {
            if (p != null && p.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void clearBasket() {
        items.clear();
    }
}

