package com.Report.Component.CS.NAMonthlyPremiums.entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import ar.com.fdvs.dj.core.DJConstants;
import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.Style;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.builders.GroupBuilder;
import ar.com.fdvs.dj.domain.constants.GroupLayout;
import ar.com.fdvs.dj.domain.constants.HorizontalAlign;
import ar.com.fdvs.dj.domain.constants.Page;
import ar.com.fdvs.dj.domain.entities.DJGroup;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import ar.com.fdvs.dj.domain.entities.columns.PropertyColumn;
import ar.com.fdvs.dj.domain.entities.conditionalStyle.ConditionalStyle;
import com.Database.BO.NAMonthlyPremiumsBO;
import com.DemoDynamicJasper.spring.config.SpringConfigurationBootstrap;
import com.Report.Component.CS.NAMonthlyPremiums.container.ProductTotal;
import com.Report.Component.CS.NAMonthlyPremiums.container.ProvinceTotal;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import static com.Utilities.ReportStyles.*;
import static com.Utilities.ReportUtilities.*;
import com.Utilities.StatusCondition;
import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;
import net.sf.jasperreports.engine.JasperReport;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author craig.deacon
 */
public class NAMonthlyPremiums
{
    //columns created in a function that need to be used in grouping or totals
    AbstractColumn employer;
    AbstractColumn premium;
    AbstractColumn pst;
    AbstractColumn retroactivePremium;
    AbstractColumn retroactivePst;
    AbstractColumn grossPremium;
    AbstractColumn administrationAmount;
    AbstractColumn commissionAmount;
    AbstractColumn netPremium;
    AbstractColumn volume;
    
    private NAMonthlyPremiumsBO naMonthlyPremiumsBO;
    
    //column widths
    int groupWidth = 40;
    int acctWidth = 35;
    int employerWidth = 200;
    int monthWidth = 70;
    int productWidth = 70;
    int rateWidth= 35;
    int livesWidth = 55;
    int volumeWidth = 50;
    int currentPremiumWidth = 65;
    int currentTaxWidth = 55;
    int adjustmentPremiumWidth = 60;
    int adjustmentTaxWidth = 60;
    int grossPremiumWidth = 65;
    int adminFeeWidth = 65;
    int commissionWidth = 70;
    int totalNetPremiumWidth = 65;
    int subreportMargin = groupWidth + acctWidth + employerWidth + monthWidth + productWidth;

