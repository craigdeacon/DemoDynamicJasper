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

public class IAPPremiumOptionalADDReport
{
    private AbstractColumn currentNumberLives;
    private AbstractColumn currentSinglePlanVolume;
    private AbstractColumn currentFamilyPlanVolume;
    private AbstractColumn totalGrossPremium;
    private AbstractColumn adjust;
    private AbstractColumn totalGrossPremiumAndAdjust;
    private AbstractColumn adminFee;
    private AbstractColumn commission;
    private AbstractColumn totalCommission;
    private AbstractColumn ontarioTax;
    private AbstractColumn quebecTax;
    private AbstractColumn netPremiumPaid;

    private IAPPremiumReportBO iapPremiumReportBO;

    public IAPPremiumOptionalADDReport()
    {
        this.iapPremiumReportBO = SpringConfigurationBootstrap.getApplicationContext().getBean(IAPPremiumReportBO.class);
    }

    public void displayIAPPremiumOptionalADDReport()
    {
        try
        {
            initStyles();



            DynamicReportBuilder dynamicReportBuilder = new DynamicReportBuilder();
            ArrayList<AbstractColumn> columnList = getIAPPremiumOptionalADDColumns();

            columnList.forEach( (column) ->
                                {
                                    DJGroupVariable test = new DJGroupVariable(column, DJCalculation.SUM);
                                    dynamicReportBuilder.addColumn( column );
                                } );


            DynamicReport dynamicReport = dynamicReportBuilder.setLeftMargin(0)
                                                              .setDefaultStyles(BOLD_LEFT, null, BOLD_LEFT, LEFT)
                                                              .setIgnorePagination(true)
                                                              .addGlobalFooterVariable(currentNumberLives, DJCalculation.SUM)
                                                              .addGlobalFooterVariable(currentSinglePlanVolume, DJCalculation.SUM)
                                                              .addGlobalFooterVariable(currentFamilyPlanVolume, DJCalculation.SUM)
                                                              .addGlobalFooterVariable(totalGrossPremium, DJCalculation.SUM)
                                                              .addGlobalFooterVariable(adjust, DJCalculation.SUM)
                                                              .addGlobalFooterVariable(totalGrossPremiumAndAdjust, DJCalculation.SUM)
                                                              .addGlobalFooterVariable(adminFee, DJCalculation.SUM)
                                                              .addGlobalFooterVariable(commission, DJCalculation.SUM)
                                                              .addGlobalFooterVariable(totalCommission, DJCalculation.SUM)
                                                              .addGlobalFooterVariable(ontarioTax, DJCalculation.SUM)
                                                              .addGlobalFooterVariable(quebecTax, DJCalculation.SUM)
                                                              .addGlobalFooterVariable(netPremiumPaid, DJCalculation.SUM)
                                                              .setGrandTotalLegend("Total")
                                                              .build();

            JRDataSource dataSource = new JRBeanCollectionDataSource(iapPremiumReportBO.getIapPremiumOptionalADDGroups() );

            JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dynamicReport, new ClassicLayoutManager(), dataSource );

            ReportUtilities.exportExcel(jasperPrint, "IAP Optional ADD Premium Report", false);
        }
        catch (JRException ex)
        {
            Logger.getLogger(IAPPremiumOptionalADDReport.class.getName() ).log(Level.SEVERE, "IAPPremiumOptionalADDReport Failed", ex );
        }
    }

    private ArrayList<AbstractColumn> getIAPPremiumOptionalADDColumns()
    {
        ArrayList<AbstractColumn> columnList = new ArrayList<>();

        AbstractColumn policyNumber = createColumnString("policyNumber", "Policy Number", 80);
        columnList.add(policyNumber);

        AbstractColumn divisionNumber = createColumnString("divisionNumber", "Division Number", 80);
        columnList.add(divisionNumber);

        AbstractColumn divisionName = createColumnString("divisionName", "Division Name", 200);
        columnList.add(divisionName);

        AbstractColumn commissionRate = createColumnFloat("fullCommissionRate", "Commission Rate %", 80);
        columnList.add(commissionRate);

        AbstractColumn coverageEffective = createColumnDate("coverageEffective", "Coverage Effective Date", 150);
        coverageEffective.setPattern("MMMM, dd, yyyy");
        columnList.add(coverageEffective);

        AbstractColumn divisionStatus = createColumnString("divisionStatus", "Division Status", 80);
        columnList.add(divisionStatus);

        AbstractColumn pctProvinceEmployeeSplit = createColumnString("pctProvinceEmployeeSplit", "PCT Province Employee Split", 100);
        columnList.add(pctProvinceEmployeeSplit);

        currentNumberLives = createColumnInt( "currentNumberLives", "Current Number Of Lives", 100 );
        columnList.add( currentNumberLives );

        currentSinglePlanVolume = createColumnFloat( "currentSinglePlanVolume", "Current Single Plan Volume", 100 );
        currentSinglePlanVolume.setPattern("#,##0.00");
        columnList.add( currentSinglePlanVolume );

        currentFamilyPlanVolume = createColumnFloat( "currentFamilyPlanVolume", "Current Family Plan Volume", 100 );
        currentFamilyPlanVolume.setPattern("#,##0.00");
        columnList.add( currentFamilyPlanVolume );

        AbstractColumn grossSinglePlanPremiumRate = createColumnFloat("grossSinglePlanPremiumRate", "Gross Single Plan Premium Rate", 100);
        grossSinglePlanPremiumRate.setPattern("#,##0.000");
        columnList.add( grossSinglePlanPremiumRate );

        AbstractColumn grossFamilyPlanPremiumRate = createColumnFloat("grossFamilyPlanPremiumRate", "Gross Family Plan Premium Rate", 100);
        grossFamilyPlanPremiumRate.setPattern("#,##0.000");
        columnList.add( grossFamilyPlanPremiumRate );


        totalGrossPremium = createColumnFloat( "totalGrossPremium", "Total Gross Premium", 100 );
        totalGrossPremium.setPattern("#,##0.00");
        columnList.add( totalGrossPremium );

        adjust = createColumnFloat( "adjust", "Adjust", 100 );
        adjust.setPattern("#,##0.00");
        columnList.add( adjust );

        totalGrossPremiumAndAdjust = createColumnFloat( "totalGrossPremiumAndAdjust", "Total Gross and Adjust", 100 );
        totalGrossPremiumAndAdjust.setPattern("#,##0.00");
        columnList.add( totalGrossPremiumAndAdjust );

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

        netPremiumPaid = createColumnFloat( "netPremiumPaid", "Net Premium", 100 );
        netPremiumPaid.setPattern("#,##0.00");
        columnList.add( netPremiumPaid );

        AbstractColumn comments = createColumnString("comments", "Comments", 300);
        columnList.add(comments);


        return columnList;
    }
}
