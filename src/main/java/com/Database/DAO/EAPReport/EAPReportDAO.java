package com.Database.DAO.EAPReport;

import com.Report.Component.ME.EAPReport.container.EapGroup;
import java.util.ArrayList;

public interface EAPReportDAO
{
    public ArrayList<EapGroup> getGroupList(int underwriterId, final String insurer);
}
