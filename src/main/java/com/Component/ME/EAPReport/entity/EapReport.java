package com.Component.ME.EAPReport.entity;

import ar.com.fdvs.dj.core.DynamicJasperHelper;
import ar.com.fdvs.dj.core.layout.ClassicLayoutManager;
import ar.com.fdvs.dj.domain.DynamicReport;
import ar.com.fdvs.dj.domain.entities.columns.AbstractColumn;
import com.BO.EAPReportBO;
import com.DemoDynamicJasper.spring.config.SpringConfigurationBootstrap;
import com.utilities.ReportUtilities;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.utilities.ReportStyles.*;
import static com.utilities.ReportUtilities.*;

public class EapReport
{

    private EAPReportBO eapReportBO;

    public EapReport()
    {
        this.eapReportBO = SpringConfigurationBootstrap.getApplicationContext().getBean(EAPReportBO.class);
    }

    public void displayEAPReport(int underwriterId)
    {
        try
        {
            initStyles();


            String title;

            if (underwriterId == 93064)
            {
                title = "Arete EAP Report";
            } else
            {
                title = "HumanaCare EAP Report";
            }

            DynamicReport dynamicReport = ReportUtilities.createBasicReportSkeleton(getAreteEapColumns(), title);

            JRDataSource dataSource = new JRBeanCollectionDataSource(eapReportBO.getEAPGroups(underwriterId));

            JasperPrint jasperPrint = DynamicJasperHelper.generateJasperPrint(dynamicReport, new ClassicLayoutManager(), dataSource);

            ReportUtilities.exportExcel(jasperPrint, title, true);
        }
        catch (JRException ex)
        {
            Logger.getLogger(EapReport.class.getName()).log(Level.SEVERE, "displayEAPReport Failed", ex);
        }
    }

    private ArrayList<AbstractColumn> getAreteEapColumns()
    {
        ArrayList<AbstractColumn> columnList = new ArrayList<>();

        AbstractColumn sequenceNumber = createColumnInt("sequenceNumber", "Sequence #", 90);
        columnList.add(sequenceNumber);

        AbstractColumn loadDate = createColumnDate("loadDate", "Load Date", 120);
        loadDate.setPattern("MMMM, dd, yyyy");
        columnList.add(loadDate);

        AbstractColumn groupId = createColumnInt("groupId", "Group #", 90);
        columnList.add(groupId);

        AbstractColumn accountNumber = createColumnString("accountNumber", "Acct #", 90);
        columnList.add(accountNumber);

        AbstractColumn employerName = createColumnString("employerName", "Employer Name", 270);
        columnList.add(employerName);

        AbstractColumn premiumMonth = createColumnDate("premiumMonth", "Premium Month", 120);
        premiumMonth.setPattern("MMMM, dd, yyyy");
        columnList.add(premiumMonth);

        AbstractColumn effectiveDate = createColumnDate("effectiveDate", "Effective Date", 120);
        effectiveDate.setPattern("MMMM, dd, yyyy");
        columnList.add(effectiveDate);

        AbstractColumn insurer = createColumnString("insurer", "Insurer", 60);
        columnList.add(insurer);

        AbstractColumn product = createColumnString("product", "Product", 60);
        columnList.add(product);

        AbstractColumn province = createColumnString("province", "Province", 60);
        columnList.add(province);

        AbstractColumn numberLives = createColumnInt("lives", "# Lives", 60);
        columnList.add(numberLives);

        return columnList;
    }


}
