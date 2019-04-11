package com.Report.TestingRepositories;

import com.Report.Component.ME.CignaUpload.container.CignaUploadGroup;
import com.Database.DAO.CignaUpload.CignaUploadDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CignaUploadRepository implements CignaUploadDAO
{
    @Override
    public ArrayList<CignaUploadGroup> getCignaUploadGroups()
    {
        ArrayList<CignaUploadGroup> listGroup = new ArrayList<>();
        listGroup.add(new CignaUploadGroup(1, 2019, "LTD", "SGD602730", 0, -87899.00, -1221.79, 0.00, 1.39, "Test", -61.07, -73.30, -1087.42));

        return listGroup;
    }
}
