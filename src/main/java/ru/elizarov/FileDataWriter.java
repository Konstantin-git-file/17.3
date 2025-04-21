package ru.elizarov;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

class FileDataWriter implements DataWriter {
    @Override
    public void write(String outputSource, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputSource))) {
            writer.write(data);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка записи файла", e);
        }
    }
}