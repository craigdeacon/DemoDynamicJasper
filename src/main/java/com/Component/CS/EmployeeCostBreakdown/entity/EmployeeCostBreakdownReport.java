/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Component.CS.EmployeeCostBreakdown.entity;




import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.AutoText;
import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.DJCrosstab;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.ImageBanner;
import ar.com.fdvs.dj.domain.builders.ColumnBuilderException;
import ar.com.fdvs.dj.domain.builders.CrosstabBuilder;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.constants.Border;
import ar.com.fdvs.dj.domain.constants.Page;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import com.Component.CS.EmployeeCostBreakdown.repository.EmployeeReportRepository;
import static com.utilities.ReportStyles.*;
import static com.utilities.ReportStyles.initStyles;
import com.utilities.ReportUtilities;
import static com.utilities.ReportUtilities.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRColorUtil;
import net.sf.jasperreports.view.JasperViewer;

/**
 * First basic report
 * 
 * @author craig.deacon
 */
public class EmployeeCostBreakdownReport
{

    public EmployeeCostBreakdownReport()
    {
    }

    
    
    private static final Logger LOGGER = Logger.getLogger( EmployeeCostBreakdownReport.class.getName() );

    public void displayEmployeeReport()
    {

        try
        {
            Map parameters = new HashMap();
           
            initStyles();
            
            //logo
            int logoHeight = 50;
            int logoWidth = 100;
            String logoPath = "src/main/resources/images/logo.jpg";
            
            //columns
            int floatWidth = 20;
           
            AbstractColumn columnName = CreateColumnString("name", "Employee Name", 40, LEFT);
            columnName.setHeaderStyle( LEFT );
            AbstractColumn columnLife = CreateColumnFloat( "life", "Life", floatWidth, AMOUNT_STYLE);
            AbstractColumn columnADD = CreateColumnFloat( "add", "AD&D", floatWidth, AMOUNT_STYLE );
            AbstractColumn columnDLife = CreateColumnFloat( "dLife", "DLife", floatWidth, AMOUNT_STYLE );
            AbstractColumn columnSTD = CreateColumnFloat( "std", "STD", floatWidth, AMOUNT_STYLE );
            AbstractColumn columnLTD = CreateColumnFloat( "ltd", "LTD", floatWidth, AMOUNT_STYLE );
            AbstractColumn columnCI = CreateColumnFloat( "ci", "CI", floatWidth, AMOUNT_STYLE );
            AbstractColumn columnDepCI = CreateColumnFloat( "depCi", "Dep CI", floatWidth, AMOUNT_STYLE );
            AbstractColumn columnEHB = CreateColumnFloat( "ehb", "EHB", floatWidth, AMOUNT_STYLE );
            AbstractColumn columnDental = CreateColumnFloat( "dental", "Dental", floatWidth, AMOUNT_STYLE );
            AbstractColumn columnHCSA = CreateColumnFloat( "hcsa", "HCSA", floatWidth, AMOUNT_STYLE );
            AbstractColumn columnPSA = CreateColumnFloat( "psa", "PSA", floatWidth, AMOUNT_STYLE );
            AbstractColumn columnEAP = CreateColumnFloat( "eap", "EAP", floatWidth, AMOUNT_STYLE );
            AbstractColumn columnDSAI = CreateColumnFloat( "dsai", "DSAI", floatWidth, AMOUNT_STYLE );
            AbstractColumn columnTotal = CreateColumnFloat( "total", "Total", floatWidth, AMOUNT_STYLE );
            
            
            
            //Dynamic Report Builder, sets parameters, margins, page sdize, etc.
            DynamicReportBuilder dynamicReportBuilder = new DynamicReportBuilder();
            dynamicReportBuilder.setTemplateFile("Templates/online_template.jrxml", true, true, true, false);
            dynamicReportBuilder.addFirstPageImageBanner( logoPath, logoWidth, logoHeight, ImageBanner.Alignment.Right );
          
            Page page = Page.Page_Letter_Landscape();
            
            JRDataSource dataSource = new JRBeanCollectionDataSource( EmployeeReportRepository.getEmployeeList() );
         
//            DJCrosstab djcross = createCrossTab();
//
//            dynamicReportBuilder.addHeaderCrosstab( djcross );
//            parameters.put("source", SortUtils.sortCollection(EmployeeReportRepository.getEmployeeList(),djcross));
//
            String groupName = EmployeeReportRepository.getGroupName();
            String pattern = "MMMMM dd, yyyy HH:mm:ss ";
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
            String date = simpleDateFormat.format(new Date());
//
            dynamicReportBuilder
                    .setPageSizeAndOrientation( page )
                    .setDefaultStyles( LEFT, SUBTITLE_STYLE, AMOUNT_STYLE, AMOUNT_STYLE )
                    .addStyle( SUBTITLE_STYLE_PARENT )
                    .setDetailHeight( 15 )
                    .setReportName( "Employee Cost Breakdown" )
                    .setTitle( "Employee Cost Breakdown" )
                    .setSubtitle( groupName )
                    .setUseFullPageWidth( Boolean.TRUE )
                    .setTitleStyle( LEFT )
                    .setPrintBackgroundOnOddRows( Boolean.TRUE )
                    .addAutoText( "Date Printed " + date, AutoText.POSITION_HEADER, AutoText.ALIGMENT_LEFT, 400 );

            //add columns to report
            dynamicReportBuilder
                    .addColumn( columnName )
                    .addColumn( columnLife )
                    .addColumn( columnADD )
                    .addColumn( columnDLife ) 
                    .addColumn( columnSTD ) 
                    .addColumn( columnLTD )
                    .addColumn( columnCI ) 
                    .addColumn( columnDepCI )
                    .addColumn( columnEHB )
                    .addColumn( columnDental )
                    .addColumn( columnHCSA )
                    .addColumn( columnPSA )
                    .addColumn( columnEAP )
                    .addColumn( columnDSAI )
                    .addColumn( columnTotal )
                    .setGrandTotalLegend( "Group Total" )
                    .addGlobalFooterVariable( columnTotal, DJCalculation.SUM )
                    ;
            
                  

            //builds the report
            DynamicReport dynamicReport = dynamicReportBuilder.build();
//            dynamicReport.setProperty( "net.sf.jasperreports.style.forecolor", "#8B0000" );
            

            //JasperPrint object is given the report object, the data source and parameters HashMap
            JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dynamicReport, new ClassicLayoutManager(), dataSource, parameters );
            
            //for viewing electronic version
            JasperViewer.viewReport( jasperPrint );
           
            //Export to pdf
//            ReportUtilities.exportPdf(jasperPrint, "EmployeeCostBreakdownReport");

            //for exporting to Xls
//            ReportUtilities.exportExcel(jasperPrint, "EmployeeCostBreakdownReport");
            
        }
        catch (ColumnBuilderException | JRException ex)
        {
            LOGGER.log( Level.SEVERE, "Employee Cost Breakdown Report Failed", ex );
        }

    }

    
    /**
     * Creates a crosstab for use in EmployeeCostBreakdownReport
     * The data and organization is meaningless, just using as proof of concept
     * 
     * @return the crosstab object
     */
    private DJCrosstab createCrossTab()
    {
        DJCrosstab djct = new CrosstabBuilder()
                    .setHeight(200)
                    .setWidth(500)
                         .setDatasource("source",DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION)
                    .setUseFullWidth(true)
                    .setColorScheme(DJConstants.COLOR_SCHEMA_LIGHT_GREEN)
                    .setAutomaticTitle(true)
                    .setCellBorder(Border.THIN())
                    
                    .addColumn("Life","life",Float.class.getName(),false)
//                    .addColumn("EAP","eap",Float.class.getName(),false)
                    .addRow("Name", "name", String.class.getName(),true, "Total")
                    
                    .addMeasure("total", Float.class.getName(), DJCalculation.NOTHING , "Total", LEFT)          
                    
                    .setCellDimension(17, 60)
                    .setColumnHeaderHeight(30)
                    .setRowHeaderWidth(80)
                    .build();
        return djct;
    }

    

}


