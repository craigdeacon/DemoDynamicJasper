package com.DemoDynamicJasper;

import com.Component.CS.PrintCertificate.entity.PrintCertificateReport;
import com.Component.ME.ProvincialSalesTaxReport.entity.ProvincialSalesTaxReport;
import com.Component.ME.RevenueReportByProduct.entity.RevenueByProduct;
import com.Component.TestReportComponent.firstReport.entity.BookReport;
import com.Component.TestReportComponent.secondReport.entity.EmployeeReport;

public class DemoDynamicJasperApplication
{

    public static void main(String[] args)
    {
//        EmployeeReport empReport = new EmployeeReport();
//        empReport.displayEmployeeReport();

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
            
        ProvincialSalesTaxReport provincialSalesTaxReport = new ProvincialSalesTaxReport();
        provincialSalesTaxReport.displayProvincialSalesTaxReport();

    }
}
