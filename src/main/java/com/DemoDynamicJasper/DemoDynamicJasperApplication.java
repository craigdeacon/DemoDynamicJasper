package com.DemoDynamicJasper;

import com.Report.Component.CS.EP3.entity.EP3;
import com.Report.Component.CS.EmployeeCostBreakdown.entity.EmployeeCostBreakdownReport;
import com.Report.Component.CS.PrintCertificate.entity.PrintCertificateReport;
import com.Report.Component.ME.CignaUpload.entity.CignaUploadReport;
import com.Report.Component.ME.EAPReport.entity.EapReport;
import com.Report.Component.ME.IAPPremiumReport.entity.IAPPremiumADDReport;
import com.Report.Component.ME.IAPPremiumReport.entity.IAPPremiumDSAIReport;
import com.Report.Component.ME.IAPPremiumReport.entity.IAPPremiumOptionalADDReport;
import com.Report.Component.ME.IAPPremiumReport.entity.IAPPremiumReport;
import com.Report.Component.ME.ProvincialSalesTaxReport.entity.ProvincialSalesTaxReport;
import com.Report.Component.ME.RevenueReportByProduct.entity.RevenueByProduct;
import com.Report.Component.TestReportComponent.firstReport.entity.BookReport;
import com.Report.Component.TestReportComponent.secondReport.entity.EmployeeReport;
import com.DemoDynamicJasper.spring.config.AppConfig;
import com.DemoDynamicJasper.spring.config.SpringConfigurationBootstrap;
import com.Report.Component.CS.NAMonthlyPremiums.entity.NAMonthlyPremiums;
import com.Report.Component.ME.FABPPremiumReport.entity.FABPPremiumReport;
import com.Report.Component.ME.GstHstByRevenueTypeReport.entity.GstHstByRevenueTypeReport;

/**
 *
 * @author craig.deacon
 */
class DemoDynamicJasperApplication
{

    /**
     *
     * @param args
     */
    public static void main(String[] args)
    {
        /*Initializing all BO and DAO Spring Dependencies*/
        initialize();

        /*Run full component of reports*/
//        testReports();
//        MEReports();
//        CSReports();

        /*Current Report Working On*/
        //TODO Work on new Report
//        IAPPremiumReport iapPremiumReport = new IAPPremiumReport();
//        iapPremiumReport.displayIAPPremiumReport();

//        NAMonthlyPremiums naMonthlyPremiums = new NAMonthlyPremiums();
//        naMonthlyPremiums.displayNAMonthlyPremiumReport();

//        FABPPremiumReport fABPPremiumReport = new FABPPremiumReport();
//        fABPPremiumReport.displayFABPremiumReport();

        GstHstByRevenueTypeReport gstHstByRevenueTypeReport = new GstHstByRevenueTypeReport();
        gstHstByRevenueTypeReport.displayGstHstRevenueByTypeReport();
           

    }

    private static void initialize()
    {
        SpringConfigurationBootstrap.initialize(AppConfig.class);
    }

    private static void testReports()
    {
        BookReport bookReport = new BookReport();
        bookReport.displayBookReport();

        EmployeeReport empReport = new EmployeeReport();
        empReport.displayEmployeeReport();

//        TemplateReport templateReport = new TemplateReport();
//        templateReport.displayTemplateReport();
    }

    private static void MEReports()
    {
        ProvincialSalesTaxReport provincialSalesTaxReport = new ProvincialSalesTaxReport();
        provincialSalesTaxReport.displayProvincialSalesTaxReport();
        
        EP3 ep3 = new EP3();
        ep3.displayEP3Report();

        EapReport eapReport = new EapReport();
        /*Arete*/
        eapReport.displayEAPReport(93064);
        /*HumanaCare*/
        eapReport.displayEAPReport(601076);

        CignaUploadReport cignaUploadReport = new CignaUploadReport();
        cignaUploadReport.displayCignaUploadReport();

        IAPPremiumDSAIReport iapPremiumDSAIReport = new IAPPremiumDSAIReport();
        iapPremiumDSAIReport.displayIAPPremiumDSAIReport();

        IAPPremiumADDReport iapPremiumADDReport = new IAPPremiumADDReport();
        iapPremiumADDReport.displayIAPPremiumADDReport();

        IAPPremiumOptionalADDReport iapPremiumOptionalADDReport = new IAPPremiumOptionalADDReport();
        iapPremiumOptionalADDReport.displayIAPPremiumOptionalADDReport();

        IAPPremiumReport iapPremiumReport = new IAPPremiumReport();
        iapPremiumReport.displayIAPPremiumReport();
    }

    private static void CSReports()
    {
        EmployeeCostBreakdownReport employeeCostBreakdownReport = new EmployeeCostBreakdownReport();
        employeeCostBreakdownReport.displayEmployeeReport();

        PrintCertificateReport printCertificateReport = new PrintCertificateReport();
        printCertificateReport.displayConcatReport();

        RevenueByProduct revenueReport = new RevenueByProduct();
        revenueReport.displayRevenueByProductReport();
    }
}
