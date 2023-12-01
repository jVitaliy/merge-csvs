package com.tauproject.test.csv;

import com.tauproject.test.repository.DataReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MyCSVReader implements DataReader {
    private String filename;

    public MyCSVReader(String filename) {
        this.filename = filename;
    }


    public List<String> readLines() {
        List<String> rows = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(filename))) {
            //skip header
            if (scanner.hasNextLine()) {
                scanner.nextLine();
            }
            while (scanner.hasNextLine()) {
                rows.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return rows;
    }


}
