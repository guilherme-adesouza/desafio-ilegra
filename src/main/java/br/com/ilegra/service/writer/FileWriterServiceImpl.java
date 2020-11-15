package br.com.ilegra.service.writer;

import br.com.ilegra.domain.SalesData;
import br.com.ilegra.service.report.ReportService;
import br.com.ilegra.service.report.ReportServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.PrintWriter;
import java.nio.file.Path;
import java.nio.file.Paths;

@Component("fileWriterService")
public class FileWriterServiceImpl implements FileWriterService {

    private static final Logger logger = LogManager.getLogger(FileWriterService.class);

    @Value("${app.report.dir}")
    public String reportDir;

    @Override
    public void generateFile(SalesData salesData, Path fileName) {
        logger.info("Generating data report on [{}]...", fileName.toString());
        ReportService reportService = ReportServiceFactory.create();
        reportService.setSalesData(salesData);
        final String reportPath = Paths.get(reportDir, fileName.toString()).toString();
        File reportFile = new File(reportPath);
        try {
            reportFile.createNewFile();
            PrintWriter pw = new PrintWriter(reportFile);
            pw.println("Quantidade de clientes: " + reportService.getNumberCustomers());
            pw.println("Quantidade de vendedores: " + reportService.getNumberSellers());
            pw.println("ID da venda mais cara: " + reportService.getExpensiveSaleId());
            pw.println("O pior vendedor: " + reportService.getWorstSeller());
            pw.close();
            logger.info("Report [{}] created!", fileName.toString());
        } catch (Exception e) {
            logger.error("Error while generating report on path [{}]: ", reportPath, e);
        }
    }
}
