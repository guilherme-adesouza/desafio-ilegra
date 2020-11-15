package br.com.ilegra.service.reader;

public class FileReaderFactory {
    public static FileReaderServiceImpl create() {
        return new FileReaderServiceImpl();
    }
}
