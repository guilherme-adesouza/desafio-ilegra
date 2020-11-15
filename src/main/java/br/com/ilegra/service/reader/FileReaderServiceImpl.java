package br.com.ilegra.service.reader;

import br.com.ilegra.domain.SalesData;
import br.com.ilegra.domain.customer.Customer;
import br.com.ilegra.domain.sale.Sale;
import br.com.ilegra.domain.seller.Seller;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;


public class FileReaderServiceImpl implements FileReaderService {

    private static final Logger logger = LogManager.getLogger(FileReaderServiceImpl.class);

    private static final String splitBy = "ç";

    @Override
    public void processFile(Path absolutePath) {
        logger.info("Processing file [{}] ...", absolutePath.getFileName());
        SalesData salesData = new SalesData();
        try {
            BufferedReader br = new BufferedReader(new FileReader(absolutePath.toFile()));
            String line = "";

            while ((line = br.readLine()) != null) {
                String[] parsedLine = line.split(splitBy);
                boolean handle = false;
                for (Identifier identifier : Identifier.values()) {
                    if (identifier.canHandle(parsedLine[0])) {
                        handle = true;
                        final Object obj = identifier.handle(parsedLine);
                        if (obj instanceof Sale) {
                            salesData.addSale((Sale) obj);
                        } else if (obj instanceof Seller) {
                            salesData.addSeller((Seller) obj);
                        } else if (obj instanceof Customer) {
                            salesData.addCustomer((Customer) obj);
                        }
                    };
                }
                if (!handle) {
                    logger.warn("Invalid identifier [{}] to handle content. Skipping...", parsedLine[0]);
                }
            }
            logger.info("Finished processing file [{}]", absolutePath.getFileName());
            br.close();
        } catch (IOException e)  {
            logger.error("Error while processing file [{}]:", absolutePath.getFileName(), e);
        }
    }
}
