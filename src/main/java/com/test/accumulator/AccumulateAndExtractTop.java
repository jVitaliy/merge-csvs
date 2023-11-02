package com.test.accumulator;

import com.test.model.CSVRow;

import java.util.*;
import java.util.stream.Collectors;

public class AccumulateAndExtractTop {
    private Map<String, CSVRow> reduced;

    public AccumulateAndExtractTop() {
        reduced = new HashMap<>();
    }

    public void append(List<CSVRow> rows) {
        rows.forEach(csvRow -> {
            reduced.computeIfPresent(csvRow.getProduct(), (product, row) -> {
                row.setQuantity(row.getQuantity() + csvRow.getQuantity());
                return row;
            });
            reduced.computeIfAbsent(csvRow.getProduct(), row -> csvRow);
        });
    }

    public Collection<CSVRow> getExtracted() {
        return reduced.values();
    }

    public Collection<CSVRow> getTopDesc(int top) {
        return reduced.values().stream()
                .sorted(Comparator.comparing(CSVRow::getQuantity).reversed())
                .limit(top)
                .collect(Collectors.toList());
    }
}
