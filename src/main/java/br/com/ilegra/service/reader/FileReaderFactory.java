package br.com.ilegra.service.reader;

public class FileReaderFactory {
    public static FileReaderService create() {
        return new FileReaderServiceImpl();
    }
}
