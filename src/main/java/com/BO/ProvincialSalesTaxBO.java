/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.BO;

import com.Component.ME.ProvincialSalesTaxReport.container.ProvincialGroup;
import com.DAO.ProvincialSalesTax.ProvincialSalesTaxDAO;
import com.DemoDynamicJasper.spring.config.AppConfig;
import java.util.List;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 *
 * @author craig.deacon
 */
public class ProvincialSalesTaxBO
{
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    
    ProvincialSalesTaxDAO provincialSalesTaxDAO = context.getBean(ProvincialSalesTaxDAO.class);
   
    public List<ProvincialGroup> getPSTGroups()
    {
        return provincialSalesTaxDAO.getGroupsProvincialSalesTax();
    }
}
