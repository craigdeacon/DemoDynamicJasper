package com.BO;

import com.Component.ME.IAPPremiumReport.container.IAPPremiumDSAIGroup;
import com.Component.ME.IAPPremiumReport.repository.IAPPremiumReportTestRepo;
import com.DAO.IAPPremiumReport.IAPPremiumReportDAO;
import com.DAO.IAPPremiumReport.IAPPremiumReportDAOImpl;
import com.DemoDynamicJasper.spring.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.logging.Logger;

public class IAPPremiumReportBO
{
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class );
    private static final Logger LOGGER = Logger.getLogger(EAPReportBO.class.getName() );
    IAPPremiumReportDAO iapPremiumReportDAO = context.getBean(IAPPremiumReportDAOImpl.class );

    public ArrayList<IAPPremiumDSAIGroup> getIapPremiumDSAIGroups()
    {
           return iapPremiumReportDAO.getIAPPremiumReportDSAIGroupList();
    }
}
