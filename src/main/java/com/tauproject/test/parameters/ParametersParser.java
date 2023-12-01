package com.tauproject.test.parameters;

import java.util.ArrayList;

public class ParametersParser {
    private static final int DEFAULT_TOP = 10;

    public Parameters getParameters(String[] args) {
        if (args.length ==0) {
            return null;
        }
        Parameters parameters = new Parameters();
        parameters.setFilenames(new ArrayList<>());
        parameters.setTop(DEFAULT_TOP);
        for (String arg : args) {
            if (arg.startsWith("-dateFormat=")) {
                parameters.setDateFormat(parseDateFormat(arg));
            } else if (arg.startsWith("-top=")) {
                parameters.setTop(parseTop(arg));
            } else if (arg.equals("-skipErrors")) {
                parameters.setSkipErrorsInRow(true);
            } else if (!arg.startsWith("-")) {
                parameters.getFilenames().add(arg);
            }
        }
        return parameters;
    }

    private int parseTop(String arg) {
        String[] parts = arg.split("=");
        if (parts.length == 0) {
            return DEFAULT_TOP;
        }
        return Integer.parseInt(parts[1]);
    }

    private String parseDateFormat(String arg) {
        String[] parts = arg.split("=");
        if (parts.length == 0) {
            return null;
        }
        return parts[1];
    }


}
