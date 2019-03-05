package com.BO;

import com.Component.ME.CignaUpload.container.CignaUploadGroup;
import com.Component.ME.CignaUpload.repository.CignaUploadTestRepo;
import com.DAO.CignaUpload.CignaUploadDAO;
import com.DAO.CignaUpload.CignaUploadDAOImpl;
import com.DemoDynamicJasper.spring.config.AppConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.logging.Logger;

public class CignaUploadBO
{
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class );
    private static final Logger LOGGER = Logger.getLogger(EAPReportBO.class.getName() );
    /*Currently Using Test Repo Since no Data in DB to return*/
    CignaUploadDAO cignaUploadDAO = context.getBean(CignaUploadTestRepo.class );

    public ArrayList<CignaUploadGroup> getCignaUploadGroups()
    {
        return cignaUploadDAO.getCignaUploadGroups();
    }
}
