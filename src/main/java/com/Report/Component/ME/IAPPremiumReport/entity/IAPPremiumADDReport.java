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

        AbstractColumn totalCommissionRate = createColumnFloat("totalCommissionRate", "Commission Rate %", 80);
        columnList.add(totalCommissionRate);

        AbstractColumn coverageEffectiveDate = createColumnDate("coverageEffectiveDate", "Coverage Effective Date", 150);
        coverageEffectiveDate.setPattern("MMMM, dd, yyyy");
        columnList.add(coverageEffectiveDate);

        AbstractColumn renewalMonth = createColumnDate("renewalMonth", "Renewal Month", 150);
        renewalMonth.setPattern("MMMM, dd, yyyy");
        columnList.add(renewalMonth);

        AbstractColumn divisionStatus = createColumnString("divisionStatus", "Division Status", 80);
        columnList.add(divisionStatus);

        AbstractColumn pctProvinceEmployeeSplit = createColumnString("pctProvinceEmployeeSplit", "PCT Province Employee Split", 100);
        columnList.add(pctProvinceEmployeeSplit);

        AbstractColumn benefitAmountPerEmployee = createColumnFloat("benefitAmountPerEmployee", "Benefit Amount Per Employee", 100);
        columnList.add(benefitAmountPerEmployee);

        currentNumberLives = createColumnInt( "currentNumberLives", "Current Number Of Lives", 100 );
        columnList.add( currentNumberLives );

        currentVolumeOfInsurer = createColumnFloat( "currentVolumeOfInsurer", "Current Volume of Insurer", 100 );
        currentVolumeOfInsurer.setPattern("#,##0.00");
        columnList.add( currentVolumeOfInsurer );

        AbstractColumn grossMonthlyPremiumRate = createColumnFloat("grossMonthlyPremiumRate", "Gross Monthly Premium Rate", 100);
        columnList.add(grossMonthlyPremiumRate);

        AbstractColumn grossPremium = createColumnFloat("grossPremium", "Gross Premium", 100);
        columnList.add(grossPremium);

        AbstractColumn benefitAmountSpouse = createColumnFloat("benefitAmountSpouse", "Benefit Amount (Spouse)", 100);
        columnList.add(benefitAmountSpouse);

        AbstractColumn currentNumberLivesSpouse = createColumnFloat("currentNumberLivesSpouse", "Current Number of Lives (Spouse)", 100);
        columnList.add(currentNumberLivesSpouse);

        AbstractColumn currentVolumeOfInsurerSpouse = createColumnFloat("currentVolumeOfInsurerSpouse", "Current Volume of Insurer (Spouse)", 100);
        columnList.add(currentVolumeOfInsurerSpouse);

        AbstractColumn grossMonthlyPremiumRateSpouse = createColumnFloat("grossMonthlyPremiumRateSpouse", "Gross Monthly Premium Rate (Spouse)", 100);
        columnList.add(grossMonthlyPremiumRateSpouse);

        AbstractColumn grossPremiumSpouse = createColumnFloat("grossPremiumSpouse", "Gross Premium (Spouse)", 100);
        columnList.add(grossPremiumSpouse);

        AbstractColumn benefitAmountDependent = createColumnFloat("benefitAmountDependent", "Benefit Amount (Dependent)", 100);
        columnList.add(benefitAmountDependent);

        AbstractColumn currentNumberOfUnitsDependent = createColumnFloat("currentNumberOfUnitsDependent", "Current Number of Units (Dependent)", 100);
        columnList.add(currentNumberOfUnitsDependent);

        AbstractColumn grossMonthlyPremiumRateDependent = createColumnFloat("grossMonthlyPremiumRateDependent", "Gross Monthly Premium Rate (Dependent)", 100);
        columnList.add(grossMonthlyPremiumRateDependent);

        AbstractColumn grossPremiumDependent = createColumnFloat("grossPremiumDependent", "Gross Premium (Dependent)", 100);
        columnList.add(grossPremiumDependent);

        totalGrossPremium = createColumnFloat( "totalGrossPremium", "Total Gross Premium", 100 );
        totalGrossPremium.setPattern("#,##0.00");
        columnList.add( totalGrossPremium );

        adjust = createColumnFloat( "adjust", "Adjust", 100 );
        adjust.setPattern("#,##0.00");
        columnList.add( adjust );

        grossPremiumAndAdjust = createColumnFloat( "grossPremiumAndAdjust", "Total Gross and Adjust", 100 );
        grossPremiumAndAdjust.setPattern("#,##0.00");
        columnList.add( grossPremiumAndAdjust );

        adminFee = createColumnFloat( "adminFee", "Admin Fee", 100 );
        adminFee.setPattern("#,##0.00");
        columnList.add( adminFee );

        commission = createColumnFloat( "commission", "Commission", 100 );
        commission.setPattern("#,##0.00");
        columnList.add( commission );

        totalCommission = createColumnFloat( "totalCommission", "Total Commission", 100 );
        totalCommission.setPattern("#,##0.00");
        columnList.add( totalCommission );

        ontarioTax = createColumnFloat( "ontarioTax", "ON Tax", 100 );
        ontarioTax.setPattern("#,##0.00");
        columnList.add( ontarioTax );

        quebecTax = createColumnFloat( "quebecTax", "QC Tax", 100 );
        quebecTax.setPattern("#,##0.00");
        columnList.add( quebecTax );

        manitobaTax = createColumnFloat( "manitobaTax", "MB Tax", 100 );
        manitobaTax.setPattern("#,##0.00");
        columnList.add( manitobaTax );

        netPremiumPaid = createColumnFloat( "netPremiumPaid", "Net Premium", 100 );
        netPremiumPaid.setPattern("#,##0.00");
        columnList.add( netPremiumPaid );

        AbstractColumn comments = createColumnString("comments", "Comments", 300);
        columnList.add(comments);


        return columnList;
    }
}
