package org.skypro.skyshop;

import java.util.*;

public class SearchEngine {
    private final Map<String, Searchable> itemsByName;
    private final Set<Searchable> uniqueItems;

    public SearchEngine() {
        this.itemsByName = new HashMap<>();
        this.uniqueItems = new HashSet<>();
    }

    public void addItem(Searchable item) {
        if (item == null || item.getName() == null) {
            return;
        }

        if (!uniqueItems.add(item)) {
            return;
        }

        itemsByName.put(item.getName(), item);
    }

    public Set<Searchable> search(String query) {
        if (query == null || query.isBlank()) {
            return Collections.emptySet();
        }

        String lowerQuery = query.toLowerCase();
        List<Searchable> matches = new ArrayList<>();

        for (Searchable item : itemsByName.values()) {
            String name = item.getName();
            if (name != null && name.toLowerCase().contains(lowerQuery)) {
                matches.add(item);
            }
        }

        matches.sort((a, b) -> {
            int lenA = a.getName().length();
            int lenB = b.getName().length();

            if (lenA != lenB) {
                return Integer.compare(lenB, lenA);
            }

            return a.getName().compareTo(b.getName());
        });

        return new LinkedHashSet<>(matches);

    }

    public Collection<Searchable> getAllItems() {
        return new ArrayList<>(itemsByName.values());
    }
}
