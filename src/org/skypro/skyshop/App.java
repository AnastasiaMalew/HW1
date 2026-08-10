package org.skypro.skyshop;

import org.skypro.skyshop.product.DiscountedProduct;
import org.skypro.skyshop.product.FixPriceProduct;
import org.skypro.skyshop.product.Product;
import org.skypro.skyshop.basket.ProductBasket;
import org.skypro.skyshop.product.SimpleProduct;

import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        ProductBasket basket = new ProductBasket();

        basket.add(new SimpleProduct("Растительное молоко", 130));
        basket.add(new SimpleProduct("Шоколад", 340));
        basket.add(new DiscountedProduct("Зеленый чай", 129, 25));
        basket.add(new FixPriceProduct("Гречка"));

        System.out.println("Содержимое корзины до удаления:");
        basket.printBasketContents();
        System.out.println();

        System.out.println("Общая стоимость корзины: " + basket.getTotalCost());
        System.out.println();

        System.out.println("Удаление существующего продукта:");
        List<Product> removedExisting = basket.removeProductByName("Шоколад");

        System.out.println("Удаленные продукты: ");
        if (removedExisting.isEmpty()) {
            System.out.println("Список пуст");
        } else {
            for (Product p : removedExisting) {
                System.out.println(p.toString());
            }
        }

        System.out.println("\nСодержимое корзины после удаления Шоколада: ");

        System.out.println("Удаление несуществующего продукта: ");
        List<Product> removedNonExisting = basket.removeProductByName("Рыба");

        System.out.println("Удаленные продукты");
        if (removedNonExisting.isEmpty()) {
            System.out.println("Список пуст");
        } else {
            for (Product p : removedNonExisting) {
                System.out.println(p.toString());
            }
        }

        System.out.println("\nСодержимое корзины после попытки удаления Рыбы");
        basket.printBasketContents();
        System.out.println();

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
        engine.add(new SimpleProduct("Бананы", 150));
        engine.add(new SimpleProduct("Какао", 250));

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
        System.out.println("Поиск по запросу: \"" + query + "\"");

        List<Searchable> results = engine.search(query);

        System.out.println("Найдено результатов: " + results.size());
        System.out.println("Результаты: ");
        for (Searchable item : results) {
            if (item != null) {
                System.out.println(item.getStringRepresentation());
            }
        }

        System.out.println();

        System.out.println("Проверка findBestMatch и обработки BestResultNotFound: ");

        try {
            Searchable best = engine.findBestMatch(query);
            System.out.println("Лучший результат: " + best.getStringRepresentation());
        } catch (BestResultNotFound e) {
            System.out.println("Ошибка поиска: " + e.getMessage());
        }
        System.out.println();
    }
}

