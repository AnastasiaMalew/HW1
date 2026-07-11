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

    public Searchable findBestMatch(String query) throws BestResultNotFound {
        if (query == null || query.isBlank()) {
            throw new BestResultNotFound(query == null ? "null" : query);
        }

        String lowerQuery = query.toLowerCase();
        Searchable bestItem = null;
        int maxCount = -1;

        for (int i = 0; i < size; i++) {
            Searchable item = items[i];
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

    public Searchable[] search(String query) {
        Searchable[] result = new Searchable[5];
        int count = 0;

        if (query == null || query.isBlank()) {
            return result;
        }
        String lowerQuery = query.toLowerCase(); // исправлена опечатка lowerQuerty -> lowerQuery
        for (int i = 0; i < size; i++) {
            Searchable item = items[i];
            if (item == null) {
                continue;
            }

            String searchTerm = item.getStringRepresentation();
            if (searchTerm != null && searchTerm.toLowerCase().contains(lowerQuery)) {
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
