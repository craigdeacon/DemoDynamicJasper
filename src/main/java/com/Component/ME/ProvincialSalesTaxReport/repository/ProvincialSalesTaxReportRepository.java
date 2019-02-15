/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Component.ME.ProvincialSalesTaxReport.repository;

import com.BO.ProvincialSalesTaxBO;
import com.Component.ME.ProvincialSalesTaxReport.container.ProvincialGroup;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author craig.deacon
 */
public class ProvincialSalesTaxReportRepository
{

    /**
     *
     */
    public ProvincialSalesTaxReportRepository()
    {
    }
    
    /**
     *
     * @return
     */
    public static HashMap<String, List<ProvincialGroup>> getProvincialGroupList()
    {
        ProvincialSalesTaxBO provincialSalesTaxBO = new ProvincialSalesTaxBO();
        return provincialSalesTaxBO.getAllUnderwritingLists();
//        
//        List<ProvincialGroup> provincialGroupList = new ArrayList<>();
//        provincialGroupList.add (new ProvincialGroup("1033387 Ontario Limited", "C001-0009", new Float ("25.00"), new Float ("2.00"), null, null, null, null, null, null));
        
        
    }
    
//     public static List<ProvincialGroup> getProvincialGroupListChubb()
//    {
//        List<ProvincialGroup> provincialGroupList = new ArrayList<>();
//        provincialGroupList.add (new ProvincialGroup("Action Express Ltd.", "GL10445606", new Float ("6.86"), new Float (".55"), null, null, null, null, null, null));
//        
//        return provincialGroupList;
//    }
}
