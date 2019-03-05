package com.Component.ME.CignaUpload.entity;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import com.BO.CignaUploadBO;
import com.utilities.ReportUtilities;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.utilities.ReportStyles.initStyles;
import static com.utilities.ReportUtilities.*;

public class CignaUploadReport
{
    public void displayCignaUploadReport()
    {
        try
        {
            initStyles();

            CignaUploadBO cignaUploadReportBO = new CignaUploadBO();

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

        AbstractColumn month = createColumnInt( "month", "Month", 90 );
        columnList.add( month );

        AbstractColumn year = createColumnInt( "year", "Year", 120 );
        columnList.add( year );

        AbstractColumn type = createColumnString( "type", "Type", 90 );
        columnList.add( type );

        AbstractColumn policyNumber = createColumnString( "policyNumber", "Policy Number", 90 );
        columnList.add( policyNumber );

        AbstractColumn employees = createColumnInt( "numEmployee", "Employees", 270 );
        columnList.add( employees );

        AbstractColumn sumInsured = createColumnFloat( "sumInsured", "Sum Insured", 120);
        columnList.add( sumInsured );

        AbstractColumn grossPremiums = createColumnFloat( "grossPremiums", "Gross Premiums", 120 );
        columnList.add( grossPremiums );

        AbstractColumn pst = createColumnFloat( "pst", "PST", 60 );
        columnList.add( pst );

        AbstractColumn grossRate = createColumnFloat( "grossRate", "Gross Rate", 60 );
        columnList.add( grossRate );

        AbstractColumn companyName = createColumnString( "companyName", "Company Name", 60 );
        columnList.add( companyName );

        AbstractColumn commissionsRetained = createColumnFloat( "commissionsRetained", "Commissions Retained", 60 );
        columnList.add( commissionsRetained );

        AbstractColumn tpaFee = createColumnFloat( "tpaFee", "TPA Fee", 60 );
        columnList.add( tpaFee );

        AbstractColumn netRemittance = createColumnFloat( "netRemittance", "Net Remittance", 60 );
        columnList.add( netRemittance );

        return columnList;
    }
}
