package br.com.ilegra.service.report;

public class ReportServiceFactory {

    public static ReportService create() {
        return new ReportServiceImpl();
    }
}
