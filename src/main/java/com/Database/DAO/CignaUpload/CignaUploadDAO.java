package com.Database.DAO.CignaUpload;

import com.Report.Component.ME.CignaUpload.container.CignaUploadGroup;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public interface CignaUploadDAO
{
    public ArrayList<CignaUploadGroup> getCignaUploadGroups();
}
