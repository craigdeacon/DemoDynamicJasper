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
        return sortProvinceProductMaps( getNAMonthlyPremiumsGroup() );
    }

    private HashMap<String, ProvinceTotal> setUpProvinceMap()
    {
        HashMap<String, ProvinceTotal> provinceTotalMap = new HashMap<>();
        provinceTotalMap.put( "BC", new ProvinceTotal( "BC", new Double( "0.0" ), new Double( "0.0" ) ) );
        provinceTotalMap.put( "AB", new ProvinceTotal( "AB", new Double( "0.0" ), new Double( "0.0" ) ) );
        provinceTotalMap.put( "SK", new ProvinceTotal( "SK", new Double( "0.0" ), new Double( "0.0" ) ) );
        provinceTotalMap.put( "MB", new ProvinceTotal( "MB", new Double( "0.0" ), new Double( "0.0" ) ) );
        provinceTotalMap.put( "QC", new ProvinceTotal( "QC", new Double( "0.0" ), new Double( "0.0" ) ) );
        provinceTotalMap.put( "ON", new ProvinceTotal( "ON", new Double( "0.0" ), new Double( "0.0" ) ) );
        provinceTotalMap.put( "NS", new ProvinceTotal( "NS", new Double( "0.0" ), new Double( "0.0" ) ) );
        provinceTotalMap.put( "NB", new ProvinceTotal( "NB", new Double( "0.0" ), new Double( "0.0" ) ) );
        provinceTotalMap.put( "YT", new ProvinceTotal( "YT", new Double( "0.0" ), new Double( "0.0" ) ) );
        provinceTotalMap.put( "NT", new ProvinceTotal( "NT", new Double( "0.0" ), new Double( "0.0" ) ) );
        provinceTotalMap.put( "NU", new ProvinceTotal( "NU", new Double( "0.0" ), new Double( "0.0" ) ) );
        provinceTotalMap.put( "NL", new ProvinceTotal( "NL", new Double( "0.0" ), new Double( "0.0" ) ) );
        provinceTotalMap.put( "PE", new ProvinceTotal( "PE", new Double( "0.0" ), new Double( "0.0" ) ) );

        return provinceTotalMap;
    }
    
    /**
     * Sets up the Product map so that even if a product has no entries it
     * appears on report
     * @return 
     */
    private HashMap<String, ProductTotal> setUpProductMap()
    {
        HashMap<String, ProductTotal> productMap = new HashMap<>();
        productMap.put( "Life", new ProductTotal("", "LIFE" ));
        productMap.put( "AD&D", new ProductTotal("", "ADD" ));
        productMap.put( "Dep Life", new ProductTotal("", "DLIFE" ));
        productMap.put( "STD", new ProductTotal("", "STD" ));
        productMap.put( "LTD", new ProductTotal("", "LTD" ));
        productMap.put( "Opt Life", new ProductTotal("", "OLIFE-E" ));
        productMap.put( "OLIFE-S", new ProductTotal("", "OLIFE-S" ));
        productMap.put( "OADD", new ProductTotal("", "OADD" ));
        productMap.put( "EAP", new ProductTotal("", "EAP" ));
        return productMap;
    }

    /**
     * Changes the names of products listed in database to match desired
     * output for report
     * Also adds 'Product Totals' above a blank column to the first product listed
     * 
     * @param productMap
     * @return 
     */
    private HashMap<String, ProductTotal> fixProductNames( HashMap<String, ProductTotal> productMap )
    {
        ProductTotal tempProductTotal = productMap.get( "Life");
        productMap.put( "Life", new ProductTotal ("LIFE", 
                tempProductTotal.getPremiumTotal(), 
                tempProductTotal.getPstTotal(),
                tempProductTotal.getRetroactivePremiumTotal(),
                tempProductTotal.getRetroactivePstTotal(),
                tempProductTotal.getGrossPremiumTotal(),
                tempProductTotal.getAdministrationAmountTotal(),
                tempProductTotal.getCommissionAmountTotal(),
                tempProductTotal.getNetPremiumTotal(),
                tempProductTotal.getGstTotal()));
        
        tempProductTotal = productMap.get( "AD&D");
        productMap.put( "AD&D", new ProductTotal ("ADD", 
                tempProductTotal.getPremiumTotal(), 
                tempProductTotal.getPstTotal(),
                tempProductTotal.getRetroactivePremiumTotal(),
                tempProductTotal.getRetroactivePstTotal(),
                tempProductTotal.getGrossPremiumTotal(),
                tempProductTotal.getAdministrationAmountTotal(),
                tempProductTotal.getCommissionAmountTotal(),
                tempProductTotal.getNetPremiumTotal(),
                tempProductTotal.getGstTotal()));
        
        tempProductTotal = productMap.get( "Dep Life");
        productMap.put( "Dep Life", new ProductTotal ("DLIFE", 
                tempProductTotal.getPremiumTotal(), 
                tempProductTotal.getPstTotal(),
                tempProductTotal.getRetroactivePremiumTotal(),
                tempProductTotal.getRetroactivePstTotal(),
                tempProductTotal.getGrossPremiumTotal(),
                tempProductTotal.getAdministrationAmountTotal(),
                tempProductTotal.getCommissionAmountTotal(),
                tempProductTotal.getNetPremiumTotal(),
                tempProductTotal.getGstTotal()));
        
        tempProductTotal = productMap.get( "STD");
        productMap.put( "STD", new ProductTotal (
                "Product Totals:", "STD", 
                tempProductTotal.getPremiumTotal(), 
                tempProductTotal.getPstTotal(),
                tempProductTotal.getRetroactivePremiumTotal(),
                tempProductTotal.getRetroactivePstTotal(),
                tempProductTotal.getGrossPremiumTotal(),
                tempProductTotal.getAdministrationAmountTotal(),
                tempProductTotal.getCommissionAmountTotal(),
                tempProductTotal.getNetPremiumTotal(),
                tempProductTotal.getGstTotal()));
        
        tempProductTotal = productMap.get( "Opt Life");
        productMap.put( "Opt Life", new ProductTotal (
                "OLIFE-E", 
                tempProductTotal.getPremiumTotal(), 
                tempProductTotal.getPstTotal(),
                tempProductTotal.getRetroactivePremiumTotal(),
                tempProductTotal.getRetroactivePstTotal(),
                tempProductTotal.getGrossPremiumTotal(),
                tempProductTotal.getAdministrationAmountTotal(),
                tempProductTotal.getCommissionAmountTotal(),
                tempProductTotal.getNetPremiumTotal(),
                tempProductTotal.getGstTotal()));
        
             
        return productMap;
    }

    /**
     * Adds a 'header' to the first province in the list to make a label for the subreport
     * Every other province has an empty string for the header property, so the 'Prov Tax Totals'
     * looks like a label, but is actually property of the first row, while every other
     * province is blank
     * 
     * @param provinceTotalMap
     * @return 
     */
    private HashMap<String, ProvinceTotal> fixProvinceMap( HashMap<String, ProvinceTotal> provinceTotalMap )
    {
        ProvinceTotal tempProvinceTotal = provinceTotalMap.get ("BC");
        provinceTotalMap.put( "BC", new ProvinceTotal("Prov Tax Totals", tempProvinceTotal.getProvince(), tempProvinceTotal.getPstTotal(), tempProvinceTotal.getRetroactivePstTotal()));
        return provinceTotalMap;
    }

    public HashMap<String, HashMap> sortProvinceProductMaps( ArrayList<NAMonthlyPremiumsGroup> naMonthlyPremiumsGroup )
    {
        HashMap<String, HashMap> productProvinceMap = new HashMap<>();
        HashMap<String, ProductTotal> productMap = setUpProductMap();
        HashMap<String, ProvinceTotal> provinceTotalMap = setUpProvinceMap();
        for ( NAMonthlyPremiumsGroup group : naMonthlyPremiumsGroup )
        {
            if ( productMap.containsKey( group.getProduct() ) )
            {
                ProductTotal total = productMap.get( group.getProduct() );

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

            ProvinceTotal pTotal = provinceTotalMap.get( group.getProvince() );
            provinceTotalMap.put( group.getProvince(), new ProvinceTotal(
                    group.getProvince(),
                    group.getPst() + pTotal.getPstTotal() + 0,
                    group.getRetroactivePst() + pTotal.getRetroactivePstTotal() ) );

        }

        productMap = fixProductNames( productMap );
        provinceTotalMap = fixProvinceMap( provinceTotalMap );
        productProvinceMap.put( "products", productMap );
        productProvinceMap.put( "provinces", provinceTotalMap );
        return productProvinceMap;
    }
}
