package com.Component.ME.CignaUpload.repository;

import com.Component.ME.CignaUpload.container.CignaUploadGroup;
import com.DAO.CignaUpload.CignaUploadDAO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public class CignaUploadTestRepo implements CignaUploadDAO
{
    @Override
    public ArrayList<CignaUploadGroup> getCignaUploadGroups()
    {
        ArrayList<CignaUploadGroup> listGroup = new ArrayList<>();
        listGroup.add(new CignaUploadGroup(1, 2019, "LTD", "SGD602730", 0, -87899.00f, -1221.79f, 0.00f, 1.39f, "Test", -61.07f, -73.30f, -1087.42f));

        return listGroup;
    }
}