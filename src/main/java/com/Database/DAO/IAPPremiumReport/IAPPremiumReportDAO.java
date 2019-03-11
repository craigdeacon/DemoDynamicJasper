package com.Database.DAO.IAPPremiumReport;

import com.Report.Component.ME.IAPPremiumReport.container.IAPPremiumADDGroup;
import com.Report.Component.ME.IAPPremiumReport.container.IAPPremiumDSAIGroup;

import java.util.ArrayList;

public interface IAPPremiumReportDAO
{
    public ArrayList<IAPPremiumDSAIGroup> getIAPPremiumReportDSAIGroupList();

    public ArrayList<IAPPremiumADDGroup> getIAPPremiumReportADDGroupList();
}
