/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Report.Component.ME.FABPPremiumReport.entity;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.constants.Page;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import com.Database.BO.FABPPremiumBO;
import com.DemoDynamicJasper.spring.config.SpringConfigurationBootstrap;
import static com.Utilities.ReportStyles.*;
import static com.Utilities.ReportUtilities.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author craig.deacon
 */
public class FABPPremiumReport
{

    private FABPPremiumBO fabpPremiumBO;

    int groupNameWidth = 350;
    int underwriterWidth = 120;
    int grossPremiumsWidth = 90;
    int commisionWidth = 70;
    int adminWidth = 55;
    int netPremiumsWidth = 90;
    int provWidth = 50;
    
    
    AbstractColumn grossPremiums;
    AbstractColumn commission;
    AbstractColumn admin;
    AbstractColumn netPremiums;
    AbstractColumn onPst;
    AbstractColumn qcPst;
    AbstractColumn skPst;

    @Autowired
    public FABPPremiumReport()
    {
        this.fabpPremiumBO = SpringConfigurationBootstrap.getApplicationContext().getBean( FABPPremiumBO.class );
    }

    public void displayFABPremiumReport()
    {
        try
        {
            initStyles();

            Map parameters = new HashMap();

            ArrayList<AbstractColumn> columnsList = getFABPPRemiumColumns();

            DynamicReportBuilder dynamicReportBuilder = createBasicReportBuilderSkeleton( columnsList, "FABP - Federation Association Benefit Plan \\nInvoice Date: January 19, 2019 \\nPremium Due Date: February 1, 2019" );

            DynamicReport dynamicReport = createFABPPremiumReport( dynamicReportBuilder );

            parameters.put( "underwriterTotals", fabpPremiumBO.getUnderwriterTotals() );

            JRDataSource dataSource = new JRBeanCollectionDataSource( fabpPremiumBO.getFABPPremiumGroups() );

            JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint( dynamicReport, new ClassicLayoutManager(), dataSource, parameters );

            exportExcel( jasperPrint, "FABP Association Benefit Plan Report", true );
        }
        catch (JRException ex)
        {
            Logger.getLogger( FABPPremiumReport.class.getName() ).log( Level.SEVERE, null, ex );
        }

    }

    private ArrayList<AbstractColumn> getFABPPRemiumColumns()
    {
        ArrayList<AbstractColumn> columnsList = new ArrayList<>();

        AbstractColumn group = createColumn( "groupName", "Group Name", groupNameWidth, "String", LEFT );
        group.setHeaderStyle( LEFT);
        columnsList.add( group );

        AbstractColumn underwriter = createColumn( "underwriter", "UnderWriter", underwriterWidth, "String", LEFT );
        underwriter.setHeaderStyle( LEFT );
        columnsList.add( underwriter );

        grossPremiums = createColumn( "grossPremiums", "Gross Premiums", grossPremiumsWidth, "Double", AMOUNT_STYLE );
        columnsList.add( grossPremiums );

        commission = createColumn( "commission", "Commission", commisionWidth, "Double", AMOUNT_STYLE );
        columnsList.add( commission );

        admin = createColumn( "admin", "Admin", adminWidth, "Double", AMOUNT_STYLE );
        columnsList.add( admin );

        netPremiums = createColumn( "netPremiums", "Net Premiums", netPremiumsWidth, "Double", AMOUNT_STYLE );
        columnsList.add( netPremiums );

        onPst = createColumn( "onPst", "ON PST", provWidth, "Double", AMOUNT_STYLE );
        columnsList.add( onPst );

        qcPst = createColumn( "qcPst", "QC PST", provWidth, "Double", AMOUNT_STYLE );
        columnsList.add( qcPst );

        skPst = createColumn( "skPst", "SK PST", provWidth, "Double", AMOUNT_STYLE );
        columnsList.add( skPst );

        return columnsList;
    }

    private DynamicReport createFABPPremiumReport( DynamicReportBuilder dynamicReportBuilder ) throws
            JRException
    {
        DynamicReport dynamicReport = dynamicReportBuilder
                .setPageSizeAndOrientation( Page.Page_A4_Landscape())
                .addGlobalFooterVariable( grossPremiums, DJCalculation.SUM )
                .addGlobalFooterVariable( commission, DJCalculation.SUM )
                .addGlobalFooterVariable( admin, DJCalculation.SUM )
                .addGlobalFooterVariable( netPremiums, DJCalculation.SUM )
                .addGlobalFooterVariable( onPst, DJCalculation.SUM )
                .addGlobalFooterVariable( qcPst, DJCalculation.SUM )
                .addGlobalFooterVariable( skPst, DJCalculation.SUM )
                .setGrandTotalLegend( "")
                .addConcatenatedReport( createUnderwritersTotalsReport(), "underwriterTotals", DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION )
                .build();
        return dynamicReport;
    }

    private JasperReport createUnderwritersTotalsReport() throws JRException
    {
        FastReportBuilder fastReport = new FastReportBuilder();

        AbstractColumn underwriter = createColumn( "underwriter", "UnderWriter", underwriterWidth, "String", LEFT );
        underwriter.setHeaderStyle( LEFT );
        AbstractColumn grossPremiumsUnderwriter = createColumn( "grossPremiums", "Gross Premiums", grossPremiumsWidth, "Double", AMOUNT_STYLE );
        AbstractColumn commissionUnderwriter = createColumn( "commission", "Commission", commisionWidth, "Double", AMOUNT_STYLE );
        AbstractColumn adminUnderwriter = createColumn( "admin", "Admin", adminWidth, "Double", AMOUNT_STYLE );
        AbstractColumn netPremiumsUnderwriter = createColumn( "netPremiums", "Net Premiums", netPremiumsWidth, "Double", AMOUNT_STYLE );
        AbstractColumn onPstUnderwriter = createColumn( "onPst", "ON PST", provWidth, "Double", AMOUNT_STYLE );
        AbstractColumn qcPstUnderwriter = createColumn( "qcPst", "QC PST", provWidth, "Double", AMOUNT_STYLE );
        AbstractColumn skPstUnderwriter = createColumn( "skPst", "SK PST", provWidth, "Double", AMOUNT_STYLE );

        DynamicReport dynamicReport = fastReport
                .addColumn( underwriter )
                .addColumn( grossPremiumsUnderwriter )
                .addColumn( commissionUnderwriter )
                .addColumn( adminUnderwriter )
                .addColumn( netPremiumsUnderwriter )
                .addColumn( onPstUnderwriter )
                .addColumn( qcPstUnderwriter )
                .addColumn( skPstUnderwriter )
//                .setDefaultStyles( null, RIGHT, RIGHT, null )
                .addGlobalFooterVariable( grossPremiumsUnderwriter, DJCalculation.SUM )
                .addGlobalFooterVariable( commissionUnderwriter, DJCalculation.SUM )
                .addGlobalFooterVariable( adminUnderwriter, DJCalculation.SUM )
                .addGlobalFooterVariable( netPremiumsUnderwriter, DJCalculation.SUM )
                .addGlobalFooterVariable( onPstUnderwriter, DJCalculation.SUM )
                .addGlobalFooterVariable( qcPstUnderwriter, DJCalculation.SUM )
                .addGlobalFooterVariable( skPstUnderwriter, DJCalculation.SUM )
                .setGrandTotalLegend( "")
                .setLeftMargin( groupNameWidth )
                .build();

        return DynamicJasperHelper.generateJasperReport( dynamicReport, new ClassicLayoutManager(), null );
    }
}
