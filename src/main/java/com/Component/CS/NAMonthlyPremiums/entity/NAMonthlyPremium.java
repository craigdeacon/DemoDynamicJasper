/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Component.CS.NAMonthlyPremiums.entity;

import com.BO.NAMonthlyPremiumsBO;
import static com.utilities.ReportStyles.initStyles;

/**
 *
 * @author craig.deacon
 */
public class NAMonthlyPremium
{

    public NAMonthlyPremium()
    {
    }
    
    public void displayNAMonthlyPremiumReport()
    {
        initStyles();
        
        NAMonthlyPremiumsBO naMonthlyPremiumsBO = new NAMonthlyPremiumsBO();
        
        
    }
}
