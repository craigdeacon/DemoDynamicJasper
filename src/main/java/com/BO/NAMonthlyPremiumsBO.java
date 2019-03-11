/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BO;

import com.Component.CS.NAMonthlyPremiums.container.NAMonthlyPremiumsGroup;
import com.DAO.NAMonthlyPremiums.NAMonthlyPremiumsDAO;
import java.util.ArrayList;
import java.util.logging.Logger;

import com.DAO.NAMonthlyPremiums.NAMonthlyPremiumsDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author craig.deacon
 */
@Service
public class NAMonthlyPremiumsBO
{
    private static final Logger LOGGER = Logger.getLogger( NAMonthlyPremiumsBO.class.getName());
    
    private NAMonthlyPremiumsDAO naMonthlyPremiumsDAO;

    @Autowired
    public NAMonthlyPremiumsBO(NAMonthlyPremiumsDAOImpl naMonthlyPremiumsDAO)
    {
        this.naMonthlyPremiumsDAO = naMonthlyPremiumsDAO;
    }

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
