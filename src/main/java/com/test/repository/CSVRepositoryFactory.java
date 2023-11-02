package com.test.repository;

import com.test.csv.MyCSVReader;
import com.test.mapper.impl.StringToCSVRowMapper;
import com.test.repository.impl.CSVRepositoryMyReader;

public class CSVRepositoryFactory {
    public static CSVRepository getInstanceOf(CSVRepositoryType csvRepositoryType, String filename, String dateFormat) {
        switch (csvRepositoryType) {
            case MY_CSV_READER:
                DataReader reader = new MyCSVReader(filename);
                StringToCSVRowMapper stringToCSVRowMapper = new StringToCSVRowMapper(",", dateFormat);
                return new CSVRepositoryMyReader(reader, stringToCSVRowMapper);
        }
        return null;
    }
}
