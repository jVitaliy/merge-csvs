package com.test.repository;

import com.test.model.CSVRow;

import java.util.List;

public interface CSVRepository {
    List<CSVRow> findAll(boolean skipRowsWithErrors);
}
