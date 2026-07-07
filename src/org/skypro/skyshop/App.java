package org.skypro.skyshop;

import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.SimpleProduct;

import java.util.Arrays;

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

        System.out.println("Тестирование работы SearchEngine");

        SearchEngine engine = new SearchEngine(5);

        engine.add(new SimpleProduct("Растительное молоко", 130));
        engine.add(new SimpleProduct("Шоколад", 340));
        engine.add(new DiscountedProduct("Зеленый чай", 129, 25));
        engine.add(new FixPriceProduct("Гречка"));

        testSearch(engine, "молоко");
        testSearch(engine, "чай");
        testSearch(engine, "гречка");
        testSearch(engine, "шоколад");
        testSearch(engine, "рыба");
    }

    private static void testSearch(SearchEngine engine, String query) {
        System.out.println("Поиск по запросу");

        Searchable[] results = engine.search(query);

        System.out.println((Arrays.toString(results)));

        System.out.println("Найденные элементы: ");
        for (Searchable item : results) {
           if (item != null) {
               System.out.println(item.getStringRepresentation());
           } else {
               break;
           }
        }
        System.out.println();
    }
}

