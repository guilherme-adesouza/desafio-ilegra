package br.com.ilegra.service;

public class ReportServiceFactory {

    public static ReportService create() {
        return new ReportServiceImpl();
    }
}
