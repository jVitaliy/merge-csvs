package com.tauproject.test.mapper.impl;

import com.tauproject.test.exception.MapperException;
import com.tauproject.test.mapper.RowMapper;
import com.tauproject.test.model.CSVRow;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class StringToCSVRowMapper implements RowMapper<CSVRow> {
    private final String delimiter;

    private String dateFormat;
    private SimpleDateFormat dateFormatter;

    public StringToCSVRowMapper(String delimiter) {
        this.delimiter = delimiter;
        this.dateFormatter = new SimpleDateFormat("MM/dd/yyyy");
    }

    public StringToCSVRowMapper(String delimiter, String dateFormat) {
        this(delimiter);
        this.dateFormat = dateFormat;
    }

    @Override
    public CSVRow mapFrom(String line) {
        String[] items = line.split(delimiter);
        CSVRow csvRow = new CSVRow();
        try {
            csvRow.setDate(dateFormatter.parse(items[0]));
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        if (items[1].isEmpty()) {
            throw new MapperException("empty quantity");
        }
        if (items[2].isEmpty()) {
            throw new MapperException("empty product");
        }

        csvRow.setQuantity(Integer.parseInt(items[1]));
        csvRow.setProduct(items[2]);
        csvRow.setPrice(!items[3].isEmpty()
                ? BigDecimal.valueOf(Double.parseDouble(items[3]))
                : BigDecimal.valueOf(0.0)
        );
        csvRow.setCurrency(items[4]);
        return csvRow;
    }
}
