package org.skypro.skyshop;

import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.SimpleProduct;

public class App {
    public static void main(String[] args) {
        ProductBasket basket = new ProductBasket();

        basket.add(new SimpleProduct("Растительное молоко", 130));
        basket.add(new SimpleProduct("Шоколад", 340));
        basket.add(new DiscountedProduct("Зеленый чай", 129, 25));
        basket.add(new FixPriceProduct("Гречка"));

        System.out.println("Содержимое корзины");
        basket.printBasketContents();
        System.out.println();

        System.out.println("Общая стоимость корзины: " + basket.getTotalCost());

        System.out.println("Есть ли в корзине Зеленый чай? " + basket.containsProductByName("Зеленый чай"));

        System.out.println("Есть ли в корзине Молоко? " + basket.containsProductByName("Молоко"));

        System.out.println("Очистка корзины");
        basket.clearBasket();

        System.out.println("Содержимое корзины:");
        basket.printBasketContents();

        System.out.println("Стоимость пустой корзины: " + basket.getTotalCost());

        System.out.println("Есть ли растительное молоко в пустой корзине? " +
                basket.containsProductByName("Растительное молоко"));
    }
}

