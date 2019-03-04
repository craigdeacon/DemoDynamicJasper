package com.utilities;

import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import com.lowagie.text.pdf.PdfContentByte;
import java.awt.Desktop;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.ExporterFilter;
import net.sf.jasperreports.engine.export.JRExportProgressMonitor;
import net.sf.jasperreports.engine.export.JRHyperlinkProducerFactory;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.PdfExporterConfiguration;
import net.sf.jasperreports.export.PdfReportConfiguration;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

import static com.utilities.ReportStyles.BOLD_LEFT;
import static com.utilities.ReportStyles.LEFT;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author craig.deacon
 */
public class ReportUtilities
{

    static String filePath;
    static File excelOutputFile;
    static File pdfOutputFile;
    static FileOutputStream fileOutputStream;

    /**
     * Creates a column when property is a String
     *
     * @param property The name of the property to be used from the data source
     * @param title Column title on the report
     * @param width width of column
     * @return column object
     */
    public static AbstractColumn createColumnString( String property, String title, int width )
    {
        AbstractColumn column = ColumnBuilder.getNew()
                                             .setColumnProperty( property, String.class.getName() )
                                             .setTitle( title )
                                             .setWidth( width )
                                             .build();
        return column;
    }

    /**
     * Creates a string based column with a Style
     *
     * @param property The property name of value from data source
     * @param title Column header in report
     * @param width Desired width of column
     * @param style Style to be applied to column
     * @return Column object
     */
    public static AbstractColumn createColumnString( String property, String title, int width, Style style )
    {
        AbstractColumn column = ColumnBuilder.getNew()
                                             .setColumnProperty( property, String.class.getName() )
                                             .setTitle( title )
                                             .setWidth( width )
                                             .setStyle( style )
                                             .build();
        return column;
    }

    /**
     * Creates a float based column
     *
     * @param property The property name of value from data source
     * @param title Column header in report
     * @param width Desired width of column
     * @return Column object
     */
    public static AbstractColumn createColumnFloat( String property, String title, int width )
    {
        AbstractColumn column = ColumnBuilder.getNew()
                                             .setColumnProperty( property, Float.class.getName() )
                                             .setTitle( title )
                                             .setWidth( width )
                                             .build();
        return column;
    }

    /**
     * Creates a float based column with a Style
     *
     * @param property The property name of value from data source
     * @param title Column header in report
     * @param width Desired width of column
     * @param style Style to be applied to column
     * @return Column object
     */
    public static AbstractColumn createColumnFloat( String property, String title, int width, Style style )
    {
        AbstractColumn column = ColumnBuilder.getNew()
                                             .setColumnProperty( property, Float.class.getName() )
                                             .setTitle( title )
                                             .setWidth( width )
                                             .setStyle( style )
                                             .build();
        return column;
    }

    /**
     *
     * @param property
     * @param title
     * @param width
     * @param style
     * @return
     */
    public static AbstractColumn createColumnInt( String property, String title, int width, Style style )
    {
        AbstractColumn column = ColumnBuilder.getNew()
                                             .setColumnProperty( property, Integer.class.getName() )
                                             .setTitle( title )
                                             .setWidth( width )
                                             .setStyle( style )
                                             .build();
        return column;
    }


    /**
     *
     * @param property
     * @param title
     * @param width
     * @param style
     * @return
     */
    public static AbstractColumn createColumnInt( String property, String title, int width )
    {
        AbstractColumn column = ColumnBuilder.getNew()
                                             .setColumnProperty( property, Integer.class.getName() )
                                             .setTitle( title )
                                             .setWidth( width )
                                             .build();
        return column;
    }

    public static AbstractColumn createColumnDate( String property, String title, int width )
    {
        AbstractColumn column = ColumnBuilder.getNew()
                                             .setColumnProperty( property, Date.class.getName() )
                                             .setTitle( title )
                                             .setWidth( width )
                                             .build();
        return column;
    }

    public static AbstractColumn createColumnDate( String property, String title, int width, Style style )
    {
        AbstractColumn column = ColumnBuilder.getNew()
                                             .setColumnProperty( property, Date.class.getName() )
                                             .setTitle( title )
                                             .setWidth( width )
                                             .setStyle( style )
                                             .build();
        return column;
    }


