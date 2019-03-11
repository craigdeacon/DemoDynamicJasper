/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Component.ME.ProvincialSalesTaxReport.entity;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.constants.Page;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import com.BO.ProvincialSalesTaxBO;

import static com.utilities.ReportStyles.*;
import static com.utilities.ReportUtilities.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.DemoDynamicJasper.spring.config.SpringConfigurationBootstrap;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperReport;

/**
 *
 * @author craig.deacon
 */
public class ProvincialSalesTaxReport
{

    private ProvincialSalesTaxBO provincialSalesTaxBO;
    /**
     *
     */
    public ProvincialSalesTaxReport()
    {
        this.provincialSalesTaxBO = SpringConfigurationBootstrap.getApplicationContext().getBean(ProvincialSalesTaxBO.class);
    }

    /**
     *
     */
    public void displayProvincialSalesTaxReport()
    {
        try
        {
            initStyles();
            Map parameters = new HashMap();
//            HashMap<String, List<ProvincialGroup>> groupMap = provincialSalesTaxBO.getProvincialGroupList();
            String pattern = "MMMMM dd, yyyy HH:mm:ss ";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(new Date());
            
            HashMap<String, Integer> groupKey = provincialSalesTaxBO.getUnderWriters();
           

            DynamicReportBuilder dynamicReportBuilder = new DynamicReportBuilder();
            dynamicReportBuilder
                    .setTitle( "Provincial Sales Tax Report" )
                    .setTitleStyle( LARGE )
                    .setPageSizeAndOrientation( Page.Page_Letter_Landscape() )
                    .setUseFullPageWidth( true )
//                    .setLeftMargin( 85 )
                    .addAutoText( date, AutoText.POSITION_FOOTER, AutoText.ALIGMENT_LEFT, 350, SMALL )
                    .addAutoText( AutoText.AUTOTEXT_PAGE_X_OF_Y, AutoText.POSITION_FOOTER, AutoText.ALIGMENT_RIGHT, 200, 20, SMALL )
                    .addAutoText ( "User: Craig Deacon", AutoText.POSITION_FOOTER, AutoText.ALIGNMENT_CENTER, 300, SMALL )
//                    .setTemplateFile("Templates/pst_template.jrxml", true, true, true, false)
                    ;
           
            
            boolean notFirstPass = false;
            for ( Map.Entry<String, Integer> entry : groupKey.entrySet() )
            {
                dynamicReportBuilder.addConcatenatedReport( createProvincialSubreport( entry.getKey() ), entry.getKey(), DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION, notFirstPass );
//                parameters.put( entry.getKey(), ( groupMap.get( entry.getKey() ) ) );
                if ( !notFirstPass )
                    notFirstPass = true;
                
            }
         
//            parameters.put("leftHeader", "Testing Left Header " ); 
//            Headers work, but line breaks won't.

            DynamicReport dynamicReport = dynamicReportBuilder.build();

            
            JasperReport jasperReport = DynamicJasperHelper.generateJasperReport( dynamicReport, new ClassicLayoutManager(), parameters );

//            JRDataSource dataSource = new JRBeanCollectionDataSource( groupMap.get( "CHUBB" ) );

//            JasperPrint jasperPrint = JasperFillManager.fillReport( jasperReport, parameters, dataSource );

//            JasperViewer.viewReport( jasperPrint );

//            ReportUtilities.exportPdf(jasperPrint, "ProvincialSalesTaxReport" );
            
//            ReportUtilities.exportExcel(jasperPrint, "PST");
        }
        catch (JRException ex)
        {
            Logger.getLogger( ProvincialSalesTaxReport.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }

    private JasperReport createProvincialSubreport( String title ) throws
            JRException
    {
        FastReportBuilder fastReport = new FastReportBuilder();

        AbstractColumn groupName = createColumnString( "groupName", "", 100, SMALL );
        groupName.setHeaderStyle( BLUE_LEFT_GREY_BG_T_BORDER );
        groupName.setBlankWhenNull( true );
        
        AbstractColumn policyNum = createColumnString( "policyNum", "", 60, SMALL );
        policyNum.setHeaderStyle( BLUE_LEFT_GREY_BG_T_BORDER );
        policyNum.setBlankWhenNull( true );
        AbstractColumn taxPremiumOn = createColumnFloat( "taxPremiumOn", "Taxable Premium", 40, SMALL_RIGHT );
        taxPremiumOn.setHeaderStyle( BLUE_LEFT_GREY_BG_BT_BORDER );
        taxPremiumOn.setBlankWhenNull( true );
        AbstractColumn salesTaxOn = createColumnFloat( "salesTaxOn", "Sales Tax", 40, SMALL_RIGHT );
        salesTaxOn.setHeaderStyle( BLUE_LEFT_GREY_BG_BT_BORDER );
        salesTaxOn.setBlankWhenNull( true );
        AbstractColumn taxPremiumMa = createColumnFloat( "taxPremiumMa", "Taxable Premium", 40, SMALL_RIGHT );
        taxPremiumMa.setHeaderStyle( BLUE_LEFT_GREY_BG_BT_BORDER );
        taxPremiumMa.setBlankWhenNull( true );
        AbstractColumn salesTaxMa = createColumnFloat( "salesTaxMa", "Sales Tax", 40, SMALL_RIGHT );
        salesTaxMa.setHeaderStyle( BLUE_LEFT_GREY_BG_BT_BORDER );
        salesTaxMa.setBlankWhenNull( true );
        AbstractColumn taxPremiumQc = createColumnFloat( "taxPremiumQc", "Taxable Premium", 40, SMALL_RIGHT );
        taxPremiumQc.setHeaderStyle( BLUE_LEFT_GREY_BG_BT_BORDER );
        taxPremiumQc.setBlankWhenNull( true );
        AbstractColumn salesTaxQc = createColumnFloat( "salesTaxQc", "Sales Tax", 40, SMALL_RIGHT );
        salesTaxQc.setHeaderStyle( BLUE_LEFT_GREY_BG_BT_BORDER );
        salesTaxQc.setBlankWhenNull( true );
        AbstractColumn taxPremiumSa = createColumnFloat( "taxPremiumSa", "Taxable Premium", 40, SMALL_RIGHT );
        taxPremiumSa.setHeaderStyle( BLUE_LEFT_GREY_BG_BT_BORDER );
        taxPremiumSa.setBlankWhenNull( true );
        AbstractColumn salesTaxSa = createColumnFloat( "salesTaxSa", "Sales Tax", 40, SMALL_RIGHT );
        salesTaxSa.setHeaderStyle( BLUE_LEFT_GREY_BG_BT_BORDER );
        salesTaxSa.setBlankWhenNull( true );
        Map parameters = new HashMap<>();
        parameters.put("leftHeader", "BBD\\nProvincial Sales Tax Report for January 2019 \\nPrepared for " + title );

        DynamicReport dynamicReport = new DynamicReport();
        dynamicReport = fastReport
                .setTitle( "")
//                .setSubtitle("BBD\\nProvincial Sales Tax Report for January 2019 \\nPrepared for " + title)
//                .setTemplateFile("Templates/pst_template.jrxml", true, true, true, false)
                .setPageSizeAndOrientation( Page.Page_Letter_Landscape())
                .setSubtitleStyle(BLUE_LEFT_GREY_BG )
                .addColumn( groupName )
                .addColumn( policyNum )
                .addColumn( taxPremiumOn )
                .addColumn( salesTaxOn )
                .addColumn( taxPremiumMa )
                .addColumn( salesTaxMa )
                .addColumn( taxPremiumQc )
                .addColumn( salesTaxQc )
                .addColumn( taxPremiumSa )
                .addColumn( salesTaxSa )
//                .setLeftMargin( 85 )
                .setColspan(0, 2, "Prepared for " + title , BLUE_LEFT_GREY_BG )
                .setColspan( 2, 2, "Ontario", BLUE_LEFT_GREY_BG )
                .setColspan( 4, 2, "Manitoba", BLUE_LEFT_GREY_BG )
                .setColspan( 6, 2, "Quebec", BLUE_LEFT_GREY_BG )
                .setColspan( 8, 2, "Saskatchewan", BLUE_LEFT_GREY_BG )
                .setUseFullPageWidth( true )
                .build();
        return DynamicJasperHelper.generateJasperReport( dynamicReport, new ClassicLayoutManager(), parameters );
    }

}
