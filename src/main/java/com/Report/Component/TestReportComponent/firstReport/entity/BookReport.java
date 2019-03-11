/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Report.Component.TestReportComponent.firstReport.entity;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import com.Report.Component.TestReportComponent.firstReport.repository.BookReportRepository;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author craig.deacon
 */
public class BookReport
{
    private static final Logger LOGGER = Logger.getLogger( BookReport.class.getName() );

    /**
     *
     */
    public BookReport()
    {
    }
    
    /**
     *
     */
    public void displayBookReport()
    {
        FastReportBuilder dynamicReportBuilder = new FastReportBuilder();
        try
        {
            DynamicReport dynamicReport = dynamicReportBuilder.addColumn( "State", "state", String.class.getName(), 30)
                                                              .addColumn("Branch", "branch", String.class.getName(), 30)
                                                              .addColumn("Product Line", "productLine", String.class.getName(), 50)
                                                              .addColumn( "Item", "item", String.class.getName(), 50)
                                                              .addColumn("Item Code", "id", Long.class.getName(), 30, true)
                                                              .addColumn("Amount", "amount", Float.class.getName(), 70, true)
                                                              .addGroups( 3)
                                                              .setTitle( "Book Report")
                                                              .setSubtitle( "This report generated at " + new Date())
                                                              .setPrintBackgroundOnOddRows( Boolean.TRUE )
                                                              .setUseFullPageWidth( Boolean.TRUE )
                                                              .build();
            JRDataSource dataSource = new JRBeanCollectionDataSource(BookReportRepository.getBookCollection());
            JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint( dynamicReport, new ClassicLayoutManager(), dataSource );
            JasperViewer.viewReport( jasperPrint );
            
        } 
        catch (ColumnBuilderException | ClassNotFoundException ex)    
        {
            LOGGER.log( Level.SEVERE, "displayBookReport failed", ex );
        }
        catch (JRException ex)
        {
            Logger.getLogger( BookReport.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }
}