    /**
     * Opens a generated report file
     *
     * @param outputFile
     */
    public static void OpenFile( File outputFile )
    {
        if ( Desktop.isDesktopSupported() )
        {
            Desktop desktop = Desktop.getDesktop();
            if ( outputFile.exists() )
            {
                try
                {
                    desktop.open( outputFile );
                }
                catch (IOException ex)
                {
                    Logger.getLogger( ReportUtilities.class.getName() ).log( Level.SEVERE, null, ex );
                }
                finally
                {

                }
            }
        }
    }

    /**
     * Creates and opens a PDF of a dynamic report
     *
     * @param jasperPrint The report to be exported
     */
    public static void exportPdf( JasperPrint jasperPrint )
    {
        exportPdf( jasperPrint, "Report" );
    }

    /**
     * Creates and opens a PDF of a dynamic report with a custom file name
     *
     * @param jasperPrint The report to be exported
     * @param reportName file name of the report
     */
    public static void exportPdf( JasperPrint jasperPrint, String reportName )
    {
        try
        {
            filePath = "Reports/";
            pdfOutputFile = new File( filePath + reportName + ".pdf" );
            fileOutputStream = new FileOutputStream( pdfOutputFile );
            JRPdfExporter pdfExporter = new JRPdfExporter();
            pdfExporter.setExporterInput( new SimpleExporterInput( jasperPrint ) );
            pdfExporter.setExporterOutput( new SimpleOutputStreamExporterOutput( fileOutputStream ) );

            //currently does nothing
            SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
            pdfExporter.setConfiguration( configuration );
            //

            pdfExporter.exportReport();
            fileOutputStream.close();
            OpenFile( pdfOutputFile );
        }
        catch (JRException | IOException ex)
        {
            Logger.getLogger( ReportUtilities.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }

    /**
     * Creates and opens an Excel file of the generated report
     *
     * @param jasperPrint the report to be exported
     */
    public static void exportExcel( JasperPrint jasperPrint )
    {
        exportExcel( jasperPrint, "Report" );
    }

    /**
     * Creates and opens an Excel file of a report with a custom file name
     *
     * @param jasperPrint the report to be exported
     * @param reportName file name of exported report
     */
    public static void exportExcel( JasperPrint jasperPrint, String reportName )
    {
        try
        {
            filePath = "Reports/";
            excelOutputFile = new File( filePath + reportName + ".xls" );
            fileOutputStream = new FileOutputStream( excelOutputFile );
            JRXlsExporter excelExporter = new JRXlsExporter();
            excelExporter.setExporterInput( new SimpleExporterInput( jasperPrint ) );
            excelExporter.setExporterOutput( new SimpleOutputStreamExporterOutput( fileOutputStream ) );

            SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
            configuration.setOnePagePerSheet( Boolean.FALSE );
            configuration.setRemoveEmptySpaceBetweenRows( Boolean.TRUE );
            configuration.setRemoveEmptySpaceBetweenColumns(Boolean.FALSE );
            configuration.setWhitePageBackground( Boolean.FALSE );
            configuration.setFreezeRow( 3 );


            excelExporter.setConfiguration( configuration );
            excelExporter.exportReport();
            fileOutputStream.close();
            OpenFile( excelOutputFile );
        }
        catch (JRException | IOException ex)
        {
            Logger.getLogger( ReportUtilities.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }

    public static DynamicReport createBasicReportSkeleton(ArrayList<AbstractColumn> columns, String title)
    {
        DynamicReportBuilder dynamicReportBuilder = new DynamicReportBuilder();
        ArrayList<AbstractColumn> columnList = columns;
        columnList.forEach( (column) ->
                            {
                                dynamicReportBuilder.addColumn( column );
                            } );

        dynamicReportBuilder
                .setTitle( title )
                .setTitleHeight( 40 )
                .setTitleStyle( BOLD_LEFT )
                .setDefaultStyles( BOLD_LEFT, null, BOLD_LEFT, LEFT)
                .setIgnorePagination( true );

        DynamicReport dynamicReport = dynamicReportBuilder.build();

        return dynamicReport;
    }
}
