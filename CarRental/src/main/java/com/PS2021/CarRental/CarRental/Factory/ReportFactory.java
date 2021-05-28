package com.PS2021.CarRental.CarRental.Factory;

public class ReportFactory {
    public Report createReport(String reportType) {
        if (reportType == null)
            return null;
        if (reportType.equalsIgnoreCase("pdf"))
            return new ReportPDF();
        return null;
    }
}
