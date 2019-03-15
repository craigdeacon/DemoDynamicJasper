/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Database.BO;




import java.util.ArrayList;
import java.util.logging.Logger;

import com.Database.DAO.NAMonthlyPremiums.NAMonthlyPremiumsDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.Database.DAO.NAMonthlyPremiums.NAMonthlyPremiumsDAO;
import com.Report.Component.CS.NAMonthlyPremiums.container.NAMonthlyPremiumsGroup;
import com.Report.Component.CS.NAMonthlyPremiums.container.ProductTotal;
import com.Report.Component.CS.NAMonthlyPremiums.container.ProvinceTotal;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author craig.deacon
 */
@Service
public class NAMonthlyPremiumsBO
{
    private static final Logger LOGGER = Logger.getLogger( NAMonthlyPremiumsBO.class.getName());
    
    private NAMonthlyPremiumsDAO naMonthlyPremiumsDAO;
    private BenefitNameByIdBO benefitNameBO;
    

    @Autowired
    public NAMonthlyPremiumsBO(NAMonthlyPremiumsDAOImpl naMonthlyPremiumsDAO, BenefitNameByIdBO benefitNameBO)
    {
        this.naMonthlyPremiumsDAO = naMonthlyPremiumsDAO;
        this.benefitNameBO = benefitNameBO;
    }
   
    public ArrayList<NAMonthlyPremiumsGroup> getNAMonthlyPremiumsGroup()
    {
        ArrayList<NAMonthlyPremiumsGroup> naMonthlyPremiumsGroups = naMonthlyPremiumsDAO.getNAMonthlyPremiumsGroup();
        HashMap<Integer, String> benefitNameByIdMap = benefitNameBO.getBenefitNameById();
        
        for (NAMonthlyPremiumsGroup group: naMonthlyPremiumsGroups)
        {
            group.setProduct( benefitNameByIdMap.get( group.getBenefitId() ) );
        }
        
        return naMonthlyPremiumsGroups;
    }
    
    public HashMap getProductProvinceMap()
    {
        
        HashMap<String, HashMap> productProvinceMap = new HashMap<>();
        HashMap<String, ProductTotal> productMap = new HashMap<>();
        HashMap<String, ProvinceTotal> provinceTotalMap = setUpProvinceMap();
        ArrayList<NAMonthlyPremiumsGroup> naMonthlyPremiumsGroups = getNAMonthlyPremiumsGroup();
        for ( NAMonthlyPremiumsGroup group : naMonthlyPremiumsGroups )
        {
            if (productMap.containsKey( group.getProduct()))
            {
                ProductTotal total = productMap.get( group.getProduct());
                
                productMap.put( group.getProduct(), new ProductTotal( 
                        group.getProduct(), 
                        group.getPremium() + total.getPremiumTotal(), 
                        group.getPst() + total.getPstTotal(), 
                        group.getRetroactivePremium() + total.getRetroactivePremiumTotal(),
                        group.getRetroactivePst() + total.getRetroactivePstTotal(),
                        group.getGrossPremium() + total.getGrossPremiumTotal(),
                        group.getAdministrationAmount() + total.getAdministrationAmountTotal(), 
                        group.getCommissionAmount() + total.getCommissionAmountTotal(), 
                        group.getNetPremium() + total.getNetPremiumTotal(),
                        group.getGst() + total.getGstTotal() ) );
            }
            else
            {
                productMap.put( group.getProduct(), new ProductTotal( 
                        group.getProduct(), 
                        group.getPremium(), 
                        group.getPst(), 
                        group.getRetroactivePremium(),
                        group.getRetroactivePst(),
                        group.getGrossPremium(),
                        group.getAdministrationAmount(), 
                        group.getCommissionAmount(), 
                        group.getNetPremium(),
                        group.getGst() ) );
            }
            ProvinceTotal pTotal = provinceTotalMap.get( group.getProvince());
            String problem = group.getEmployer();
            System.out.println( problem );
            provinceTotalMap.put (group.getProvince(), new ProvinceTotal(
                    group.getProvince(), 
                    group.getPst() + pTotal.getPstTotal(), 
                    group.getRetroactivePst() + pTotal.getRetroactivePstTotal() ));
            
        }
        productProvinceMap.put("products", productMap);
        productProvinceMap.put("provinces", provinceTotalMap);
        return productProvinceMap;
    }

    private HashMap<String, ProvinceTotal> setUpProvinceMap()
    {
         HashMap<String, ProvinceTotal> provinceTotalMap = new HashMap<>();
         provinceTotalMap.put( "BC", new ProvinceTotal( "BC", new Float( "0.0" ), new Float( "0.0" ) ) );
         provinceTotalMap.put( "AB", new ProvinceTotal( "AB", new Float( "0.0" ), new Float( "0.0" ) ) );
         provinceTotalMap.put( "SK", new ProvinceTotal( "SK", new Float( "0.0" ), new Float( "0.0" ) ) );
         provinceTotalMap.put( "MB", new ProvinceTotal( "MB", new Float( "0.0" ), new Float( "0.0" ) ) );
         provinceTotalMap.put( "QC", new ProvinceTotal( "QC", new Float( "0.0" ), new Float( "0.0" ) ) );
         provinceTotalMap.put( "ON", new ProvinceTotal( "ON", new Float( "0.0" ), new Float( "0.0" ) ) );
         provinceTotalMap.put( "NS", new ProvinceTotal( "NS", new Float( "0.0" ), new Float( "0.0" ) ) );
         provinceTotalMap.put( "NB", new ProvinceTotal( "NB", new Float( "0.0" ), new Float( "0.0" ) ) );
         provinceTotalMap.put( "YT", new ProvinceTotal( "YT", new Float( "0.0" ), new Float( "0.0" ) ) );
         provinceTotalMap.put( "NT", new ProvinceTotal( "NT", new Float( "0.0" ), new Float( "0.0" ) ) );
         provinceTotalMap.put( "NU", new ProvinceTotal( "NU", new Float( "0.0" ), new Float( "0.0" ) ) );
         
         return provinceTotalMap;
    }
}
