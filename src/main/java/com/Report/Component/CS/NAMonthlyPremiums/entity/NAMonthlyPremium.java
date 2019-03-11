/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Report.Component.CS.NAMonthlyPremiums.entity;

import com.Database.BO.NAMonthlyPremiumsBO;
import com.DemoDynamicJasper.spring.config.SpringConfigurationBootstrap;
import org.springframework.beans.factory.annotation.Autowired;

import static com.Utilities.ReportStyles.initStyles;

/**
 *
 * @author craig.deacon
 */
class NAMonthlyPremium
{

    @Autowired
    public NAMonthlyPremium()
    {
        NAMonthlyPremiumsBO naMonthlyPremiumsBO = SpringConfigurationBootstrap.getApplicationContext().getBean(NAMonthlyPremiumsBO.class);
    }
    
    public void displayNAMonthlyPremiumReport()
    {
        initStyles();
        

        
        
    }
}
