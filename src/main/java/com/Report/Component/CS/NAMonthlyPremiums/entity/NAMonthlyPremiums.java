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
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.builders.FastReportBuilder;
import ar.com.fdvs.dj.domain.builders.GroupBuilder;
import ar.com.fdvs.dj.domain.constants.GroupLayout;
import ar.com.fdvs.dj.domain.constants.Page;
import ar.com.fdvs.dj.domain.entities.DJGroup;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import ar.com.fdvs.dj.domain.entities.columns.PropertyColumn;
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
    
    int xSmallColumn = 55;
    int smallColumn = 70;
    int medColumn = 90;
    int largeColumn = 150;

    @Autowired
    public NAMonthlyPremiums()
    {
        this.naMonthlyPremiumsBO = SpringConfigurationBootstrap.getApplicationContext().getBean(NAMonthlyPremiumsBO.class);
    }
    
   
    
    public void displayNAMonthlyPremiumReport()
    {
        try
        {
            initStyles();
            
            ArrayList<AbstractColumn> columnsList = getNAMonthlyPremiumsColumns();
            
            Map parameters = new HashMap();
            
            HashMap<String, HashMap> productProvinceMap = naMonthlyPremiumsBO.getProductProvinceMap();
            
            HashMap<String, ProductTotal> productMap = productProvinceMap.get( "products");
            HashMap<String, ProvinceTotal> provinceTotalMap = productProvinceMap.get("provinces");
            
            ArrayList<ProductTotal> productTotalList = new ArrayList<ProductTotal>(productMap.values());
            ArrayList<ProvinceTotal> provinceTotalList = new ArrayList<ProvinceTotal>(provinceTotalMap.values());
            
            DynamicReportBuilder dynamicReportBuilder = createBasicReportBuilderSkeleton(columnsList, "Non-Affiliations Monthly Premiums Report");
            
            GroupBuilder groupBuilder = new GroupBuilder();
                     
            DJGroup group = groupBuilder.setCriteriaColumn( (PropertyColumn) employer )
//                    .setFooterLabel( new DJGroupLabel("Totals: ", HEADER_STYLE))
//                    .addFooterVariable( volume, expression )
//                    .setFooterVariablesHeight( 50)
                    
                    .addFooterVariable( premium, DJCalculation.SUM, FOOTER_TOTAL )
                    .addFooterVariable( pst, DJCalculation.SUM, FOOTER_TOTAL )
                    .addFooterVariable( retroactivePremium, DJCalculation.SUM, FOOTER_TOTAL )
                    .addFooterVariable( retroactivePst, DJCalculation.SUM, FOOTER_TOTAL )
                    .addFooterVariable( grossPremium, DJCalculation.SUM, FOOTER_TOTAL )
                    .addFooterVariable( administrationAmount, DJCalculation.SUM, FOOTER_TOTAL )
                    .addFooterVariable( commissionAmount, DJCalculation.SUM, FOOTER_TOTAL )
                    .addFooterVariable( netPremium, DJCalculation.SUM, FOOTER_TOTAL )
                   
                    .setGroupLayout( GroupLayout.VALUE_FOR_EACH_WITH_HEADERS )
                    
                    .build();
            
            DynamicReport dynamicReport = dynamicReportBuilder
                    .setPrintColumnNames( false )
                    .addGroup( group )
                    
                    .setDefaultStyles( null, null, null, RIGHT )
                    .addConcatenatedReport( createProductTotalsReport(), "productTypes", DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION )
                    .addConcatenatedReport( createProvinceTotalsReport(), "provinces", DJConstants.DATA_SOURCE_ORIGIN_PARAMETER, DJConstants.DATA_SOURCE_TYPE_COLLECTION )
                    .build();
            
            parameters.put("productTypes", productTotalList );
            parameters.put("provinces", provinceTotalList );
            JRDataSource dataSource = new JRBeanCollectionDataSource(naMonthlyPremiumsBO.getNAMonthlyPremiumsGroup());
            
            JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dynamicReport, new ClassicLayoutManager(), dataSource, parameters );
            
            exportExcel( jasperPrint, "Non-Affiliations Monthly Premiums Report Repeating Headers With Subreport", true);
        }
        catch (JRException ex)
        {
            Logger.getLogger(NAMonthlyPremiums.class.getName() ).log( Level.SEVERE, null, ex );
        }
        
    }

    private ArrayList<AbstractColumn> getNAMonthlyPremiumsColumns()
    {
        ArrayList<AbstractColumn> columnList = new ArrayList<>();
        
        

        AbstractColumn group = createColumnString( "policySeries", "Group", xSmallColumn );
        columnList.add( group );
        
        AbstractColumn acct = createColumnString( "policyAccount", "Acct", xSmallColumn );
        columnList.add( acct );
        
        employer = createColumnString( "employer", "ER Name", 200 );
        columnList.add( employer );
        
        AbstractColumn month = createColumnDate( "month", "Month", medColumn );
        month.setPattern( "d MMM, YYY");
        columnList.add( month );
        
        AbstractColumn product = createColumnString( "product", "Product", xSmallColumn );
        columnList.add( product );
        
        AbstractColumn rate = createColumnFloat("premiumRate", "Rate", xSmallColumn );
        columnList.add( rate );
        
        AbstractColumn province = createColumnString( "province", "Prov", xSmallColumn );
        columnList.add( province );
        
        AbstractColumn lives = createColumnInt( "employeeCount", "Lives", xSmallColumn );
        columnList.add( lives );
        
        volume = createColumnInt( "volume", "Volume", smallColumn );
        columnList.add( volume );
        
        premium = createColumnFloat( "premium", "Current Premium", smallColumn, AMOUNT_STYLE );
        premium.setPattern( "$0.00");
        columnList.add( premium );
        
        pst = createColumnFloat( "pst", "Current Tax", xSmallColumn, AMOUNT_STYLE );
        pst.setPattern( "$0.00");
        columnList.add( pst );
        
        retroactivePremium = createColumnFloat( "retroactivePremium", "Adjustment Premium", smallColumn, AMOUNT_STYLE );
        retroactivePremium.setPattern( "$0.00");
        columnList.add( retroactivePremium );
        
        retroactivePst = createColumnFloat( "retroactivePst", "Adjustment Tax", smallColumn, AMOUNT_STYLE );
        retroactivePst.setPattern( "$0.00");
        columnList.add( retroactivePst );
        
        grossPremium = createColumnFloat( "grossPremium", "Gross Premium", smallColumn, AMOUNT_STYLE );
        grossPremium.setPattern( "$0.00");
        columnList.add( grossPremium );
        
        administrationAmount = createColumnFloat( "administrationAmount", "Admin Fee", smallColumn, AMOUNT_STYLE  );
        administrationAmount.setPattern( "$0.00");
        columnList.add( administrationAmount );
        
        commissionAmount = createColumnFloat( "commissionAmount", "Commission", smallColumn, AMOUNT_STYLE  );
        commissionAmount.setPattern( "$0.00");
        columnList.add( commissionAmount );
        
        netPremium = createColumnFloat( "netPremium", "Total Net Premium", smallColumn, AMOUNT_STYLE  );
        netPremium.setPattern( "$0.00");
        columnList.add( netPremium );
        
        AbstractColumn gst = createColumnFloat( "gst", "GST", xSmallColumn, RIGHT );
        gst.setBlankWhenNull( true );
        columnList.add( gst );
        
        AbstractColumn administrationRate = createColumnFloat( "administrationRate", "Admin Rate", smallColumn, RIGHT );
        administrationRate.setPattern( "0.00");
        columnList.add( administrationRate );
        
        AbstractColumn commissionRate = createColumnFloat( "commissionRate", "Commission Rate", medColumn, RIGHT );
        commissionRate.setPattern( "0.00");
        columnList.add( commissionRate );
        
        return columnList;
    }
    
    private JasperReport createProductTotalsReport() throws JRException
    {
        FastReportBuilder fastReport = new FastReportBuilder();
        
        
        
        AbstractColumn productName = createColumnString ("productName", "", smallColumn );
        
        AbstractColumn premiumTotal = createColumnFloat( "premiumTotal", "", smallColumn );
          
        AbstractColumn pstTotal = createColumnFloat( "pstTotal", "", xSmallColumn );
        AbstractColumn retroactivePremiumTotal= createColumnFloat( "retroactivePremiumTotal", "", smallColumn );
        AbstractColumn retroactivePstTotal = createColumnFloat( "retroactivePstTotal", "", smallColumn );
        AbstractColumn grossPremiumTotal = createColumnFloat( "grossPremiumTotal", "", smallColumn ); 
        AbstractColumn administrationAmountTotal = createColumnFloat( "administrationAmountTotal", "", smallColumn );
        AbstractColumn commissionAmountTotal = createColumnFloat( "commissionAmountTotal", "", smallColumn );
        AbstractColumn netPremiumTotal = createColumnFloat( "netPremiumTotal", "", smallColumn ); 
        AbstractColumn gstTotal = createColumnFloat( "gstTotal", "", xSmallColumn );
        
        DynamicReport dynamicReport = fastReport
                .setDefaultStyles( null, null, BOLD_LEFT, AMOUNT_STYLE )
                .setPageSizeAndOrientation( Page.Page_A4_Landscape())
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
                .setLeftMargin( 590 )
                .build();
       
//       JasperViewer.viewReport( jasperPrint );
       
       return DynamicJasperHelper.generateJasperReport( dynamicReport, new ClassicLayoutManager(), null ); 
    }
    
     private JasperReport createProvinceTotalsReport() throws JRException
    {
        FastReportBuilder fastReport = new FastReportBuilder();
        
        
        
        AbstractColumn province = createColumnString ("province", "", smallColumn );
        
                 
        AbstractColumn pstTotal = createColumnFloat( "pstTotal", "", xSmallColumn );
       
        AbstractColumn retroactivePstTotal = createColumnFloat( "retroactivePstTotal", "", smallColumn );
               
        DynamicReport dynamicReport = fastReport
                .setDefaultStyles( null, null, BOLD_LEFT, AMOUNT_STYLE )
                .setPageSizeAndOrientation( Page.Page_A4_Landscape())
                .addColumn( province )
                .addColumn( pstTotal )
               
                .addColumn( retroactivePstTotal )
               
                .setLeftMargin( 590 )
                .build();
       
//       JasperViewer.viewReport( jasperPrint );
       
       return DynamicJasperHelper.generateJasperReport( dynamicReport, new ClassicLayoutManager(), null ); 
    }
}
