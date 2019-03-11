/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BO;

import com.Component.ME.ProvincialSalesTaxReport.container.ProvincialGroup;
import com.DAO.ProvincialSalesTax.ProvincialSalesTaxDAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.DAO.ProvincialSalesTax.ProvincialSalesTaxDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author craig.deacon
 */
@Service
public class ProvincialSalesTaxBO
{
    private static final Logger LOGGER = Logger.getLogger( ProvincialSalesTaxBO.class.getName());
    
    private ProvincialSalesTaxDAO provincialSalesTaxDAO;

    @Autowired
    public ProvincialSalesTaxBO(ProvincialSalesTaxDAOImpl provincialSalesTaxDAO)
    {
        this.provincialSalesTaxDAO = provincialSalesTaxDAO;
    }

    private List<ProvincialGroup> getPSTGroups()
    {
        return provincialSalesTaxDAO.getGroupsProvincialSalesTax();
    }
    /*
    Method: gets all the lists (how to return more than one list)
    Inside Method: calls other methods within it create each datasource
    
    */
    
    /**
     *
     * @return
     */
    public HashMap<String, Integer> getUnderWriters()
    {
        return provincialSalesTaxDAO.getUnderWriters();
    }
    
    /**
     *
     * @return
     */
    public HashMap<String, List<ProvincialGroup>> getAllUnderwritingLists()
    {
       HashMap<String, List<ProvincialGroup>> groupMap = new HashMap<>();
       List <ProvincialGroup> groupList = getPSTGroups();
       HashMap<String, Integer> groupKey = getUnderWriters();
      
        if ( !groupList.isEmpty() )
        {
            groupKey.forEach( (k, v) ->
            {
                groupMap.put( k, getProvincialGroupList( groupList, v ) );
            } );
        }
        else
        {
            LOGGER.log( Level.SEVERE, "getAllUnderWritingLists is empty" );
        }
        return groupMap;
    }

    private List<ProvincialGroup> getProvincialGroupList(List<ProvincialGroup> groupList, Integer underwriterId)
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
