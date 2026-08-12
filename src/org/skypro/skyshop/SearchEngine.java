package org.skypro.skyshop;

import java.util.Map;
import java.util.TreeMap;

public class SearchEngine {
    private final Map<String, Searchable> itemsByName;

    public SearchEngine() {
        this.itemsByName = new TreeMap<>();
    }

    public void addItem(Searchable item) {
        if (item == null || item.getName() == null) {
            return;
        }
        itemsByName.put(item.getName(), item);
    }

    public Map<String, Searchable> search(String query) {
        Map<String, Searchable> result = new TreeMap<>();

        if (query == null || query.isBlank()) {
            return result;
        }

        String lowerQuery = query.toLowerCase();

        for (Map.Entry<String, Searchable> entry : itemsByName.entrySet()) {
            String name = entry.getKey();
            if (name != null && name.toLowerCase().contains(lowerQuery)) {
                result.put(name, entry.getValue());
            }
        }

        return result;
    }

    public Map<String,Searchable> getAllItems() {
        return new TreeMap<>(itemsByName);
    }
}
