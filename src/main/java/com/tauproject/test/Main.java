package com.tauproject.test;

import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<String> list = Arrays.asList("a","b","c","d","a","c");
        Set<String> noDuplicates = list.stream()
                .collect(Collectors.reducing(String::compare))

    }

    private void newId() {
        Executors.newFixedThreadPool(3);
        String k = "dffgg";
        String p = "dffgg";
        list.stream().filter().max collect(map(id, countComments())).
    }

}