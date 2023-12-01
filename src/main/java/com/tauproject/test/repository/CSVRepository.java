package com.tauproject.test.repository;

import com.tauproject.test.model.CSVRow;

import java.util.List;

public interface CSVRepository {
    List<CSVRow> findAll(boolean skipRowsWithErrors);
}
