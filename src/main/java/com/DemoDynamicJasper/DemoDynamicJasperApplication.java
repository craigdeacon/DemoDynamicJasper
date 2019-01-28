package com.DemoDynamicJasper;

import com.Component.CS.EmployeeCostBreakdown.entity.EmployeeCostBreakdownReport;

public class DemoDynamicJasperApplication
{

    public static void main(String[] args)
    {
        EmployeeCostBreakdownReport empReport = new EmployeeCostBreakdownReport();
        empReport.displayEmployeeReport();

//        PrintCertificateReport concateReport = new PrintCertificateReport();
//        concateReport.displayConcatReport();
//
//        RevenueByProduct revenueReport = new RevenueByProduct();
//        revenueReport.displayRevenueByProductReport();
//
//        BookReport bookReport = new BookReport();
//        bookReport.displayBookReport();

//        TemplateReport templateReport = new TemplateReport();
//        templateReport.displayTemplateReport();
    }
}
