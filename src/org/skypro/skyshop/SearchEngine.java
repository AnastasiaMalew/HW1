package org.skypro.skyshop;

public class SearchEngine {
    private final Searchable[] items;
    private int size;

    public SearchEngine(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Значение должно быть больше нуля");
        }
        this.items = new Searchable[capacity];
        this.size = 0;
    }

    public void add(Searchable item) {
        if (item == null) {
            return;
        }
        if (size < items.length) {
            items[size] = item;
            size++;
        }
    }

    public Searchable[] search(String query) {
        Searchable[] result = new Searchable[5];
        int count = 0;

        if (query == null || query.isBlank()) {
            return result;
        }
        String lowerQuerty = query.toLowerCase();
        for (int i = 0; i < size; i++) {
            Searchable item = items[i];
            if (item == null) {
                continue;
            }

            String searchTerm = item.getStringRepresentation();
            if (searchTerm != null && searchTerm.toLowerCase().contains(lowerQuerty)) {
                result[count] = item;
                count++;
                if (count == 5) {
                    break;
                }
            }
        }
        return result;
    }
}