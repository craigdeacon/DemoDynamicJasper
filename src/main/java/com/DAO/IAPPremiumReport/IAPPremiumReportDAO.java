package com.DAO.IAPPremiumReport;

import com.Component.ME.IAPPremiumReport.container.IAPPremiumADDGroup;
import com.Component.ME.IAPPremiumReport.container.IAPPremiumDSAIGroup;

import java.util.ArrayList;

public interface IAPPremiumReportDAO
{
    public ArrayList<IAPPremiumDSAIGroup> getIAPPremiumReportDSAIGroupList();

    public ArrayList<IAPPremiumADDGroup> getIAPPremiumReportADDGroupList();
}
