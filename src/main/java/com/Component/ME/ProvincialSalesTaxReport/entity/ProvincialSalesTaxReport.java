/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Component.ME.ProvincialSalesTaxReport.entity;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
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
                    .setTitle( "Main Title")
                    .setSubtitle( "BBD Main Subtitle\\nProvincial Sales Tax Report for January 2019\\nPrepared for BGM Financial Services Limited")
                    .setUseFullPageWidth( true )
                    .addConcatenatedReport( createProvincialSubreport("BGM"), "provincialBgm", DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION )
                    .addConcatenatedReport( createProvincialSubreport("CHUBB"), "provincialChubb", DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION, true)
                    ;
            
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
           
           AbstractColumn groupName = CreateColumnString("groupName", "Group Name \\n ", 85 );
           AbstractColumn policyNum = CreateColumnString("policyNum", "Policy Number \\n ", 60 );
           AbstractColumn taxPremiumOn = CreateColumnFloat("taxPremiumOn", "Taxable Premium", 40);
           AbstractColumn salesTaxOn = CreateColumnFloat("salesTaxOn", "Sales Tax", 40);
           AbstractColumn taxPremiumMa = CreateColumnFloat("taxPremiumMa", "Taxable Premium", 40);
           AbstractColumn salesTaxMa = CreateColumnFloat("salesTaxMa", "Sales Tax", 40);
           AbstractColumn taxPremiumQc = CreateColumnFloat("taxPremiumQc", "Taxable Premium", 40);
           AbstractColumn salesTaxQc = CreateColumnFloat("salesTaxQc", "Sales Tax", 40);
           AbstractColumn taxPremiumSa = CreateColumnFloat("taxPremiumSa", "Taxable Premium", 40);
           AbstractColumn salesTaxSa = CreateColumnFloat("salesTaxSa", "Sales Tax", 40);
           
           DynamicReport dynamicReport = fastReport
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
                   .setColspan( 2, 2, "Ontario" )
                   .setColspan( 4, 2, "Manitoba")
                   .setColspan( 6, 2, "Quebec" )
                   .setColspan( 8, 2, "Saskatchewan")
                   .setTitle( title )
                   .setTitleStyle( LEFT )
                   .setUseFullPageWidth( true )
                   .build();
           return DynamicJasperHelper.generateJasperReport( dynamicReport, new ClassicLayoutManager(), null );
     }
    
}
