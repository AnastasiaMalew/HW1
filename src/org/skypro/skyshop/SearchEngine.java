package org.skypro.skyshop;

import java.util.ArrayList;
import java.util.List;

public class SearchEngine {
    private final List<Searchable> items;

    public SearchEngine(int capacity) {
        if (capacity <= 0) {
            throw new IllegalArgumentException("Значение должно быть больше нуля");
        }
        this.items = new ArrayList<>(capacity);
    }

    public void add(Searchable item) {
        if (item == null) {
            return;
        }
        items.add(item);
    }

    public Searchable findBestMatch(String query) throws BestResultNotFound {
        if (query == null || query.isBlank()) {
            throw new BestResultNotFound(query == null ? "null" : query);
        }

        String lowerQuery = query.toLowerCase();
        Searchable bestItem = null;
        int maxCount = -1;

        for (Searchable item : items) {
            if (item == null) {
                continue;
            }

            String term = item.getSearchTerm();
            if (term == null) {
                continue;
            }

            int count = countOccurrences(term.toLowerCase(), lowerQuery);
            if (count > maxCount) {
                maxCount = count;
                bestItem = item;
            }
        }

        if (maxCount <= 0 || bestItem == null) {
            throw new BestResultNotFound(query);
        }

        return bestItem;
    }

    private int countOccurrences(String text, String substring) {
        if (substring.isEmpty()) {
            return 0;
        }

        int count = 0;
        int index = 0;

        while (true) {
            int foundIndex = text.indexOf(substring, index);
            if (foundIndex == -1) {
                break;
            }
            count++;
            index = foundIndex + 1; // перекрывающиеся вхождения; для неперекрывающихся: + substring.length()
        }

        return count;
    }

    public List<Searchable> search(String query) {
        List<Searchable> result = new ArrayList<>();

        if (query == null || query.isBlank()) {
            return result;
        }
        String lowerQuery = query.toLowerCase();

        for (Searchable item : items) {
            if (item == null) {
                continue;
            }

            String searchTerm = item.getStringRepresentation();
            if (searchTerm != null && searchTerm.toLowerCase().contains(lowerQuery)) {
                result.add(item);
                }
            }
        return result;
    }
}