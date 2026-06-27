package org.skypro.skyshop;

import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.basket.ProductBasket;

public class App {
    public static void main(String[] args) {
        ProductBasket basket = new ProductBasket();

        Product beer = new Product("Пиво", 349);
        basket.addProduct(beer);

        basket.addProduct(new Product("Растительное молоко", 130));
        basket.addProduct(new Product("Шоколад", 340));
        basket.addProduct(new Product("Зеленый чай", 129));
        basket.addProduct(new Product("Гречка", 96));

        System.out.println("Попытка добавить шестой продукт");
        basket.addProduct(new Product("Кофе", 580));

        System.out.println();

        System.out.println("Содержимое корзины");
        basket.printBasketContents();
        System.out.println();

        System.out.println("Общая стоимость корзины: " + basket.getTotalCost());

        System.out.println("Есть ли в корзине Пиво? " + basket.containsProductByName("Пиво"));

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

