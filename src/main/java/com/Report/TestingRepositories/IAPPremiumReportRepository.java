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
        iapPremiumGroupsDSAIList.add(new IAPPremiumDSAIGroup("Test 1 Division Name", "100001111", 1234, 2, 4, 22.22, 0.00, 0.00));
        iapPremiumGroupsDSAIList.add(new IAPPremiumDSAIGroup("Test 2 Division Name", "100001112", 1235, 2, 4, 22.22, 0.00, 0.00));
        iapPremiumGroupsDSAIList.add(new IAPPremiumDSAIGroup("Test 3 Division Name", "100001113", 1236, 2, 4, 22.22, 0.00, 0.00));
        iapPremiumGroupsDSAIList.add(new IAPPremiumDSAIGroup("Test 4 Division Name", "100001114", 1237, 2, 4, 22.22, 0.00, 0.00));
        return iapPremiumGroupsDSAIList;
    }

    @Override
    public ArrayList<IAPPremiumADDGroup> getIAPPremiumReportADDGroupList()
    {
        ArrayList<IAPPremiumADDGroup> iapPremiumGroupsADDList = new ArrayList<>();
        iapPremiumGroupsADDList.add(new IAPPremiumADDGroup("10001111",
                                                           "1111A",
                                                           "Test 1 Division Name",
                                                           53.83,
                                                           0.00,
                                                           sqlDate,
                                                           sqlDate,
                                                           "A",
                                                           null,
                                                           null,
                                                           50,
                                                           525000.00,
                                                           0.039,
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
                                                           20.58,
                                                           0.98,
                                                           10.12,
                                                           1.54,
                                                           11.66,
                                                           null,
                                                           null,
                                                           null,
                                                           9.90,
                                                           "Retroactive Addition"));
        iapPremiumGroupsADDList.add(new IAPPremiumADDGroup("10001112",
                                                           "1111B",
                                                           "Test 2 Division Name",
                                                           53.83,
                                                           0.00,
                                                           sqlDate,
                                                           sqlDate,
                                                           "A",
                                                           null,
                                                           null,
                                                           50,
                                                           525000.00,
                                                           0.039,
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
                                                           20.58,
                                                           0.98,
                                                           10.12,
                                                           1.54,
                                                           11.66,
                                                           null,
                                                           null,
                                                           null,
                                                           9.90,
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
                                                                           0.00,
                                                                           0.00,
                                                                           0.00,
                                                                           sqlDate,
                                                                           "A",
                                                                           null,
                                                                           50,
                                                                           0.00,
                                                                           0.00,
                                                                           0.350,
                                                                           0.98,
                                                                           15.00,
                                                                           0.00,
                                                                           2.10,
                                                                           3.00,
                                                                           null,
                                                                           null,
                                                                           12.00,
                                                                           "Retroactive Addition"));
        iapPremiumGroupsOptionalADDList.add(new IAPPremiumOptionalADDGroup("10001112",
                                                                           "1111B",
                                                                           "Test 2 Division Name",
                                                                           0.00,
                                                                           0.00,
                                                                           0.00,
                                                                           sqlDate,
                                                                           "A",
                                                                           null,
                                                                           50,
                                                                           0.00,
                                                                           0.00,
                                                                           0.350,
                                                                           0.98,
                                                                           15.00,
                                                                           0.00,
                                                                           2.10,
                                                                           3.00,
                                                                           null,
                                                                           null,
                                                                           12.00,
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
                                                           53.83,
                                                           sqlDate,
                                                           sqlDate,
                                                           "A",
                                                           null,
                                                           null,
                                                           50,
                                                           525000.00,
                                                           0.039,
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
                                                           20.58,
                                                           0.98,
                                                           10.12,
                                                           1.54,
                                                           11.66,
                                                           null,
                                                           null,
                                                           null,
                                                           9.90,
                                                           "Retroactive Addition"));
        iapPremiumGroupsList.add(new IAPPremiumGroup("10001112",
                                                           "1111B",
                                                           "Test 2 Division Name",
                                                           53.83,
                                                           sqlDate,
                                                           sqlDate,
                                                           "A",
                                                           null,
                                                           null,
                                                           50,
                                                           525000.00,
                                                           0.039,
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
                                                           20.58,
                                                           0.98,
                                                           10.12,
                                                           1.54,
                                                           11.66,
                                                           null,
                                                           null,
                                                           null,
                                                           9.90,
                                                           "Retroactive Addition"));
        return iapPremiumGroupsList;
    }
}
