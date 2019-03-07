/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.DAO.NAMonthlyPremiums;

import com.Component.CS.NAMonthlyPremiums.container.NAMonthlyPremiumsGroup;
import java.util.ArrayList;

/**
 *
 * @author craig.deacon
 */
public interface NAMonthlyPremiumsDAO
{
    ArrayList<NAMonthlyPremiumsGroup> getNAMonthlyPremiumsGroup();

    String getBenefitName( int benefitId );
}
