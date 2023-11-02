package com.test;

import com.test.accumulator.AccumulateAndExtractTop;
import com.test.parameters.Parameters;
import com.test.parameters.ParametersParser;
import com.test.printer.CSVTablePrinter;
import com.test.repository.CSVRepository;
import com.test.repository.CSVRepositoryFactory;
import com.test.repository.CSVRepositoryType;

public class Main {
    public static void main(String[] args) {
        ParametersParser parametersParser = new ParametersParser();
        Parameters parameters = parametersParser.getParameters(args);
        if (parameters == null || !parameters.isParamsExist()) {
            printHelp();
            return;
        }
        AccumulateAndExtractTop extractTop = new AccumulateAndExtractTop();
        for (String filename : parameters.getFilenames()) {

            CSVRepository csvRepository = CSVRepositoryFactory.getInstanceOf(CSVRepositoryType.MY_CSV_READER, filename,
                    parameters.getDateFormat());
            if (csvRepository == null) {
                System.err.println("not supported repository");
                return;
            }
            extractTop.append(csvRepository.findAll(parameters.isSkipErrorsInRow()));
        }
        CSVTablePrinter printer = new CSVTablePrinter(parameters.getDateFormat());
        printer.printResult(extractTop.getTopDesc(parameters.getTop()));
    }

    private static void printHelp(){
        System.out.println("Usage java -jar company-orders-0.0.1.jar [OPTIONS] <filename1> <filename2>");
    }


}