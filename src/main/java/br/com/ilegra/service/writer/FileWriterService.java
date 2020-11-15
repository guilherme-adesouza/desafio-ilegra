package br.com.ilegra.service.writer;

import br.com.ilegra.domain.SalesData;

import java.nio.file.Path;

public interface FileWriterService {

    void generateFile(SalesData salesData, Path fileName);
}
