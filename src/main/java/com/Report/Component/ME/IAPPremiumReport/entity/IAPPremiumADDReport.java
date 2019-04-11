package com.Report.Component.ME.IAPPremiumReport.entity;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.entities.DJGroupVariable;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import com.Database.BO.IAPPremiumReportBO;
import com.DemoDynamicJasper.spring.config.SpringConfigurationBootstrap;
import com.Utilities.ReportUtilities;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.Utilities.ReportStyles.*;
import static com.Utilities.ReportUtilities.*;

public class IAPPremiumADDReport
{
    private AbstractColumn currentNumberLives;
    private AbstractColumn currentVolumeOfInsurer;
    private AbstractColumn totalGrossPremium;
    private AbstractColumn adjust;
    private AbstractColumn grossPremiumAndAdjust;
    private AbstractColumn adminFee;
    private AbstractColumn commission;
    private AbstractColumn totalCommission;
    private AbstractColumn ontarioTax;
    private AbstractColumn quebecTax;
    private AbstractColumn manitobaTax;
    private AbstractColumn netPremiumPaid;

    private IAPPremiumReportBO iapPremiumReportBO;

    public IAPPremiumADDReport()
    {
        this.iapPremiumReportBO = SpringConfigurationBootstrap.getApplicationContext().getBean(IAPPremiumReportBO.class);
    }

