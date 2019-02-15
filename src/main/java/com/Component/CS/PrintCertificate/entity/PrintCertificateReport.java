/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Component.CS.PrintCertificate.entity;

import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.builders.GroupBuilder;
import ar.com.fdvs.dj.domain.constants.GroupLayout;
import ar.com.fdvs.dj.domain.entities.DJGroup;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import ar.com.fdvs.dj.domain.entities.columns.PropertyColumn;
import com.Component.CS.PrintCertificate.repository.PrintCertificateReportRepository;
import com.utilities.ReportUtilities;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.utilities.ReportStyles.*;
import static com.utilities.ReportUtilities.CreateColumnString;

/**
 * Attempting to combine two reports side by side, work in progress
 *
 * @author craig.deacon
 */
public class PrintCertificateReport
{

    /**
     *
     */
    public PrintCertificateReport()
    {
    }

    private static final Logger LOGGER = Logger.getLogger( PrintCertificateReport.class.getName() );

    /**
     * Combines the two created subreports into a single report, does not
     * currently work
     */
    public void displayConcatReport()
    {
        try
        {
            initStyles();
            Map parameters = new HashMap();
            Style empty = new Style();
            empty.isBlankWhenNull();
            empty.isOverridesExistingStyle();
            AbstractColumn columnName = ColumnBuilder.getNew()
                    .setColumnProperty( "name", String.class.getName() )
                    .build();

//            GroupBuilder groupBuilder = new GroupBuilder();
//
//            DJGroup group1 = groupBuilder
//                    .setCriteriaColumn( (PropertyColumn) columnName )
//                    .setGroupLayout( GroupLayout.EMPTY )
//                    .build();

            DynamicReportBuilder dynamicReportBuilder = new DynamicReportBuilder();

            dynamicReportBuilder
                    .addConcatenatedReport( createSubReportA(), "rows", DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION )
                    .addConcatenatedReport( createSubReportB(), "benefits", DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION )
//                    .addField( "name", String.class.getName() )
//                    .addGroup( group1 )
//                    .setColumnsPerPage( 2)
                    ;

            parameters.put("rows", PrintCertificateReportRepository.getRows() );
            parameters.put("benefits", PrintCertificateReportRepository.getBenefits() );
            DynamicReport dynamicReport = dynamicReportBuilder.build();

            JasperReport jr = DynamicJasperHelper.generateJasperReport( dynamicReport, new ClassicLayoutManager(), parameters );
            JRDataSource dataSource = new JRBeanCollectionDataSource(PrintCertificateReportRepository.getRows() );

            JasperPrint jasperPrint = JasperFillManager.fillReport( jr, parameters, dataSource );

            JasperViewer.viewReport( jasperPrint );

//            ReportUtilities.exportPdf( jasperPrint, "SubReports" );
        }
        catch (JRException ex)
        {
            Logger.getLogger( PrintCertificateReport.class.getName() ).log(Level.SEVERE, null, ex );
        }
    }

    /**
     * Creates the first subreport of the concatenated report
     *
     * @return The created dynamic report
     */
    private JasperReport createSubReportA() throws JRException
    {
        AbstractColumn column1 = CreateColumnString("column1", "", 60, BOLD_RIGHT );
        AbstractColumn column2 = CreateColumnString( "column2", "Group Insurance Certificate Plan Administered by BBD", 120, LEFT );

        FastReportBuilder fastReport = new FastReportBuilder();
        DynamicReport dynamicReport = fastReport
                .setPrintBackgroundOnOddRows( Boolean.TRUE )
                .addColumn( column1 )
                .addColumn( column2 )
                .setTitle( "Subreport A" )
                .setDefaultStyles( LEFT, LEFT, HEADER_STYLE, LEFT )
                .build();

//            JRDataSource dataSource = new JRBeanCollectionDataSource( PrintCertificateReportRepository.getRows() );
//            JasperPrint jr = DynamicJasperHelper.generateJasperPrint(dynamicReport, new ClassicLayoutManager(), dataSource);
//            JasperViewer.viewReport( jasperPrint );
        return DynamicJasperHelper.generateJasperReport( dynamicReport, new ClassicLayoutManager(), null );
    }

    /**
     * Creates the second subreport of the Concatenated Report
     *
     * @return the created DynamicReport
     */
    private JasperReport createSubReportB() throws JRException
    {
        DynamicReportBuilder dynamicReportBuilder = new DynamicReportBuilder();

        AbstractColumn columnBenefit = CreateColumnString( "benefit", "Benefit", 70 );
        AbstractColumn columnSupplier = CreateColumnString( "supplier", "Supplier", 100 );
        AbstractColumn columnPolicyNo = CreateColumnString( "policyNo", "Policy #", 100 );
        AbstractColumn columnEffective = CreateColumnString( "date", "Effective", 70 );

        dynamicReportBuilder
                .setDetailHeight( 15 )
                .setReportName( "Benefit Summary" )
                .setTitle( "Benefit Summary" )
                .setPrintBackgroundOnOddRows( Boolean.TRUE );

        dynamicReportBuilder
                .addColumn( columnBenefit )
                .addColumn( columnSupplier )
                .addColumn( columnPolicyNo )
                .addColumn( columnEffective );

        DynamicReport dynamicReport = dynamicReportBuilder.build();
        return DynamicJasperHelper.generateJasperReport( dynamicReport, new ClassicLayoutManager(), null );

    }

//    public class CustomLayoutManager extends ClassicLayoutManager
//    {
//
//        @Override
//        protected void transformDetailBandTextField( AbstractColumn column, JRDesignTextField textField )
//        {
//            super.transformDetailBandTextField( column, textField );
//            if ( column.getStyle().isBlankWhenNull() )
//            {
//                textField.setRemoveLineWhenBlank( true );
//            }
//        }
//    }
}
