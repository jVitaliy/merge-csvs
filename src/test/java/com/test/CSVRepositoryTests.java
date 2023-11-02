package com.test;

import com.test.exception.MapperException;
import com.test.mapper.RowMapper;
import com.test.mapper.impl.StringToCSVRowMapper;
import com.test.model.CSVRow;
import com.test.repository.DataReader;
import com.test.repository.impl.CSVRepositoryMyReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class CSVRepositoryTests {
    @Test
    public void testRepository() {
        RowMapper<CSVRow> mapper = new StringToCSVRowMapper(",");
        DataReader reader = mock(DataReader.class);
        CSVRepositoryMyReader repository = new CSVRepositoryMyReader(reader, mapper);
        when(reader.readLines()).thenReturn(List.of(
                "1/1/2008,1000,Blue Shoes,40.00,CHF",
                "1/1/2008,20,Red Shoes,35.11,CHF"
        ));
        List<CSVRow> allRead = repository.findAll(false);
        Assertions.assertEquals(2, allRead.size());
        Assertions.assertEquals("Blue Shoes", allRead.get(0).getProduct());
        Assertions.assertEquals(1000, allRead.get(0).getQuantity());
        Assertions.assertEquals("Red Shoes", allRead.get(1).getProduct());
        Assertions.assertEquals(20, allRead.get(1).getQuantity());
    }

    @Test
    public void testRepositoryEmptyPrice() {
        RowMapper<CSVRow> mapper = new StringToCSVRowMapper(",");
        DataReader reader = mock(DataReader.class);
        CSVRepositoryMyReader repository = new CSVRepositoryMyReader(reader, mapper);
        when(reader.readLines()).thenReturn(List.of(
                "1/1/2008,1000,Blue Shoes,,CHF",
                "1/1/2008,20,Red Shoes,35.11,CHF"
        ));
        List<CSVRow> allRead = repository.findAll(false);
        Assertions.assertEquals(2, allRead.size());
        Assertions.assertEquals("Blue Shoes", allRead.get(0).getProduct());
        Assertions.assertEquals(1000, allRead.get(0).getQuantity());
        Assertions.assertEquals("Red Shoes", allRead.get(1).getProduct());
        Assertions.assertEquals(20, allRead.get(1).getQuantity());
    }

    @Test
    public void testRepositoryEmptyQuantityAndProductNoSkipErrors() {
        RowMapper<CSVRow> mapper = new StringToCSVRowMapper(",");
        DataReader reader = mock(DataReader.class);
        CSVRepositoryMyReader repository = new CSVRepositoryMyReader(reader, mapper);
        when(reader.readLines()).thenReturn(List.of(
                "1/1/2008,,,35.0,CHF",
                "1/1/2008,20,Red Shoes,35.11,CHF"
        ));
        MapperException thrown = Assertions.assertThrows(MapperException.class, () -> {
            repository.findAll(false);
        });
        Assertions.assertEquals("empty quantity", thrown.getMessage());


    }

    @Test
    public void testRepositoryEmptyQuantityAndProductSkipErrors() {
        RowMapper<CSVRow> mapper = new StringToCSVRowMapper(",");
        DataReader reader = mock(DataReader.class);
        CSVRepositoryMyReader repository = new CSVRepositoryMyReader(reader, mapper);
        when(reader.readLines()).thenReturn(List.of(
                "1/1/2008,,,35.0,CHF",
                "1/1/2008,20,Red Shoes,35.11,CHF"
        ));

        List<CSVRow> allRead = repository.findAll(true);

        Assertions.assertEquals(1, allRead.size());
        Assertions.assertEquals("Red Shoes", allRead.get(0).getProduct());
        Assertions.assertEquals(20, allRead.get(0).getQuantity());
    }
}
