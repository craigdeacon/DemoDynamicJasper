package com.DemoDynamicJasper;

import com.Component.ME.EAPReport.entity.EapReport;

/**
 *
 * @author craig.deacon
 */
public class DemoDynamicJasperApplication
{

    /**
     *
     * @param args
     */
    public static void main(String[] args)
    {
//        EmployeeReport empReport = new EmployeeReport();
//        empReport.displayEmployeeReport();
//
//        EmployeeCostBreakdownReport empReport = new EmployeeCostBreakdownReport();
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

//        ProvincialSalesTaxReport provincialSalesTaxReport = new ProvincialSalesTaxReport();
//        provincialSalesTaxReport.displayProvincialSalesTaxReport();

//        EP3 ep3 = new EP3();
//        ep3.displayEP3Report();

        EapReport eapReport = new EapReport();
        /*Arete*/
        eapReport.displayEAPReport(93064);
        /*HumanaCare*/
        eapReport.displayEAPReport(601076);

    }
}
