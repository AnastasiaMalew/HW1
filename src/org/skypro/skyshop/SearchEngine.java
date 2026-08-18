package org.skypro.skyshop;

import java.util.*;
import java.util.stream.Collectors;

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

        return itemsByName.values().stream()
                .filter(item -> item.getSearchTerm() != null
                        && item.getSearchTerm().toLowerCase().contains(lowerQuery))
                .collect(Collectors.toCollection(() -> new TreeSet<>(
                        (a, b) -> {
                            int lenA = a.getName().length();
                            int lenB = b.getName().length();

                            if (lenA != lenB) {
                                return Integer.compare(lenB, lenA);
                            }

                            return a.getName().compareTo(b.getName());
                        }
                )));
    }

    public Collection<Searchable> getAllItems() {
        return new ArrayList<>(itemsByName.values());
    }
}
