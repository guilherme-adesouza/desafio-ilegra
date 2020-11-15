package br.com.ilegra.service.reader;

import br.com.ilegra.domain.SalesData;

import java.nio.file.Path;

public interface FileReaderService {
    SalesData processFile(Path absolutePath);
}
