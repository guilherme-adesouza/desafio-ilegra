package br.com.ilegra.service.reader;

import java.nio.file.Path;

public interface FileReaderService {
    void processFile(Path absolutePath);
}
