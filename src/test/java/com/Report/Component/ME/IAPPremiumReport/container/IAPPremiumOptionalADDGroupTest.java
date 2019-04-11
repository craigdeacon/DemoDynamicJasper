package com.Report.Component.ME.IAPPremiumReport.container;

import com.Database.DAO.IAPPremiumReport.IAPPremiumReportDAO;
import com.DemoDynamicJasper.spring.config.AppConfig;
import com.DemoDynamicJasper.spring.config.SpringConfigurationBootstrap;
import com.Report.Component.ME.IAPPremiumReport.entity.IAPPremiumOptionalADDReport;
import com.Report.TestingRepositories.IAPPremiumReportRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class IAPPremiumOptionalADDGroupTest
{
    ArrayList<IAPPremiumOptionalADDGroup>  iapPremiumOptionalADDGroupArrayList;
    IAPPremiumReportDAO iapPremiumReportDAO;

    @Before
    public void setUp() throws Exception
    {
        SpringConfigurationBootstrap.initialize(AppConfig.class);
        iapPremiumReportDAO = SpringConfigurationBootstrap.getApplicationContext().getBean(IAPPremiumReportRepository.class);
        iapPremiumOptionalADDGroupArrayList = iapPremiumReportDAO.getIAPPremiumReportOptionalADDGroupList();
    }

    @After
    public void tearDown() throws Exception
    {

    }

    @Test
    public void grossPremiumAndAdjustOptionalADDTest()
    {
        System.out.println("grossPremiumAndAdjustOptionalADDTest");
        for (IAPPremiumOptionalADDGroup group : iapPremiumOptionalADDGroupArrayList )
        {
            Double bothGrossAndAdjust = group.getTotalGrossPremiumAndAdjust();
            Double addedGrossAndAdjust = group.getTotalGrossPremium() + group.getAdjust();
            Assert.assertEquals(addedGrossAndAdjust, bothGrossAndAdjust);
        }
    }
}
