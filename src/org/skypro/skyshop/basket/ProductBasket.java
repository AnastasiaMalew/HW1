package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;

import java.util.*;
import java.util.stream.Collectors;

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
        return itemsByProductName.values().stream()
                .flatMap(Collection::stream)
                .mapToDouble(Product::getPrice)
                .sum();
    }

    private long getSpecialCount() {
        return itemsByProductName.values().stream()
                .flatMap(Collection::stream)
                .filter(Product::isSpecial)
                .count();
    }

    public void printBasketContents() {
        List<Product> allProducts = itemsByProductName.values().stream()
                .flatMap(Collection::stream)
                .collect(Collectors.toList());

        if (allProducts.isEmpty()) {
            System.out.println("В корзине пусто");
            return;
        }

        double total = allProducts.stream()
                .mapToDouble(Product::getPrice)
                .sum();

        allProducts.forEach(System.out::println);

        System.out.println("Итого: " + total);
        System.out.println("Специальных товаров: " + getSpecialCount());
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

