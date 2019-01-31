/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Component.ME.RevenueReportByProduct.entity;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.DJCrosstab;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.CrosstabBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Page;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import com.Component.ME.RevenueReportByProduct.repository.RevenueByProductRepository;
import com.utilities.ReportUtilities;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.utilities.ReportStyles.*;
import static com.utilities.ReportUtilities.*;

/**
 *
 * @author craig.deacon
 */
public class RevenueByProduct
{

    public RevenueByProduct()
    {
    }

    private static final Logger LOGGER = Logger.getLogger( RevenueByProduct.class.getName() );

    public void displayRevenueByProductReport()
    {
        try
        {
            initStyles();
            Map parameters = new HashMap();
            String date = getFirstDayOfCurrentMonth();
//            DJCrosstab djcross = createCrossTab();
//            parameters.put( "source", SortUtils.sortCollection( ( RevenueByProductRepository.getGroupList() ), djcross ) );
            AbstractColumn groupAcct = CreateColumnString( "accountNo", "Group Acct #", 40, RIGHT );
            AbstractColumn groupName = CreateColumnString( "groupName", "Group Name", 50, RIGHT );
            AbstractColumn groupDivision = CreateColumnString( "division", "Group Division Name", 60, RIGHT );
            AbstractColumn productType = CreateColumnString( "productType", "Product Type", 40, RIGHT );
            AbstractColumn grossPremiums = CreateColumnFloat( "grossPremiums", "Gross Premiums", 40, AMOUNT_STYLE );
            AbstractColumn bbdAdminFee = CreateColumnFloat( "bbdAdminFee", "BBD Admin Fee", 40, AMOUNT_STYLE );
            AbstractColumn advisorCommission = CreateColumnFloat( "advisorCommission", "BBD Admin Fee", 40, AMOUNT_STYLE );
            AbstractColumn netPremiums = CreateColumnFloat( "netPremiums", "Net Premiums", 40, AMOUNT_STYLE );
            
            groupAcct.setHeaderStyle( BOLD_RIGHT_BLUE );
            groupName.setHeaderStyle( BOLD_RIGHT_BLUE );
            groupDivision.setHeaderStyle( BOLD_RIGHT_BLUE );
            productType.setHeaderStyle( BOLD_RIGHT_BLUE);
            grossPremiums.setHeaderStyle( BOLD_RIGHT_BLUE);
            bbdAdminFee.setHeaderStyle( BOLD_RIGHT_BLUE);
            advisorCommission.setHeaderStyle( BOLD_RIGHT_BLUE);
            netPremiums.setHeaderStyle( BOLD_RIGHT_BLUE);
                        
            Page page = Page.Page_Letter_Landscape();

            DynamicReportBuilder dynamicReportBuilder = new DynamicReportBuilder();

            dynamicReportBuilder
//                    .setDefaultStyles( LEFT, BOLD_RIGHT, AMOUNT_STYLE, AMOUNT_STYLE )
                    .addStyle( SUBTITLE_STYLE_PARENT )
                    .setTitle( "Premium due date: " + date )
                    .setTitleStyle( BOLD_LEFT )
                    .setTitleHeight( 15)
                    .setDetailHeight( 15 )
                    .setReportName( "Revenue by Product Type Report" )
                    .addAutoText("Benefits by Design Inc.", AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT, 400, LARGE )
                    
                    .setSubtitle( "Revenue by Product Type Report" )
                    .setSubtitleStyle( BOLD_LEFT )
                    .setUseFullPageWidth( Boolean.TRUE )
                    .addColumn( groupAcct )
                    .addColumn( groupName )
                    .addColumn( groupDivision )
                    .addColumn( productType )
                    .addColumn( grossPremiums )
                    .addColumn( bbdAdminFee )
                    .addColumn( advisorCommission )
                    .addColumn( netPremiums )
                    .addGlobalFooterVariable(grossPremiums, DJCalculation.SUM, BOLD_RIGHT )
                    .addGlobalFooterVariable(bbdAdminFee, DJCalculation.SUM, BOLD_RIGHT )
                    .addGlobalFooterVariable(advisorCommission, DJCalculation.SUM, BOLD_RIGHT )
                    .addGlobalFooterVariable(netPremiums, DJCalculation.SUM, BOLD_RIGHT )
                    .setGrandTotalLegend( "" )
                    .setPageSizeAndOrientation( page )
                    .addConcatenatedReport( createSubReportProductTypes(), "productTypes", DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION ) //                    .addFooterCrosstab( djcross )
                    ;

            parameters.put("productTypes", RevenueByProductRepository.getProductTypesList() );
            DynamicReport dynamicReport = dynamicReportBuilder.build();
            JRDataSource dataSource = new JRBeanCollectionDataSource(RevenueByProductRepository.getGroupList() );

            JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint( dynamicReport, new ClassicLayoutManager(), dataSource, parameters );

            JasperViewer.viewReport( jasperPrint );

//            ReportUtilities.exportExcel(jasperPrint, "RevByProduct");
        }
        catch (JRException ex)
        {
            LOGGER.log( Level.SEVERE, null, ex );
        }
    }

