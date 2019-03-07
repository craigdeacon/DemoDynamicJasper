/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BO;

import com.Component.CS.NAMonthlyPremiums.container.NAMonthlyPremiumsGroup;
import com.DAO.NAMonthlyPremiums.NAMonthlyPremiumsDAO;
import com.DemoDynamicJasper.spring.config.AppConfig;
import java.util.ArrayList;
import java.util.logging.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author craig.deacon
 */
public class NAMonthlyPremiumsBO
{
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    private static final Logger LOGGER = Logger.getLogger( NAMonthlyPremiumsBO.class.getName());
    
    NAMonthlyPremiumsDAO naMonthlyPremiumsDAO = context.getBean( NAMonthlyPremiumsDAO.class);
    
    public ArrayList<NAMonthlyPremiumsGroup> getNAMonthlyPremiumsGroup()
    {
        ArrayList<NAMonthlyPremiumsGroup> naMonthlyPremiumsGroups = naMonthlyPremiumsDAO.getNAMonthlyPremiumsGroup();
        
        for (NAMonthlyPremiumsGroup group: naMonthlyPremiumsGroups)
        {
           group.setProduct( naMonthlyPremiumsDAO.getBenefitName( group.getBenefitId()));
        }
        
        return naMonthlyPremiumsGroups;
    }
}
