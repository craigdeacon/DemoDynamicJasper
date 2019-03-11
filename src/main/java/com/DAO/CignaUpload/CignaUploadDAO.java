package com.DAO.CignaUpload;

import com.Component.ME.CignaUpload.container.CignaUploadGroup;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Component
public interface CignaUploadDAO
{
    public ArrayList<CignaUploadGroup> getCignaUploadGroups();
}
