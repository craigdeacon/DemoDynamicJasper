package com.DAO.EAPReport;

import com.Component.ME.EAPReport.container.EapGroup;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;

@Component
public class EAPReportTestRepo implements EAPReportDAO
{
    public ArrayList<EapGroup> eapReportList = new ArrayList<>();
    java.sql.Date sqlDate = new java.sql.Date(new Date().getTime());

    @Override
    public ArrayList<EapGroup> getGroupList(int underwriterId, String insurer)
    {
        ArrayList<EapGroup> eapGroupList;
        if (underwriterId == 93064)
        {
            eapGroupList = getAreteEapReportList(insurer);
        }
        else
        {
            eapGroupList = getHumanaCareReportList(insurer);
        }

        return eapGroupList;
    }

    public ArrayList<EapGroup> getAreteEapReportList(String insurer)
    {
        eapReportList.add(new EapGroup(1, sqlDate, 1, "AR-00001", "Test AR 1", sqlDate, sqlDate, insurer, "EAP", "AB", 2));
        eapReportList.add(new EapGroup(2, sqlDate, 2, "AR-00002", "Test AR 2", sqlDate, sqlDate, insurer, "EAP", "ON", 6));
        eapReportList.add(new EapGroup(3, sqlDate, 3, "AR-00003", "Test AR 3", sqlDate, sqlDate, insurer, "EAP", "BC", 101));
        eapReportList.add(new EapGroup(4, sqlDate, 4, "AR-00004", "Test AR 4", sqlDate, sqlDate, insurer, "EAP", "AB", 9));
        return eapReportList;
    }

    public ArrayList<EapGroup> getHumanaCareReportList(String insurer)
    {
        eapReportList.add(new EapGroup(1, sqlDate, 1, "HC-00001", "Test HC 1", sqlDate, sqlDate, insurer, "EAP", "AB", 2));
        eapReportList.add(new EapGroup(2, sqlDate, 2, "HC-00002", "Test HC 2", sqlDate, sqlDate, insurer, "EAP", "ON", 6));
        eapReportList.add(new EapGroup(3, sqlDate, 3, "HC-00003", "Test HC 3", sqlDate, sqlDate, insurer, "EAP", "BC", 101));
        eapReportList.add(new EapGroup(4, sqlDate, 4, "HC-00004", "Test HC 4", sqlDate, sqlDate, insurer, "EAP", "AB", 9));
        return eapReportList;
    }
}
