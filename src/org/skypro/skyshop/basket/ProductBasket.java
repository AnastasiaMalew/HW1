package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.*;

public class ProductBasket {
    private final Map<String, List<Product>> itemsByProductName;

    public ProductBasket() {
        this.itemsByProductName = new HashMap<>();
    }

    public void add(Product product) {
        if (product == null || product.getName() == null) {
            return;
        }
        String name = product.getName();
        itemsByProductName
                .computeIfAbsent(name, k -> new LinkedList<>())
                .add(product);
    }

    public double getTotalCost() {
        double total = 0.0;
        for (List<Product> products : itemsByProductName.values()) {
            for (Product p : products) {
                total += p.getPrice();
            }
        }
        return total;
    }

    public void printBasketContents() {
        List<Product> allProducts = new LinkedList<>();
        for (List<Product> products : itemsByProductName.values()) {
            allProducts.addAll(products);
        }

        if (allProducts.isEmpty()) {
            System.out.println("В корзине пусто");
            return;
        }

        double total = 0.0;
        int specialCount = 0;

        for (Product p : allProducts) {
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
        return itemsByProductName.containsKey(name) && !itemsByProductName.get(name).isEmpty();
    }

    public List<Product> removeProductByName(String name) {
        List<Product> removed = new LinkedList<>();
        if (name == null) {
            return removed;
        }

        List<Product> productsWithName = itemsByProductName.remove(name);
        if (productsWithName != null) {
            removed.addAll(productsWithName);
        }

        return removed;
    }

    public void clearBasket() {
        itemsByProductName.clear();
    }
}

