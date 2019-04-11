package com.Report.Component.ME.IAPPremiumReport.entity;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import com.Database.BO.IAPPremiumReportBO;
import com.DemoDynamicJasper.spring.config.SpringConfigurationBootstrap;
import com.Utilities.ReportUtilities;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.Utilities.ReportStyles.initStyles;
import static com.Utilities.ReportUtilities.*;

public class IAPPremiumDSAIReport
{
    private IAPPremiumReportBO iapPremiumReportBO;

    public IAPPremiumDSAIReport()
    {
        this.iapPremiumReportBO = SpringConfigurationBootstrap.getApplicationContext().getBean(IAPPremiumReportBO.class);
    }

    public void displayIAPPremiumDSAIReport()
    {
        try
        {
            initStyles();

            DynamicReport dynamicReport = ReportUtilities.createBasicReportSkeletonNoTitle(getIAPPremiumDSAIColumns());

            JRDataSource dataSource = new JRBeanCollectionDataSource(iapPremiumReportBO.getIapPremiumDSAIGroups() );

            JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dynamicReport, new ClassicLayoutManager(), dataSource );

            ReportUtilities.exportExcel( jasperPrint,"IAP DSAI Premium Report", false);
        }
        catch (JRException ex)
        {
            Logger.getLogger(IAPPremiumDSAIReport.class.getName() ).log(Level.SEVERE, "displayIAPPremiumDSAIReport Failed", ex );
        }
    }

    private ArrayList<AbstractColumn> getIAPPremiumDSAIColumns()
    {
        ArrayList<AbstractColumn> columnList = new ArrayList<>();

        AbstractColumn divisionName = createColumnString( "divisionName", "Division Name", 200 );
        columnList.add( divisionName );

        AbstractColumn policyNumber = createColumnString( "policyNumber", "Policy Number", 80 );
        columnList.add( policyNumber );

        AbstractColumn divisionNumber = createColumnInt( "divisionNumber", "Division Number", 80 );
        columnList.add( divisionNumber );

        AbstractColumn singleLives = createColumnInt( "singleLives", "Singles", 50 );
        columnList.add( singleLives );

        AbstractColumn coupleLives = createColumnInt( "coupleLives", "Couples", 50 );
        columnList.add( coupleLives );

        AbstractColumn currentPremium = createColumn( "currentPremium", "Current Premium", 100, "Double" );
        columnList.add( currentPremium );

        AbstractColumn retroPremium = createColumn( "retroPremium", "Retro Premium", 100, "Double" );
        columnList.add( retroPremium );

        AbstractColumn pst = createColumn( "pst", "PST", 50, "Double" );
        columnList.add( pst );


        return columnList;
    }
}