/*
Inline method for creating and adding columns in one line

 DynamicReport dynamicReport = dynamicReportBuilder
                    
                    .addColumn( "Employee Name", "name", String.class.getName(), 40, left )
                    .addColumn( "Life", "life", Float.class.getName(), 20, AMOUNT_STYLE )
                    .addColumn( "AD&D", "add", Float.class.getName(), 20, AMOUNT_STYLE )
                    .addColumn( "Dlife", "dLife", Float.class.getName(), 20, AMOUNT_STYLE )
                    .addColumn( "STD", "std", Float.class.getName(), 20, AMOUNT_STYLE )
                    .addColumn( "LTD", "ltd", Float.class.getName(), 20, AMOUNT_STYLE )
                    .addColumn( "CI", "ci", Float.class.getName(), 20, AMOUNT_STYLE )
                    .addColumn( "Dep CI", "depCi", Float.class.getName(), 20, AMOUNT_STYLE )
                    .addColumn( "EHB", "ehb", Float.class.getName(), 20, AMOUNT_STYLE )
                    .addColumn( "Dental", "dental", Float.class.getName(), 20, AMOUNT_STYLE )
                    .addColumn( "HCSA", "hcsa", Float.class.getName(), 20, AMOUNT_STYLE )
                    .addColumn( "PSA", "psa", Float.class.getName(), 20, AMOUNT_STYLE )
                    .addColumn( "EAP", "eap", Float.class.getName(), 20, AMOUNT_STYLE )
                    .addColumn( "DSAI", "dsai", Float.class.getName(), 20, AMOUNT_STYLE )
                    .addColumn ("Total", "total", Float.class.getName(), 20, AMOUNT_STYLE)
                    .setPageSizeAndOrientation( page )
                    .setReportName("Employee Report")
                    .setTitle( "Employee report" )
                    .setSubtitle( "Date Printed " + date)
                    .setUseFullPageWidth( Boolean.TRUE )
                    
                    .setTitleStyle( left)
                    .build();
*/