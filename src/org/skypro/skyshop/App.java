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

    System.out.println("Проверка ошибок:");

    try {
        basket.add(new SimpleProduct("Странное молоко", 0));
    } catch (IllegalArgumentException e) {
        System.out.println("Ошибка! Не удается добавить товар! " + e.getMessage());
    }

    try {
        basket.add(new SimpleProduct("Странный шоколад", -35));
    } catch (IllegalArgumentException e) {
        System.out.println("Ошибка! Не удается добавить товар! " + e.getMessage());
    }

    try {
        basket.add(new SimpleProduct("", 89));
    } catch (IllegalArgumentException e) {
        System.out.println("Ошибка! Не удается добавить товар! " + e.getMessage());
    }

    try {
        basket.add(new SimpleProduct("    ", 100));
    } catch (IllegalArgumentException e) {
        System.out.println("Ошибка! Не удается добавить товар! " + e.getMessage());
    }

    try {
        basket.add(new DiscountedProduct("Странный корм для кошек", 0, 40));
    } catch (IllegalArgumentException e) {
        System.out.println("Ошибка! Не удается добавить товар! " + e.getMessage());
    }

    try {
        basket.add(new DiscountedProduct("Странное какао", 245, 135));
    } catch (IllegalArgumentException e) {
        System.out.println("Ошибка! Не удается добавить товар! " + e.getMessage());
    }
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

        System.out.println("Проверка findBestMatch и обработки BestResultNotFound:");

        try {
            engine.add(new SimpleProduct("Растительное молоко", 130));
            engine.add(new SimpleProduct("Шоколад", 340));
            engine.add(new DiscountedProduct("Зеленый чай", 129, 25));
            engine.add(new FixPriceProduct("Гречка"));

            Searchable best = engine.findBestMatch("молоко");
            System.out.println("Лучший результат: " + best.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка поиска: " + e.getMessage());
        }

        try {
            SearchEngine engine2 = new SearchEngine(5);
            // добавим товары
            engine2.add(new SimpleProduct("Растительное молоко", 130));
            engine2.add(new SimpleProduct("Шоколад", 340));

            // запрос, которого точно нет
            Searchable best = engine2.findBestMatch("рыба");
            System.out.println("Лучший результат: " + best.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка поиска: " + e.getMessage());
        }
    }

}

