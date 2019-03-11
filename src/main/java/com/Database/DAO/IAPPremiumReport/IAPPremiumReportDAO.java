package com.Database.DAO.IAPPremiumReport;

import com.Report.Component.ME.IAPPremiumReport.container.IAPPremiumADDGroup;
import com.Report.Component.ME.IAPPremiumReport.container.IAPPremiumDSAIGroup;
import com.Report.Component.ME.IAPPremiumReport.container.IAPPremiumGroup;
import com.Report.Component.ME.IAPPremiumReport.container.IAPPremiumOptionalADDGroup;

import java.util.ArrayList;

public interface IAPPremiumReportDAO
{
    public ArrayList<IAPPremiumDSAIGroup> getIAPPremiumReportDSAIGroupList();

    public ArrayList<IAPPremiumADDGroup> getIAPPremiumReportADDGroupList();

    public ArrayList<IAPPremiumOptionalADDGroup> getIAPPremiumReportOptionalADDGroupList();

    public ArrayList<IAPPremiumGroup> getIAPPremiumReportGroupList();
}
