/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Report.Component.CS.NaMonthlyPremiums.container;

import com.Database.BO.NAMonthlyPremiumsBO;
import com.Database.DAO.NAMonthlyPremiums.NAMonthlyPremiumsDAO;
import com.DemoDynamicJasper.spring.config.AppConfig;
import com.DemoDynamicJasper.spring.config.SpringConfigurationBootstrap;
import com.Report.Component.CS.NAMonthlyPremiums.container.NAMonthlyPremiumsGroup;
import com.Report.Component.CS.NAMonthlyPremiums.container.ProductTotal;
import com.Report.Component.CS.NAMonthlyPremiums.container.ProvinceTotal;
import com.Report.TestingRepositories.NAMonthlyPremiumsRepository;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author craig.deacon
 */
public class NaMonthlyPremiumsTest
{
    
    NAMonthlyPremiumsDAO naMonthlyPremiumsDAO;
    ArrayList<NAMonthlyPremiumsGroup> naMonthlyPremiumsGroupList;
    NAMonthlyPremiumsBO naMonthlyPremiumsBO;
    HashMap<String, HashMap> productProvinceMap;
    HashMap<String, ProvinceTotal> provinceTotalMap;
    HashMap<String, ProductTotal> productMap;
    
    @Before
    public void setUp() throws Exception
    {
        SpringConfigurationBootstrap.initialize(AppConfig.class);
        naMonthlyPremiumsDAO = SpringConfigurationBootstrap.getApplicationContext().getBean(NAMonthlyPremiumsRepository.class);
        naMonthlyPremiumsGroupList = naMonthlyPremiumsDAO.getNAMonthlyPremiumsGroup();
        naMonthlyPremiumsBO = SpringConfigurationBootstrap.getApplicationContext().getBean(NAMonthlyPremiumsBO.class);
        productProvinceMap = naMonthlyPremiumsBO.sortProvinceProductMaps(naMonthlyPremiumsGroupList);
        productMap = productProvinceMap.get( "products");
        provinceTotalMap = productProvinceMap.get("provinces");
    }
    
    @Test
    public void testProductMonthlyTotals()
    {
       
        
        ProductTotal std = productMap.get("STD");
        System.out.println( std.getPremiumTotal() );
        Assert.assertEquals( std.getPremiumTotal(), new Float ("20.0"));
        Assert.assertEquals( std.getPstTotal(), new Float ("4.0"));
        Assert.assertEquals( std.getRetroactivePremiumTotal(), new Float ("10.0"));
        Assert.assertEquals( std.getRetroactivePstTotal(), new Float ("1.0"));
        Assert.assertEquals( std.getGrossPremiumTotal(), new Float ("12.0"));
        Assert.assertEquals( std.getAdministrationAmountTotal(), new Float ("0.6"));
        Assert.assertEquals( std.getCommissionAmountTotal(), new Float ("1.6"));
        Assert.assertEquals( std.getNetPremiumTotal(), new Float ("30.5"));
    }
    @Test
    public void testProvinceMonthlyTotals()
    {
       
        ProvinceTotal on = provinceTotalMap.get("ON");        
        Assert.assertEquals (on.getPstTotal(), new Float ("4.0"));
        Assert.assertEquals (on.getRetroactivePstTotal(), new Float ("1.0"));
    }
    
    @Test
    public void testProductMapSize()
    {
        Assert.assertEquals (productMap.size(), 9);
    }
    
    @Test
    public void testProvinceMapSize()
    {
        Assert.assertEquals (provinceTotalMap.size(), 13);
    }
}

