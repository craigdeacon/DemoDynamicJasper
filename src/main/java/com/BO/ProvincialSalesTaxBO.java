/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BO;

import com.Component.ME.ProvincialSalesTaxReport.container.ProvincialGroup;
import com.DAO.ProvincialSalesTax.ProvincialSalesTaxDAO;
import com.DemoDynamicJasper.spring.config.AppConfig;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author craig.deacon
 */
public class ProvincialSalesTaxBO
{
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    private static final Logger LOGGER = Logger.getLogger( ProvincialSalesTaxBO.class.getName());
    
    ProvincialSalesTaxDAO provincialSalesTaxDAO = context.getBean(ProvincialSalesTaxDAO.class);
   
    private List<ProvincialGroup> getPSTGroups()
    {
        return provincialSalesTaxDAO.getGroupsProvincialSalesTax();
    }
    /*
    Method: gets all the lists (how to return more than one list)
    Inside Method: calls other methods within it create each datasource
    
    */
    
    public HashMap<String, List<ProvincialGroup>> getAllUnderwritingLists()
    {
       HashMap<String, List<ProvincialGroup>> groupMap = new HashMap<>();
       List <ProvincialGroup> groupList = getPSTGroups();

        //TODO CHUBB Method for list
        if ( !groupList.isEmpty() )
        {
            groupMap.put( "BGM", getProvincialGroupList( groupList, 601067 ) );
            groupMap.put( "CHUBB", getProvincialGroupList( groupList, 93063 ) );
            groupMap.put( "Empire Life", getProvincialGroupList( groupList, 601075 ) );
        }
        else
        {
            LOGGER.log( Level.SEVERE, "getAllUnderWritingLists is empty" );
        }
        return groupMap;
    }

    private List<ProvincialGroup> getProvincialGroupList( List<ProvincialGroup> groupList, Integer underwriterId )
    {
        List<ProvincialGroup> provincialGroupList = new ArrayList<>();
        for (ProvincialGroup group : groupList)
        {
            if (group.getUnderwriterId() != null && group.getUnderwriterId().equals(underwriterId)) //possibly turn these into constants?
            {
                provincialGroupList.add( group );
            }
        }
        return provincialGroupList;
    }
    
}
