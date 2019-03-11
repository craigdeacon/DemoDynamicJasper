package com.Component.ME.IAPPremiumReport.repository;

import com.Component.ME.IAPPremiumReport.container.IAPPremiumADDGroup;
import com.Component.ME.IAPPremiumReport.container.IAPPremiumDSAIGroup;
import com.DAO.IAPPremiumReport.IAPPremiumReportDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class IAPPremiumReportTestRepo implements IAPPremiumReportDAO
{
    private java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());
    private ArrayList<IAPPremiumDSAIGroup> iapPremiumGroupsDSAIList = new ArrayList<>();
    private ArrayList<IAPPremiumADDGroup> iapPremiumGroupsADDList = new ArrayList<>();

    @Override
    public ArrayList<IAPPremiumDSAIGroup> getIAPPremiumReportDSAIGroupList()
    {
        iapPremiumGroupsDSAIList.add(new IAPPremiumDSAIGroup("Test 1 Division Name", "100001111", 1234, 2, 4, 22.22f, 0.00f, 0.00f));
        iapPremiumGroupsDSAIList.add(new IAPPremiumDSAIGroup("Test 2 Division Name", "100001112", 1235, 2, 4, 22.22f, 0.00f, 0.00f));
        iapPremiumGroupsDSAIList.add(new IAPPremiumDSAIGroup("Test 3 Division Name", "100001113", 1236, 2, 4, 22.22f, 0.00f, 0.00f));
        iapPremiumGroupsDSAIList.add(new IAPPremiumDSAIGroup("Test 4 Division Name", "100001114", 1237, 2, 4, 22.22f, 0.00f, 0.00f));
        return iapPremiumGroupsDSAIList;
    }

    @Override
    public ArrayList<IAPPremiumADDGroup> getIAPPremiumReportADDGroupList()
    {
        iapPremiumGroupsADDList.add(new IAPPremiumADDGroup("10001111",
                                                           "1111A",
                                                           "Test 1 Division Name",
                                                           53.83f,
                                                           0.00f,
                                                           sqlDate,
                                                           sqlDate,
                                                           "A",
                                                           null,
                                                           null,
                                                           50,
                                                           525000.00f,
                                                           0.039f,
                                                           null,
                                                           null,
                                                           null,
                                                           null,
                                                           null,
                                                           null,
                                                           null,
                                                           null,
                                                           null,
                                                           null,
                                                           20.58f,
                                                           0.98f,
                                                           10.12f,
                                                           1.54f,
                                                           11.66f,
                                                           null,
                                                           null,
                                                           null,
                                                           9.90f,
                                                           "Retroactive Addition"));
        iapPremiumGroupsADDList.add(new IAPPremiumADDGroup("10001112",
                                                           "1111B",
                                                           "Test 2 Division Name",
                                                           53.83f,
                                                           0.00f,
                                                           sqlDate,
                                                           sqlDate,
                                                           "A",
                                                           null,
                                                           null,
                                                           50,
                                                           525000.00f,
                                                           0.039f,
                                                           null,
                                                           null,
                                                           null,
                                                           null,
                                                           null,
                                                           null,
                                                           null,
                                                           null,
                                                           null,
                                                           null,
                                                           20.58f,
                                                           0.98f,
                                                           10.12f,
                                                           1.54f,
                                                           11.66f,
                                                           null,
                                                           null,
                                                           null,
                                                           9.90f,
                                                           "Retroactive Addition"));
        return iapPremiumGroupsADDList;
    }
}
