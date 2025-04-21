package ru.elizarov;

import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.function.Function;

@RequiredArgsConstructor
class DataProcessor {
    private final DataReader reader;
    private final DataWriter writer;
    private final List<Function<String, String>> transformations;

    public void process(String inputFile, String outputFile) {
        String data = reader.read(inputFile);
        for (Function<String, String> transformation : transformations) {
            data = transformation.apply(data);
        }
        writer.write(outputFile, data);
    }
}