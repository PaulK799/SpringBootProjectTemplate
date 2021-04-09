package com.paulk.demo.utils;

import com.paulk.demo.model.Audit;
import com.paulk.demo.model.Entry;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.Optional;

public class EntryComparator implements Comparator<Entry> {

    /**
     * {@link Comparator#compare(Object, Object)} implementation for {@link Entry} based on the following order to ensure expected sorting
     * 1. Sort by Value
     * 2. Audit timestamp.
     *
     * @param firstEntry  - The first {@link Entry} to be processed.
     * @param secondEntry - The second {@link Entry} to be processed.
     * @return An integer which indicates a > b when positive, a < b when negative, 0 when the same.
     */
    @Override
    public int compare(Entry firstEntry, Entry secondEntry) {
        // 1. Compare by Entry Value.
        int valueCompare = firstEntry.getValue().compareTo(secondEntry.getValue());
        if (valueCompare != 0) {
            return valueCompare;
        }

        // 2. Compare by Audit date.
        Optional<LocalDateTime> firstEntryLastAuditDateTime = firstEntry.getAudits().getAuditList().stream()
                .filter(audit -> audit.getAuditId() != null)
                .max(Comparator.comparing(Audit::getAuditId))
                .map(Audit::getTimestamp);

        Optional<LocalDateTime> secondEntryLastAuditDateTime = secondEntry.getAudits().getAuditList().stream()
                .filter(audit -> audit.getAuditId() != null)
                .max(Comparator.comparing(Audit::getAuditId))
                .map(Audit::getTimestamp);
        if (firstEntryLastAuditDateTime.isPresent() && secondEntryLastAuditDateTime.isPresent()) {
            return firstEntryLastAuditDateTime.get().compareTo(secondEntryLastAuditDateTime.get());
        }

        // 3. Default equal.
        return 0;
    }
}
