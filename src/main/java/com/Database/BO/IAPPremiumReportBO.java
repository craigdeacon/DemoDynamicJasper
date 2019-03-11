package com.Database.BO;

import com.Report.Component.ME.IAPPremiumReport.container.IAPPremiumADDGroup;
import com.Report.Component.ME.IAPPremiumReport.container.IAPPremiumDSAIGroup;
import com.Database.DAO.IAPPremiumReport.IAPPremiumReportDAO;
import com.Database.DAO.IAPPremiumReport.IAPPremiumReportDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.logging.Logger;

@Service
public class IAPPremiumReportBO
{
    private static final Logger LOGGER = Logger.getLogger(EAPReportBO.class.getName() );
    private IAPPremiumReportDAO iapPremiumReportDAO;

    @Autowired
    public IAPPremiumReportBO(IAPPremiumReportDAOImpl iapPremiumReportDAO)
    {
        this.iapPremiumReportDAO = iapPremiumReportDAO;
    }

    public ArrayList<IAPPremiumDSAIGroup> getIapPremiumDSAIGroups()
    {
           return iapPremiumReportDAO.getIAPPremiumReportDSAIGroupList();
    }

    public ArrayList<IAPPremiumADDGroup> getIapPremiumADDGroups()
    {
        return iapPremiumReportDAO.getIAPPremiumReportADDGroupList();
    }
}
