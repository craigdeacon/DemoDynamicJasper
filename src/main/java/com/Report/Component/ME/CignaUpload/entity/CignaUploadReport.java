package com.Report.Component.ME.CignaUpload.entity;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import com.Database.BO.CignaUploadBO;
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

public class CignaUploadReport
{

    private CignaUploadBO cignaUploadReportBO;

    public CignaUploadReport()
    {
        this.cignaUploadReportBO = SpringConfigurationBootstrap.getApplicationContext().getBean(CignaUploadBO.class);
    }

    public void displayCignaUploadReport()
    {
        try
        {
            initStyles();

            DynamicReport dynamicReport = ReportUtilities.createBasicReportSkeletonNoTitle(getCignaUploadColumns());

            JRDataSource dataSource = new JRBeanCollectionDataSource(cignaUploadReportBO.getCignaUploadGroups() );

            JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dynamicReport, new ClassicLayoutManager(), dataSource );

            ReportUtilities.exportExcel( jasperPrint, "Cigna Upload");
        }
        catch (JRException ex)
        {
            Logger.getLogger(CignaUploadReport.class.getName() ).log(Level.SEVERE, "displayCignaUploadReport Failed", ex );
        }
    }

    private ArrayList<AbstractColumn> getCignaUploadColumns()
    {
        ArrayList<AbstractColumn> columnList = new ArrayList<>();

        AbstractColumn month = createColumnInt( "month", "Month", 50 );
        columnList.add( month );

        AbstractColumn year = createColumnInt( "year", "Year", 50 );
        columnList.add( year );

        AbstractColumn type = createColumnString( "type", "Type", 50 );
        columnList.add( type );

        AbstractColumn policyNumber = createColumnString( "policyNumber", "Policy Number", 90 );
        columnList.add( policyNumber );

        AbstractColumn employees = createColumnInt( "numEmployee", "Employees", 90 );
        columnList.add( employees );

        AbstractColumn sumInsured = createColumn( "sumInsured", "Sum Insured", 100, "Double" );
        columnList.add( sumInsured );

        AbstractColumn grossPremiums = createColumn( "grossPremiums", "Gross Premiums", 100, "Double" );
        columnList.add( grossPremiums );

        AbstractColumn pst = createColumn( "pst", "PST", 60, "Double" );
        columnList.add( pst );

        AbstractColumn grossRate = createColumn( "grossRate", "Gross Rate", 80, "Double" );
        columnList.add( grossRate );

        AbstractColumn companyName = createColumnString( "companyName", "Company Name", 80 );
        columnList.add( companyName );

        AbstractColumn commissionsRetained = createColumn( "commissionsRetained", "Commissions Retained", 100, "Double" );
        columnList.add( commissionsRetained );

        AbstractColumn tpaFee = createColumn( "tpaFee", "TPA Fee", 60, "Double" );
        columnList.add( tpaFee );

        AbstractColumn netRemittance = createColumn( "netRemittance", "Net Remittance", 100, "Double" );
        columnList.add( netRemittance );

        return columnList;
    }
}