    @Autowired
    public NAMonthlyPremiums()
    {
        this.naMonthlyPremiumsBO = SpringConfigurationBootstrap.getApplicationContext().getBean(NAMonthlyPremiumsBO.class);
    }
    
   
    /**
     *  Creates and exports to excel file the Non-Affiliations Monthly Premiums Report
     */
    public void displayNAMonthlyPremiumReport() 
    {
        try
        {
            //initializes styles to be used in report
            initStyles();
            
            //parameters used in Jasperprint creation
            Map parameters = new HashMap();
            
            //list of columns for main report
            ArrayList<AbstractColumn> columnsList = getNAMonthlyPremiumsColumns();
            
            //hashmap containing the data sources for the Product Total and Provincial total subreports
            HashMap<String, HashMap> productProvinceMap = naMonthlyPremiumsBO.getProductProvinceMap();
       
            //separating Product and Province data sources
            ArrayList<ProductTotal> productTotalList = new ArrayList<>(productProvinceMap.get("products").values());
            ArrayList<ProvinceTotal> provinceTotalList = new ArrayList<>(productProvinceMap.get("provinces").values());
            
            //adds columns and title into a basic report builder
            DynamicReportBuilder dynamicReportBuilder = createBasicReportBuilderSkeleton(columnsList, "Non-Affiliations Monthly Premiums Report");
                     
            //creating group which main report body is sorted by
            DJGroup group = createNAPremiumsGroup();
 
            DynamicReport dynamicReport = createNAMonthlyPremiumsDynamicReport( group, dynamicReportBuilder) ;
                    
            //add data sources for subreports as map parameters
            parameters.put("productTypes", productTotalList );
            parameters.put("provinces", provinceTotalList );
            
            //fetch data source for main body report
            JRDataSource dataSource = new JRBeanCollectionDataSource(naMonthlyPremiumsBO.getNAMonthlyPremiumsGroup());
            
            //merges report with data sources
            JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dynamicReport, new ClassicLayoutManager(), dataSource, parameters );
            
            //export report to Excel
            exportExcel( jasperPrint, "Non-Affiliations Monthly Premiums Report", true);
        }
        catch (JRException | IllegalAccessException | InstantiationException | NoSuchMethodException | InvocationTargetException ex)
        {
            Logger.getLogger(NAMonthlyPremiums.class.getName() ).log( Level.SEVERE, null, ex );
        }
        
    }

    /**
     * Creates and returns an ArrayList of all columns in the main body of the NA
     * Monthly Premiums report
     * 
     * @return ArrayList of columns to be added to main body report
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws NoSuchMethodException
     * @throws InvocationTargetException 
     */
    private ArrayList<AbstractColumn> getNAMonthlyPremiumsColumns() throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException
    {
        ArrayList<AbstractColumn> columnList = new ArrayList<>();
        ArrayList conditionalStyles = createConditionalStyles();
        
        AbstractColumn group = createColumnString( "policySeries", "Group", groupWidth, LEFT );
        group.setHeaderStyle( HEADER_STYLE_JUSTIFIED );
        columnList.add( group );
        
        AbstractColumn acct = createColumnString( "policyAccount", "Acct", acctWidth, LEFT );
        acct.setHeaderStyle( HEADER_STYLE_JUSTIFIED );
        columnList.add( acct );
        
        employer = createColumnString( "employer", "ER Name", employerWidth, LEFT );
        employer.setHeaderStyle( HEADER_STYLE_JUSTIFIED );
        columnList.add( employer );
        
        AbstractColumn month = createColumnDate( "month", "Month", monthWidth, LEFT );
        month.setPattern( "mmm YYY");
        columnList.add( month );
        
        AbstractColumn product = createColumnString( "product", "Product", productWidth, LEFT );
        columnList.add( product );
        
        AbstractColumn rate = createColumn("premiumRate", "Rate", rateWidth, "Double", LEFT );
        rate.setPattern( "0.000");
        columnList.add( rate );
       
        AbstractColumn lives = createColumnInt( "employeeCount", "Lives", livesWidth, LEFT );
        columnList.add( lives );
        
        volume = createColumnInt( "volume", "Volume", volumeWidth, LEFT );
        columnList.add( volume );
        
        premium = createColumn("premium", "Current Premium", currentPremiumWidth, "Double", AMOUNT_STYLE );  
        columnList.add( premium );
        
        pst = createColumn("pst", "Current Tax", currentTaxWidth, "Double", AMOUNT_STYLE );
        columnList.add( pst );
        
        retroactivePremium = createColumn( "retroactivePremium", "Adjustment Premium", adjustmentPremiumWidth, "Double", AMOUNT_STYLE );
        columnList.add( retroactivePremium );
        
        retroactivePst = createColumn( "retroactivePst", "Adjustment Tax", adjustmentTaxWidth, "Double", AMOUNT_STYLE );
        columnList.add( retroactivePst );
        
        grossPremium = createColumn( "grossPremium", "Gross Premium", grossPremiumWidth, "Double", AMOUNT_STYLE );
        columnList.add( grossPremium );
        
        administrationAmount = createColumn( "administrationAmount", "Admin Fee", adminFeeWidth, "Double", AMOUNT_STYLE  );
        columnList.add( administrationAmount );
        
        commissionAmount = createColumn( "commissionAmount", "Commission", commissionWidth, "Double", AMOUNT_STYLE );
        columnList.add( commissionAmount );
        
        netPremium = createColumn( "netPremium", "Total Net Premium", totalNetPremiumWidth, "Double", AMOUNT_STYLE );
        columnList.add( netPremium );
        
        AbstractColumn gst = createColumn( "gst", "GST", 40, "Double", RIGHT );
        gst.setBlankWhenNull( true );
        gst.setPattern( "#");
        columnList.add( gst );
        
        AbstractColumn administrationRate = createColumn( "administrationRate", "Admin Rate", 40, "Double", RIGHT );
        administrationRate.setPattern( "0.00");
        columnList.add( administrationRate );
        
        AbstractColumn commissionRate = createColumn( "commissionRate", "Commission Rate", 70, "Double", RIGHT );
        commissionRate.setPattern( "0.00");
        columnList.add( commissionRate );
        
        return columnList;
    }
    
    /**
     * Creates a subreport that totals the values for multiple columns in the 
     * main report body and is appended to the main report
     * 
     * @return the subreport to be concatenated to main report
     * @throws JRException 
     */
    private JasperReport createProductTotalsReport() throws JRException
    {
        
        FastReportBuilder fastReport = new FastReportBuilder();
        
        AbstractColumn header = createColumnString("header", "", rateWidth + livesWidth, BOLD_LEFT);
        AbstractColumn productName = createColumnString ("productName", "", volumeWidth, LEFT );
        AbstractColumn premiumTotal = createColumn( "premiumTotal", "", currentPremiumWidth, "Double" );          
        AbstractColumn pstTotal = createColumn( "pstTotal", "", currentTaxWidth, "Double" );
        AbstractColumn retroactivePremiumTotal= createColumn( "retroactivePremiumTotal", "", adjustmentPremiumWidth, "Double" );
        AbstractColumn retroactivePstTotal = createColumn( "retroactivePstTotal", "", adjustmentTaxWidth, "Double" );
        AbstractColumn grossPremiumTotal = createColumn( "grossPremiumTotal", "", grossPremiumWidth, "Double" ); 
        AbstractColumn administrationAmountTotal = createColumn( "administrationAmountTotal", "", adminFeeWidth, "Double" );
        AbstractColumn commissionAmountTotal = createColumn( "commissionAmountTotal", "", commissionWidth, "Double" );
        AbstractColumn netPremiumTotal = createColumn( "netPremiumTotal", "", totalNetPremiumWidth, "Double" ); 
        AbstractColumn gstTotal = createColumn( "gstTotal", "", 40, "Double" );
        
        DynamicReport dynamicReport = fastReport
                .setDefaultStyles( null, null, BOLD_LEFT, AMOUNT_STYLE )
                .setPageSizeAndOrientation( Page.Page_A4_Landscape())
                .addColumn( header )
                .addColumn( productName )
                .addColumn( premiumTotal )
                .addColumn( pstTotal )
                .addColumn( retroactivePremiumTotal )
                .addColumn( retroactivePstTotal )
                .addColumn( grossPremiumTotal )
                .addColumn( administrationAmountTotal )
                .addColumn( commissionAmountTotal )
                .addColumn( netPremiumTotal )
                .addColumn( gstTotal )
                .addGlobalFooterVariable( premiumTotal, DJCalculation.SUM )
                .addGlobalFooterVariable( pstTotal, DJCalculation.SUM )
                .addGlobalFooterVariable( retroactivePremiumTotal, DJCalculation.SUM )
                .addGlobalFooterVariable( retroactivePstTotal, DJCalculation.SUM )
                .addGlobalFooterVariable( grossPremiumTotal, DJCalculation.SUM )
                .addGlobalFooterVariable( administrationAmountTotal, DJCalculation.SUM )
                .addGlobalFooterVariable( commissionAmountTotal, DJCalculation.SUM )
                .addGlobalFooterVariable( netPremiumTotal, DJCalculation.SUM )
                .setGrandTotalLegend( "Totals: ")
                .setGrandTotalLegendStyle( BOLD_LEFT )
                .setLeftMargin(subreportMargin )
                .build();
       
       return DynamicJasperHelper.generateJasperReport( dynamicReport, new ClassicLayoutManager(), null ); 
    }
    
    
    /**
     * Creates a subreport that totals the tax from the main report grouped
     * by province
     * 
     * @return Completed subreport to be concatenated to main report
     * @throws JRException 
     */
     private JasperReport createProvinceTotalsReport() throws JRException
    {
        FastReportBuilder fastReport = new FastReportBuilder();
        
        AbstractColumn header = createColumnString("header", "", rateWidth + livesWidth, BOLD_LEFT);
        AbstractColumn province = createColumnString ("province", "", volumeWidth, LEFT );
        AbstractColumn pstTotal = createColumn( "pstTotal", "", currentPremiumWidth + currentTaxWidth, "Double" );
        AbstractColumn retroactivePstTotal = createColumn( "retroactivePstTotal", "", adjustmentPremiumWidth + adjustmentTaxWidth, "Double" );
               
        DynamicReport dynamicReport = fastReport
                .setDefaultStyles( null, null, BOLD_LEFT, AMOUNT_STYLE )
                .setPageSizeAndOrientation( Page.Page_A4_Landscape() )
                .addColumn( header )
                .addColumn( province )
                .addColumn( pstTotal )
                .addColumn( retroactivePstTotal )
                .addGlobalFooterVariable( pstTotal, DJCalculation.SUM )
                .addGlobalFooterVariable( retroactivePstTotal, DJCalculation.SUM )
                .setGrandTotalLegend( "Totals: ")
                .setGrandTotalLegendStyle( BOLD_LEFT )
                .setLeftMargin( subreportMargin )
                .build();
       
       return DynamicJasperHelper.generateJasperReport( dynamicReport, new ClassicLayoutManager(), null ); 
    }
     
     
    /**
     * This function is not currently necessary but being preserved as an example
     * of how to do conditional styles
     * 
     * Creates an arrayList of conditional styles to be applied to a column
     * Conditional styles evaluate the value of each cell in column at runtime, and applies
     * a different style based on result
     * 
     * @return The arrayList of conditionalStyles to be applied
     * @throws IllegalAccessException
     * @throws InstantiationException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException 
     */
    private ArrayList<ConditionalStyle> createConditionalStyles( ) throws
            IllegalAccessException,
            InstantiationException,
            InvocationTargetException,
            NoSuchMethodException
    { 
        //creating the conditional styles
        Style positive = new Style();
        positive.setHorizontalAlign( HorizontalAlign.RIGHT );
        positive.setPattern( "$0.00; ($0.00)" );
        
        Style negative = new Style();
        negative.setHorizontalAlign( HorizontalAlign.RIGHT );
        negative.setPattern( "(($0.00); ($0.00)" );
        
        //creating the conditions by which the column values are evaluated
        StatusCondition statusPositive = new StatusCondition( new Float(0), new Float (100000000));
        StatusCondition statusNegative = new StatusCondition( new Float (-100000000), new Float(0) );
        
        //combines the styles and the conditions
        ConditionalStyle positiveCondition = new ConditionalStyle( statusPositive, positive );
        ConditionalStyle negativeCondition = new ConditionalStyle( statusNegative, negative );

        ArrayList<ConditionalStyle> conditionalStyles = new ArrayList();
        conditionalStyles.add( positiveCondition );
        conditionalStyles.add( negativeCondition );
        return conditionalStyles;
    }

    /**
     * Creates the sorting group used in main body report
     * 
     * @return Dynamic Jasper group
     */
    private DJGroup createNAPremiumsGroup()
    {
          GroupBuilder groupBuilder = new GroupBuilder();
          
          return groupBuilder
                .setCriteriaColumn( (PropertyColumn) employer )
                .addFooterVariable( premium, DJCalculation.SUM, FOOTER_TOTAL )
                .addFooterVariable( pst, DJCalculation.SUM, FOOTER_TOTAL )
                .addFooterVariable( retroactivePremium, DJCalculation.SUM, FOOTER_TOTAL )
                .addFooterVariable( retroactivePst, DJCalculation.SUM, FOOTER_TOTAL )
                .addFooterVariable( grossPremium, DJCalculation.SUM, FOOTER_TOTAL_CYAN )
                .addFooterVariable( administrationAmount, DJCalculation.SUM, FOOTER_TOTAL )
                .addFooterVariable( commissionAmount, DJCalculation.SUM, FOOTER_TOTAL )
                .addFooterVariable( netPremium, DJCalculation.SUM, FOOTER_TOTAL )
                .setGroupLayout( GroupLayout.VALUE_FOR_EACH )
                .build();

    }

    /**
     * Creates the dynamic report for the main body of the NA Monthly Premium report
     * 
     * @param group The group used to sort report by Employer
     * @param dynamicReportBuilder the report builder with column information to create report
     * @return The built and completed report
     */
    private DynamicReport createNAMonthlyPremiumsDynamicReport( DJGroup group, DynamicReportBuilder dynamicReportBuilder )
    {
        DynamicReport dynamicReport = new DynamicReport();
        try
        {
            dynamicReport = dynamicReportBuilder
                    .addGroup( group )
                    .setDefaultStyles( null, null, HEADER_STYLE_JUSTIFIED, RIGHT )
                    .setGrandTotalLegend( "Totals: " )
                    .setGrandTotalLegendStyle( BOLD_LEFT )
                    .addConcatenatedReport( createProductTotalsReport(), "productTypes", DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION )
                    .addConcatenatedReport( createProvinceTotalsReport(), "provinces", DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION )
                    .build();
        }
        catch (JRException ex)
        {
            Logger.getLogger( NAMonthlyPremiums.class.getName() ).log( Level.SEVERE, null, ex );
        }
        return dynamicReport;
    }
}
