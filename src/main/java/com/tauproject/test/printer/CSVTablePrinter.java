package com.tauproject.test.printer;

import com.tauproject.test.model.CSVRow;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class CSVTablePrinter {
    private static final List<String> RESULT_HEADERS = List.of("Product","Total Quantity","Currency");
    private static final List<Integer> RESULT_COLSWIDTH = List.of(15,14,10);
    private SimpleDateFormat formatter;

    public CSVTablePrinter(String dateFormat) {
        this.formatter = new SimpleDateFormat(dateFormat);

    }

    private void printFormatted(List<Integer> widths, List<?> row) {
        for (int i = 0; i < row.size(); i++) {
            if (row.get(i) instanceof String) {
                System.out.print(String.format("%-" + widths.get(i) + "s ", row.get(i)));
            } else if (row.get(i) instanceof Date) {
                System.out.print(String.format("%-" + widths.get(i) + "s ", formatter.format(row.get(i))));
            } else if (row.get(i) instanceof Integer || row.get(i) instanceof BigDecimal) {
                System.out.print(String.format("%" + widths.get(i) + "s ", row.get(i)));
            }
        }
        System.out.println();
    }

    public void printResult(Collection<CSVRow> table) {
        if (table == null) {
            return;
        }
        printFormatted(RESULT_COLSWIDTH, RESULT_HEADERS);
        table.forEach(row -> {
            List<Object> columns = List.of(row.getProduct(),
                    row.getQuantity(),
                    row.getCurrency());
            printFormatted(RESULT_COLSWIDTH, columns);
        });
    }

}

