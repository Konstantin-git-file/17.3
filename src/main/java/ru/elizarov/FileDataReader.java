package ru.elizarov;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

class FileDataReader implements DataReader {
    @Override
    public String read(String inputSource) {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputSource))) {
            return reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException("Ошибка чтения файла", e);
        }
    }
}

