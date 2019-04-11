/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Report.TestingRepositories;

import com.Database.DAO.NAMonthlyPremiums.NAMonthlyPremiumsDAO;
import com.Report.Component.CS.NAMonthlyPremiums.container.NAMonthlyPremiumsGroup;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import org.springframework.stereotype.Component;

/**
 *
 * @author craig.deacon
 */
@Component
public class NAMonthlyPremiumsRepository implements NAMonthlyPremiumsDAO
{

    @Override
    public ArrayList<NAMonthlyPremiumsGroup> getNAMonthlyPremiumsGroup()
    {
        ArrayList<NAMonthlyPremiumsGroup> monthlyGroupList = new ArrayList<>();
      
        monthlyGroupList.add( new NAMonthlyPremiumsGroup("Policy", "Account", "Avengers", new Date(0), "STD", 1, new Double ("10.0"), new Double ("1.0"), "ON", 1, 
                20, new Double ("2.0"), new Double ("5.0") , new Double ("0.5"), new Double ("6.0"), new Double ("0.3"), new Double ("0.80"), new Double ("15.25"), 
                new Double ("0"), new Double ("0"), new Double ("0")));
        monthlyGroupList.add( new NAMonthlyPremiumsGroup("Policy", "Account", "Fantastic Four", new Date(0), "STD", 1, new Double ("10.0"), new Double ("1.0"), "ON", 1, 
                20, new Double ("2.0"), new Double ("5.0") , new Double ("0.5"), new Double ("6.0"), new Double ("0.3"), new Double ("0.80"), new Double ("15.25"), 
                new Double ("0"), new Double ("0"), new Double ("0")));
         monthlyGroupList.add( new NAMonthlyPremiumsGroup("Policy", "Account", "Bionic 6", new Date(0), "Life", 1, new Double ("10.0"), new Double ("1.0"), "BC", 1, 
                20, new Double ("2.0"), new Double ("5.0") , new Double ("0.5"), new Double ("6.0"), new Double ("0.3"), new Double ("0.80"), new Double ("15.25"), 
                new Double ("0"), new Double ("0"), new Double ("0")));
         monthlyGroupList.add( new NAMonthlyPremiumsGroup("Policy", "Account", "Justice League", new Date(0), "Life", 1, new Double ("10.0"), new Double ("1.0"), "BC", 1, 
                20, new Double ("2.0"), new Double ("5.0") , new Double ("0.5"), new Double ("6.0"), new Double ("0.3"), new Double ("0.80"), new Double ("15.25"), 
                new Double ("0"), new Double ("0"), new Double ("0")));
        return monthlyGroupList;
    }
    
// ( String policySeries, String policyAccount, String employer, Date month, String product, int benefitId, Double premium, Double premiumRate, String province, int employeeCount, 
//    int volume, Double pst, Double retroactivePremium, Double retroactivePst, Double grossPremium, Double administrationAmount, Double commissionAmount, Double netPremium, 
//    Double gst, Double administrationRate, Double commissionRate )
}