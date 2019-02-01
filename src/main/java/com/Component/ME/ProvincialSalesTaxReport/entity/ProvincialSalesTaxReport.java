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
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import com.Component.ME.ProvincialSalesTaxReport.repository.ProvincialSalesTaxReportRepository;
import static com.utilities.ReportStyles.*;
import com.utilities.ReportUtilities;
import static com.utilities.ReportUtilities.*;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author craig.deacon
 */
public class ProvincialSalesTaxReport
{

    public ProvincialSalesTaxReport()
    {
        
    }
    
    public void displayProvincialSalesTaxReport()
    {
        try
        {
            initStyles();
            Map parameters = new HashMap();
            
            
            
            DynamicReportBuilder dynamicReportBuilder = new DynamicReportBuilder();
            
            dynamicReportBuilder
                    .setTitle( "Provincial Sales Tax Report")
                    .setTitleStyle( LARGE )
//                    .setSubtitle( "BBD Main Subtitle\\nProvincial Sales Tax Report for January 2019\\nPrepared for BGM Financial Services Limited")
                    .setUseFullPageWidth( true )
//                    .addAutoText( AutoText.AUTOTEXT_CREATED_ON, 0, 0 )
                    .addConcatenatedReport( createProvincialSubreport("BGM Financial Services Limited"), "provincialBgm", DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION )
                    .addConcatenatedReport( createProvincialSubreport("CHUBB"), "provincialChubb", DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION, true)
                    ;
            ProvincialSalesTaxReportRepository.getProvincialGroupListBGM();
            parameters.put( "provincialBgm", ProvincialSalesTaxReportRepository.getProvincialGroupListBGM());
            parameters.put( "provincialChubb", ProvincialSalesTaxReportRepository.getProvincialGroupListChubb());
             
            DynamicReport dynamicReport = dynamicReportBuilder.build();
            
            JasperReport jasperReport = DynamicJasperHelper.generateJasperReport( dynamicReport, new ClassicLayoutManager(), parameters );
            JRDataSource dataSource = new JRBeanCollectionDataSource(ProvincialSalesTaxReportRepository.getProvincialGroupListBGM());
            
            JasperPrint jasperPrint = JasperFillManager.fillReport( jasperReport, parameters, dataSource );
            
            JasperViewer.viewReport( jasperPrint );
            
//            ReportUtilities.exportPdf( jasperPrint, "ProvincialSalesTaxReport" );
        }
        catch (JRException ex)
        {
            Logger.getLogger( ProvincialSalesTaxReport.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }
    
    
     private JasperReport createProvincialSubreport(String title) throws JRException
     {
           FastReportBuilder fastReport = new FastReportBuilder();
           
           AbstractColumn groupName = CreateColumnString("groupName", "", 85, SMALL );
           groupName.setHeaderStyle( BLUE_LEFT_GREY_BG);
           AbstractColumn policyNum = CreateColumnString("policyNum", "", 60, SMALL );
           policyNum.setHeaderStyle( BLUE_LEFT_GREY_BG);
           AbstractColumn taxPremiumOn = CreateColumnFloat("taxPremiumOn", "Taxable Premium", 40, SMALL);
           taxPremiumOn.setHeaderStyle( BLUE_LEFT_GREY_BG_BT_BORDER);
           AbstractColumn salesTaxOn = CreateColumnFloat("salesTaxOn", "Sales Tax", 40, SMALL);
           salesTaxOn.setHeaderStyle( BLUE_LEFT_GREY_BG_BT_BORDER);
           AbstractColumn taxPremiumMa = CreateColumnFloat("taxPremiumMa", "Taxable Premium", 40, SMALL);
           taxPremiumMa.setHeaderStyle( BLUE_LEFT_GREY_BG_BT_BORDER);
           AbstractColumn salesTaxMa = CreateColumnFloat("salesTaxMa", "Sales Tax", 40, SMALL);
           salesTaxMa.setHeaderStyle( BLUE_LEFT_GREY_BG_BT_BORDER);
           AbstractColumn taxPremiumQc = CreateColumnFloat("taxPremiumQc", "Taxable Premium", 40, SMALL);
           taxPremiumQc.setHeaderStyle( BLUE_LEFT_GREY_BG_BT_BORDER);
           AbstractColumn salesTaxQc = CreateColumnFloat("salesTaxQc", "Sales Tax", 40, SMALL);
           salesTaxQc.setHeaderStyle( BLUE_LEFT_GREY_BG_BT_BORDER);
           AbstractColumn taxPremiumSa = CreateColumnFloat("taxPremiumSa", "Taxable Premium", 40, SMALL);
           taxPremiumSa.setHeaderStyle( BLUE_LEFT_GREY_BG_BT_BORDER);
           AbstractColumn salesTaxSa = CreateColumnFloat("salesTaxSa", "Sales Tax", 40, SMALL);
           salesTaxSa.setHeaderStyle( BLUE_LEFT_GREY_BG_BT_BORDER);
           
           DynamicReport dynamicReport = fastReport
                   .setTitle( "")
                   .setSubtitle("BBD\\nProvincial Sales Tax Report for January 2019 \\nPrepared for " + title)
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
                   .addColumn( salesTaxSa)
                   .setColspan (0, 2, "Group Name           Policy Number", BLUE_LEFT_GREY_BG_BT_BORDER)
                   .setColspan( 2, 2, "Ontario", BLUE_LEFT_GREY_BG_BT_BORDER )
                   .setColspan( 4, 2, "Manitoba", BLUE_LEFT_GREY_BG_BT_BORDER)
                   .setColspan( 6, 2, "Quebec", BLUE_LEFT_GREY_BG_BT_BORDER )
                   .setColspan( 8, 2, "Saskatchewan", BLUE_LEFT_GREY_BG_BT_BORDER)
                   
//                   .setTitleStyle( LEFT )
                   .setUseFullPageWidth( true )
                   .build();
           return DynamicJasperHelper.generateJasperReport( dynamicReport, new ClassicLayoutManager(), null );
     }
    
}
