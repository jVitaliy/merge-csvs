package com.test;

import com.test.accumulator.AccumulateAndExtractTop;
import com.test.mapper.RowMapper;
import com.test.mapper.impl.StringToCSVRowMapper;
import com.test.model.CSVRow;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class AccumulateAndExtractTopTests {
    @Test
    public void testReducing(){
        AccumulateAndExtractTop extractTop = new AccumulateAndExtractTop();
        List<CSVRow> rowsSet = List.of(
                buildRow("Product 1", 100, BigDecimal.valueOf(35.0)),
                buildRow("Product 2", 10, BigDecimal.valueOf(30.0)),
                buildRow("Product 2", 10, BigDecimal.valueOf(30.0))
        );
        extractTop.append(rowsSet);
        List<CSVRow> list = new ArrayList<>(extractTop.getExtracted());
        Assertions.assertEquals("Product 1", list.get(0).getProduct());
        Assertions.assertEquals(100, list.get(0).getQuantity());
        Assertions.assertEquals("Product 2", list.get(1).getProduct());
        Assertions.assertEquals(20, list.get(1).getQuantity());
    }

    @Test
    public void testTop(){
        AccumulateAndExtractTop extractTop = new AccumulateAndExtractTop();
        List<CSVRow> rowsSet = List.of(
                buildRow("Product 1", 100, BigDecimal.valueOf(35.0)),
                buildRow("Product 2", 10, BigDecimal.valueOf(30.0)),
                buildRow("Product 3", 10, BigDecimal.valueOf(30.0))
        );
        extractTop.append(rowsSet);
        List<CSVRow> rowsSet2 = List.of(
                buildRow("Product 1", 100, BigDecimal.valueOf(35.0)),
                buildRow("Product 2", 10, BigDecimal.valueOf(30.0)),
                buildRow("Product 2", 10, BigDecimal.valueOf(30.0))
        );
        extractTop.append(rowsSet2);
        List<CSVRow> list = new ArrayList<>(extractTop.getTopDesc(2));
        Assertions.assertEquals("Product 1", list.get(0).getProduct());
        Assertions.assertEquals(200, list.get(0).getQuantity());
        Assertions.assertEquals("Product 2", list.get(1).getProduct());
        Assertions.assertEquals(30, list.get(1).getQuantity());
    }

    private CSVRow buildRow(String productName, Integer quantity, BigDecimal price){
        CSVRow row = new CSVRow();
        row.setDate(Calendar.getInstance().getTime());
        row.setProduct(productName);
        row.setPrice(price);
        row.setQuantity(quantity);
        row.setCurrency("USD");
        return row;
    }
}