    private JasperReport createSubReportProductTypes() throws JRException
    {
        AbstractColumn productName = CreateColumnString("productName", "", 60, BOLD_LEFT_BLUE );
        AbstractColumn groupCount = CreateColumnInt( "groupCount", "Group Count", 40, LEFT );
        AbstractColumn totalGrossPremiums = CreateColumnFloat( "totalGrossPremiums", "Gross Premiums", 50, AMOUNT_STYLE );
        AbstractColumn totalBbdAdminFee = CreateColumnFloat( "totalBbdAdminFee", "BBD Admin Fee", 50, AMOUNT_STYLE );
        AbstractColumn totalAdvisorCommissions = CreateColumnFloat( "totalAdvisorCommissions", "Advisor Commission", 60, AMOUNT_STYLE );
        AbstractColumn totalNetPremiums = CreateColumnFloat( "totalNetPremiums", "Net Premiums", 40, AMOUNT_STYLE );
        
        productName.setHeaderStyle( BOLD_LEFT_BLUE);
        groupCount.setHeaderStyle( BOLD_LEFT_BLUE );
        totalGrossPremiums.setHeaderStyle( BOLD_RIGHT_BLUE);
        totalBbdAdminFee.setHeaderStyle( BOLD_RIGHT_BLUE);
        totalAdvisorCommissions.setHeaderStyle( BOLD_RIGHT_BLUE);
        totalNetPremiums.setHeaderStyle( BOLD_RIGHT_BLUE);

        FastReportBuilder fastReport = new FastReportBuilder();
        DynamicReport dynamicReport = fastReport
                .addColumn( productName )
                .addColumn( groupCount )
                .addColumn( totalGrossPremiums )
                .addColumn( totalBbdAdminFee )
                .addColumn( totalAdvisorCommissions )
                .addColumn( totalNetPremiums )
                .addGlobalFooterVariable(totalGrossPremiums, DJCalculation.SUM, BOLD_RIGHT )
                .addGlobalFooterVariable(totalBbdAdminFee, DJCalculation.SUM, BOLD_RIGHT )
                .addGlobalFooterVariable(totalAdvisorCommissions, DJCalculation.SUM, BOLD_RIGHT )
                .addGlobalFooterVariable(totalNetPremiums, DJCalculation.SUM, BOLD_RIGHT )
                .setGrandTotalLegend( "" )
                .setUseFullPageWidth( true )
                .build();

        return DynamicJasperHelper.generateJasperReport( dynamicReport, new ClassicLayoutManager(), null );

    }

    private DJCrosstab createCrossTab()
    {
        DJCrosstab djct = new CrosstabBuilder()
                .setHeight( 200 )
                .setWidth( 500 )
                .setDatasource( "source", DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION )
                .setUseFullWidth( true )
                .setAutomaticTitle( false )
                .setCellBorder( Border.THIN() )
                .addRow( "Product Type", "productType", String.class.getName(), true )
                .addColumn( "Gross Premiums", "grossPremiums", Float.class.getName(), true )
                .addColumn( "BBD Admin Fee", "bbdAdminFee", Float.class.getName(), true )
                .addColumn( "Advisor Commission", "advisorCommission", Float.class.getName(), true )
                .addColumn( "Net Premiums", "netPremiums", Float.class.getName(), true )
                .addMeasure( "grossPremiums", Float.class.getName(), DJCalculation.SUM, "Total", LEFT )
                .setCellDimension( 17, 60 )
                .setColumnHeaderHeight( 30 )
                .setRowHeaderWidth( 80 )
                .build();
        return djct;
    }
    
    protected String getFirstDayOfCurrentMonth()
    {
        String currentDate;
        Date date = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime( date );
        int year = cal.get( Calendar.YEAR );
        int month = cal.get( Calendar.MONTH ) + 1;

        currentDate = year + "";
        currentDate += month < 10 ? "0" + month : month;
        currentDate += "01";
        return currentDate;
    }
}
