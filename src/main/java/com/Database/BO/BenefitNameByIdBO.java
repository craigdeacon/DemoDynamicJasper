/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.Database.BO;


import com.Database.DAO.BenefitNameById.BenefitNameByIdDAO;
import com.DemoDynamicJasper.spring.config.AppConfig;
import java.util.HashMap;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Service;

/**
 *
 * @author craig.deacon
 */
@Service
public class BenefitNameByIdBO
{
    private static final Logger LOGGER = Logger.getLogger(BenefitNameByIdBO.class.getName() );
    
    BenefitNameByIdDAO benefitNameByIdDAO;
    
    @Autowired
    public BenefitNameByIdBO(BenefitNameByIdDAO benefitNameByIdDAO)
    {
        this.benefitNameByIdDAO = benefitNameByIdDAO;
    }
    
    public HashMap<Integer, String> getBenefitNameById()
    {
        HashMap<Integer, String> benefitByIdMap = benefitNameByIdDAO.getBenefitNameById();
        benefitByIdMap.put( -8, "OLIFE-S" );
        return benefitByIdMap;
    }
}
