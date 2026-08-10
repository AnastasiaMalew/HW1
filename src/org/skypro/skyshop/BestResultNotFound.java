package org.skypro.skyshop;

public class BestResultNotFound extends Exception {
    public BestResultNotFound(String query) {
        super("Не найдено наиболее подходящее совпадение для запроса: \\" + query + "\\");
    }
}
