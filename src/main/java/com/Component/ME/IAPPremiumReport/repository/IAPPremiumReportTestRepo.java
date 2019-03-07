package com.Component.ME.IAPPremiumReport.repository;

import com.Component.ME.IAPPremiumReport.container.IAPPremiumDSAIGroup;
import com.DAO.IAPPremiumReport.IAPPremiumReportDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class IAPPremiumReportTestRepo implements IAPPremiumReportDAO
{
    ArrayList<IAPPremiumDSAIGroup> iapPremiumGroupsList = new ArrayList<>();

    @Override
    public ArrayList<IAPPremiumDSAIGroup> getIAPPremiumReportDSAIGroupList()
    {
        iapPremiumGroupsList.add(new IAPPremiumDSAIGroup("Test 1 Division Name", "100001111", 1234, 2, 4, 22.22f, 0.00f, 0.00f));
        iapPremiumGroupsList.add(new IAPPremiumDSAIGroup("Test 2 Division Name", "100001112", 1235, 2, 4, 22.22f, 0.00f, 0.00f));
        iapPremiumGroupsList.add(new IAPPremiumDSAIGroup("Test 3 Division Name", "100001113", 1236, 2, 4, 22.22f, 0.00f, 0.00f));
        iapPremiumGroupsList.add(new IAPPremiumDSAIGroup("Test 4 Division Name", "100001114", 1237, 2, 4, 22.22f, 0.00f, 0.00f));
        return iapPremiumGroupsList;
    }
}
