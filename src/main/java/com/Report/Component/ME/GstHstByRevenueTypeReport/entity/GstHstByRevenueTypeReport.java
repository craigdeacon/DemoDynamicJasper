/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Report.Component.ME.GstHstByRevenueTypeReport.entity;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DJCalculation;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.builders.DynamicReportBuilder;
import ar.com.fdvs.dj.domain.constants.Page;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import com.Database.BO.GstHstRevenueByTypeBO;
import com.DemoDynamicJasper.spring.config.SpringConfigurationBootstrap;
import static com.Utilities.ReportStyles.*;
import static com.Utilities.ReportUtilities.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author craig.deacon
 */
public class GstHstByRevenueTypeReport
{
    int groupNameWidth = 350;
    int provinceWidth = 50;
    int premiumWidth = 70;
    int taxWidth = 50;
    int totalWidth = 70;
    
    GstHstRevenueByTypeBO gstHstRevenueByTypeBO;
    
    AbstractColumn hsaGst;
    AbstractColumn asoGst;
    AbstractColumn otherGst;
    AbstractColumn totalGst;

    @Autowired
    public GstHstByRevenueTypeReport()
    {
        this.gstHstRevenueByTypeBO = SpringConfigurationBootstrap.getApplicationContext().getBean(GstHstRevenueByTypeBO.class);
    }
    
    
    
    public void displayGstHstRevenueByTypeReport()
    {
        try
        {
            initStyles();
            
            ArrayList<AbstractColumn> columnsList = getGstHstByRevenueTypeColumns();
            
            DynamicReportBuilder dynamicReportBuilder = createBasicReportBuilderSkeleton( columnsList, "BBD" );
            
            DynamicReport dynamicReport = createGstHstRevenueByTypeReport( dynamicReportBuilder );
            
            JRDataSource dataSource = new JRBeanCollectionDataSource( gstHstRevenueByTypeBO.getGstHstByRevenueTypeGroupList());
            
            JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint( dynamicReport, new ClassicLayoutManager(), dataSource );
            
            exportExcel( jasperPrint, "GST HST By Revenue Type", false );
        }
        catch (JRException ex)
        {
            Logger.getLogger( GstHstByRevenueTypeReport.class.getName() ).log( Level.SEVERE, null, ex );
        }
        
    }

    private ArrayList<AbstractColumn> getGstHstByRevenueTypeColumns()
    {
        ArrayList<AbstractColumn> columnsList = new ArrayList<>();
        
        AbstractColumn groupName = createColumn( "groupName", "Group Name", groupNameWidth, "String" );
        groupName.setHeaderStyle( BLUE_LEFT_GREY_BG );
        columnsList.add( groupName );
        
        AbstractColumn province = createColumn( "province", "Prov", provinceWidth, "String" );
        province.setHeaderStyle( BLUE_LEFT_GREY_BG );
        columnsList.add( province );
        
        AbstractColumn hsaPremium = createColumn ("hsaPremium", "Taxable Premium", premiumWidth, "Double", AMOUNT_STYLE );
        hsaPremium.setHeaderStyle( BLUE_RIGHT_GREY_BG );
        columnsList.add( hsaPremium );
        
        hsaGst = createColumn ("hsaGst", "Tax", taxWidth, "Double", AMOUNT_STYLE  );
        hsaGst.setHeaderStyle( BLUE_RIGHT_GREY_BG );
        columnsList.add( hsaGst );
        
        AbstractColumn asoPremium = createColumn ("asoPremium", "Taxable Premium", premiumWidth, "Double", AMOUNT_STYLE );
        asoPremium.setHeaderStyle( BLUE_RIGHT_GREY_BG );
        columnsList.add( asoPremium );
        
        asoGst = createColumn ("asoGst", "Tax", taxWidth, "Double", AMOUNT_STYLE  );
        asoGst.setHeaderStyle( BLUE_RIGHT_GREY_BG );
        columnsList.add( asoGst );
        
        AbstractColumn otherPremium = createColumn ("otherPremium", "Taxable Premium", premiumWidth, "Double", AMOUNT_STYLE );
        otherPremium.setHeaderStyle( BLUE_RIGHT_GREY_BG );
        columnsList.add( otherPremium );
        
        otherGst = createColumn ("otherGst", "Tax", taxWidth, "Double", AMOUNT_STYLE );
        otherGst.setHeaderStyle( BLUE_RIGHT_GREY_BG );
        columnsList.add( otherGst );
        
        totalGst = createColumn( "totalGst", "Total", totalWidth, "Double", AMOUNT_STYLE );
        totalGst.setHeaderStyle( BLUE_RIGHT_GREY_BG );
        columnsList.add( totalGst );
        
        return columnsList;
    }

    private DynamicReport createGstHstRevenueByTypeReport( DynamicReportBuilder dynamicReportBuilder )
    {
        DynamicReport dynamicReport = dynamicReportBuilder
                .setPageSizeAndOrientation( Page.Page_A4_Landscape())
                .setTitleStyle( BLUE_RIGHT_GREY_BG)
                .setColspan( 0, 2, "GST / HST By Revenue Type Report for January", BLUE_LEFT_GREY_BG )
                .setColspan( 2, 2, "HCSA", BLUE_RIGHT_GREY_BG)
                .setColspan( 4, 2, "ASO", BLUE_RIGHT_GREY_BG)
                .setColspan( 6, 3, "Others", BLUE_RIGHT_GREY_BG)
//                .addAutoText( AutoText.PATTERN_DATE_DATE_TIME, AutoText.POSITION_FOOTER, AutoText.ALIGMENT_LEFT, groupNameWidth, taxWidth)
//                .addAutoText( AutoText.AUTOTEXT_PAGE_X_OF_Y, AutoText.POSITION_FOOTER, AutoText.ALIGMENT_RIGHT )
                .addGlobalFooterVariable( hsaGst, DJCalculation.SUM )
                .addGlobalFooterVariable( asoGst, DJCalculation.SUM )
                .addGlobalFooterVariable( otherGst, DJCalculation.SUM )
                .addGlobalFooterVariable( totalGst, DJCalculation.SUM )
                .setGrandTotalLegend( "Totals" )
                .setTitleStyle( BOLD_LEFT )
                .build();
        
        return dynamicReport;
    }
}
