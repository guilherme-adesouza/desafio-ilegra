package br.com.ilegra.service.reader;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.Arrays;

public class FileReaderServiceImpl implements FileReaderService {

    private static final Logger logger = LogManager.getLogger(FileReaderServiceImpl.class);

    private static final String splitBy = "ç";

    @Override
    public void processFile(Path absolutePath) {
        String line = "";
        try {
            BufferedReader br = new BufferedReader(new FileReader(absolutePath.toFile()));
            while ((line = br.readLine()) != null) {
                String[] parsedLine = line.split(splitBy);
                System.out.println("parsedLine: " + Arrays.toString(parsedLine));
            }
        } catch (IOException e)  {
            logger.error("Error while processing file [{}]:", absolutePath.getFileName(), e);
        }
    }
}
