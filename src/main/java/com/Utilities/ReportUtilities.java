package com.Utilities;

import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.ColumnBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.entities.DJGroupVariable;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
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
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimpleXlsReportConfiguration;

import static com.Utilities.ReportStyles.BOLD_LEFT;
import static com.Utilities.ReportStyles.LEFT;

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

    private static String filePath;
    private static FileOutputStream fileOutputStream;
    
    
    
     /**
     * Creates abstract column
     *
     * @param property The name of the property to be used from the data source
     * @param title Column title on the report
     * @param width width of column
     * @param type data type of property (String, Float, Integer, Date)
     * @return column object
     */
    public static AbstractColumn createColumnNoStyle( String property, String title, int width, String type )
    {
        
        return ColumnBuilder.getNew()
                            .setColumnProperty( property, "java.lang." + type )
                            .setTitle( title )
                            .setWidth( width )
                            .build();
    }
    
    /**
     * Creates abstract column
     *
     * @param property The name of the property to be used from the data source
     * @param title Column title on the report
     * @param width width of column
     * @param type data type of property (String, Float, Integer, Date)
     * @param style The style to be applied to column
     * @return column object
     */
    public static AbstractColumn createColumn( String property, String title, int width, String type, Style style )
    {        
        return ColumnBuilder.getNew()
                            .setColumnProperty( property, "java.lang." + type )
                            .setTitle( title )
                            .setWidth( width )
                            .setStyle( style )
                            .build();
    }

    

    /**
     * Creates a column where property is a String
     *
     * @param property The name of the property to be used from the data source
     * @param title Column title on the report
     * @param width width of column
     * @return column object
     */
    public static AbstractColumn createColumnString( String property, String title, int width )
    {
        
        return ColumnBuilder.getNew()
                            .setColumnProperty( property, String.class.getName() )
                            .setTitle( title )
                            .setWidth( width )
                            .build();
    }

    /**
     * Creates a string based column with a Style applied
     *
     * @param property The property name of value from data source
     * @param title Column header in report
     * @param width Desired width of column
     * @param style Style to be applied to column
     * @return Column object
     */
    public static AbstractColumn createColumnString( String property, String title, int width, Style style )
    {
   
        return ColumnBuilder.getNew()
                            .setColumnProperty( property, String.class.getName() )
                            .setTitle( title )
                            .setWidth( width )
                            .setStyle( style )
                            .build();
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
        return ColumnBuilder.getNew()
                            .setColumnProperty( property, Float.class.getName() )
                            .setTitle( title )
                            .setWidth( width )
                            .build();
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
        return ColumnBuilder.getNew()
                            .setColumnProperty( property, Float.class.getName() )
                            .setTitle( title )
                            .setWidth( width )
                            .setStyle( style )
                            .build();
    }
    
    /**
     * Creates a float based column with a conditional Styles applied
     * 
     * @param property The property name of value from data source
     * @param title Column header in report
     * @param width Width of the column
     * @param conditionalStyles An arrayList of conditional styles that will be
     * applied based on property value
     * @return The completed column.
     */       
    public static AbstractColumn createColumnFloatConditionalStyle( String property, String title, int width, ArrayList conditionalStyles )
    {
        return ColumnBuilder.getNew()
                .setColumnProperty( property, Float.class.getName() )
                .setTitle( title )
                .setWidth( width )
                .addConditionalStyles( conditionalStyles )
                .build();
    }
    
    /**
     * Creates a int based column with a Style applied
     *
     * @param property The property name of value from data source
     * @param title Column header in report
     * @param width Desired width of column
     * @param style Style to be applied to column
     * @return Column object
     */
    public static AbstractColumn createColumnInt( String property, String title, int width, Style style )
    {
        return ColumnBuilder.getNew()
                            .setColumnProperty( property, Integer.class.getName() )
                            .setTitle( title )
                            .setWidth( width )
                            .setStyle( style )
                            .build();
    }

    
    /**
     * Creates a int based column
     * 
     * @param property The property name of value from data source
     * @param title Column header in report
     * @param width Desired width of column
     * @return Column object
     */
    public static AbstractColumn createColumnInt( String property, String title, int width )
    {
        return ColumnBuilder.getNew()
                            .setColumnProperty( property, Integer.class.getName() )
                            .setTitle( title )
                            .setWidth( width )
                            .build();
    }
   
    /**
     * Creates a date based column with a style applied
     * 
     * @param property The property name of value from data source
     * @param title Column header in report
     * @param width Desired width of column
     * @param style Style to be applied to column
     * @return Column object
     */
    public static AbstractColumn createColumnDate( String property, String title, int width, Style style )
    {
        return ColumnBuilder.getNew()
                            .setColumnProperty( property, Date.class.getName() )
                            .setTitle( title )
                            .setWidth( width )
                            .setStyle( style )
                            .build();
    }
   
    /**
     * Creates a date based column
     * 
     * @param property The property name of value from data source
     * @param title Column header in report
     * @param width Desired width of column
     * @return Column object
     */
    public static AbstractColumn createColumnDate( String property, String title, int width )
    {
        return ColumnBuilder.getNew()
                            .setColumnProperty( property, Date.class.getName() )
                            .setTitle( title )
                            .setWidth( width )
                            .build();
    }
    
    /**
     * Opens a generated report file
     *
     * @param outputFile The file to be opened
     */
    private static void OpenFile(File outputFile)
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
            File pdfOutputFile = new File(filePath + reportName + ".pdf");
            fileOutputStream = new FileOutputStream(pdfOutputFile);
            JRPdfExporter pdfExporter = new JRPdfExporter();
            pdfExporter.setExporterInput( new SimpleExporterInput( jasperPrint ) );
            pdfExporter.setExporterOutput( new SimpleOutputStreamExporterOutput( fileOutputStream ) );

            //currently does nothing
            SimplePdfExporterConfiguration configuration = new SimplePdfExporterConfiguration();
            pdfExporter.setConfiguration( configuration );
            //

            pdfExporter.exportReport();
            fileOutputStream.close();
            OpenFile(pdfOutputFile);
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
        exportExcel( jasperPrint, "Report", false );
    }

    /**
     * Creates and opens an Excel file of the generated report
     *
     * @param jasperPrint the report to be exported
     */
    public static void exportExcel( JasperPrint jasperPrint, boolean freezeRows )
    {
        exportExcel( jasperPrint, "Report", freezeRows );
    }

    public static void exportExcel( JasperPrint jasperPrint, String reportName )
    {
        exportExcel( jasperPrint, reportName, false );
    }

    /**
     * Creates and opens an Excel file of a report with a custom file name
     *
     * @param jasperPrint the report to be exported
     * @param reportName file name of exported report
     * @param freezeRow
     */
    public static void exportExcel( JasperPrint jasperPrint, String reportName, boolean freezeRow )
    {
        try
        {
            filePath = "Reports/";
            File excelOutputFile = new File(filePath + reportName + ".xls");
            fileOutputStream = new FileOutputStream(excelOutputFile);
            JRXlsExporter excelExporter = new JRXlsExporter();
            excelExporter.setExporterInput( new SimpleExporterInput( jasperPrint ) );
            excelExporter.setExporterOutput( new SimpleOutputStreamExporterOutput( fileOutputStream ) );

            SimpleXlsReportConfiguration configuration = new SimpleXlsReportConfiguration();
            configuration.setOnePagePerSheet( Boolean.FALSE );
            configuration.setRemoveEmptySpaceBetweenRows( Boolean.TRUE );
            configuration.setRemoveEmptySpaceBetweenColumns(Boolean.FALSE );
            configuration.setWhitePageBackground( Boolean.TRUE );
            configuration.setDetectCellType(true);

            if (freezeRow)
            {
                configuration.setFreezeRow( 3 );
            }

            excelExporter.setConfiguration( configuration );
            excelExporter.exportReport();
            fileOutputStream.close();
            OpenFile(excelOutputFile);
        }
        catch (JRException | IOException ex)
        {
            Logger.getLogger( ReportUtilities.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }
    
    /**
     * Returns a dynamic jasper report, and adds the columns and titles
     *
     * @param columns An array list of columns to be added to the report
     * @param title Title of report
     * @return The built report
     */
    public static DynamicReport createBasicReportSkeleton(ArrayList<AbstractColumn> columns, String title)
    {
        DynamicReportBuilder dynamicReportBuilder = new DynamicReportBuilder();
        columns.forEach((column) ->
                            {
                                dynamicReportBuilder.addColumn( column );
                            } );

        dynamicReportBuilder
                .setLeftMargin(0)
                .setTitle( title )
                .setTitleHeight( 40 )
                .setTitleStyle( BOLD_LEFT )
                .setDefaultStyles( BOLD_LEFT, null, BOLD_LEFT, LEFT)
                .setIgnorePagination( true );

        return dynamicReportBuilder.build();
    }

    /**
     * Returns a dynamic jasper report, and adds the columns 
     *
     * @param columns An array list of columns to be added to the report
     * @return The built report
     */
    public static DynamicReport createBasicReportSkeletonNoTitle(ArrayList<AbstractColumn> columns)
    {
        DynamicReportBuilder dynamicReportBuilder = new DynamicReportBuilder();

        columns.forEach((column) ->
                            {
                                DJGroupVariable test = new DJGroupVariable(column, DJCalculation.SUM);
                                dynamicReportBuilder.addColumn( column );
                            } );

        dynamicReportBuilder
                .setLeftMargin(0)
                .setDefaultStyles( BOLD_LEFT, null, BOLD_LEFT, LEFT)
                .setIgnorePagination( true );

        return dynamicReportBuilder.build();
    }
    
    /**
     * Returns a dynamic report builder with title. Dynamic report builder can have additional
     * options configured before building the final report
     * 
     * @param columns Arraylist of columns to be used in the report
     * @param title Title of the report
     * @return The dynamic report builder, which still must be built before export.
     */
    public static DynamicReportBuilder createBasicReportBuilderSkeleton( ArrayList<AbstractColumn> columns, String title )
    {
        DynamicReportBuilder dynamicReportBuilder = new DynamicReportBuilder();
        ArrayList<AbstractColumn> columnList = columns;
        columnList.forEach( (column) ->
        {
            dynamicReportBuilder.addColumn( column );
        } );

        dynamicReportBuilder
                .setLeftMargin( 0 )
                .setTitle( title )
                .setTitleHeight( 20 )
                .setTitleStyle( BOLD_LEFT )
                .setDefaultStyles( BOLD_LEFT, null, BOLD_LEFT, LEFT )
                .setIgnorePagination( true );

        return dynamicReportBuilder;
    }
}
