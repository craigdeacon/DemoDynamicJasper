package com.BO;

import com.Component.ME.CignaUpload.container.CignaUploadGroup;
import com.DAO.CignaUpload.CignaUploadDAO;
import com.DAO.CignaUpload.CignaUploadDAOImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.logging.Logger;

@Service
public class CignaUploadBO
{
    private static final Logger LOGGER = Logger.getLogger(CignaUploadBO.class.getName() );
    /*Currently Using Test Repo Since no Data in DB to return*/
    private final CignaUploadDAO cignaUploadDAO;

    @Autowired
    public CignaUploadBO(CignaUploadDAOImpl cignaUploadDAO)
    {
        this.cignaUploadDAO = cignaUploadDAO;
    }

    public ArrayList<CignaUploadGroup> getCignaUploadGroups()
    {
        return cignaUploadDAO.getCignaUploadGroups();
    }
}
