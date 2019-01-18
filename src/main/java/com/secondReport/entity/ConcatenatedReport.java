/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.secondReport.entity;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import com.secondReport.repository.ConcatenatedReportRepository;
import static com.secondReport.utilities.ReportStyles.BOLD;
import static com.secondReport.utilities.ReportStyles.HEADER_STYLE;
import static com.secondReport.utilities.ReportStyles.LEFT;
import static com.secondReport.utilities.ReportStyles.initStyles;
import static com.secondReport.utilities.ReportUtilities.CreateColumnString;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 * Attempting to combine two reports side by side, work in progress
 * 
 * @author craig.deacon
 */
public class ConcatenatedReport
{

    public ConcatenatedReport()
    {
    }

    private static final Logger LOGGER = Logger.getLogger( EmployeeReport.class.getName() );
    
    /**
     * Combines the two created subreports into a single report, does not currently work
     */
    public void displayConcatReport()
    {
        try
        {
            initStyles();
            Map parameters = new HashMap();

            parameters.put("rows", ConcatenatedReportRepository.getRows());
            parameters.put("benefits", ConcatenatedReportRepository.getBenefits());
            DynamicReportBuilder dynamicReportBuilder = new DynamicReportBuilder();
            
            dynamicReportBuilder
                    .addConcatenatedReport( createSubReportA(), new ClassicLayoutManager(), "rows", DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION)
                    .addConcatenatedReport( createSubReportB(), new ClassicLayoutManager(), "benefits", DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION);
        
        
            DynamicReport dynamicReport = dynamicReportBuilder.build();
            
            JasperReport jr = DynamicJasperHelper.generateJasperReport(dynamicReport, new ClassicLayoutManager(), parameters);
            
            JasperPrint jasperPrint = JasperFillManager.fillReport(jr, parameters);
            
            JasperViewer.viewReport( jasperPrint );
        }
        catch (JRException ex)
        {
            Logger.getLogger( ConcatenatedReport.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }
    
    /**
     * Creates the first subreport of the concatenated reoprt
     * 
     * @return The created dynamic report
     */
    
    private DynamicReport createSubReportA()
    {
        AbstractColumn column1 = CreateColumnString( "column1", "", 60, BOLD );
        AbstractColumn column2 = CreateColumnString( "column2", "Group Insurance Certificate Plan Administered by BBD", 120, LEFT );
        DynamicReportBuilder dynamicReportBuilder = new DynamicReportBuilder();
        dynamicReportBuilder
                .setPrintBackgroundOnOddRows( Boolean.TRUE )
                .addColumn( column1 )
                .addColumn( column2 )
                .setDefaultStyles( LEFT, LEFT, HEADER_STYLE, LEFT );
                
        DynamicReport dynamicReport = dynamicReportBuilder.build();
        return dynamicReport;
    }
    
    /**
     * Creates the second subreport of the Concatenated Report
     * 
     * @return the created DynamicReport
     */
    private DynamicReport createSubReportB()
    {
        DynamicReportBuilder dynamicReportBuilder = new DynamicReportBuilder();
        
        AbstractColumn columnBenefit = CreateColumnString( "benefit", "Benefit", 30);
        AbstractColumn columnSupplier = CreateColumnString( "supplier", "Supplier", 30);
        AbstractColumn columnPolicyNo = CreateColumnString( "policyNo", "Policy #", 30);
        AbstractColumn columnEffective = CreateColumnString( "date", "Effective", 30);
        
        dynamicReportBuilder
                    
                    .setDetailHeight( 15 )
                    .setReportName( "Benefit Summary" )
                    .setTitle( "Benefit Summary" )                 
                    .setPrintBackgroundOnOddRows( Boolean.TRUE );
                    
        dynamicReportBuilder
                .addColumn( columnBenefit )
                .addColumn( columnSupplier)
                .addColumn(columnPolicyNo)
                .addColumn(columnEffective);
                
        
        DynamicReport dynamicReport = dynamicReportBuilder.build();
        return dynamicReport;
        
    }

}
