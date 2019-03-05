/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Component.CS.EP3.entity;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import com.BO.EP3ProcessBO;
import com.DAO.EP3.EP3ProcessDAOImpl;
import static com.utilities.ReportStyles.*;
import com.utilities.ReportUtilities;
import static com.utilities.ReportUtilities.*;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

/**
 *
 * @author craig.deacon
 */
public class EP3
{

    public EP3()
    {
    }

    public void displayEP3Report()
    {
        try
        {
            initStyles();
            
            EP3ProcessBO ep3ProcessBO = new EP3ProcessBO();

            DynamicReport dynamicReport = ReportUtilities.createBasicReportSkeleton(getEP3Columns(), "EP3 Process Report");

            Date invoiceDate = getInvoiceDate("2019-02-01");
           
            JRDataSource dataSource = new JRBeanCollectionDataSource( ep3ProcessBO.filterAffiliatedGroupsList( invoiceDate ) );

            JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint( dynamicReport, new ClassicLayoutManager(), dataSource );
//            JasperViewer.viewReport( jasperPrint );
            ReportUtilities.exportExcel( jasperPrint, "EP3 Process Report", true);
        }
        catch (JRException ex)
        {
            Logger.getLogger( EP3.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }

    private ArrayList<AbstractColumn> getEP3Columns()
    {
        ArrayList<AbstractColumn> columnList = new ArrayList<>();

        AbstractColumn greenShieldDivision = createColumnString( "greenShieldDivision", "GSC Division #", 90 );
        columnList.add( greenShieldDivision );

        AbstractColumn billingDivisionName = createColumnString( "billingDivisionName", "Billing Division Name", 270 );
        columnList.add( billingDivisionName );

        AbstractColumn renewalMonth = createColumnString( "renewalMonth", "Renewal Month", 100 );
        columnList.add( renewalMonth );

        AbstractColumn initialEffective = createColumnString( "initialEffective", "Initial Effective Date", 120 );
        columnList.add( initialEffective );

        AbstractColumn poolingThreshold = createColumnString( "poolingThreshold", "Pooling Threshold", 110, RIGHT );
        columnList.add( poolingThreshold );

        AbstractColumn poolingBasis = createColumnString( "poolingBasis", "Pooling Basis", 90 );
        columnList.add( poolingBasis );

        AbstractColumn exclusion = createColumnString( "exclusion", "Exclusions", 70 );
        columnList.add( exclusion );

        AbstractColumn terminationDate = createColumnString( "terminationDate", "Termination Date", 100 );
        columnList.add( terminationDate );

        AbstractColumn dateEntered = createColumnString( "dateEntered", "Date Entered", 80 );
        columnList.add( dateEntered );

        return columnList;
    }

    private Date getInvoiceDate( String strDate )
    {
        
            SimpleDateFormat sdf1 = new SimpleDateFormat( "yyyy-MM-dd" );
            java.util.Date date = null;
            try
            {
                date = sdf1.parse( strDate );
            }
            catch (ParseException ex)
            {
                Logger.getLogger( EP3ProcessDAOImpl.class.getName() ).log( Level.SEVERE, null, ex );
            }
            java.sql.Date invoiceDate = new java.sql.Date( date.getTime() );
            return invoiceDate;
    }
}
