package com.Report.TestingRepositories;

import com.Report.Component.ME.IAPPremiumReport.container.IAPPremiumADDGroup;
import com.Report.Component.ME.IAPPremiumReport.container.IAPPremiumDSAIGroup;
import com.Database.DAO.IAPPremiumReport.IAPPremiumReportDAO;
import com.Report.Component.ME.IAPPremiumReport.container.IAPPremiumGroup;
import com.Report.Component.ME.IAPPremiumReport.container.IAPPremiumOptionalADDGroup;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class IAPPremiumReportRepository implements IAPPremiumReportDAO
{
    private java.sql.Date sqlDate = new java.sql.Date(new java.util.Date().getTime());

    @Override
    public ArrayList<IAPPremiumDSAIGroup> getIAPPremiumReportDSAIGroupList()
    {
        ArrayList<IAPPremiumDSAIGroup> iapPremiumGroupsDSAIList = new ArrayList<>();
        iapPremiumGroupsDSAIList.add(new IAPPremiumDSAIGroup("Test 1 Division Name", "100001111", 1234, 2, 4, 22.22f, 0.00f, 0.00f));
        iapPremiumGroupsDSAIList.add(new IAPPremiumDSAIGroup("Test 2 Division Name", "100001112", 1235, 2, 4, 22.22f, 0.00f, 0.00f));
        iapPremiumGroupsDSAIList.add(new IAPPremiumDSAIGroup("Test 3 Division Name", "100001113", 1236, 2, 4, 22.22f, 0.00f, 0.00f));
        iapPremiumGroupsDSAIList.add(new IAPPremiumDSAIGroup("Test 4 Division Name", "100001114", 1237, 2, 4, 22.22f, 0.00f, 0.00f));
        return iapPremiumGroupsDSAIList;
    }

    @Override
    public ArrayList<IAPPremiumADDGroup> getIAPPremiumReportADDGroupList()
    {
        ArrayList<IAPPremiumADDGroup> iapPremiumGroupsADDList = new ArrayList<>();
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

    @Override
    public ArrayList<IAPPremiumOptionalADDGroup> getIAPPremiumReportOptionalADDGroupList()
    {
        ArrayList<IAPPremiumOptionalADDGroup> iapPremiumGroupsOptionalADDList = new ArrayList<>();
        iapPremiumGroupsOptionalADDList.add(new IAPPremiumOptionalADDGroup("10001111",
                                                                           "1111A",
                                                                           "Test 1 Division Name",
                                                                           0.00f,
                                                                           sqlDate,
                                                                           "A",
                                                                           null,
                                                                           50,
                                                                           0.00f,
                                                                           0.00f,
                                                                           0.350f,
                                                                           0.98f,
                                                                           15.00f,
                                                                           0.00f,
                                                                           2.10f,
                                                                           0.90f,
                                                                           3.00f,
                                                                           null,
                                                                           null,
                                                                           12.00f,
                                                                           "Retroactive Addition"));
        iapPremiumGroupsOptionalADDList.add(new IAPPremiumOptionalADDGroup("10001112",
                                                                           "1111B",
                                                                           "Test 2 Division Name",
                                                                           0.00f,
                                                                           sqlDate,
                                                                           "A",
                                                                           null,
                                                                           50,
                                                                           0.00f,
                                                                           0.00f,
                                                                           0.350f,
                                                                           0.98f,
                                                                           15.00f,
                                                                           0.00f,
                                                                           2.10f,
                                                                           0.90f,
                                                                           3.00f,
                                                                           null,
                                                                           null,
                                                                           12.00f,
                                                                           "Retroactive Addition"));
        return iapPremiumGroupsOptionalADDList;
    }

    @Override
    public ArrayList<IAPPremiumGroup> getIAPPremiumReportGroupList()
    {
        ArrayList<IAPPremiumGroup> iapPremiumGroupsList = new ArrayList<>();
        iapPremiumGroupsList.add(new IAPPremiumGroup("10001111",
                                                           "1111A",
                                                           "Test 1 Division Name",
                                                           53.83f,
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
        iapPremiumGroupsList.add(new IAPPremiumGroup("10001112",
                                                           "1111B",
                                                           "Test 2 Division Name",
                                                           53.83f,
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
        return iapPremiumGroupsList;
    }
}
