package com.test.repository.impl;

import com.test.mapper.RowMapper;
import com.test.model.CSVRow;
import com.test.repository.CSVRepository;
import com.test.repository.DataReader;

import java.util.List;
import java.util.stream.Collectors;

public class CSVRepositoryMyReader implements CSVRepository {
    private DataReader reader;
    private RowMapper<CSVRow> mapper;

    public CSVRepositoryMyReader(DataReader reader, RowMapper<CSVRow> mapper) {
        this.reader = reader;
        this.mapper = mapper;
    }

    @Override
    public List<CSVRow> findAll() {

        return reader.readLines().stream()
                .map(mapper::mapFrom)
                .collect(Collectors.toList());
    }
}
