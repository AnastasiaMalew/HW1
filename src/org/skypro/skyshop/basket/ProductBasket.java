package org.skypro.skyshop.basket;

import org.skypro.skyshop.product.Product;


public class ProductBasket {
    private final Product[] products = new Product[5];

    public void addProduct(Product product) {
        for (int i = 0; i < products.length; i++) {
            if (products[i] == null) {
                products[i] = product;
                return;
            }
        }
        System.out.println("Невозможно добавить продукт!");
    }

    public int getTotalCost() {
        int total = 0;
        for (Product p : products) {
            if (p != null) {
                total +=p.getPrice();
            }
        }
        return total;
    }

    public void printBasketContents() {
        boolean hasItems = false;
        int total = 0;

        for (Product p : products) {
            if (p != null) {
                hasItems = true;
                total += p.getPrice();
            }
        }

        if (!hasItems) {
            System.out.println("В корзине пусто");
            return;
        }
        for (Product p : products) {
            if (p != null) {
                System.out.println(p.getName() + ": " + p.getPrice());
            }
        }
        System.out.println("Итого: " + total);
    }

    public boolean containsProductByName(String name) {
        if (name == null) {
            return false;
        }
        for (Product p: products) {
            if (p != null && p.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public void clearBasket() {
        for (int i = 0; i < products.length; i++) {
            products[i] = null;
        }
    }
}