    public void displayIAPPremiumADDReport()
    {
        try
        {
            initStyles();



            DynamicReportBuilder dynamicReportBuilder = new DynamicReportBuilder();
            ArrayList<AbstractColumn> columnList = getIAPPremiumADDColumns();

            columnList.forEach( (column) ->
                                {
                                    DJGroupVariable test = new DJGroupVariable(column, DJCalculation.SUM);
                                    dynamicReportBuilder.addColumn( column );
                                } );


            DynamicReport dynamicReport = dynamicReportBuilder.setLeftMargin(0)
                                                              .setDefaultStyles(BOLD_LEFT, null, BOLD_LEFT, LEFT)
                                                              .setIgnorePagination(true)
                                                              .addGlobalFooterVariable(currentNumberLives, DJCalculation.SUM)
                                                              .addGlobalFooterVariable(currentVolumeOfInsurer, DJCalculation.SUM)
                                                              .addGlobalFooterVariable(totalGrossPremium, DJCalculation.SUM)
                                                              .addGlobalFooterVariable(adjust, DJCalculation.SUM)
                                                              .addGlobalFooterVariable(grossPremiumAndAdjust, DJCalculation.SUM)
                                                              .addGlobalFooterVariable(adminFee, DJCalculation.SUM)
                                                              .addGlobalFooterVariable(commission, DJCalculation.SUM)
                                                              .addGlobalFooterVariable(totalCommission, DJCalculation.SUM)
                                                              .addGlobalFooterVariable(ontarioTax, DJCalculation.SUM)
                                                              .addGlobalFooterVariable(quebecTax, DJCalculation.SUM)
                                                              .addGlobalFooterVariable(manitobaTax, DJCalculation.SUM)
                                                              .addGlobalFooterVariable(netPremiumPaid, DJCalculation.SUM)
                                                              .setGrandTotalLegend("Grand Total:")
                                                              .build();

            JRDataSource dataSource = new JRBeanCollectionDataSource(iapPremiumReportBO.getIapPremiumADDGroups() );

            JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dynamicReport, new ClassicLayoutManager(), dataSource );

            ReportUtilities.exportExcel( jasperPrint,"IAP ADD Premium Report", false);
        }
        catch (JRException ex)
        {
            Logger.getLogger(IAPPremiumADDReport.class.getName() ).log(Level.SEVERE, "displayIAPPremiumADDReport Failed", ex );
        }
    }

    private ArrayList<AbstractColumn> getIAPPremiumADDColumns()
    {
        ArrayList<AbstractColumn> columnList = new ArrayList<>();

        AbstractColumn policyNumber = createColumnString("policyNumber", "Policy Number", 80);
        columnList.add(policyNumber);

        AbstractColumn divisionNumber = createColumnString("divisionNumber", "Division Number", 80);
        columnList.add(divisionNumber);

        AbstractColumn divisionName = createColumnString("divisionName", "Division Name", 200);
        columnList.add(divisionName);

        AbstractColumn totalCommissionRate = createColumn("totalCommissionRate", "Commission Rate %", 80, "Double" );
        columnList.add(totalCommissionRate);

        AbstractColumn coverageEffectiveDate = createColumnDate("coverageEffectiveDate", "Coverage Effective Date", 150);
        coverageEffectiveDate.setPattern("MMMM, dd, yyyy");
        columnList.add(coverageEffectiveDate);

        AbstractColumn renewalMonth = createColumnDate("renewalMonth", "Renewal Month", 150);
        renewalMonth.setPattern("MMMM, dd, yyyy");
        columnList.add(renewalMonth);

        AbstractColumn divisionStatus = createColumnString("divisionStatus", "Division Status", 80);
        columnList.add(divisionStatus);

        AbstractColumn pctProvinceEmployeeSplit = createColumnString("pctProvinceEmployeeSplit", "PCT Province Employee Split", 100 );
        columnList.add(pctProvinceEmployeeSplit);

        AbstractColumn benefitAmountPerEmployee = createColumn("benefitAmountPerEmployee", "Benefit Amount Per Employee", 100, "Double" );
        columnList.add(benefitAmountPerEmployee);

        currentNumberLives = createColumnInt( "currentNumberLives", "Current Number Of Lives", 100 );
        columnList.add( currentNumberLives );

        currentVolumeOfInsurer = createColumn( "currentVolumeOfInsurer", "Current Volume of Insurer", 100, "Double" );
        currentVolumeOfInsurer.setPattern("#,##0.00");
        columnList.add( currentVolumeOfInsurer );

        AbstractColumn grossMonthlyPremiumRate = createColumn("grossMonthlyPremiumRate", "Gross Monthly Premium Rate", 100, "Double" );
        columnList.add(grossMonthlyPremiumRate);

        AbstractColumn grossPremium = createColumn("grossPremium", "Gross Premium", 100, "Double" );
        columnList.add(grossPremium);

        AbstractColumn benefitAmountSpouse = createColumn("benefitAmountSpouse", "Benefit Amount (Spouse)", 100, "Double" );
        columnList.add(benefitAmountSpouse);

        AbstractColumn currentNumberLivesSpouse = createColumn("currentNumberLivesSpouse", "Current Number of Lives (Spouse)", 100, "Double" );
        columnList.add(currentNumberLivesSpouse);

        AbstractColumn currentVolumeOfInsurerSpouse = createColumn("currentVolumeOfInsurerSpouse", "Current Volume of Insurer (Spouse)", 100, "Double" );
        columnList.add(currentVolumeOfInsurerSpouse);

        AbstractColumn grossMonthlyPremiumRateSpouse = createColumn("grossMonthlyPremiumRateSpouse", "Gross Monthly Premium Rate (Spouse)", 100, "Double" );
        columnList.add(grossMonthlyPremiumRateSpouse);

        AbstractColumn grossPremiumSpouse = createColumn("grossPremiumSpouse", "Gross Premium (Spouse)", 100, "Double" );
        columnList.add(grossPremiumSpouse);

        AbstractColumn benefitAmountDependent = createColumn("benefitAmountDependent", "Benefit Amount (Dependent)", 100, "Double" );
        columnList.add(benefitAmountDependent);

        AbstractColumn currentNumberOfUnitsDependent = createColumn("currentNumberOfUnitsDependent", "Current Number of Units (Dependent)", 100, "Double" );
        columnList.add(currentNumberOfUnitsDependent);

        AbstractColumn grossMonthlyPremiumRateDependent = createColumn("grossMonthlyPremiumRateDependent", "Gross Monthly Premium Rate (Dependent)", 100, "Double" );
        columnList.add(grossMonthlyPremiumRateDependent);

        AbstractColumn grossPremiumDependent = createColumn("grossPremiumDependent", "Gross Premium (Dependent)", 100, "Double" );
        columnList.add(grossPremiumDependent);

        totalGrossPremium = createColumn( "totalGrossPremium", "Total Gross Premium", 100, "Double" );
        totalGrossPremium.setPattern("#,##0.00");
        columnList.add( totalGrossPremium );

        adjust = createColumn( "adjust", "Adjust", 100, "Double" );
        adjust.setPattern("#,##0.00");
        columnList.add( adjust );

        grossPremiumAndAdjust = createColumn( "grossPremiumAndAdjust", "Total Gross and Adjust", 100, "Double" );
        grossPremiumAndAdjust.setPattern("#,##0.00");
        columnList.add( grossPremiumAndAdjust );

        adminFee = createColumn( "adminFee", "Admin Fee", 100, "Double" );
        adminFee.setPattern("#,##0.00");
        columnList.add( adminFee );

        commission = createColumn( "commission", "Commission", 100, "Double" );
        commission.setPattern("#,##0.00");
        columnList.add( commission );

        totalCommission = createColumn( "totalCommission", "Total Commission", 100, "Double" );
        totalCommission.setPattern("#,##0.00");
        columnList.add( totalCommission );

        ontarioTax = createColumn( "ontarioTax", "ON Tax", 100, "Double" );
        ontarioTax.setPattern("#,##0.00");
        columnList.add( ontarioTax );

        quebecTax = createColumn( "quebecTax", "QC Tax", 100, "Double" );
        quebecTax.setPattern("#,##0.00");
        columnList.add( quebecTax );

        manitobaTax = createColumn( "manitobaTax", "MB Tax", 100, "Double" );
        manitobaTax.setPattern("#,##0.00");
        columnList.add( manitobaTax );

        netPremiumPaid = createColumn( "netPremiumPaid", "Net Premium", 100, "Double" );
        netPremiumPaid.setPattern("#,##0.00");
        columnList.add( netPremiumPaid );

        AbstractColumn comments = createColumnString("comments", "Comments", 300);
        columnList.add(comments);


        return columnList;
    }
}
