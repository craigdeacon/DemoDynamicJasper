package com.Report.Component.ME.IAPPremiumReport.container;

import com.Database.DAO.IAPPremiumReport.IAPPremiumReportDAO;
import com.DemoDynamicJasper.spring.config.AppConfig;
import com.DemoDynamicJasper.spring.config.SpringConfigurationBootstrap;
import com.Report.TestingRepositories.IAPPremiumReportRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;

public class IAPPremiumADDGroupTest
{

    ArrayList<IAPPremiumADDGroup> iapPremiumADDGroupArrayList;
    IAPPremiumReportDAO iapPremiumReportDAO;
    @Before
    public void setUp() throws Exception
    {
        SpringConfigurationBootstrap.initialize(AppConfig.class);
        iapPremiumReportDAO = SpringConfigurationBootstrap.getApplicationContext().getBean(IAPPremiumReportRepository.class);
        iapPremiumADDGroupArrayList = iapPremiumReportDAO.getIAPPremiumReportADDGroupList();
    }

    @Test
    public void grossPremiumAndAdjustADDTest()
    {
        System.out.println("grossPremiumAndAdjustTest");
        for (IAPPremiumADDGroup group : iapPremiumADDGroupArrayList )
        {
            Float bothGrossAndAdjust = group.getGrossPremiumAndAdjust();
            Float addedGrossAndAdjust = group.getTotalGrossPremium() + group.getAdjust();
            Assert.assertEquals(addedGrossAndAdjust, bothGrossAndAdjust);
        }
    }

    @After
    public void tearDown() throws Exception
    {
    }
}
